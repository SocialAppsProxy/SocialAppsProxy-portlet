package com.wordpress.metaphorm.authProxy;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException;
import com.wordpress.metaphorm.authProxy.state.ExpiredStateException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProtocolCallbackHandlerFactory {

	public static void delegateCallback(
			HttpServletRequest servletReq,
			HttpServletResponse servletResp)
	
					throws IOException, RedirectRequiredException, ExpiredStateException, NoSuchOAuthProviderException, SystemException, OAuthProviderConfigurationException, ProtocolNotSupportedException {
		
		// OAuth 1.0a callback
		OAuth10ACallbackHandler handler = OAuth10ACallbackHandler.newInstance(servletReq, servletResp);
		if (handler != null) {
			
			handler.handleProtocolCallback();
			
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
			
			throw new RedirectRequiredException(callbackURL);
			
		} // Else not a callback or at least not one supported by the proxy
	}
	
	private static Log _log = LogFactoryUtil.getLog(ProtocolCallbackHandlerFactory.class);
}
