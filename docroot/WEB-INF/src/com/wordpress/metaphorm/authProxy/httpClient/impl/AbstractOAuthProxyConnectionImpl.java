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
import com.wordpress.metaphorm.authProxy.OAuthProviderConnection;
import com.wordpress.metaphorm.authProxy.RedirectRequiredException;
import com.wordpress.metaphorm.authProxy.httpClient.AuthProxyConnection;
import com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException;
import com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider;
import com.wordpress.metaphorm.authProxy.sb.service.OAuthProviderLocalServiceUtil;
import com.wordpress.metaphorm.authProxy.state.ExpiredStateException;
import com.wordpress.metaphorm.authProxy.state.OAuthState;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

/**
 * @author Stian Sigvartsen
 */
public abstract class AbstractOAuthProxyConnectionImpl implements AuthProxyConnection {
	
	private OAuthState oAuthState;
	
	public abstract URL getRequestedURL() throws MalformedURLException;
	public abstract URL getOAuthCallbackURL() throws MalformedURLException;
	public abstract void sendRequest() throws IOException;
	public abstract int getResponseCode() throws IOException; // Already added by wrapping HttpURLConnection
	public abstract String getAuthChallengeString();
	public abstract void reset() throws IOException;
	public abstract void signRequest(OAuthProviderConnection oAuthConn) throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ExpiredStateException;
	public abstract String getContentType();
	public abstract InputStream getInputStream() throws IOException;
	public abstract Map<String, List<String>> getResponseHeaders() throws IOException;
	
	protected void setOAuthState(OAuthState oAuthState) {
		this.oAuthState = oAuthState;
	}
	
	protected OAuthState getOAuthState() {
		return this.oAuthState;
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
	
	
	public static URL deriveURL(HttpServletRequest servletReq) throws MalformedURLException {
		
		String urlStr = servletReq.getRequestURL().toString();
		
		String oAuthRealm;
		try {
			
			OAuthProvider provider = OAuthProviderLocalServiceUtil.getMatchingOAuthProvider(new URL(servletReq.getRequestURL().toString()));
			
			if (provider.getUseHTTPS()) {
				_log.debug("AuthProxyHttpURLConnection :: Provider configured for HTTPS");
				if (urlStr.startsWith("http://"))
					urlStr = urlStr.replaceFirst("http://", "https://");
			}

			oAuthRealm = provider.getRealm();
			_log.debug("AuthProxyHttpURLConnection :: Derived OAuth realm as \"" + oAuthRealm + "\" through URL patterns");
			
		} catch (NoSuchOAuthProviderException e) {
			// Do nothing
		} catch (SystemException e) {
			e.printStackTrace();
		}
				
		String queryString = servletReq.getQueryString();
		
		return new URL(urlStr + (queryString != null ? "?" + queryString : ""));
	}

	public void connect() throws RedirectRequiredException, OAuthProviderConfigurationException, IOException {
				
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
				
				sendRequest();
				
				// If a protected resource
				if (getResponseCode() == 401) {
					
					_log.debug("AuthProxyHttpURLConnection :: Received response HTTP 401");
					
					String authChallengeStr = getAuthChallengeString();
					
					_log.debug("AuthProxyHttpURLConnection :: Authorizaton/WWW-Authenticate header value: " + authChallengeStr);
					
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
					
					// If an OAuthProvider is configured for this realm
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
					
					// Need to reset to enable request header manipulation later
					reset();
										
				} else {
					
					// Either an unprotected resource 
					// or a portal admin needs to set up preemptive authentication pattern for the URL
					
					_log.debug("AuthProxyHttpURLConnection :: Received response other than HTTP 401");
					
					return;
				}
				
			} else 
				realmProvider = preEmptiveProvider;

			OAuthProviderConnection oAuthConn = new OAuthProviderConnection(realmProvider, oAuthState);
			
			try {
				
				oAuthConn.connect();
				
			} catch (NoSuchOAuthProviderException e) {
				
				e.printStackTrace();
				
				sendRequest();
				
				return;
				
			} catch (ExpiredStateException e) {
				throw new IOException(e);
				
			} catch (SystemException e) {			
				throw new IOException(e);
			}

	
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
								
		        signRequest(oAuthConn);
				
		        sendRequest();
		        
		        // Check if the access token has been revoked on the service provider side
		        if (getResponseCode() == 401) {
		        	
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
		}
		
	}
	private URL constructCallbackURL(String oAuthRealm)
			throws MalformedURLException {
		
		URL oauth_callbackObj = getOAuthCallbackURL();
		
		String oauth_callback = oauth_callbackObj.toString();
		
		oauth_callback += (oauth_callback.indexOf('?') != -1 ? "&oauth_realm=" : "?oauth_realm=") + URLEncoder.encode(oAuthRealm);

		return new URL(oauth_callback);
	}
	
	private String deriveResponseContentType(String acceptHeader) {
		
		if (acceptHeader == null) return "application/xml";
	
		String[] mimeTypes = acceptHeader.split(",");
		return mimeTypes[0].trim();
	}
	
	private static Log _log = LogFactoryUtil.getLog(AbstractOAuthProxyConnectionImpl.class);
}
