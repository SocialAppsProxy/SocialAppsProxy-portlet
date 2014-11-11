package com.wordpress.metaphorm.authProxy.portlet;

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

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider;
import com.wordpress.metaphorm.authProxy.sb.service.OAuthConnectionLocalServiceUtil;
import com.wordpress.metaphorm.authProxy.sb.service.OAuthProviderLocalServiceUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;

/**
 * Portlet implementation class AuthProxyProvidersAdminPortlet
 * 
 * @author Stian Sigvartsen
 */
public class AuthProxyProvidersAdminPortlet extends MVCPortlet {
 
	private void updateEntity(OAuthProvider oAuthProvider, ActionRequest request) throws SystemException {
		
		String realm = request.getParameter("realm");
		String preemptiveAuthURLPattern = request.getParameter("preemptiveAuthURLPattern");
		String consumerKey = request.getParameter("consumerKey");
		String consumerSecret = request.getParameter("consumerSecret");
		String requestTokenURL = request.getParameter("requestTokenURL");
		String authoriseURL = request.getParameter("authoriseURL");
		String accessTokenURL = request.getParameter("accessTokenURL");
		String oAuthVersion = request.getParameter("protocolVersion");
		boolean useHTTPS = Boolean.parseBoolean(request.getParameter("useHTTPS"));

		_log.debug("oAuthVersion = " + oAuthVersion);
		_log.debug("useHTTPS = " + useHTTPS);
		
		oAuthProvider.setRealm(realm);
		oAuthProvider.setPreemptiveAuthURLPattern(preemptiveAuthURLPattern);
		oAuthProvider.setConsumerKey(consumerKey);
		oAuthProvider.setConsumerSecret(consumerSecret);
		oAuthProvider.setRequestTokenURL(requestTokenURL);
		oAuthProvider.setAuthoriseURL(authoriseURL);
		oAuthProvider.setAccessTokenURL(accessTokenURL);
		oAuthProvider.setProtocolVersion(oAuthVersion);
		oAuthProvider.setUseHTTPS(useHTTPS);
		
		OAuthProviderLocalServiceUtil.updateOAuthProvider(oAuthProvider);		
	}
	
	@ProcessAction(name="addProvider")
	public void addProvider(ActionRequest request, ActionResponse response) 
			throws IOException, PortletException {
		
		OAuthProvider oAuthProvider = null;
		
		try {

			long providerId = CounterLocalServiceUtil.increment(OAuthProvider.class.getName());
			oAuthProvider = OAuthProviderLocalServiceUtil.createOAuthProvider(providerId);
			
			updateEntity(oAuthProvider, request);
			
			if (request.getParameter("redirect") != null)
				response.sendRedirect(request.getParameter("redirect"));
		
		} catch (SystemException e) {
			
			request.setAttribute("provider", oAuthProvider);
			e.printStackTrace();
		}
	}
	
	@ProcessAction(name="updateProvider")
	public void updateProvider(ActionRequest request, ActionResponse response) 
			throws IOException, PortletException {
	
		OAuthProvider oAuthProvider = null;
		
		try {
			
			String providerId = request.getParameter("providerId");
			oAuthProvider = OAuthProviderLocalServiceUtil.getOAuthProvider(Long.valueOf(providerId));
			
			updateEntity(oAuthProvider, request);
			
			if (request.getParameter("redirect") != null)
				response.sendRedirect(request.getParameter("redirect"));

		} catch (NumberFormatException e) {
			
			request.setAttribute("provider", oAuthProvider);			
			e.printStackTrace();
			
		} catch (PortalException e) {
			
			request.setAttribute("provider", oAuthProvider);
			e.printStackTrace();
			
		} catch (SystemException e) {
			
			request.setAttribute("provider", oAuthProvider);
			e.printStackTrace();
		}

	}
	
	@ProcessAction(name="deleteProvider")
	public void deleteProvider(ActionRequest request, ActionResponse response) 
			throws IOException, PortletException {
		
		String providerId = request.getParameter("providerId");
		try {
			
			OAuthProviderLocalServiceUtil.deleteOAuthProvider(Long.valueOf(providerId));
			
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (PortalException e) {

			e.printStackTrace();
		} catch (SystemException e) {

			e.printStackTrace();
		}
	}

	@ProcessAction(name="deleteAllConnections")
	public void deleteAllConnections(ActionRequest request, ActionResponse response) 
			throws IOException, PortletException {
		
		try {
			OAuthConnectionLocalServiceUtil.deleteAllConnections();
		} catch (SystemException e) {
			e.printStackTrace();
		}
	}

	private static Log _log = LogFactoryUtil.getLog(AuthProxyProvidersAdminPortlet.class);
}
