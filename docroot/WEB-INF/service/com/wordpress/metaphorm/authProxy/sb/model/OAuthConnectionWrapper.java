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

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link OAuthConnection}.
 * </p>
 *
 * @author    stiansigvartsen
 * @see       OAuthConnection
 * @generated
 */
public class OAuthConnectionWrapper implements OAuthConnection,
	ModelWrapper<OAuthConnection> {
	public OAuthConnectionWrapper(OAuthConnection oAuthConnection) {
		_oAuthConnection = oAuthConnection;
	}

	public Class<?> getModelClass() {
		return OAuthConnection.class;
	}

	public String getModelClassName() {
		return OAuthConnection.class.getName();
	}

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

	/**
	* Returns the primary key of this o auth connection.
	*
	* @return the primary key of this o auth connection
	*/
	public long getPrimaryKey() {
		return _oAuthConnection.getPrimaryKey();
	}

	/**
	* Sets the primary key of this o auth connection.
	*
	* @param primaryKey the primary key of this o auth connection
	*/
	public void setPrimaryKey(long primaryKey) {
		_oAuthConnection.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the connection ID of this o auth connection.
	*
	* @return the connection ID of this o auth connection
	*/
	public long getConnectionId() {
		return _oAuthConnection.getConnectionId();
	}

	/**
	* Sets the connection ID of this o auth connection.
	*
	* @param connectionId the connection ID of this o auth connection
	*/
	public void setConnectionId(long connectionId) {
		_oAuthConnection.setConnectionId(connectionId);
	}

	/**
	* Returns the company ID of this o auth connection.
	*
	* @return the company ID of this o auth connection
	*/
	public long getCompanyId() {
		return _oAuthConnection.getCompanyId();
	}

	/**
	* Sets the company ID of this o auth connection.
	*
	* @param companyId the company ID of this o auth connection
	*/
	public void setCompanyId(long companyId) {
		_oAuthConnection.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this o auth connection.
	*
	* @return the group ID of this o auth connection
	*/
	public long getGroupId() {
		return _oAuthConnection.getGroupId();
	}

	/**
	* Sets the group ID of this o auth connection.
	*
	* @param groupId the group ID of this o auth connection
	*/
	public void setGroupId(long groupId) {
		_oAuthConnection.setGroupId(groupId);
	}

	/**
	* Returns the user ID of this o auth connection.
	*
	* @return the user ID of this o auth connection
	*/
	public long getUserId() {
		return _oAuthConnection.getUserId();
	}

	/**
	* Sets the user ID of this o auth connection.
	*
	* @param userId the user ID of this o auth connection
	*/
	public void setUserId(long userId) {
		_oAuthConnection.setUserId(userId);
	}

	/**
	* Returns the user uuid of this o auth connection.
	*
	* @return the user uuid of this o auth connection
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthConnection.getUserUuid();
	}

	/**
	* Sets the user uuid of this o auth connection.
	*
	* @param userUuid the user uuid of this o auth connection
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_oAuthConnection.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this o auth connection.
	*
	* @return the user name of this o auth connection
	*/
	public java.lang.String getUserName() {
		return _oAuthConnection.getUserName();
	}

	/**
	* Sets the user name of this o auth connection.
	*
	* @param userName the user name of this o auth connection
	*/
	public void setUserName(java.lang.String userName) {
		_oAuthConnection.setUserName(userName);
	}

	/**
	* Returns the create date of this o auth connection.
	*
	* @return the create date of this o auth connection
	*/
	public java.util.Date getCreateDate() {
		return _oAuthConnection.getCreateDate();
	}

	/**
	* Sets the create date of this o auth connection.
	*
	* @param createDate the create date of this o auth connection
	*/
	public void setCreateDate(java.util.Date createDate) {
		_oAuthConnection.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this o auth connection.
	*
	* @return the modified date of this o auth connection
	*/
	public java.util.Date getModifiedDate() {
		return _oAuthConnection.getModifiedDate();
	}

	/**
	* Sets the modified date of this o auth connection.
	*
	* @param modifiedDate the modified date of this o auth connection
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_oAuthConnection.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the token of this o auth connection.
	*
	* @return the token of this o auth connection
	*/
	public java.lang.String getToken() {
		return _oAuthConnection.getToken();
	}

	/**
	* Sets the token of this o auth connection.
	*
	* @param token the token of this o auth connection
	*/
	public void setToken(java.lang.String token) {
		_oAuthConnection.setToken(token);
	}

	/**
	* Returns the token secret of this o auth connection.
	*
	* @return the token secret of this o auth connection
	*/
	public java.lang.String getTokenSecret() {
		return _oAuthConnection.getTokenSecret();
	}

	/**
	* Sets the token secret of this o auth connection.
	*
	* @param tokenSecret the token secret of this o auth connection
	*/
	public void setTokenSecret(java.lang.String tokenSecret) {
		_oAuthConnection.setTokenSecret(tokenSecret);
	}

	/**
	* Returns the realm of this o auth connection.
	*
	* @return the realm of this o auth connection
	*/
	public java.lang.String getRealm() {
		return _oAuthConnection.getRealm();
	}

	/**
	* Sets the realm of this o auth connection.
	*
	* @param realm the realm of this o auth connection
	*/
	public void setRealm(java.lang.String realm) {
		_oAuthConnection.setRealm(realm);
	}

	/**
	* Returns the phase of this o auth connection.
	*
	* @return the phase of this o auth connection
	*/
	public int getPhase() {
		return _oAuthConnection.getPhase();
	}

	/**
	* Sets the phase of this o auth connection.
	*
	* @param phase the phase of this o auth connection
	*/
	public void setPhase(int phase) {
		_oAuthConnection.setPhase(phase);
	}

	public boolean isNew() {
		return _oAuthConnection.isNew();
	}

	public void setNew(boolean n) {
		_oAuthConnection.setNew(n);
	}

	public boolean isCachedModel() {
		return _oAuthConnection.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_oAuthConnection.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _oAuthConnection.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _oAuthConnection.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_oAuthConnection.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _oAuthConnection.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_oAuthConnection.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new OAuthConnectionWrapper((OAuthConnection)_oAuthConnection.clone());
	}

	public int compareTo(OAuthConnection oAuthConnection) {
		return _oAuthConnection.compareTo(oAuthConnection);
	}

	@Override
	public int hashCode() {
		return _oAuthConnection.hashCode();
	}

	public com.liferay.portal.model.CacheModel<OAuthConnection> toCacheModel() {
		return _oAuthConnection.toCacheModel();
	}

	public OAuthConnection toEscapedModel() {
		return new OAuthConnectionWrapper(_oAuthConnection.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _oAuthConnection.toString();
	}

	public java.lang.String toXmlString() {
		return _oAuthConnection.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_oAuthConnection.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public OAuthConnection getWrappedOAuthConnection() {
		return _oAuthConnection;
	}

	public OAuthConnection getWrappedModel() {
		return _oAuthConnection;
	}

	public void resetOriginalValues() {
		_oAuthConnection.resetOriginalValues();
	}

	private OAuthConnection _oAuthConnection;
}