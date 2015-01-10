package com.wordpress.metaphorm.authProxy.httpClient.impl;

/**
 * Copyright (c) 2014-present Stian Sigvartsen. All rights reserved.
 *
 * This file is part of Social Apps Proxy.
 *
 * Social Apps Proxy is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Social Apps Proxy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Social Apps Proxy.  If not, see <http://www.gnu.org/licenses/>.
 */

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.wordpress.metaphorm.authProxy.OAuthProviderConfigurationException;
import com.wordpress.metaphorm.authProxy.RedirectRequiredException;
import com.wordpress.metaphorm.authProxy.httpClient.AuthProxyConnection;
import com.wordpress.metaphorm.authProxy.httpClient.AuthProxyConnectionFactory;
import com.wordpress.metaphorm.authProxy.oauthClient.OAuthProviderConnection;
import com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException;
import com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider;
import com.wordpress.metaphorm.authProxy.sb.service.OAuthProviderLocalServiceUtil;
import com.wordpress.metaphorm.authProxy.state.ExpiredStateException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.Header;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

/**
 * @author Stian Sigvartsen
 */
public class OAuthProxyConnectionImpl implements AuthProxyConnection { //extends AbstractOAuthProxyConnectionImpl {
	
	private HttpServletRequest servletReq;
	private AuthProxyConnection connection;
	private AuthProxyConnectionFactory connectionFactory;
	
	public OAuthProxyConnectionImpl(HttpServletRequest servletReq, AuthProxyConnectionFactory connectionFactory) throws MalformedURLException, IOException {
		
		this.servletReq = servletReq;
		this.connectionFactory = connectionFactory;
	}
	
	public boolean isNegotiatingConnection() {
		return true;
	}

	public static URL deriveURL(HttpServletRequest servletReq) throws MalformedURLException {
		
		String urlStr = servletReq.getRequestURL().toString();		
		String queryString = servletReq.getQueryString();
		
		return new URL(urlStr + (queryString != null ? "?" + queryString : ""));
	}	
	
	public URL getRequestedURL() throws MalformedURLException {
		return new URL(servletReq.getRequestURL().toString());
	}
	
	@Override
	public void sendRequest() throws IOException {

		throw new RuntimeException("Not implemented");
	}

	public String getAuthChallengeString(AuthProxyConnection connection) throws IOException {
		
		Map<String, List<String>> headers = connection.getResponseHeaders();
		
		List<String> headerValues;
		String authChallengeStr = ((headerValues = headers.get("Authorization")) != null ? headerValues.get(0) : null);
		if (authChallengeStr == null) authChallengeStr = ((headerValues = headers.get("WWW-Authenticate")) != null ? headerValues.get(0) : null);

		return authChallengeStr;
	}
	
	public String extractOAuthRealm(String authChallengeStr) {
		
		if (authChallengeStr != null) {
			
			Pattern pattern = Pattern.compile("OAuth\\srealm=\"([^\"]*)\"");
			Matcher matcher = pattern.matcher(authChallengeStr);
			
			if (matcher.find()) {
				
				String oAuthRealm = matcher.group(1);	
				_log.debug("AuthProxyHttpURLConnection :: Was able to extract the OAuth realm: " + oAuthRealm);
				
				return oAuthRealm;
			}			
		}
		return null;
	}	
	
	private URL constructCallbackURL(String oAuthRealm)
			throws MalformedURLException {
		
		URL oauth_callbackObj = getOAuthCallbackURL();
		
		String oauth_callback = oauth_callbackObj.toString();
		
		oauth_callback += (oauth_callback.indexOf('?') != -1 ? "&oauth_realm=" : "?oauth_realm=") + URLEncoder.encode(oAuthRealm);

		return new URL(oauth_callback);
	}
	
	public URL getOAuthCallbackURL() throws MalformedURLException {
		
		String oauth_callback = servletReq.getHeader("oauth_callback");
		if (oauth_callback == null) {
			
			// Use the requested URL as a callback URL (after OAuth authorisation)
			// FYI: Upon returning, this filter will swap the request token for an access token before requesting the resource
			oauth_callback = servletReq.getRequestURL() + (servletReq.getQueryString() != null ? "?" + servletReq.getQueryString() : "");
		}
		
		return new URL(oauth_callback);
	}
	
