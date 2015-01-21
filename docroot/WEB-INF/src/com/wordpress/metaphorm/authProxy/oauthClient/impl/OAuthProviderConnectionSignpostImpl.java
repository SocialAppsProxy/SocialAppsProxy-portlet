package com.wordpress.metaphorm.authProxy.oauthClient.impl;

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
import com.wordpress.metaphorm.authProxy.OAuthProviderConfigurationException;
import com.wordpress.metaphorm.authProxy.httpClient.AuthProxyConnection;
import com.wordpress.metaphorm.authProxy.oauthClient.OAuthProviderConnection;
import com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException;
import com.wordpress.metaphorm.authProxy.sb.service.OAuthProviderLocalServiceUtil;
import com.wordpress.metaphorm.authProxy.state.ExpiredStateException;
import com.wordpress.metaphorm.authProxy.state.OAuthCredentials;
import com.wordpress.metaphorm.authProxy.state.OAuthState;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.commonshttp3.CommonsHttp3OAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

import org.apache.commons.httpclient.HttpMethod;

/**
 * @author Stian Sigvartsen
 */
public class OAuthProviderConnectionSignpostImpl implements OAuthProviderConnection {

	boolean connected;
	String realm;
	OAuthState oAuthState;
	DefaultOAuthProvider oAuthProvider;
	com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider sbOAuthProvider;
	
	public OAuthProviderConnectionSignpostImpl(com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider provider, OAuthState oAuthState) {
		
		this.sbOAuthProvider = provider;
		this.oAuthState = oAuthState;
		
		_log.debug("Constructing with provider for realm \"" + provider.getRealm() + "\"");
	}
	
	public OAuthProviderConnectionSignpostImpl(String realm, OAuthState oAuthState) {
		
		_log.debug("Constructing without provider for realm \"" + realm + "\"");
		
		connected = false;
		this.realm = realm;
		this.oAuthState = oAuthState;
	}
	
	private void initialiseState() throws ExpiredStateException {
		
		if (oAuthState.getOAuthCredentials(realm) == null) {
			
			_log.debug("No OAuth state found. Initialising...");
			oAuthState.setOAuthCredentials(realm, new OAuthCredentials(
					sbOAuthProvider.getConsumerKey(), sbOAuthProvider.getConsumerSecret()));
			
			//oAuthState.setConsumer(realm, new CommonsHttp3OAuthConsumer(
			//		sbOAuthProvider.getConsumerKey(),
			//		sbOAuthProvider.getConsumerSecret()));
			
			oAuthState.commitChanges(realm);
		}
		
		//oAuthProvider = new CommonsHttp3OAuthProvider(
		//		sbOAuthProvider.getRequestTokenURL(),
		//		sbOAuthProvider.getAccessTokenURL(),
		//		sbOAuthProvider.getAuthoriseURL());	
		
		oAuthProvider = new DefaultOAuthProvider(
				sbOAuthProvider.getRequestTokenURL(),
				sbOAuthProvider.getAccessTokenURL(),
				sbOAuthProvider.getAuthoriseURL());	
		
		oAuthProvider.setOAuth10a(true);
	}
	
	private OAuthCredentials getOAuthCredentials() throws ExpiredStateException  {		
		return oAuthState.getOAuthCredentials(realm);
	}
	
	private OAuthConsumer getOAuthConsumer(OAuthCredentials oAuthCredentials) {
		
		DefaultOAuthConsumer oAuthConsumer = new DefaultOAuthConsumer(
				sbOAuthProvider.getConsumerKey(), sbOAuthProvider.getConsumerSecret());
		
		oAuthConsumer.setTokenWithSecret(oAuthCredentials.getToken(), oAuthCredentials.getTokenSecret());
		
		return oAuthConsumer;
	}
	
	private OAuthProvider getSignpostOAuthProvider() {
		return oAuthProvider;
	}
	
