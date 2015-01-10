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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.wordpress.metaphorm.authProxy.state.ExpiredStateException;
import com.wordpress.metaphorm.authProxy.state.OAuthCredentials;
import com.wordpress.metaphorm.authProxy.state.OAuthState;

import javax.servlet.http.HttpSession;

/**
 * @author Stian Sigvartsen
 */
public class PortalGuestSessionOAuthStateImpl extends HttpSessionOAuthStateImpl implements OAuthState {

	private static final long serialVersionUID = 1L;
	
	private HttpSession httpSession;
	
	public PortalGuestSessionOAuthStateImpl(HttpSession session) throws ExpiredStateException {
		super(session);
		this.httpSession = session;
	}
	
	@Override
	public void setOAuthCredentials(String oAuthRealm, OAuthCredentials oAuthCredentials) throws ExpiredStateException {
		
		_log.debug("setOAuthCredentials() :: oAuthCredentials" + (oAuthCredentials == null ? " is null" : ".toString() = " + oAuthCredentials.toString()));
		httpSession.setAttribute("oAuthConsumer-" + oAuthRealm, oAuthCredentials);
	}

	@Override
	public OAuthCredentials getOAuthCredentials(String oAuthRealm) throws ExpiredStateException {
		
		try {
			
			OAuthCredentials oAuthCredentials = (OAuthCredentials)httpSession.getAttribute("oAuthConsumer-" + oAuthRealm);
			
			if (oAuthCredentials == null) 
				return super.getOAuthCredentials(oAuthRealm);
			
			return oAuthCredentials;
		
		} catch (ClassCastException e) { 
			
			// This occurs upon interaction with a HttpSession from a previous app deployment.
			// Probably because of AbstractOAuthConsumer not implementing Serializable
			
			return super.getOAuthCredentials(oAuthRealm);
		}
	}
	
	@Override
	public void setPAuth(String p_auth) throws ExpiredStateException {
		httpSession.setAttribute("p_auth", p_auth);
	}
	
	@Override
	public String getPAuth() throws ExpiredStateException {
		return (String)httpSession.getAttribute("p_auth");
	}	
	
	@Override
	public void setVerifier(String oAuthRealm, String verifier) throws ExpiredStateException {
		httpSession.setAttribute("oAuthConsumer-" + oAuthRealm + "-verifier", verifier);
	}	
	
	@Override
	public String getVerifier(String oAuthRealm) throws ExpiredStateException {
		return (String)httpSession.getAttribute("oAuthConsumer-" + oAuthRealm + "-verifier");
	}

	@Override
	public boolean isExpired() {
		
		try {
			httpSession.getCreationTime();
			return false;
		} catch (IllegalStateException ise) {
			return true;
		}
	}

	@Override
	public void setPhase(String oAuthRealm, int phase) throws ExpiredStateException {
		httpSession.setAttribute("oAuthConsumer-" + oAuthRealm + "-phase", Integer.valueOf(phase));
	}
	
	@Override
	public int getPhase(String oAuthRealm) throws ExpiredStateException {
		
		try {
			return ((Integer)httpSession.getAttribute("oAuthConsumer-" + oAuthRealm + "-phase")).intValue();
		} catch (Exception e) {
			return OAuthState.REQUEST_PHASE;
		}
	}

	@Override
	public void commitChanges(String oAuthRealm) {
	}
	
	private static Log _log = LogFactoryUtil.getLog(PortalGuestSessionOAuthStateImpl.class);
}
