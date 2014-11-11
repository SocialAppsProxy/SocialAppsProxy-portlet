/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.wordpress.metaphorm.authProxy.sb.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing OAuthConnection in entity cache.
 *
 * @author stiansigvartsen
 * @see OAuthConnection
 * @generated
 */
public class OAuthConnectionCacheModel implements CacheModel<OAuthConnection>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(23);

		sb.append("{connectionId=");
		sb.append(connectionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", token=");
		sb.append(token);
		sb.append(", tokenSecret=");
		sb.append(tokenSecret);
		sb.append(", realm=");
		sb.append(realm);
		sb.append(", phase=");
		sb.append(phase);
		sb.append("}");

		return sb.toString();
	}

	public OAuthConnection toEntityModel() {
		OAuthConnectionImpl oAuthConnectionImpl = new OAuthConnectionImpl();

		oAuthConnectionImpl.setConnectionId(connectionId);
		oAuthConnectionImpl.setCompanyId(companyId);
		oAuthConnectionImpl.setGroupId(groupId);
		oAuthConnectionImpl.setUserId(userId);

		if (userName == null) {
			oAuthConnectionImpl.setUserName(StringPool.BLANK);
		}
		else {
			oAuthConnectionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			oAuthConnectionImpl.setCreateDate(null);
		}
		else {
			oAuthConnectionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			oAuthConnectionImpl.setModifiedDate(null);
		}
		else {
			oAuthConnectionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (token == null) {
			oAuthConnectionImpl.setToken(StringPool.BLANK);
		}
		else {
			oAuthConnectionImpl.setToken(token);
		}

		if (tokenSecret == null) {
			oAuthConnectionImpl.setTokenSecret(StringPool.BLANK);
		}
		else {
			oAuthConnectionImpl.setTokenSecret(tokenSecret);
		}

		if (realm == null) {
			oAuthConnectionImpl.setRealm(StringPool.BLANK);
		}
		else {
			oAuthConnectionImpl.setRealm(realm);
		}

		oAuthConnectionImpl.setPhase(phase);

		oAuthConnectionImpl.resetOriginalValues();

		return oAuthConnectionImpl;
	}

	public long connectionId;
	public long companyId;
	public long groupId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String token;
	public String tokenSecret;
	public String realm;
	public int phase;
}