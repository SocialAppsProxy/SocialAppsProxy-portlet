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

import java.net.URL;
import java.util.TimeZone;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Stian Sigvartsen
 */
@XmlRootElement(name="themeDisplay")
public class ThemeDisplayWrapper {

	//User user;

	public long getUserId() { return userId; }

	public String getCdnDynamicResourcesHost() {
		return cdnDynamicResourcesHost;
	}

	public void setCdnDynamicResourcesHost(String cdnDynamicResourcesHost) {
		this.cdnDynamicResourcesHost = cdnDynamicResourcesHost;
	}

	public String getCdnHost() {
		return cdnHost;
	}

	public void setCdnHost(String cdnHost) {
		this.cdnHost = cdnHost;
	}

	public String getUrlAddContent() {
		return urlAddContent;
	}

	public void setUrlAddContent(String urlAddContent) {
		this.urlAddContent = urlAddContent;
	}

	public String getUrlControlPanel() {
		return urlControlPanel;
	}

	public void setUrlControlPanel(String urlControlPanel) {
		this.urlControlPanel = urlControlPanel;
	}

	public String getUrlCurrent() {
		return urlCurrent;
	}

	public void setUrlCurrent(String urlCurrent) {
		this.urlCurrent = urlCurrent;
	}

	public String getUrlHome() {
		return urlHome;
	}

	public void setUrlHome(String urlHome) {
		this.urlHome = urlHome;
	}

	public String getUrlLayoutTemplates() {
		return urlLayoutTemplates;
	}

	public void setUrlLayoutTemplates(String urlLayoutTemplates) {
		this.urlLayoutTemplates = urlLayoutTemplates;
	}

	public URL getUrlManageSiteMemberships() {
		return urlManageSiteMemberships;
	}

	public void setUrlManageSiteMemberships(URL urlManageSiteMemberships) {
		this.urlManageSiteMemberships = urlManageSiteMemberships;
	}

	public URL getUrlMyAccount() {
		return urlMyAccount;
	}

	public void setUrlMyAccount(URL urlMyAccount) {
		this.urlMyAccount = urlMyAccount;
	}

	public URL getUrlPageSettings() {
		return urlPageSettings;
	}

	public void setUrlPageSettings(URL urlPageSettings) {
		this.urlPageSettings = urlPageSettings;
	}

	public String getUrlPortal() {
		return urlPortal;
	}

	public void setUrlPortal(String urlPortal) {
		this.urlPortal = urlPortal;
	}

	public URL getUrlPublishToLive() {
		return urlPublishToLive;
	}

	public void setUrlPublishToLive(URL urlPublishToLive) {
		this.urlPublishToLive = urlPublishToLive;
	}

	public String getUrlSignIn() {
		return urlSignIn;
	}

	public void setUrlSignIn(String urlSignIn) {
		this.urlSignIn = urlSignIn;
	}

	public String getUrlSignOut() {
		return urlSignOut;
	}

	public void setUrlSignOut(String urlSignOut) {
		this.urlSignOut = urlSignOut;
	}

	public String getUrlSiteContent() {
		return urlSiteContent;
	}

	public void setUrlSiteContent(String urlSiteContent) {
		this.urlSiteContent = urlSiteContent;
	}

	public URL getUrlSiteMapSettings() {
		return urlSiteMapSettings;
	}

	public void setUrlSiteMapSettings(URL urlSiteMapSettings) {
		this.urlSiteMapSettings = urlSiteMapSettings;
	}

	public URL getUrlSiteSettings() {
		return urlSiteSettings;
	}

	public void setUrlSiteSettings(URL urlSiteSettings) {
		this.urlSiteSettings = urlSiteSettings;
	}

	public URL getUrlUpdateManager() {
		return urlUpdateManager;
	}

	public void setUrlUpdateManager(URL urlUpdateManager) {
		this.urlUpdateManager = urlUpdateManager;
	}

	public String getColorSchemeId() {
		return colorSchemeId;
	}