	/* (non-Javadoc)
	 * @see com.wordpress.metaphorm.authProxy.OAuthProviderConnection_int#connect()
	 */
	@Override
	public void connect() throws OAuthProviderConfigurationException, NoSuchOAuthProviderException, OAuthMessageSignerException, SystemException, ExpiredStateException {
		
		
		if (this.sbOAuthProvider == null && realm != null)
			sbOAuthProvider = OAuthProviderLocalServiceUtil.getProviderForRealm(realm);
		else		
			realm = sbOAuthProvider.getRealm();
		
		if (realm == null) {
			throw new OAuthProviderConfigurationException("OAuth realm must be set to something, even if this is just fictious for a service which doesn't check realms.");
		}
		
		String consumerKey = sbOAuthProvider.getConsumerKey();
		if (consumerKey != null && consumerKey.trim().length() == 0) consumerKey = null;
		String consumerSecret = sbOAuthProvider.getConsumerSecret();
		if (consumerSecret != null && consumerSecret.trim().length() == 0) consumerSecret = null;
		
		if (consumerKey == null || consumerSecret == null) {
			throw new OAuthProviderConfigurationException("An administrator needs to configure the consumer " 
					+ (consumerKey == null ? " key" + (consumerSecret == null ? " and" : "") : "") 
					+ (consumerSecret == null ? " secret " : "") + " for the OAuth realm \"" + realm + "\" using the Liferay Portal control panel");
		}
		
		initialiseState();		
		connected = true;
	}	
			
	
	/* (non-Javadoc)
	 * @see com.wordpress.metaphorm.authProxy.OAuthProviderConnection_int#retrieveRequestToken(java.lang.String)
	 */
	@Override
	public String retrieveRequestToken(String oauth_callback) 
			throws OAuthCommunicationException, OAuthMessageSignerException, 
			OAuthNotAuthorizedException, OAuthExpectationFailedException, ExpiredStateException {
				
		if (!connected) throw new RuntimeException("OAuth provider not connected");
		OAuthCredentials oAuthCredentials = oAuthState.getOAuthCredentials(realm);
		
		if (oAuthCredentials == null) initialiseState();
		
		_log.debug("**** Retrieving request token with oauth_callback = " + oauth_callback);
		
		OAuthConsumer oAuthConsumer = getOAuthConsumer(oAuthCredentials);
		
		String nextURL = oAuthProvider.retrieveRequestToken(oAuthConsumer, oauth_callback);
		
		oAuthState.setPhase(realm, OAuthState.AUTHORISE_PHASE);
		
		oAuthCredentials.setToken(oAuthConsumer.getToken());
		oAuthCredentials.setTokenSecret(oAuthConsumer.getTokenSecret());
		oAuthState.setOAuthCredentials(realm, oAuthCredentials);
		
		_log.debug("Received request token / request token secret: " + oAuthCredentials.getToken() + ", "+ oAuthCredentials.getTokenSecret());

		_log.debug("Updating OAuthState...");
		oAuthState.commitChanges(realm);
		_log.debug("OAuthState state changes committed.");
		
		return nextURL;
	}
	
	/* (non-Javadoc)
	 * @see com.wordpress.metaphorm.authProxy.OAuthProviderConnection_int#isAuthorised()
	 */
	@Override
	public boolean isAuthorised() throws ExpiredStateException {
		
		_log.debug("isAuthorised()");

		if (!connected) throw new RuntimeException("OAuth provider not connected");
		
		return oAuthState.getPhase(realm) >= OAuthState.RESOURCE_PHASE;
	}
	
	/* (non-Javadoc)
	 * @see com.wordpress.metaphorm.authProxy.OAuthProviderConnection_int#getToken()
	 */
	@Override
	public String getToken() throws ExpiredStateException {	
		
		if (!connected) throw new RuntimeException("OAuth provider not connected");
		
		return oAuthState.getOAuthCredentials(realm).getToken();
	}
	
