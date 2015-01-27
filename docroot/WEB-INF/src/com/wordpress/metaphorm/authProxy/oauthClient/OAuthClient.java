package com.wordpress.metaphorm.authProxy.oauthClient;

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
import com.wordpress.metaphorm.authProxy.OAuthProviderConfigurationException;
import com.wordpress.metaphorm.authProxy.ProtocolNotSupportedException;
import com.wordpress.metaphorm.authProxy.httpClient.AuthProxyConnection;
import com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException;
import com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider;
import com.wordpress.metaphorm.authProxy.state.ExpiredStateException;

import java.io.IOException;
import java.net.MalformedURLException;

import com.wordpress.metaphorm.authProxy.oauthClient.OAuthCommunicationException;
import com.wordpress.metaphorm.authProxy.oauthClient.OAuthExpectationFailedException;
import com.wordpress.metaphorm.authProxy.oauthClient.OAuthNotAuthorizedException;

public interface OAuthClient {

	public void connect() throws OAuthProviderConfigurationException,
			NoSuchOAuthProviderException, ProtocolNotSupportedException,
			SystemException, ExpiredStateException;

	/**
	 * Returns the URL to redirect clients to for request token authorisation
	 * 
	 * @param oauth_callback
	 * @return
	 * @throws OAuthCommunicationException
	 * @throws OAuthMessageSignerException
	 * @throws OAuthNotAuthorizedException
	 * @throws OAuthExpectationFailedException
	 */
	public String retrieveRequestToken(String oauth_callback)
			throws OAuthCommunicationException, ProtocolNotSupportedException,
			OAuthNotAuthorizedException, OAuthExpectationFailedException,
			ExpiredStateException;

	public boolean isAuthorised() throws ExpiredStateException;

	public String getToken() throws ExpiredStateException;

	public void setVerifier(String verifier) throws ExpiredStateException;
	
	public void retrieveAccessToken() throws OAuthCommunicationException,
			OAuthExpectationFailedException, OAuthNotAuthorizedException,
			ProtocolNotSupportedException, ExpiredStateException;
	
	public AuthProxyConnection getAuthProxyConnection() throws MalformedURLException, IOException;
	
	public OAuthProvider getOAuthProvider(); 	
}