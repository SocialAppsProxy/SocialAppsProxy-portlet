package com.wordpress.metaphorm.authProxy.jaxb;

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

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Stian Sigvartsen
 */
public class ThemeDisplayFactory {

	public static ThemeDisplayWrapper convert(com.liferay.portal.theme.ThemeDisplay themeDisplay) {
		
		ThemeDisplayWrapper themeDisplayWrapper = new ThemeDisplayWrapper();
		

		themeDisplayWrapper.setCompanyId(themeDisplay.getCompanyId());
		themeDisplayWrapper.setUserId(themeDisplay.getUserId());
		themeDisplayWrapper.setScopeGroupId(themeDisplay.getScopeGroupId());
		
		themeDisplayWrapper.setCDNDynamicResourcesHost(themeDisplay.getCDNDynamicResourcesHost());
		themeDisplayWrapper.setCDNHost(themeDisplay.getCDNHost());
		themeDisplayWrapper.setColorSchemeId(themeDisplay.getColorSchemeId());
		themeDisplayWrapper.setCompanyGroupId(themeDisplay.getCompanyGroupId());
		themeDisplayWrapper.setCompanyId(themeDisplay.getCompanyId());
		themeDisplayWrapper.setCompanyLogo(themeDisplay.getCompanyLogo());
		themeDisplayWrapper.setCompanyLogoHeight(themeDisplay.getCompanyLogoHeight());
		themeDisplayWrapper.setCompanyLogoWidth(themeDisplay.getCompanyLogoWidth());
		themeDisplayWrapper.setControlPanelCategory(themeDisplay.getControlPanelCategory());
		//themeDisplay.getDefaultUserId();
		themeDisplayWrapper.setDoAsGroupId(themeDisplay.getDoAsGroupId());
		themeDisplayWrapper.setDoAsUserId(themeDisplay.getDoAsUserId());
		themeDisplayWrapper.setDoAsUserLanguageId(themeDisplay.getDoAsUserLanguageId());
		themeDisplayWrapper.setFacebookCanvasPageURL(themeDisplay.getFacebookCanvasPageURL());
		themeDisplayWrapper.setI18nLanguageId(themeDisplay.getI18nLanguageId());
		themeDisplayWrapper.setI18nPath(themeDisplay.getI18nPath());
		themeDisplayWrapper.setLanguageId(themeDisplay.getLanguageId());
		themeDisplayWrapper.setLayoutSetLogo(themeDisplay.getLayoutSetLogo());
		themeDisplayWrapper.setLifecycle(themeDisplay.getLifecycle());
		themeDisplayWrapper.setParentGroupId(themeDisplay.getParentGroupId());
		//themeDisplay.getParentGroupName();
		themeDisplayWrapper.setPathApplet(themeDisplay.getPathApplet());
		themeDisplayWrapper.setPathCms(themeDisplay.getPathCms());
		themeDisplayWrapper.setPathColorSchemeImages(themeDisplay.getPathColorSchemeImages());
		themeDisplayWrapper.setPathContext(themeDisplay.getPathContext());
		themeDisplayWrapper.setPathFlash(themeDisplay.getPathFlash());
		themeDisplayWrapper.setPathFriendlyURLPrivateGroup(themeDisplay.getPathFriendlyURLPrivateGroup());
		themeDisplayWrapper.setPathFriendlyURLPrivateUser(themeDisplay.getPathFriendlyURLPrivateUser());
		themeDisplayWrapper.setPathFriendlyURLPublic(themeDisplay.getPathFriendlyURLPublic());
		themeDisplayWrapper.setPathImage(themeDisplay.getPathImage());
		themeDisplayWrapper.setPathJavaScript(themeDisplay.getPathJavaScript());
		themeDisplayWrapper.setPathMain(themeDisplay.getPathMain());
		themeDisplayWrapper.setPathSound(themeDisplay.getPathSound());
		themeDisplayWrapper.setPathThemeCss(themeDisplay.getPathThemeCss());
		themeDisplayWrapper.setPathThemeImages(themeDisplay.getPathThemeImages());
		themeDisplayWrapper.setPathThemeJavaScript(themeDisplay.getPathThemeJavaScript());
		themeDisplayWrapper.setPathThemeRoot(themeDisplay.getPathThemeRoot());
		themeDisplayWrapper.setPathThemeTemplates(themeDisplay.getPathThemeTemplates());
		themeDisplayWrapper.setPlid(themeDisplay.getPlid());
		themeDisplayWrapper.setPortalURL(themeDisplay.getPortalURL());
		themeDisplayWrapper.setRealCompanyLogo(themeDisplay.getRealCompanyLogo());
		themeDisplayWrapper.setRealCompanyLogoHeight(themeDisplay.getRealCompanyLogoHeight());
		themeDisplayWrapper.setRealCompanyLogoWidth(themeDisplay.getRealCompanyLogoWidth());
		themeDisplayWrapper.setRealUserId(themeDisplay.getRealUserId());
		themeDisplayWrapper.setRefererPlid(themeDisplay.getRefererPlid());
		themeDisplayWrapper.setScopeGroupId(themeDisplay.getScopeGroupId());
		//themeDisplay.getScopeGroupIdOrLiveGroupId();
		//themeDisplay.getScopeGroupName();
		themeDisplayWrapper.setServerName(themeDisplay.getServerName());
		themeDisplayWrapper.setServerPort(themeDisplay.getServerPort());
		themeDisplayWrapper.setSessionId(themeDisplay.getSessionId());
		themeDisplayWrapper.setThemeId(themeDisplay.getThemeId());
		themeDisplayWrapper.setTilesContent(themeDisplay.getTilesContent());
		themeDisplayWrapper.setTilesTitle(themeDisplay.getTilesTitle());
		themeDisplayWrapper.setTimeZone(themeDisplay.getTimeZone());
		themeDisplayWrapper.setURLAddContent(themeDisplay.getURLAddContent());
		themeDisplayWrapper.setURLControlPanel(themeDisplay.getURLControlPanel());
		themeDisplayWrapper.setURLCurrent(themeDisplay.getURLCurrent());
		themeDisplayWrapper.setURLHome(themeDisplay.getURLHome());
		themeDisplayWrapper.setURLLayoutTemplates(themeDisplay.getURLLayoutTemplates());
		
		try {
			themeDisplayWrapper.setURLManageSiteMemberships(new URL(themeDisplay.getURLManageSiteMemberships().toString()));
		} catch (MalformedURLException e) {} catch (NullPointerException e) {}
		
		try {
			themeDisplayWrapper.setURLMyAccount(new URL(themeDisplay.getURLMyAccount().toString()));
		} catch (MalformedURLException e) {} catch (NullPointerException e) {}
		
		try {
			themeDisplayWrapper.setURLPageSettings(new URL(themeDisplay.getURLPageSettings().toString()));
		} catch (MalformedURLException e) {} catch (NullPointerException e) {}
		
		themeDisplayWrapper.setURLPortal(themeDisplay.getURLPortal());
		
		try {
			themeDisplayWrapper.setURLPublishToLive(new URL(themeDisplay.getURLPublishToLive().toString()));
		} catch (MalformedURLException e) {} catch (NullPointerException e) {}
		
		themeDisplayWrapper.setURLSignIn(themeDisplay.getURLSignIn());
		themeDisplayWrapper.setURLSignOut(themeDisplay.getURLSignOut());
		themeDisplayWrapper.setURLSiteContent(themeDisplay.getURLSiteContent());
		
		try {
			themeDisplayWrapper.setURLSiteMapSettings(new URL(themeDisplay.getURLSiteMapSettings().toString()));
		} catch (MalformedURLException e) {} catch (NullPointerException e) {}
		
		try {
			themeDisplayWrapper.setURLSiteSettings(new URL(themeDisplay.getURLSiteSettings().toString()));
		} catch (MalformedURLException e) {} catch (NullPointerException e) {}
		
		try {
			themeDisplayWrapper.setURLUpdateManager(new URL(themeDisplay.getURLUpdateManager().toString()));
		} catch (MalformedURLException e) {} catch (NullPointerException e) {}
		
		themeDisplayWrapper.setUserId(themeDisplay.getUserId());
	
		return themeDisplayWrapper;
	}
}