	@Override
	public void setVerifier(String verifier) throws ExpiredStateException {
		
		if (!connected) throw new RuntimeException("OAuth provider not connected");
		
		this.oAuthState.setVerifier(realm, verifier);
		this.oAuthState.commitChanges(realm);
	}
	
	/* (non-Javadoc)
	 * @see com.wordpress.metaphorm.authProxy.OAuthProviderConnection_int#retrieveAccessToken()
	 */
	@Override
	public void retrieveAccessToken() 
			throws 	OAuthCommunicationException, OAuthExpectationFailedException, 
					OAuthNotAuthorizedException, OAuthMessageSignerException, ExpiredStateException {
		
		if (!connected) throw new RuntimeException("OAuth provider not connected");
		
		OAuthCredentials oAuthCredentials = getOAuthCredentials();
		String verificationCode = oAuthState.getVerifier(realm);
		
		_log.debug("**** Retrieving access token for " + oAuthCredentials.getToken() + ", " + oAuthCredentials.getTokenSecret() + ", " + verificationCode);
		
		OAuthConsumer oAuthConsumer = getOAuthConsumer(oAuthCredentials);
		
		getSignpostOAuthProvider().retrieveAccessToken(oAuthConsumer, verificationCode);
		
		oAuthState.setVerifier(realm, null);
		oAuthState.setPhase(realm, OAuthState.RESOURCE_PHASE);

		oAuthCredentials.setToken(oAuthConsumer.getToken());
		oAuthCredentials.setTokenSecret(oAuthConsumer.getTokenSecret());
		oAuthState.setOAuthCredentials(realm, oAuthCredentials);
		
		_log.debug("Received access token / access token secret: " + oAuthCredentials.getToken() + ", "+ oAuthCredentials.getTokenSecret());
		
		_log.debug("Updating OAuthState...");
		oAuthState.commitChanges(realm);
		_log.debug("OAuthState state changes committed.");
	}
	
	public void sign(HttpURLConnection uRLConn) 
			throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ExpiredStateException {
		
		if (!connected) throw new RuntimeException("OAuth provider not connected");
		
		OAuthCredentials oAuthCredentials = getOAuthCredentials();
		getOAuthConsumer(oAuthCredentials).sign(uRLConn);		
	}
	
	public void sign(HttpMethod httpMethod) 
			throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ExpiredStateException {
		
		if (!connected) throw new RuntimeException("OAuth provider not connected");
		
		_log.debug("Signing Apache Commons Client 3.x request");
		
		OAuthCredentials oAuthCredentials = getOAuthCredentials();
		OAuthConsumer defaultConsumer = getOAuthConsumer(oAuthCredentials);
		
		CommonsHttp3OAuthConsumer consumer = new CommonsHttp3OAuthConsumer(
				defaultConsumer.getConsumerKey(),
				defaultConsumer.getConsumerSecret()
				);
		
		consumer.setTokenWithSecret(
				defaultConsumer.getToken(), 
				defaultConsumer.getTokenSecret());
		
		_log.debug("**** SIGNING INPUT START ****");
		_log.debug("consumer.getConsumerKey() = " + consumer.getConsumerKey());
		_log.debug("consumer.getConsumerSecret() = " + consumer.getConsumerSecret());
		_log.debug("consumer.getToken() = " + consumer.getToken());
		_log.debug("consumer.getTokenSecret() = " + consumer.getTokenSecret());
		_log.debug("**** SIGNING INPUT END ****");
		
		consumer.sign(httpMethod);
	}
	
	private static Log _log = LogFactoryUtil.getLog(OAuthProviderConnectionSignpostImpl.class);

	@Override
	public AuthProxyConnection getAuthProxyConnection() throws MalformedURLException, IOException {

		return null;
	}

	@Override
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider getOAuthProvider() {
		return sbOAuthProvider;
	}
}
