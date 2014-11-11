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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    stiansigvartsen
 * @generated
 */
public class OAuthConnectionSoap implements Serializable {
	public static OAuthConnectionSoap toSoapModel(OAuthConnection model) {
		OAuthConnectionSoap soapModel = new OAuthConnectionSoap();

		soapModel.setConnectionId(model.getConnectionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setToken(model.getToken());
		soapModel.setTokenSecret(model.getTokenSecret());
		soapModel.setRealm(model.getRealm());
		soapModel.setPhase(model.getPhase());

		return soapModel;
	}

	public static OAuthConnectionSoap[] toSoapModels(OAuthConnection[] models) {
		OAuthConnectionSoap[] soapModels = new OAuthConnectionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OAuthConnectionSoap[][] toSoapModels(
		OAuthConnection[][] models) {
		OAuthConnectionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OAuthConnectionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OAuthConnectionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OAuthConnectionSoap[] toSoapModels(
		List<OAuthConnection> models) {
		List<OAuthConnectionSoap> soapModels = new ArrayList<OAuthConnectionSoap>(models.size());

		for (OAuthConnection model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OAuthConnectionSoap[soapModels.size()]);
	}

	public OAuthConnectionSoap() {
	}

	public long getPrimaryKey() {
		return _connectionId;
	}

	public void setPrimaryKey(long pk) {
		setConnectionId(pk);
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

	private long _connectionId;
	private long _companyId;
	private long _groupId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _token;
	private String _tokenSecret;
	private String _realm;
	private int _phase;
}