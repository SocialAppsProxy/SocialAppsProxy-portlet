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
import com.wordpress.metaphorm.authProxy.hook.SessionDestroyAction;
import com.wordpress.metaphorm.authProxy.state.DependencyListener;
import com.wordpress.metaphorm.authProxy.state.ExpiredStateException;
import com.wordpress.metaphorm.authProxy.state.OAuthCredentials;
import com.wordpress.metaphorm.authProxy.state.OAuthState;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

/**
 * @author Stian Sigvartsen
 */
public class HttpSessionOAuthStateImpl extends AbstractOAuthStateImpl implements OAuthState {

	private static final long serialVersionUID = 1L;
	
	private HttpSession httpSession;
	
	public HttpSessionOAuthStateImpl(HttpSession session) throws ExpiredStateException {
		this.httpSession = session;
		_log.debug("Constructing HttpSessionOAuthStateImpl for session " + session.getId());
	}
	
	public void registerDependencyListener(DependencyListener listener) {
		
		SessionDestroyAction.registerDependencyListener(httpSession.getId(), listener);
	}
	
	@Override
	public List<DependencyListener> getDependencyListeners() {
		return new LinkedList<DependencyListener>(Arrays.asList(SessionDestroyAction.getDependencyListener(httpSession.getId())));
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
	
	/**
	 * Call to invalidate the OAuthState prematurely (ahead of HttpSession dependency expiry)
	 */
	@Override
	public void invalidate() {
		
		DependencyListener dependencyListener = SessionDestroyAction.getDependencyListener(httpSession.getId());
		
		// Notify the listeners
		dependencyListener.dependencyExpired();
		
		// Only manually remove the required attributes if the HttpSession hasn't already expired
		if (!isExpired()) {
		
			Set<String> clearAttrSet = new HashSet<String>();
			
			Enumeration<String> attrEnum = httpSession.getAttributeNames();
			while (attrEnum.hasMoreElements()) {
				String attr = attrEnum.nextElement();
				
				if (attr.startsWith("oAuthConsumer-")) {
					clearAttrSet.add(attr);
				}
			}
			clearAttrSet.add("p_auth");
	
			for (String attr : clearAttrSet) {
				httpSession.removeAttribute(attr);
			}
		}
		
		// Unregister this instance as listener to HTTP Session. The HttpSession will expire by other means.
		SessionDestroyAction.clearRegisteredDependencyListeners(httpSession.getId());
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
	
	private static final Log _log = LogFactoryUtil.getLog(HttpSessionOAuthStateImpl.class);
}