	public long getCompanyGroupId() {
		return companyGroupId;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public int getCompanyLogoHeight() {
		return companyLogoHeight;
	}

	public int getCompanyLogoWidth() {
		return companyLogoWidth;
	}

	public String getControlPanelCategory() {
		return controlPanelCategory;
	}

	public long getDoAsGroupId() {
		return doAsGroupId;
	}

	public String getDoAsUserId() {
		return doAsUserId;
	}

	public String getDoAsUserLanguageId() {
		return doAsUserLanguageId;
	}

	public String getFacebookCanvasPageURL() {
		return facebookCanvasPageURL;
	}

	public String getI18nLanguageId() {
		return i18nLanguageId;
	}

	public String getI18nPath() {
		return i18nPath;
	}

	public String getLanguageId() {
		return languageId;
	}

	public String getLayoutSetLogo() {
		return layoutSetLogo;
	}

	public String getLifecycle() {
		return lifecycle;
	}

	public long getParentGroupId() {
		return parentGroupId;
	}

	public String getPathApplet() {
		return pathApplet;
	}

	public String getPathCms() {
		return pathCms;
	}

	public String getPathColorSchemeImages() {
		return pathColorSchemeImages;
	}

	public String getPathContext() {
		return pathContext;
	}

	public String getPathFlash() {
		return pathFlash;
	}

	public String getPathFriendlyURLPrivateGroup() {
		return pathFriendlyURLPrivateGroup;
	}

	public String getPathFriendlyURLPrivateUser() {
		return pathFriendlyURLPrivateUser;
	}

	public String getPathFriendlyURLPublic() {
		return pathFriendlyURLPublic;
	}

	public String getPathImage() {
		return pathImage;
	}

	public String getPathJavaScript() {
		return pathJavaScript;
	}

	public String getPathMain() {
		return pathMain;
	}

	public String getPathSound() {
		return pathSound;
	}

	public String getPathThemeCss() {
		return pathThemeCss;
	}

	public String getPathThemeImages() {
		return pathThemeImages;
	}

	public String getPathThemeJavaScript() {
		return pathThemeJavaScript;
	}

	public String getPathThemeRoot() {
		return pathThemeRoot;
	}

	public String getPathThemeTemplates() {
		return pathThemeTemplates;
	}

	public long getPlid() {
		return plid;
	}

	public String getPortalURL() {
		return portalURL;
	}

	public String getRealCompanyLogo() {
		return realCompanyLogo;
	}

	public int getRealCompanyLogoHeight() {
		return realCompanyLogoHeight;
	}

	public int getRealCompanyLogoWidth() {
		return realCompanyLogoWidth;
	}

	public long getRealUserId() {
		return realUserId;
	}

	public long getRefererPlid() {
		return refererPlid;
	}

	public String getServerName() {
		return serverName;
	}

	public int getServerPort() {
		return serverPort;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getThemeId() {
		return themeId;
	}

	public String getTilesContent() {
		return tilesContent;
	}

	public String getTilesTitle() {
		return tilesTitle;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	long userId;
	public void setUserId(long userId) { this.userId = userId; }

	public long getCompanyId() { return companyId; }

	long companyId;
	public void setCompanyId(long companyId) { this.companyId = companyId; }

	public long getScopeGroupId() { return scopeGroupId; }

	long scopeGroupId;
	public void setScopeGroupId(long scopeGroupId) { this.scopeGroupId = scopeGroupId; }

	String cdnDynamicResourcesHost;
	public void setCDNDynamicResourcesHost(String cdnDynamicResourcesHost) { this.cdnDynamicResourcesHost = cdnDynamicResourcesHost; }

	String cdnHost;
	public void setCDNHost(String cdnHost) { this.cdnHost = cdnHost; }

	String colorSchemeId;
	public void setColorSchemeId(String colorSchemeId) { this.colorSchemeId = colorSchemeId; }

	long companyGroupId;
	public void setCompanyGroupId(long companyGroupId) { this.companyGroupId = companyGroupId; }

	String companyLogo;
	public void setCompanyLogo(String companyLogo) { this.companyLogo = companyLogo; }

	int companyLogoHeight;
	public void setCompanyLogoHeight(int companyLogoHeight) { this.companyLogoHeight = companyLogoHeight; }

	int companyLogoWidth;
	public void setCompanyLogoWidth(int companyLogoWidth) { this.companyLogoWidth = companyLogoWidth; }

	String controlPanelCategory;
	public void setControlPanelCategory(String controlPanelCategory) { this.controlPanelCategory = controlPanelCategory; }

	long doAsGroupId;
	public void setDoAsGroupId(long doAsGroupId) { this.doAsGroupId = doAsGroupId; }

	String doAsUserId;
	public void setDoAsUserId(String doAsUserId) { this.doAsUserId = doAsUserId; }

	String doAsUserLanguageId;
	public void setDoAsUserLanguageId(String doAsUserLanguageId) { this.doAsUserLanguageId = doAsUserLanguageId; }

	String facebookCanvasPageURL;
	public void setFacebookCanvasPageURL(String facebookCanvasPageURL) { this.facebookCanvasPageURL = facebookCanvasPageURL; }

	String i18nLanguageId;
	public void setI18nLanguageId(String i18nLanguageId) { this.i18nLanguageId = i18nLanguageId; }

	String i18nPath;
	public void setI18nPath(String i18nPath) { this.i18nPath = i18nPath; }

	String languageId;
	public void setLanguageId(String languageId) { this.languageId = languageId; }

	String layoutSetLogo;
	public void setLayoutSetLogo(String layoutSetLogo) { this.layoutSetLogo = layoutSetLogo; }

	String lifecycle;
	public void setLifecycle(String lifecycle) { this.lifecycle = lifecycle; }

	long parentGroupId;
	public void setParentGroupId(long parentGroupId) { this.parentGroupId = parentGroupId; }

	String pathApplet;
	public void setPathApplet(String pathApplet) { this.pathApplet = pathApplet; }

	String pathCms;
	public void setPathCms(String pathCms) { this.pathCms = pathCms; }

	String pathColorSchemeImages;
	public void setPathColorSchemeImages(String pathColorSchemeImages) { this.pathColorSchemeImages = pathColorSchemeImages; }

	String pathContext;
	public void setPathContext(String pathContext) { this.pathContext = pathContext; }

	String pathFlash;
	public void setPathFlash(String pathFlash) { this.pathFlash = pathFlash; }


	String pathFriendlyURLPrivateGroup;
	public void setPathFriendlyURLPrivateGroup(
			String pathFriendlyURLPrivateGroup) { this.pathFriendlyURLPrivateGroup = pathFriendlyURLPrivateGroup; }

	String pathFriendlyURLPrivateUser;
	public void setPathFriendlyURLPrivateUser(String pathFriendlyURLPrivateUser) { this.pathFriendlyURLPrivateUser = pathFriendlyURLPrivateUser; }

	String pathFriendlyURLPublic;
	public void setPathFriendlyURLPublic(String pathFriendlyURLPublic) { this.pathFriendlyURLPublic = pathFriendlyURLPublic; }

	String pathImage;
	public void setPathImage(String pathImage) { this.pathImage = pathImage; }

	String pathJavaScript;
	public void setPathJavaScript(String pathJavaScript) { this.pathJavaScript = pathJavaScript; }

	String pathMain;
	public void setPathMain(String pathMain) { this.pathMain = pathMain; }

	String pathSound;
	public void setPathSound(String pathSound) { this.pathSound = pathSound; }

	String pathThemeCss;
	public void setPathThemeCss(String pathThemeCss) { this.pathThemeCss = pathThemeCss; }

	String pathThemeImages;
	public void setPathThemeImages(String pathThemeImages) { this.pathThemeImages = pathThemeImages; }

	String pathThemeJavaScript;
	public void setPathThemeJavaScript(String pathThemeJavaScript) { this.pathThemeJavaScript = pathThemeJavaScript; }

	String pathThemeRoot;
	public void setPathThemeRoot(String pathThemeRoot) { this.pathThemeRoot = pathThemeRoot; }

	String pathThemeTemplates;
	public void setPathThemeTemplates(String pathThemeTemplates) { this.pathThemeTemplates = pathThemeTemplates; }

	long plid;
	public void setPlid(long plid) { this.plid = plid; }

	String portalURL;
	public void setPortalURL(String portalURL) { this.portalURL = portalURL; }

	String realCompanyLogo;
	public void setRealCompanyLogo(String realCompanyLogo) { this.realCompanyLogo = realCompanyLogo; }

	int realCompanyLogoHeight;
	public void setRealCompanyLogoHeight(int realCompanyLogoHeight) { this.realCompanyLogoHeight = realCompanyLogoHeight; }

	int realCompanyLogoWidth;
	public void setRealCompanyLogoWidth(int realCompanyLogoWidth) { this.realCompanyLogoWidth = realCompanyLogoWidth; }

	long realUserId;
	public void setRealUserId(long realUserId) { this.realUserId = realUserId; }

	long refererPlid;
	public void setRefererPlid(long refererPlid) { this.refererPlid = refererPlid; }

	String serverName;
	public void setServerName(String serverName) { this.serverName = serverName; }

	int serverPort;
	public void setServerPort(int serverPort) { this.serverPort = serverPort; }

	String sessionId;
	public void setSessionId(String sessionId) { this.sessionId = sessionId; }

	String themeId;
	public void setThemeId(String themeId) { this.themeId = themeId; }

	String tilesContent;
	public void setTilesContent(String tilesContent) { this.tilesContent = tilesContent; }

	String tilesTitle;
	public void setTilesTitle(String tilesTitle) { this.tilesTitle = tilesTitle; }

	TimeZone timeZone;
	public void setTimeZone(TimeZone timeZone) { this.timeZone = timeZone; }

	String urlAddContent;
	public void setURLAddContent(String urlAddContent) { this.urlAddContent = urlAddContent; }

	String urlControlPanel;
	public void setURLControlPanel(String urlControlPanel) { this.urlControlPanel = urlControlPanel; }

	String urlCurrent;
	public void setURLCurrent(String urlCurrent) { this.urlCurrent = urlCurrent; }

	String urlHome;
	public void setURLHome(String urlHome) { this.urlHome = urlHome; }

	String urlLayoutTemplates;
	public void setURLLayoutTemplates(String urlLayoutTemplates) { this.urlLayoutTemplates = urlLayoutTemplates; }

	URL urlManageSiteMemberships;
	public void setURLManageSiteMemberships(URL urlManageSiteMemberships) { this.urlManageSiteMemberships = urlManageSiteMemberships; }

	URL urlMyAccount;
	public void setURLMyAccount(URL urlMyAccount) { this.urlMyAccount = urlMyAccount; }

	URL urlPageSettings;
	public void setURLPageSettings(URL urlPageSettings) { this.urlPageSettings = urlPageSettings; }

	String urlPortal;
	public void setURLPortal(String urlPortal) { this.urlPortal = urlPortal; }

	URL urlPublishToLive;
	public void setURLPublishToLive(URL urlPublishToLive) { this.urlPublishToLive = urlPublishToLive; }

	String urlSignIn;
	public void setURLSignIn(String urlSignIn) { this.urlSignIn = urlSignIn; }

	String urlSignOut;
	public void setURLSignOut(String urlSignOut) { this.urlSignOut = urlSignOut; }

	String urlSiteContent;
	public void setURLSiteContent(String urlSiteContent) { this.urlSiteContent = urlSiteContent; }

	URL urlSiteMapSettings;
	public void setURLSiteMapSettings(URL urlSiteMapSettings) { this.urlSiteMapSettings = urlSiteMapSettings; }

	URL urlSiteSettings;
	public void setURLSiteSettings(URL urlSiteSettings) { this.urlSiteSettings = urlSiteSettings; }

	URL urlUpdateManager;
	public void setURLUpdateManager(URL urlUpdateManager) { this.urlUpdateManager = urlUpdateManager; }
}