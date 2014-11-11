package com.wordpress.metaphorm.authProxy.state.impl;

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
import com.liferay.portal.service.ServiceContext;
import com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException;
import com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException;
import com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection;
import com.wordpress.metaphorm.authProxy.sb.service.OAuthConnectionLocalServiceUtil;
import com.wordpress.metaphorm.authProxy.sb.service.OAuthProviderLocalServiceUtil;
import com.wordpress.metaphorm.authProxy.state.ExpiredStateException;
import com.wordpress.metaphorm.authProxy.state.OAuthState;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

/**
 * @author Stian Sigvartsen
 */
public class PortalAuthedSessionOAuthStateImpl extends HttpSessionOAuthStateImpl implements OAuthState {

	private static final long serialVersionUID = 1L;

	private HttpSession portalSession;
	
	Map<String, String> dirtyToken;
	Map<String, String> dirtyTokenSecret;
	Map<String, Integer> dirtyPhase;
	
	public PortalAuthedSessionOAuthStateImpl(ServiceContext serviceContext, HttpSession portalSession) throws ExpiredStateException {
		super(portalSession);
		this.portalSession = portalSession;
		
		setUserId(serviceContext.getUserId());
		setGroupId(serviceContext.getScopeGroupId());
		setCompanyId(serviceContext.getCompanyId());
		
		dirtyToken = new HashMap<String, String>();
		dirtyTokenSecret = new HashMap<String, String>();
		dirtyPhase = new HashMap<String, Integer>();
	}
	
	private void setUserId(long userId) {
		portalSession.setAttribute("oAuthConsumer-userId", Long.valueOf(userId));
	}
	
	private long getUserId() {
		Long userIdObj = (Long)portalSession.getAttribute("oAuthConsumer-userId");
		if (userIdObj == null) return -1;
		return userIdObj.longValue();
	}

	private void setGroupId(long groupId) {
		portalSession.setAttribute("oAuthConsumer-groupId", Long.valueOf(groupId));
	}
	
	private long getGroupId() {
		Long groupIdObj = (Long)portalSession.getAttribute("oAuthConsumer-groupId");
		if (groupIdObj == null) return -1;
		return groupIdObj.longValue();
	}
	
	private void setCompanyId(long companyId) {
		portalSession.setAttribute("oAuthConsumer-companyId", Long.valueOf(companyId));
	}
	
	private long getCompanyId() {
		Long companyIdObj = (Long)portalSession.getAttribute("oAuthConsumer-companyId");
		if (companyIdObj == null) return -1;
		return companyIdObj.longValue();
	}
	
	@Override
	public void setConsumer(String oAuthRealm, OAuthConsumer consumer) throws ExpiredStateException {
		
		_log.debug("setConsumer(\"" + oAuthRealm + "\", " + "OAuthConsumer object" + ")");
		
		dirtyToken.put(oAuthRealm, consumer.getToken());			
		dirtyTokenSecret.put(oAuthRealm, consumer.getTokenSecret());	
		
		_log.debug("setConsumer() completed successfully");
	}
	
	private OAuthConnection addOAuthConnection(String oAuthRealm)
			throws SystemException {
		
		OAuthConnection conn;
		_log.debug("**** creating new OAuthConnection for " 
				+ getCompanyId() + " " 
				+ getGroupId() + " " 
				+ getUserId() + " " 
				+ oAuthRealm);
		
		conn = OAuthConnectionLocalServiceUtil.addOAuthConnection(
				getCompanyId(), getGroupId(), getUserId(), oAuthRealm);
		return conn;
	}

	@Override
	public OAuthConsumer getOAuthConsumer(String oAuthRealm) throws ExpiredStateException {
		
		
		try {
			
			com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider provider = OAuthProviderLocalServiceUtil.getProviderForRealm(oAuthRealm);
			OAuthConnection conn = getOAuthConnection(oAuthRealm);			

			OAuthConsumer consumer = new DefaultOAuthConsumer(provider.getConsumerKey(), provider.getConsumerSecret());						
			
			if (conn.getToken() != null && conn.getToken().length() > 0)
				consumer.setTokenWithSecret(conn.getToken(), conn.getTokenSecret());
			
			return consumer; 
			
		} catch (NoSuchOAuthProviderException e) {
			
			e.printStackTrace();
			return null;
			
		} catch (SystemException e) {
			
			e.printStackTrace();
			return null;
			
		}
	}

	@Override
	public int getPhase(String oAuthRealm) throws ExpiredStateException {
		
		_log.debug("getPhase(\"" + oAuthRealm + "\")");
		
		try {
			
			OAuthConnection conn = getOAuthConnection(oAuthRealm);
			return conn.getPhase();
			
		} catch (SystemException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void setPhase(String oAuthRealm, int phase)
			throws ExpiredStateException {
		
		_log.debug("setPhase(\"" + oAuthRealm + "\", " + phase + ")");
		
		dirtyPhase.put(oAuthRealm, phase);
	}
	
	private OAuthConnection getOAuthConnection(String oAuthRealm) throws SystemException {
		
		try {
			
			return OAuthConnectionLocalServiceUtil.findByRealm(getCompanyId(), getGroupId(), getUserId(), oAuthRealm);
		
		} catch (NoSuchOAuthConnectionException e) {
				
				OAuthConnection conn = addOAuthConnection(oAuthRealm);
				return conn;

		}
	}
	
	@Override
	public void commitChanges(String oAuthRealm) {
		
		_log.debug("commitChanges(\"" + oAuthRealm + "\")");
		
		try {
			
			OAuthConnection conn = getOAuthConnection(oAuthRealm);
			
			if (dirtyPhase.get(oAuthRealm) != null) {
				_log.debug("Detected dirty phase: " + dirtyPhase.get(oAuthRealm).intValue());
				conn.setPhase(dirtyPhase.get(oAuthRealm).intValue());
				
				_log.debug("ServiceBuilder.getPhase() now returns " + conn.getPhase());
			}

			if (dirtyToken.get(oAuthRealm) != null) {
				_log.debug("Detected dirty token");
				conn.setToken(dirtyToken.get(oAuthRealm));
			}
			
			if (dirtyTokenSecret.get(oAuthRealm) != null) {
				_log.debug("Detected dirty token secret");
				conn.setTokenSecret(dirtyTokenSecret.get(oAuthRealm));
			}
			
			OAuthConnectionLocalServiceUtil.updateOAuthConnection(conn);

			if (dirtyPhase.get(oAuthRealm) != null)
				dirtyPhase.put(oAuthRealm, null);

			if (dirtyToken.get(oAuthRealm) != null)
				dirtyToken.put(oAuthRealm, null);
			
			if (dirtyTokenSecret.get(oAuthRealm) != null)
				dirtyTokenSecret.put(oAuthRealm, null);
			
		} catch (SystemException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		}

	}
	
	private static Log _log = LogFactoryUtil.getLog(PortalAuthedSessionOAuthStateImpl.class);
}
