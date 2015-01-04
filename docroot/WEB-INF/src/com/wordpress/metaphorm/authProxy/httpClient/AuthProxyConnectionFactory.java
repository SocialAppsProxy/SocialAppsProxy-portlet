package com.wordpress.metaphorm.authProxy.httpClient;

import com.liferay.portal.kernel.exception.SystemException;
import com.wordpress.metaphorm.authProxy.OAuthProviderConfigurationException;
import com.wordpress.metaphorm.authProxy.httpClient.impl.OAuthProxyConnectionApacheHttpCommonsClientImpl;
import com.wordpress.metaphorm.authProxy.httpClient.impl.OAuthProxyConnectionHttpURLConnectionImpl;
import com.wordpress.metaphorm.authProxy.oauthClient.OAuthProviderConnection;
import com.wordpress.metaphorm.authProxy.oauthClient.impl.OAuthProviderConnectionSignpostImpl;
import com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException;
import com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider;
import com.wordpress.metaphorm.authProxy.state.ExpiredStateException;
import com.wordpress.metaphorm.authProxy.state.OAuthState;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

public class AuthProxyConnectionFactory {

	private HttpServletRequest servletReq;
	private OAuthState oAuthState;
	private OAuthProxyConnectionApacheHttpCommonsClientImpl uRLConn;
	
	private final String APACHE_HTTP_COMMONS_CLIENT_3 = "USE_APACHE_HTTP_COMMONS_CLIENT_3";
	private final String HTTP_URL_CONNECTION = "HTTP_URL_CONNECTION";
	
	// Set which HTTP client to use for which auth protocols
	private final String OAUTH_10A_HTTP_CLIENT = APACHE_HTTP_COMMONS_CLIENT_3;
	
	public static AuthProxyConnectionFactory getFactory(HttpServletRequest servletReq, OAuthState oAuthState) {
		
		return new AuthProxyConnectionFactory(servletReq, oAuthState);
	}

	public AuthProxyConnectionFactory(HttpServletRequest servletReq, OAuthState oAuthState) {
		
		this.servletReq = servletReq;
		this.oAuthState = oAuthState;
		this.uRLConn = null;
	}
	
	/**
	 * Get the primary HTTP client implementation. Used to proxy basic requests.
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public AuthProxyConnection getAuthProxyConnection() throws MalformedURLException, IOException {
		
		//OAuthProxyConnectionApacheHttpCommonsClientImpl uRLConn =
		
		if (uRLConn == null)
			uRLConn = new OAuthProxyConnectionApacheHttpCommonsClientImpl(servletReq/*, oAuthState*/);
		
		return uRLConn;
	}
	
	/**
	 * Get a configured OAuth client object for supporting the OAuth 1.0a protocol.
	 * May or may not bind to the primary HTTP client implementation based on compatibility. 
	 * 
	 * @param realmProvider
	 * @param oAuthState
	 * @return
	 * @throws OAuthProviderConfigurationException
	 * @throws IOException
	 */
	public OAuthProviderConnection getOAuth10AProviderConnection(OAuthProvider realmProvider) 
			throws OAuthProviderConfigurationException, IOException {
		
		if (realmProvider.getProtocolVersion().equalsIgnoreCase("1.0a")) {
			
			// Return a OAuthProxyConnection subclass that's compatible with the Signpost OAuth 1.0a library
						
			if (OAUTH_10A_HTTP_CLIENT == APACHE_HTTP_COMMONS_CLIENT_3) {
			
				// Inline class to bootstrap getAuthProxyConnection() to return a signing connection ...
				final OAuthProviderConnectionSignpostImpl oAuthConn = new OAuthProviderConnectionSignpostImpl(realmProvider, oAuthState) {
					
					public AuthProxyConnection getAuthProxyConnection() throws MalformedURLException, IOException {
	
						final OAuthProviderConnectionSignpostImpl instantiatedOAuthConn = this;
						
						// ... which signs OAuth 1.0a credentials upon sending requests 
						final OAuthProxyConnectionApacheHttpCommonsClientImpl uRLConn = new OAuthProxyConnectionApacheHttpCommonsClientImpl(servletReq/*, oAuthState*/) {
							
							@Override
							public boolean isNegotiatingConnection() {
								return false;
							}
							
							@Override
							public void sendRequest() throws IOException {
							
								OAuthProviderConnectionSignpostImpl oAuthConn = instantiatedOAuthConn;
								
								// Sign the request just before sending it
								try {
									
									oAuthConn.connect();
									
									oAuthConn.sign(this.getHttpMethod());
									
								} catch (OAuthMessageSignerException e) {
									e.printStackTrace();
								} catch (OAuthExpectationFailedException e) {
									e.printStackTrace();
								} catch (OAuthCommunicationException e) {
									e.printStackTrace();
								} catch (ExpiredStateException e) {
									e.printStackTrace();
								} catch (NoSuchOAuthProviderException e) {
									e.printStackTrace();
								} catch (SystemException e) {
									e.printStackTrace();
								} catch (OAuthProviderConfigurationException e) {
									e.printStackTrace();
								}
								
								super.sendRequest();
							}
						};
	
						return uRLConn;
					}
				};
				
				return oAuthConn;
			
			} else {
			
				// Inline class to bootstrap getAuthProxyConnection() to return a signing connection ...
				final OAuthProviderConnectionSignpostImpl oAuthConn = new OAuthProviderConnectionSignpostImpl(realmProvider, oAuthState) {
					
					public AuthProxyConnection getAuthProxyConnection() throws MalformedURLException, IOException {
	
						final OAuthProviderConnectionSignpostImpl instantiatedOAuthConn = this;
						
						// ... which signs OAuth 1.0a credentials upon sending requests 
						final OAuthProxyConnectionHttpURLConnectionImpl uRLConn = new OAuthProxyConnectionHttpURLConnectionImpl(servletReq) {
							
							@Override
							public boolean isNegotiatingConnection() {
								return false;
							}
							
							@Override
							public void sendRequest() throws IOException {
							
								OAuthProviderConnectionSignpostImpl oAuthConn = instantiatedOAuthConn;
								
								// Sign the request just before sending it
								try {
									
									oAuthConn.connect();
									
									oAuthConn.sign(this.getHttpURLConnection());
									
								} catch (OAuthMessageSignerException e) {
									e.printStackTrace();
								} catch (OAuthExpectationFailedException e) {
									e.printStackTrace();
								} catch (OAuthCommunicationException e) {
									e.printStackTrace();
								} catch (ExpiredStateException e) {
									e.printStackTrace();
								} catch (NoSuchOAuthProviderException e) {
									e.printStackTrace();
								} catch (SystemException e) {
									e.printStackTrace();
								} catch (OAuthProviderConfigurationException e) {
									e.printStackTrace();
								}
								
								super.sendRequest();
							}
						};
	
						return uRLConn;
					}
				};
				
				return oAuthConn;
			}
		}
		
		throw new OAuthProviderConfigurationException("OAuth " + realmProvider.getProtocolVersion() + " not supported");
	}

}