	@Override
	public void connect() throws RedirectRequiredException,
			OAuthProviderConfigurationException, IOException {

		try {
			
			OAuthProvider provider;
			
			String oAuthRealm;
			OAuthProvider preEmptiveProvider = null;
			OAuthProvider realmProvider;
			
			try {
				preEmptiveProvider = OAuthProviderLocalServiceUtil.getMatchingOAuthProvider(getRequestedURL());	
				oAuthRealm = preEmptiveProvider.getRealm();
				_log.debug("AuthProxyHttpURLConnection :: Derived OAuth realm as \"" + oAuthRealm + "\"");
				
				// If a realm has not been manually set up, allow auto-discovery...
				if (oAuthRealm != null && oAuthRealm.trim().length() == 0) oAuthRealm = null;
				
			} catch (NoSuchOAuthProviderException e) {
				oAuthRealm = null;
			} catch (SystemException e) {
				throw new IOException(e);
			}				
			
			
			
			// If pre-emptive authentication not configured for this URL then use discovery
			if (oAuthRealm == null) {
				
				this.connection = connectionFactory.getAuthProxyConnection();
				
				this.connection.sendRequest();
				
				// If a protected resource
				if (getResponseCode() == 401) {
					
					_log.debug("AuthProxyHttpURLConnection :: Received response HTTP 401");
					
					String authChallengeStr = getAuthChallengeString(this.connection);
					
					_log.debug("AuthProxyHttpURLConnection :: Authorization/WWW-Authenticate header value: " + authChallengeStr);
					
					// This optional header should look like this
					// "WWW-Authenticate: OAuth realm="http://sp.example.com/"
					
					oAuthRealm = extractOAuthRealm(authChallengeStr);
						
					if (oAuthRealm == null) {
						// A portal admin needs to set up preemptive authentication pattern for the URL
						throw new OAuthProviderConfigurationException(
								"An administrator needs to configure pre-emptive authentiation for the requested URL using the Liferay Portal control panel, because the OAuth provider doesn't describe the realm in its responses");
					}
					
					
					// Attempt to resolve the realm into a provider configuration (more specific than URL pattern matched provider retrieval)
					try {
						realmProvider = OAuthProviderLocalServiceUtil.getProviderForRealm(oAuthRealm);
					} catch (NoSuchOAuthProviderException e1) {
						realmProvider = null;
					} catch (SystemException e) {
						throw new IOException(e);
					}
					
					// If an OAuthProvider is not configured for this realm
					if (realmProvider == null) {
						try {
							
							// ... then create another OAuthProvider with the discovered realm
							// By creating a new one this allows the existing one which has a pre-emptive URL pattern configured to continue to be
							// used to proxy HTTP to HTTPS for non-OAuth resource as well
							
							_log.debug("AuthProxyHttpURLConnection :: Creating a new OAuthProvider with discovered oAuth realm...");
							
							long realmProviderId = CounterLocalServiceUtil.increment(OAuthProvider.class.getName());
							realmProvider = OAuthProviderLocalServiceUtil.createOAuthProvider(realmProviderId);
							realmProvider.setRealm(oAuthRealm);
							
							if (preEmptiveProvider != null) {	
								realmProvider.setUseHTTPS(preEmptiveProvider.getUseHTTPS());
							} else {
								realmProvider.setUseHTTPS(false);
							}
							OAuthProviderLocalServiceUtil.updateOAuthProvider(realmProvider);
														
						} catch (SystemException e) {
							// Ok to fail silently?
							e.printStackTrace();
							return;
						}
						
						provider = realmProvider;
					}
															
				} else {
					
					// Either an unprotected resource 
					// or a portal admin needs to set up preemptive authentication pattern for the URL
					
					_log.debug("AuthProxyHttpURLConnection :: Received response other than HTTP 401");
					
					return;
				}
				
			} else 
				realmProvider = preEmptiveProvider;

			_log.debug("Getting OAuthProviderConnection with compatible AuthProviderConnection for realm: " + realmProvider.getRealm());
			
			OAuthProviderConnection oAuthConn = 
					this.connectionFactory.getOAuth10AProviderConnection(realmProvider); 
					//new OAuthProviderConnection(realmProvider, oAuthState);
			
			this.connection = oAuthConn.getAuthProxyConnection();
			
			oAuthConn.connect();
			
			
			if (!oAuthConn.isAuthorised()) {
			
				_log.debug("AuthProxyHttpURLConnection :: No token available yet.");			
				
				URL oauth_callback = constructCallbackURL(oAuthRealm);			
				_log.debug("AuthProxyHttpURLConnection :: Set oauth_callback = \"" + oauth_callback + "\"");
				
				String authoriseURL = oAuthConn.retrieveRequestToken(oauth_callback.toString());
								
				throw new RedirectRequiredException(new URL(authoriseURL));
						
			} else { // Access token is available for OAuth provider
				
				_log.debug("AuthProxyHttpURLConnection :: Token is available: " + oAuthConn.getToken());
				
				URL urlObj = getRequestedURL();
				
				String url = urlObj.toString();
				
				_log.debug("AuthProxyHttpURLConnection :: Requesting " + url);
								
				this.connection.sendRequest();
		        
		        // Check if the access token has been revoked on the service provider side
		        if (this.connection.getResponseCode() == 401) {
		        	
					URL oauth_callback = constructCallbackURL(oAuthRealm);				
					_log.debug("AuthProxyHttpURLConnection :: Set oauth_callback = \"" + oauth_callback + "\"");
					
					String authoriseURL = oAuthConn.retrieveRequestToken(oauth_callback.toString());
					
		        	throw new RedirectRequiredException(new URL(authoriseURL));
		        }
			}
			
		} catch (ExpiredStateException e) {
			throw new IOException(e);
		} catch (OAuthExpectationFailedException e) {
			throw new IOException(e);
		} catch (OAuthMessageSignerException e) {
			throw new IOException(e);
		} catch (OAuthCommunicationException e) {
			throw new IOException(e);
		} catch (OAuthNotAuthorizedException e) {
			throw new IOException(e);
		} catch (NoSuchOAuthProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public InputStream getInputStream() throws IOException {

		return this.connection.getInputStream();
	}

	@Override
	public int getResponseCode() throws IOException {

		return this.connection.getResponseCode();
	}

	@Override
	public String getContentType() {
		
		return this.connection.getContentType();
	}

	@Override
	public Map<String, List<String>> getResponseHeaders() throws IOException {
		
		return this.connection.getResponseHeaders();
	}
	
	private static Log _log = LogFactoryUtil.getLog(OAuthProxyConnectionImpl.class);
}
