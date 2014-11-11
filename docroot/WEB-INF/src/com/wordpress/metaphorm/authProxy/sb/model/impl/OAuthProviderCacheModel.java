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

import com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing OAuthProvider in entity cache.
 *
 * @author stiansigvartsen
 * @see OAuthProvider
 * @generated
 */
public class OAuthProviderCacheModel implements CacheModel<OAuthProvider>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{providerId=");
		sb.append(providerId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", consumerKey=");
		sb.append(consumerKey);
		sb.append(", consumerSecret=");
		sb.append(consumerSecret);
		sb.append(", realm=");
		sb.append(realm);
		sb.append(", requestTokenURL=");
		sb.append(requestTokenURL);
		sb.append(", authoriseURL=");
		sb.append(authoriseURL);
		sb.append(", accessTokenURL=");
		sb.append(accessTokenURL);
		sb.append(", protocolVersion=");
		sb.append(protocolVersion);
		sb.append(", preemptiveAuthURLPattern=");
		sb.append(preemptiveAuthURLPattern);
		sb.append(", useHTTPS=");
		sb.append(useHTTPS);
		sb.append("}");

		return sb.toString();
	}

	public OAuthProvider toEntityModel() {
		OAuthProviderImpl oAuthProviderImpl = new OAuthProviderImpl();

		oAuthProviderImpl.setProviderId(providerId);
		oAuthProviderImpl.setUserId(userId);

		if (userName == null) {
			oAuthProviderImpl.setUserName(StringPool.BLANK);
		}
		else {
			oAuthProviderImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			oAuthProviderImpl.setCreateDate(null);
		}
		else {
			oAuthProviderImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			oAuthProviderImpl.setModifiedDate(null);
		}
		else {
			oAuthProviderImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (consumerKey == null) {
			oAuthProviderImpl.setConsumerKey(StringPool.BLANK);
		}
		else {
			oAuthProviderImpl.setConsumerKey(consumerKey);
		}

		if (consumerSecret == null) {
			oAuthProviderImpl.setConsumerSecret(StringPool.BLANK);
		}
		else {
			oAuthProviderImpl.setConsumerSecret(consumerSecret);
		}

		if (realm == null) {
			oAuthProviderImpl.setRealm(StringPool.BLANK);
		}
		else {
			oAuthProviderImpl.setRealm(realm);
		}

		if (requestTokenURL == null) {
			oAuthProviderImpl.setRequestTokenURL(StringPool.BLANK);
		}
		else {
			oAuthProviderImpl.setRequestTokenURL(requestTokenURL);
		}

		if (authoriseURL == null) {
			oAuthProviderImpl.setAuthoriseURL(StringPool.BLANK);
		}
		else {
			oAuthProviderImpl.setAuthoriseURL(authoriseURL);
		}

		if (accessTokenURL == null) {
			oAuthProviderImpl.setAccessTokenURL(StringPool.BLANK);
		}
		else {
			oAuthProviderImpl.setAccessTokenURL(accessTokenURL);
		}

		if (protocolVersion == null) {
			oAuthProviderImpl.setProtocolVersion(StringPool.BLANK);
		}
		else {
			oAuthProviderImpl.setProtocolVersion(protocolVersion);
		}

		if (preemptiveAuthURLPattern == null) {
			oAuthProviderImpl.setPreemptiveAuthURLPattern(StringPool.BLANK);
		}
		else {
			oAuthProviderImpl.setPreemptiveAuthURLPattern(preemptiveAuthURLPattern);
		}

		oAuthProviderImpl.setUseHTTPS(useHTTPS);

		oAuthProviderImpl.resetOriginalValues();

		return oAuthProviderImpl;
	}

	public long providerId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String consumerKey;
	public String consumerSecret;
	public String realm;
	public String requestTokenURL;
	public String authoriseURL;
	public String accessTokenURL;
	public String protocolVersion;
	public String preemptiveAuthURLPattern;
	public boolean useHTTPS;
}