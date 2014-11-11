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

import com.wordpress.metaphorm.authProxy.state.ExpiredStateException;
import com.wordpress.metaphorm.authProxy.state.OAuthState;

import oauth.signpost.OAuthConsumer;

/**
 * @author Stian Sigvartsen
 */
public abstract class AbstractOAuthStateImpl implements OAuthState {
	
	private static final long serialVersionUID = 1L;

	public abstract void setConsumer(String oAuthRealm, OAuthConsumer consumer) throws ExpiredStateException;
	
	public OAuthConsumer getOAuthConsumer(String oAuthRealm) throws ExpiredStateException {
		
		OAuthConsumer consumer = null;
		setConsumer(oAuthRealm, consumer);
		
		return consumer;
	}

	public abstract void setPAuth(String p_auth) throws ExpiredStateException;
	
	public abstract String getPAuth() throws ExpiredStateException;
	
	public abstract void setVerifier(String oAuthRealm, String verifier) throws ExpiredStateException;

	public abstract String getVerifier(String oAuthRealm) throws ExpiredStateException;

	public abstract void setPhase(String oAuthRealm, int phase) throws ExpiredStateException;

	public abstract int getPhase(String oAuthRealm) throws ExpiredStateException;

	public abstract boolean isExpired();	
	
	public abstract void commitChanges(String oAuthRealm);
}
