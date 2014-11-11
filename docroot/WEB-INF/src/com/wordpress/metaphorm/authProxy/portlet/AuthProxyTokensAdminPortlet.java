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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.wordpress.metaphorm.authProxy.sb.service.OAuthConnectionLocalServiceUtil;

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
public class AuthProxyTokensAdminPortlet extends MVCPortlet {
	
	@ProcessAction(name="deleteConnection")
	public void deleteProvider(ActionRequest request, ActionResponse response) 
			throws IOException, PortletException {
		
		String connectionId = request.getParameter("connectionId");
		try {
			
			OAuthConnectionLocalServiceUtil.deleteOAuthConnection(Long.valueOf(connectionId));
			
		} catch (NumberFormatException e) {

			e.printStackTrace();
		} catch (PortalException e) {

			e.printStackTrace();
		} catch (SystemException e) {

			e.printStackTrace();
		}
	}
	
	private static Log _log = LogFactoryUtil.getLog(AuthProxyTokensAdminPortlet.class);
}
