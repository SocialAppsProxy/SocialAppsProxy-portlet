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
public class OAuthProviderSoap implements Serializable {
	public static OAuthProviderSoap toSoapModel(OAuthProvider model) {
		OAuthProviderSoap soapModel = new OAuthProviderSoap();

		soapModel.setProviderId(model.getProviderId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setConsumerKey(model.getConsumerKey());
		soapModel.setConsumerSecret(model.getConsumerSecret());
		soapModel.setRealm(model.getRealm());
		soapModel.setRequestTokenURL(model.getRequestTokenURL());
		soapModel.setAuthoriseURL(model.getAuthoriseURL());
		soapModel.setAccessTokenURL(model.getAccessTokenURL());
		soapModel.setProtocolVersion(model.getProtocolVersion());
		soapModel.setPreemptiveAuthURLPattern(model.getPreemptiveAuthURLPattern());
		soapModel.setUseHTTPS(model.getUseHTTPS());

		return soapModel;
	}

	public static OAuthProviderSoap[] toSoapModels(OAuthProvider[] models) {
		OAuthProviderSoap[] soapModels = new OAuthProviderSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OAuthProviderSoap[][] toSoapModels(OAuthProvider[][] models) {
		OAuthProviderSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OAuthProviderSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OAuthProviderSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OAuthProviderSoap[] toSoapModels(List<OAuthProvider> models) {
		List<OAuthProviderSoap> soapModels = new ArrayList<OAuthProviderSoap>(models.size());

		for (OAuthProvider model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OAuthProviderSoap[soapModels.size()]);
	}

	public OAuthProviderSoap() {
	}

	public long getPrimaryKey() {
		return _providerId;
	}

	public void setPrimaryKey(long pk) {
		setProviderId(pk);
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

	private long _providerId;
	private long _userId;
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
}