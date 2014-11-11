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

import com.wordpress.metaphorm.authProxy.sb.service.OAuthConnectionLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author stiansigvartsen
 */
public class OAuthConnectionClp extends BaseModelImpl<OAuthConnection>
	implements OAuthConnection {
	public OAuthConnectionClp() {
	}

	public Class<?> getModelClass() {
		return OAuthConnection.class;
	}

	public String getModelClassName() {
		return OAuthConnection.class.getName();
	}

	public long getPrimaryKey() {
		return _connectionId;
	}

	public void setPrimaryKey(long primaryKey) {
		setConnectionId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_connectionId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("connectionId", getConnectionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("token", getToken());
		attributes.put("tokenSecret", getTokenSecret());
		attributes.put("realm", getRealm());
		attributes.put("phase", getPhase());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long connectionId = (Long)attributes.get("connectionId");

		if (connectionId != null) {
			setConnectionId(connectionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
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

		String token = (String)attributes.get("token");

		if (token != null) {
			setToken(token);
		}

		String tokenSecret = (String)attributes.get("tokenSecret");

		if (tokenSecret != null) {
			setTokenSecret(tokenSecret);
		}

		String realm = (String)attributes.get("realm");

		if (realm != null) {
			setRealm(realm);
		}

		Integer phase = (Integer)attributes.get("phase");

		if (phase != null) {
			setPhase(phase);
		}
	}

	public long getConnectionId() {
		return _connectionId;
	}

	public void setConnectionId(long connectionId) {
		_connectionId = connectionId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
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

	public String getToken() {
		return _token;
	}

	public void setToken(String token) {
		_token = token;
	}

	public String getTokenSecret() {
		return _tokenSecret;
	}

	public void setTokenSecret(String tokenSecret) {
		_tokenSecret = tokenSecret;
	}

	public String getRealm() {
		return _realm;
	}

	public void setRealm(String realm) {
		_realm = realm;
	}

	public int getPhase() {
		return _phase;
	}

	public void setPhase(int phase) {
		_phase = phase;
	}

	public BaseModel<?> getOAuthConnectionRemoteModel() {
		return _oAuthConnectionRemoteModel;
	}

	public void setOAuthConnectionRemoteModel(
		BaseModel<?> oAuthConnectionRemoteModel) {
		_oAuthConnectionRemoteModel = oAuthConnectionRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			OAuthConnectionLocalServiceUtil.addOAuthConnection(this);
		}
		else {
			OAuthConnectionLocalServiceUtil.updateOAuthConnection(this);
		}
	}

	@Override
	public OAuthConnection toEscapedModel() {
		return (OAuthConnection)Proxy.newProxyInstance(OAuthConnection.class.getClassLoader(),
			new Class[] { OAuthConnection.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		OAuthConnectionClp clone = new OAuthConnectionClp();

		clone.setConnectionId(getConnectionId());
		clone.setCompanyId(getCompanyId());
		clone.setGroupId(getGroupId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setToken(getToken());
		clone.setTokenSecret(getTokenSecret());
		clone.setRealm(getRealm());
		clone.setPhase(getPhase());

		return clone;
	}

	public int compareTo(OAuthConnection oAuthConnection) {
		int value = 0;

		value = getRealm().compareTo(oAuthConnection.getRealm());

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

		OAuthConnectionClp oAuthConnection = null;

		try {
			oAuthConnection = (OAuthConnectionClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = oAuthConnection.getPrimaryKey();

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
		StringBundler sb = new StringBundler(23);

		sb.append("{connectionId=");
		sb.append(getConnectionId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", token=");
		sb.append(getToken());
		sb.append(", tokenSecret=");
		sb.append(getTokenSecret());
		sb.append(", realm=");
		sb.append(getRealm());
		sb.append(", phase=");
		sb.append(getPhase());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(37);

		sb.append("<model><model-name>");
		sb.append("com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>connectionId</column-name><column-value><![CDATA[");
		sb.append(getConnectionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
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
			"<column><column-name>token</column-name><column-value><![CDATA[");
		sb.append(getToken());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>tokenSecret</column-name><column-value><![CDATA[");
		sb.append(getTokenSecret());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>realm</column-name><column-value><![CDATA[");
		sb.append(getRealm());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>phase</column-name><column-value><![CDATA[");
		sb.append(getPhase());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _connectionId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userUuid;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _token;
	private String _tokenSecret;
	private String _realm;
	private int _phase;
	private BaseModel<?> _oAuthConnectionRemoteModel;
}