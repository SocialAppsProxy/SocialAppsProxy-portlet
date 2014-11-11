package com.wordpress.metaphorm.authProxy.hook;

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

import com.liferay.portal.kernel.events.SessionAction;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.wordpress.metaphorm.authProxy.state.DependencyListener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * @author Stian Sigvartsen
 */
public class SessionDestroyAction extends SessionAction {

	private static Map<String, DependencyListener> dependencyListeners;
	
	public static DependencyListener getDependencyListener(String sessionId) {
		
		_log.debug("Getting DependencyListeners for HTTP session " + sessionId);
		
		if (dependencyListeners == null) dependencyListeners = new HashMap<String, DependencyListener>();
		return dependencyListeners.get(sessionId);
	}
	
	public static void registerDependencyListener(String sessionId, DependencyListener listener) {
	
		_log.debug("Registering DependencyListener for HTTP session " + sessionId);
		
		if (dependencyListeners == null) dependencyListeners = new HashMap<String, DependencyListener>();
		dependencyListeners.put(sessionId, listener);
	}
	
	public static void clearRegisteredDependencyListeners(String sessionId) {
		
		_log.debug("Clearing all DependencyListeners for HTTP session " + sessionId);
		if (dependencyListeners != null) dependencyListeners.remove(sessionId);
	}
	
	@Override
	public void run(HttpSession session) {
		
		_log.debug("Session destroyed: " + session.getId() + " (last accessed " + ((System.currentTimeMillis() - session.getLastAccessedTime()) / 1000) + " seconds ago)");
		
		DependencyListener listener = getDependencyListener(session.getId());
		if (listener != null) {
			_log.debug("Found DependencyListener for HTTP session " + session.getId());
			listener.dependencyExpired();
		} else {
			_log.debug("No DependencyListener found for HTTP session " + session.getId());
		}
		
		_log.debug("There are " + (dependencyListeners != null ? dependencyListeners.size() : 0) + " DependencyListeners registered");
		for (String sessionId : dependencyListeners.keySet()) {
			_log.debug("  " + sessionId);
		}

	}

	private static final Log _log = LogFactoryUtil.getLog(SessionDestroyAction.class);
}