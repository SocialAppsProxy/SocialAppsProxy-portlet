package com.wordpress.metaphorm.authProxy.state;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.http.HttpParameters;
import oauth.signpost.http.HttpRequest;
import oauth.signpost.signature.OAuthMessageSigner;
import oauth.signpost.signature.SigningStrategy;

public interface OAuthConsumer {

	public String getConsumerKey();

	public String getConsumerSecret();

	public HttpParameters getRequestParameters();

	public String getToken();

	public String getTokenSecret();

	public void setAdditionalParameters(HttpParameters arg0);

	public void setMessageSigner(OAuthMessageSigner arg0);

	public void setSendEmptyTokens(boolean arg0);

	public void setSigningStrategy(SigningStrategy arg0);

	public void setTokenWithSecret(String arg0, String arg1);

	public HttpRequest sign(HttpRequest arg0)
			throws OAuthMessageSignerException,
			OAuthExpectationFailedException, OAuthCommunicationException;

	public HttpRequest sign(Object arg0) throws OAuthMessageSignerException,
			OAuthExpectationFailedException, OAuthCommunicationException;

	public String sign(String arg0) throws OAuthMessageSignerException,
			OAuthExpectationFailedException, OAuthCommunicationException;
}
