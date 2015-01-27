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
