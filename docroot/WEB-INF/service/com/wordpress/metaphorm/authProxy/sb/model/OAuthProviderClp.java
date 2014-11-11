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

package com.wordpress.metaphorm.authProxy.sb.model;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.wordpress.metaphorm.authProxy.sb.service.OAuthProviderLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author stiansigvartsen
 */
public class OAuthProviderClp extends BaseModelImpl<OAuthProvider>
	implements OAuthProvider {
	public OAuthProviderClp() {
	}

	public Class<?> getModelClass() {
		return OAuthProvider.class;
	}

	public String getModelClassName() {
		return OAuthProvider.class.getName();
	}

	public long getPrimaryKey() {
		return _providerId;
	}

	public void setPrimaryKey(long primaryKey) {
		setProviderId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_providerId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("providerId", getProviderId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("consumerKey", getConsumerKey());
		attributes.put("consumerSecret", getConsumerSecret());
		attributes.put("realm", getRealm());
		attributes.put("requestTokenURL", getRequestTokenURL());
		attributes.put("authoriseURL", getAuthoriseURL());
		attributes.put("accessTokenURL", getAccessTokenURL());
		attributes.put("protocolVersion", getProtocolVersion());
		attributes.put("preemptiveAuthURLPattern", getPreemptiveAuthURLPattern());
		attributes.put("useHTTPS", getUseHTTPS());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long providerId = (Long)attributes.get("providerId");

		if (providerId != null) {
			setProviderId(providerId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String consumerKey = (String)attributes.get("consumerKey");

		if (consumerKey != null) {
			setConsumerKey(consumerKey);
		}

		String consumerSecret = (String)attributes.get("consumerSecret");

		if (consumerSecret != null) {
			setConsumerSecret(consumerSecret);
		}

		String realm = (String)attributes.get("realm");

		if (realm != null) {
			setRealm(realm);
		}

		String requestTokenURL = (String)attributes.get("requestTokenURL");

		if (requestTokenURL != null) {
			setRequestTokenURL(requestTokenURL);
		}

		String authoriseURL = (String)attributes.get("authoriseURL");

		if (authoriseURL != null) {
			setAuthoriseURL(authoriseURL);
		}

		String accessTokenURL = (String)attributes.get("accessTokenURL");

		if (accessTokenURL != null) {
			setAccessTokenURL(accessTokenURL);
		}

		String protocolVersion = (String)attributes.get("protocolVersion");

		if (protocolVersion != null) {
			setProtocolVersion(protocolVersion);
		}

		String preemptiveAuthURLPattern = (String)attributes.get(
				"preemptiveAuthURLPattern");

		if (preemptiveAuthURLPattern != null) {
			setPreemptiveAuthURLPattern(preemptiveAuthURLPattern);
		}

		Boolean useHTTPS = (Boolean)attributes.get("useHTTPS");

		if (useHTTPS != null) {
			setUseHTTPS(useHTTPS);
		}
	}

	public long getProviderId() {
		return _providerId;
	}

	public void setProviderId(long providerId) {
		_providerId = providerId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getConsumerKey() {
		return _consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		_consumerKey = consumerKey;
	}

	public String getConsumerSecret() {
		return _consumerSecret;
	}

	public void setConsumerSecret(String consumerSecret) {
		_consumerSecret = consumerSecret;
	}

	public String getRealm() {
		return _realm;
	}

	public void setRealm(String realm) {
		_realm = realm;
	}

	public String getRequestTokenURL() {
		return _requestTokenURL;
	}

	public void setRequestTokenURL(String requestTokenURL) {
		_requestTokenURL = requestTokenURL;
	}

	public String getAuthoriseURL() {
		return _authoriseURL;
	}

	public void setAuthoriseURL(String authoriseURL) {
		_authoriseURL = authoriseURL;
	}

	public String getAccessTokenURL() {
		return _accessTokenURL;
	}

	public void setAccessTokenURL(String accessTokenURL) {
		_accessTokenURL = accessTokenURL;
	}

	public String getProtocolVersion() {
		return _protocolVersion;
	}

	public void setProtocolVersion(String protocolVersion) {
		_protocolVersion = protocolVersion;
	}

	public String getPreemptiveAuthURLPattern() {
		return _preemptiveAuthURLPattern;
	}

	public void setPreemptiveAuthURLPattern(String preemptiveAuthURLPattern) {
		_preemptiveAuthURLPattern = preemptiveAuthURLPattern;
	}

	public boolean getUseHTTPS() {
		return _useHTTPS;
	}

	public boolean isUseHTTPS() {
		return _useHTTPS;
	}

	public void setUseHTTPS(boolean useHTTPS) {
		_useHTTPS = useHTTPS;
	}

	public BaseModel<?> getOAuthProviderRemoteModel() {
		return _oAuthProviderRemoteModel;
	}

	public void setOAuthProviderRemoteModel(
		BaseModel<?> oAuthProviderRemoteModel) {
		_oAuthProviderRemoteModel = oAuthProviderRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			OAuthProviderLocalServiceUtil.addOAuthProvider(this);
		}
		else {
			OAuthProviderLocalServiceUtil.updateOAuthProvider(this);
		}
	}

	@Override
	public OAuthProvider toEscapedModel() {
		return (OAuthProvider)Proxy.newProxyInstance(OAuthProvider.class.getClassLoader(),
			new Class[] { OAuthProvider.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		OAuthProviderClp clone = new OAuthProviderClp();

		clone.setProviderId(getProviderId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setConsumerKey(getConsumerKey());
		clone.setConsumerSecret(getConsumerSecret());
		clone.setRealm(getRealm());
		clone.setRequestTokenURL(getRequestTokenURL());
		clone.setAuthoriseURL(getAuthoriseURL());
		clone.setAccessTokenURL(getAccessTokenURL());
		clone.setProtocolVersion(getProtocolVersion());
		clone.setPreemptiveAuthURLPattern(getPreemptiveAuthURLPattern());
		clone.setUseHTTPS(getUseHTTPS());

		return clone;
	}

	public int compareTo(OAuthProvider oAuthProvider) {
		int value = 0;

		value = getRealm().compareTo(oAuthProvider.getRealm());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		OAuthProviderClp oAuthProvider = null;

		try {
			oAuthProvider = (OAuthProviderClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = oAuthProvider.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{providerId=");
		sb.append(getProviderId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", consumerKey=");
		sb.append(getConsumerKey());
		sb.append(", consumerSecret=");
		sb.append(getConsumerSecret());
		sb.append(", realm=");
		sb.append(getRealm());
		sb.append(", requestTokenURL=");
		sb.append(getRequestTokenURL());
		sb.append(", authoriseURL=");
		sb.append(getAuthoriseURL());
		sb.append(", accessTokenURL=");
		sb.append(getAccessTokenURL());
		sb.append(", protocolVersion=");
		sb.append(getProtocolVersion());
		sb.append(", preemptiveAuthURLPattern=");
		sb.append(getPreemptiveAuthURLPattern());
		sb.append(", useHTTPS=");
		sb.append(getUseHTTPS());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append("com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>providerId</column-name><column-value><![CDATA[");
		sb.append(getProviderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>consumerKey</column-name><column-value><![CDATA[");
		sb.append(getConsumerKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>consumerSecret</column-name><column-value><![CDATA[");
		sb.append(getConsumerSecret());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>realm</column-name><column-value><![CDATA[");
		sb.append(getRealm());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>requestTokenURL</column-name><column-value><![CDATA[");
		sb.append(getRequestTokenURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>authoriseURL</column-name><column-value><![CDATA[");
		sb.append(getAuthoriseURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>accessTokenURL</column-name><column-value><![CDATA[");
		sb.append(getAccessTokenURL());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>protocolVersion</column-name><column-value><![CDATA[");
		sb.append(getProtocolVersion());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>preemptiveAuthURLPattern</column-name><column-value><![CDATA[");
		sb.append(getPreemptiveAuthURLPattern());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>useHTTPS</column-name><column-value><![CDATA[");
		sb.append(getUseHTTPS());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _providerId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _consumerKey;
	private String _consumerSecret;
	private String _realm;
	private String _requestTokenURL;
	private String _authoriseURL;
	private String _accessTokenURL;
	private String _protocolVersion;
	private String _preemptiveAuthURLPattern;
	private boolean _useHTTPS;
	private BaseModel<?> _oAuthProviderRemoteModel;
}