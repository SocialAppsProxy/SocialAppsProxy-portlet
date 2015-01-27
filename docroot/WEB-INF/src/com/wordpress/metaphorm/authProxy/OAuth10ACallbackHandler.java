package com.wordpress.metaphorm.authProxy;

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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.wordpress.metaphorm.authProxy.httpClient.AuthProxyConnectionFactory;
import com.wordpress.metaphorm.authProxy.oauthClient.OAuthCommunicationException;
import com.wordpress.metaphorm.authProxy.oauthClient.OAuthExpectationFailedException;
import com.wordpress.metaphorm.authProxy.oauthClient.OAuthNotAuthorizedException;
import com.wordpress.metaphorm.authProxy.oauthClient.OAuthClient;
import com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException;
import com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider;
import com.wordpress.metaphorm.authProxy.sb.service.OAuthProviderLocalServiceUtil;
import com.wordpress.metaphorm.authProxy.state.ExpiredStateException;
import com.wordpress.metaphorm.authProxy.state.OAuthState;
import com.wordpress.metaphorm.authProxy.state.OAuthStateManager;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OAuth10ACallbackHandler {

	private HttpServletRequest servletReq;
	private HttpServletResponse servletResp;
	
	private String oAuthRealm, token, verifier;
	
	private OAuth10ACallbackHandler(HttpServletRequest servletReq, HttpServletResponse servletResp) {
		this.servletReq = servletReq;
		this.servletResp = servletResp;		
		this.oAuthRealm = servletReq.getParameter("oauth_realm");
		this.token = servletReq.getParameter("oauth_token");
		this.verifier = servletReq.getParameter("oauth_verifier");
	}
	
	public static OAuth10ACallbackHandler newInstance(HttpServletRequest servletReq, HttpServletResponse servletResp) {

		// OAuth 1.0a callback
		if (servletReq.getParameter("oauth_realm") != null
				&& servletReq.getParameter("oauth_token") != null 
				&& servletReq.getParameter("oauth_verifier") != null) {
			
			return new OAuth10ACallbackHandler(servletReq, servletResp);
		} else
			return null;
	}
	
	public void handleProtocolCallback() throws 
			ExpiredStateException, NoSuchOAuthProviderException, SystemException, IOException, RedirectRequiredException, OAuthProviderConfigurationException, ProtocolNotSupportedException {
		
		try {
			_log.debug("Handling OAuth callback request for token " + this.token + " for the realm " + this.oAuthRealm);
			handleOAuth10ACallback();
			
		} catch (OAuthCommunicationException e) {
			throw new IOException(e);
		} catch (OAuthExpectationFailedException e) {
			throw new IOException(e);
		} catch (OAuthNotAuthorizedException e) {
			throw new IOException(e);
		}
	}
	
	private void handleOAuth10ACallback() throws 
			ExpiredStateException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthNotAuthorizedException,
			NoSuchOAuthProviderException, SystemException, IOException, OAuthProviderConfigurationException, ProtocolNotSupportedException {
	
		OAuthProvider oAuthProvider = OAuthProviderLocalServiceUtil.getProviderForRealm(oAuthRealm);
		
		OAuthState oAuthState = OAuthStateManager.getRelatedOAuthState(oAuthRealm, token);
		
		OAuthClient oAuthConn;
		if (oAuthState != null) {
			
			oAuthConn = AuthProxyConnectionFactory.getFactory(servletReq, oAuthState).getOAuth10AClient(oAuthProvider);
			
			// Retrieve access token
			oAuthConn.connect();
			oAuthConn.setVerifier(verifier);
			oAuthConn.retrieveAccessToken();
			
		} else
			oAuthConn = null;			
	}
	
	private static Log _log = LogFactoryUtil.getLog(ProtocolCallbackHandlerFactory.class);
}
