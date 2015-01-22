package com.wordpress.metaphorm.authProxy.httpClient;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.wordpress.metaphorm.authProxy.OAuthProviderConfigurationException;
import com.wordpress.metaphorm.authProxy.ProtocolNotSupportedException;
import com.wordpress.metaphorm.authProxy.RedirectRequiredException;
import com.wordpress.metaphorm.authProxy.httpClient.impl.OAuthProxyConnectionApacheHttpCommonsClientImpl;
import com.wordpress.metaphorm.authProxy.httpClient.impl.OAuthProxyConnectionHttpURLConnectionImpl;
import com.wordpress.metaphorm.authProxy.oauthClient.OAuthCommunicationException;
import com.wordpress.metaphorm.authProxy.oauthClient.OAuthExpectationFailedException;
import com.wordpress.metaphorm.authProxy.oauthClient.OAuthNotAuthorizedException;
import com.wordpress.metaphorm.authProxy.oauthClient.OAuthProviderConnection;
import com.wordpress.metaphorm.authProxy.oauthClient.impl.OAuthProviderConnectionSignpostImpl;
import com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException;
import com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider;
import com.wordpress.metaphorm.authProxy.sb.service.OAuthProviderLocalServiceUtil;
import com.wordpress.metaphorm.authProxy.state.ExpiredStateException;
import com.wordpress.metaphorm.authProxy.state.OAuthState;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	public OAuthProviderConnection getOAuth20ProviderConnection(OAuthProvider realmProvider) 
			throws OAuthProviderConfigurationException, ProtocolNotSupportedException, IOException {
		
		return null;
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
			throws OAuthProviderConfigurationException, ProtocolNotSupportedException, IOException {
		
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
								
								} catch (ProtocolNotSupportedException e) {
									e.printStackTrace();
								} catch (oauth.signpost.exception.OAuthMessageSignerException e) {
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
									
								} catch (ProtocolNotSupportedException e) {
									e.printStackTrace();
								} catch (oauth.signpost.exception.OAuthMessageSignerException e) {
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
	
	
	public OAuthProviderConnection getProviderConnection(URL uRL) throws NoSuchOAuthProviderException, SystemException, OAuthProviderConfigurationException, ProtocolNotSupportedException, IOException {
		
		OAuthProvider preEmptiveProvider = OAuthProviderLocalServiceUtil.getMatchingOAuthProvider(uRL);	

		if (preEmptiveProvider != null) {

			if (preEmptiveProvider.getProtocolVersion().equalsIgnoreCase("2.0")) {
			
				return getOAuth20ProviderConnection(preEmptiveProvider);
			}

			if (preEmptiveProvider.getProtocolVersion().equalsIgnoreCase("1.0a")) {
			
				return getOAuth10AProviderConnection(preEmptiveProvider);
			}
		}
		
		return null;
	}
		
	
	public OAuthProviderConnection getProviderConnection(String authChallengeStr) throws NoSuchOAuthProviderException, SystemException, OAuthProviderConfigurationException, ProtocolNotSupportedException, IOException {
		
		String realm = null;
		String oAuthVersion = null;
		OAuthProvider oAuthProvider = null;

		try {
		
			if ((realm = extractOAuth20Realm(authChallengeStr)) != null) {
				
				oAuthVersion = "2.0";
				oAuthProvider = OAuthProviderLocalServiceUtil.getProviderForRealm(realm);
				
				return getOAuth20ProviderConnection(oAuthProvider);
				
			}
			
			if ((realm = extractOAuth10aRealm(authChallengeStr)) != null) {
				
				oAuthVersion = "1.0a";
				oAuthProvider = OAuthProviderLocalServiceUtil.getProviderForRealm(realm);
				
				return getOAuth10AProviderConnection(oAuthProvider);
			}
			
		} catch (NoSuchOAuthProviderException e) {
			
			// If an OAuthProvider is not configured for this realm

			try {
				
				// ... then create another OAuthProvider with the discovered realm
				// By creating a new one this allows the existing one which has a pre-emptive URL pattern configured to continue to be
				// used to proxy HTTP to HTTPS for non-OAuth resource as well
				
				_log.debug("AuthProxyHttpURLConnection :: Creating a new OAuthProvider with discovered oAuth realm...");
				
				long realmProviderId = CounterLocalServiceUtil.increment(OAuthProvider.class.getName());
				OAuthProvider realmProvider = OAuthProviderLocalServiceUtil.createOAuthProvider(realmProviderId);
				realmProvider.setRealm(realm);
				realmProvider.setUseHTTPS(false);
				realmProvider.setProtocolVersion(oAuthVersion);

				OAuthProviderLocalServiceUtil.updateOAuthProvider(realmProvider);
											
			} catch (SystemException e2) {
				// Ok to fail silently?
				e2.printStackTrace();
				return null;
			}
			
			throw new OAuthProviderConfigurationException(
					"No OAuth provider configured for realm \""+ realm + "\". A new one has been automatically created but an administrator needs to configure an appropriate consumer key and consumer secret using the Liferay Portal control panel before it can be used.");
		}
		
		return null;
	}

	
	private String extractOAuth10aRealm(String authChallengeStr) {
		
		if (authChallengeStr != null) {
			
			Pattern pattern = Pattern.compile("OAuth\\srealm=\"([^\"]*)\"");
			Matcher matcher = pattern.matcher(authChallengeStr);
			
			if (matcher.find()) {
				
				String oAuthRealm = matcher.group(1);	
				_log.debug("AuthProxyHttpURLConnection :: Was able to extract the OAuth 1.0a realm: " + oAuthRealm);
				
				return oAuthRealm;
			}			
		}
		return null;
	}	
	
	private String extractOAuth20Realm(String authChallengeStr) {
		
		if (authChallengeStr != null) {
			
			Pattern pattern = Pattern.compile("Bearer\\srealm=\"([^\"]*)\"");
			Matcher matcher = pattern.matcher(authChallengeStr);
			
			if (matcher.find()) {
				
				String oAuthRealm = matcher.group(1);	
				_log.debug("AuthProxyHttpURLConnection :: Was able to extract the OAuth 2.0 realm: " + oAuthRealm);
				
				return oAuthRealm;
			}			
		}
		return null;
	}	
	
	public void delegateCallback(
			HttpServletRequest servletReq,
			HttpServletResponse servletResp)
	
					throws IOException, RedirectRequiredException, ExpiredStateException, NoSuchOAuthProviderException, SystemException, OAuthProviderConfigurationException, ProtocolNotSupportedException {
		
		// OAuth 1.0a callback
		if (servletReq.getParameter("oauth_realm") != null
				&& servletReq.getParameter("oauth_token") != null 
				&& servletReq.getParameter("oauth_verifier") != null) {
			
			String oAuthRealm = servletReq.getParameter("oauth_realm");
			String token = servletReq.getParameter("oauth_token");
			String verifier = servletReq.getParameter("oauth_verifier");	
			
			_log.debug("Received OAuth callback request for token " + token + " for the realm " + oAuthRealm);
			
			// Redirect
			_log.debug("Stripping out oauth_token and oauth_verifier parameters from requested URL to create callback URL");
			String queryString = servletReq.getQueryString();
			
			if (queryString != null) {
				_log.debug("  Before: " + queryString);
				queryString = queryString.replaceAll("&oauth_token(=[^&]*)?|^oauth_token(=[^&]*)?&?", "")
						.replaceAll("&oauth_verifier(=[^&]*)?|^oauth_verifier(=[^&]*)?&?", "")
						.replaceAll("&oauth_realm(=[^&]*)?|^oauth_realm(=[^&]*)?&?", "");
				_log.debug("  After: " + queryString);
			}
			
			URL callbackURL;
			
			try {
				callbackURL = new URL(servletReq.getRequestURL() + (queryString != null && queryString.length() > 0 ? "?" + queryString : ""));
				
			} catch (MalformedURLException e) {
				throw new IOException(e);
			}
			
			try {
				
				handleOAuth10ACallback(oAuthRealm, token, verifier, callbackURL);
				
			} catch (OAuthCommunicationException e) {
				
				_log.error(e);
				
			} catch (OAuthExpectationFailedException e) {
				
				_log.error(e);
				
			} catch (OAuthNotAuthorizedException e) {
				
				_log.warn(e);
			}

		}
	}
	
	private void handleOAuth10ACallback(String oAuthRealm, String token, final String verifier, URL callbackURL) throws 
				ExpiredStateException, OAuthCommunicationException, OAuthExpectationFailedException, OAuthNotAuthorizedException,
				NoSuchOAuthProviderException, SystemException, IOException, OAuthProviderConfigurationException, ProtocolNotSupportedException {
		
		OAuthProvider oAuthProvider = OAuthProviderLocalServiceUtil.getProviderForRealm(oAuthRealm);
		OAuthProviderConnection oAuthConn = getOAuth10AProviderConnection(oAuthProvider);
				
		// Retrieve access token
		oAuthConn.connect();
		oAuthConn.setVerifier(verifier);
		oAuthConn.retrieveAccessToken();

		// Retrieve access token
		throw new RedirectRequiredException(callbackURL);
	}
	

	private static Log _log = LogFactoryUtil.getLog(AuthProxyConnectionFactory.class);
}
