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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.util.PortalUtil;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author Stian Sigvartsen
 */
public class UserToken {

	public static String secret;
	
	public String intendedFor;
	public long userId;
	public long scopeGroupId;
	public long companyId;
	public String sessionIdHashed;
	public String hash;
	public String p_auth;
	
	public static UserToken constructFromHttpServletRequest(HttpServletRequest servletReq) throws PortalException, SystemException {
		
		UserToken userToken = new UserToken();
		
		String sessionId = servletReq.getSession().getId();
		userToken.sessionIdHashed = DigestUtils.sha1Hex(sessionId);
		
		userToken.userId = PortalUtil.getUserId(servletReq);
		userToken.scopeGroupId = PortalUtil.getScopeGroupId(servletReq);
		userToken.companyId = PortalUtil.getCompanyId(servletReq);
		String unhashed = "PORTLET:" + userToken.companyId + ":" + userToken.scopeGroupId + ":" + userToken.userId + ":" + userToken.sessionIdHashed; // + ":" + secret;
		userToken.hash = DigestUtils.sha1Hex(unhashed + ":" + secret);
		
		userToken.p_auth = "";
		
		return userToken;
	}
	
	public static UserToken parseFromString(String userTokenStr) {
		
		UserToken userToken = new UserToken();
		
		String[] userTokenParts = userTokenStr.split(":");
		userToken.intendedFor = userTokenParts[0];
		userToken.companyId = Long.parseLong(userTokenParts[1]);
		userToken.scopeGroupId = Long.parseLong(userTokenParts[2]);
		userToken.userId = Long.parseLong(userTokenParts[3]);
		userToken.sessionIdHashed = userTokenParts[4];
		userToken.hash = userTokenParts[5];
		
		if (userTokenParts.length > 6) {
			userToken.p_auth = userTokenParts[6];
		} else {
			userToken.p_auth = "";	
		}
		
		return userToken;
	}
	
	public boolean isUntampedWith() {
		
		String unhashed = this.intendedFor + ":" + this.companyId + ":" + this.scopeGroupId + ":" + this.userId + ":" + this.sessionIdHashed;		
		String reHash = DigestUtils.sha1Hex(unhashed + ":" + secret);
		return (reHash.equals(this.hash));
	}
	
	@Override
	public String toString() {
		String unhashed = "PORTLET:" + companyId + ":" + scopeGroupId + ":" + userId + ":" + sessionIdHashed; // + ":" + secret;
		String hash = DigestUtils.sha1Hex(unhashed + ":" + secret);
		return unhashed + ":" + hash;
	}
	
	@Override
	public boolean equals(Object userToken2) {		
		return this.toString().equals(userToken2.toString());
	}
}
