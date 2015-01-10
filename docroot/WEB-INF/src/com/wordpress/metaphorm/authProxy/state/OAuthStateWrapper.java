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

import java.util.List;

/**
 * @author Stian Sigvartsen
 */
public class OAuthStateWrapper implements OAuthState {

	private static final long serialVersionUID = 1L;

	OAuthState wrappedState;
	
	public OAuthStateWrapper(OAuthState oAuthState) {
		this.wrappedState = oAuthState;
	}
	
	public void setOAuthCredentials(String oAuthRealm, OAuthCredentials oAuthCredentials)
			throws ExpiredStateException {
		this.wrappedState.setOAuthCredentials(oAuthRealm, oAuthCredentials);
	}

	public OAuthCredentials getOAuthCredentials(String oAuthRealm)
			throws ExpiredStateException {
		return this.wrappedState.getOAuthCredentials(oAuthRealm);
	}

	public void setPAuth(String p_auth) throws ExpiredStateException {
		this.wrappedState.setPAuth(p_auth);
	}

	public String getPAuth() throws ExpiredStateException {
		return this.wrappedState.getPAuth();
	}

	public void setVerifier(String oAuthRealm, String verifier)
			throws ExpiredStateException {
		this.wrappedState.setVerifier(oAuthRealm, verifier);
	}

	public String getVerifier(String oAuthRealm) throws ExpiredStateException {
		return this.wrappedState.getVerifier(oAuthRealm);
	}

	public void setPhase(String oAuthRealm, int phase)
			throws ExpiredStateException {
		this.wrappedState.setPhase(oAuthRealm, phase);
	}

	public int getPhase(String oAuthRealm) throws ExpiredStateException {
		return this.wrappedState.getPhase(oAuthRealm);
	}

	public boolean isExpired() {
		return this.wrappedState.isExpired();
	}

	public void commitChanges(String oAuthRealm) {
		this.wrappedState.commitChanges(oAuthRealm);
	}

	@Override
	public void registerDependencyListener(DependencyListener listener) {
		this.wrappedState.registerDependencyListener(listener);
	}
	
	@Override
	public List<DependencyListener> getDependencyListeners() {
		return this.wrappedState.getDependencyListeners();
	}

	@Override
	public void invalidate() {
		this.wrappedState.invalidate();
	}	
}
