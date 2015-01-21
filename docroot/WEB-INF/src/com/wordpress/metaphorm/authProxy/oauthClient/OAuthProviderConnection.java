package com.wordpress.metaphorm.authProxy.oauthClient;

import com.liferay.portal.kernel.exception.SystemException;
import com.wordpress.metaphorm.authProxy.OAuthProviderConfigurationException;
import com.wordpress.metaphorm.authProxy.httpClient.AuthProxyConnection;
import com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException;
import com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider;
import com.wordpress.metaphorm.authProxy.state.ExpiredStateException;

import java.io.IOException;
import java.net.MalformedURLException;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

public interface OAuthProviderConnection {

	public void connect() throws OAuthProviderConfigurationException,
			NoSuchOAuthProviderException, OAuthMessageSignerException,
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
			throws OAuthCommunicationException, OAuthMessageSignerException,
			OAuthNotAuthorizedException, OAuthExpectationFailedException,
			ExpiredStateException;

	public boolean isAuthorised() throws ExpiredStateException;

	public String getToken() throws ExpiredStateException;

	public void setVerifier(String verifier) throws ExpiredStateException;
	
	public void retrieveAccessToken() throws OAuthCommunicationException,
			OAuthExpectationFailedException, OAuthNotAuthorizedException,
			OAuthMessageSignerException, ExpiredStateException;
	
	public AuthProxyConnection getAuthProxyConnection() throws MalformedURLException, IOException;
	
	public OAuthProvider getOAuthProvider(); 
	
	/*
	public void signRequest() 
			throws OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException, ExpiredStateException;
	 */
}