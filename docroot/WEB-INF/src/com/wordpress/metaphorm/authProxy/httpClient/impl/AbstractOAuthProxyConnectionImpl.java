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
import com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException;
import com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider;
import com.wordpress.metaphorm.authProxy.sb.service.OAuthProviderLocalServiceUtil;
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

/**
 * @author Stian Sigvartsen
 */
public abstract class AbstractOAuthProxyConnectionImpl implements AuthProxyConnection {
	
	private OAuthState oAuthState;
	private AuthProxyConnectionFactory authProxyConnectionFactory;
	
	public abstract URL getRequestedURL() throws MalformedURLException;
	public abstract void sendRequest() throws IOException;
	public abstract int getResponseCode() throws IOException; // Already added by wrapping HttpURLConnection
	public abstract void reset() throws IOException;
	public abstract String getContentType();
	public abstract InputStream getInputStream() throws IOException;
	public abstract Map<String, List<String>> getResponseHeaders() throws IOException;
	public abstract boolean isNegotiatingConnection();
	
	protected void setOAuthState(OAuthState oAuthState) {
		this.oAuthState = oAuthState;
	}
	
	protected OAuthState getOAuthState() {
		return this.oAuthState;
	}
	
	/*
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
	*/
	
	
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

		sendRequest();
	}
	
	/*
	private URL constructCallbackURL(String oAuthRealm)
			throws MalformedURLException {
		
		URL oauth_callbackObj = getOAuthCallbackURL();
		
		String oauth_callback = oauth_callbackObj.toString();
		
		oauth_callback += (oauth_callback.indexOf('?') != -1 ? "&oauth_realm=" : "?oauth_realm=") + URLEncoder.encode(oAuthRealm);

		return new URL(oauth_callback);
	}
	*/
	
	/*
	private String deriveResponseContentType(String acceptHeader) {
		
		if (acceptHeader == null) return "application/xml";
	
		String[] mimeTypes = acceptHeader.split(",");
		return mimeTypes[0].trim();
	}
	*/
	
	public void setAuthProxyConnectionFactory(AuthProxyConnectionFactory authProxyConnectionFactory) {
		this.authProxyConnectionFactory = authProxyConnectionFactory;
	}
	
	public AuthProxyConnectionFactory getAuthProxyConnectionFactory() {
		return this.authProxyConnectionFactory;
	}

	private static Log _log = LogFactoryUtil.getLog(AbstractOAuthProxyConnectionImpl.class);
}
