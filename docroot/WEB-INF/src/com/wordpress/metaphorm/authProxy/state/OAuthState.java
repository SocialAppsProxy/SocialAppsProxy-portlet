package com.wordpress.metaphorm.authProxy.state;

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

import java.io.Serializable;
import java.util.List;

import oauth.signpost.OAuthConsumer;

/**
 * @author Stian Sigvartsen
 */
public interface OAuthState extends Serializable {

	public static final int REQUEST_PHASE = 0;
	public static final int AUTHORISE_PHASE = 1;
	public static final int RESOURCE_PHASE = 2;
	
	public void setConsumer(String oAuthRealm, OAuthConsumer consumer) throws ExpiredStateException;
	public OAuthConsumer getOAuthConsumer(String oAuthRealm) throws ExpiredStateException;
		
	public void setPAuth(String p_auth) throws ExpiredStateException;	
	public String getPAuth() throws ExpiredStateException;
	
	public void setVerifier(String oAuthRealm, String verifier) throws ExpiredStateException;
	public String getVerifier(String oAuthRealm) throws ExpiredStateException;

	public void setPhase(String oAuthRealm, int phase) throws ExpiredStateException;
	public int getPhase(String oAuthRealm) throws ExpiredStateException;
	
	public boolean isExpired();
	
	public void commitChanges(String oAuthRealm);
	
	public void registerDependencyListener(DependencyListener listener);
	public List<DependencyListener> getDependencyListeners();
	
	public void invalidate();
}
