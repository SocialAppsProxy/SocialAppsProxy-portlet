package com.wordpress.metaphorm.authProxy.state;

import java.util.Map;

//import oauth.signpost.OAuthProviderListener;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import oauth.signpost.http.HttpParameters;

public interface OAuthProvider {

	public String getAccessTokenEndpointUrl();

	public String getAuthorizationWebsiteUrl();

	//public Map<String, String> getRequestHeaders();

	public String getRequestTokenEndpointUrl();

	//public HttpParameters getResponseParameters();

	public boolean isOAuth10a();

	//public void removeListener(OAuthProviderListener arg0);

	public void retrieveAccessToken(OAuthConsumer oAuthConsumer, String arg1,
			String... arg2) throws OAuthMessageSignerException,
			OAuthNotAuthorizedException, OAuthExpectationFailedException,
			OAuthCommunicationException;

	public String retrieveRequestToken(OAuthConsumer oAuthConsumer, String arg1,
			String... arg2) throws OAuthMessageSignerException,
			OAuthNotAuthorizedException, OAuthExpectationFailedException,
			OAuthCommunicationException;

	//public void setListener(OAuthProviderListener arg0);

	public void setOAuth10a(boolean arg0);

	public void setRequestHeader(String arg0, String arg1);

	public void setResponseParameters(HttpParameters arg0);
}
