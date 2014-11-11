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
 * This class is a wrapper for {@link OAuthProvider}.
 * </p>
 *
 * @author    stiansigvartsen
 * @see       OAuthProvider
 * @generated
 */
public class OAuthProviderWrapper implements OAuthProvider,
	ModelWrapper<OAuthProvider> {
	public OAuthProviderWrapper(OAuthProvider oAuthProvider) {
		_oAuthProvider = oAuthProvider;
	}

	public Class<?> getModelClass() {
		return OAuthProvider.class;
	}

	public String getModelClassName() {
		return OAuthProvider.class.getName();
	}

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

	/**
	* Returns the primary key of this o auth provider.
	*
	* @return the primary key of this o auth provider
	*/
	public long getPrimaryKey() {
		return _oAuthProvider.getPrimaryKey();
	}

	/**
	* Sets the primary key of this o auth provider.
	*
	* @param primaryKey the primary key of this o auth provider
	*/
	public void setPrimaryKey(long primaryKey) {
		_oAuthProvider.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the provider ID of this o auth provider.
	*
	* @return the provider ID of this o auth provider
	*/
	public long getProviderId() {
		return _oAuthProvider.getProviderId();
	}

	/**
	* Sets the provider ID of this o auth provider.
	*
	* @param providerId the provider ID of this o auth provider
	*/
	public void setProviderId(long providerId) {
		_oAuthProvider.setProviderId(providerId);
	}

	/**
	* Returns the user ID of this o auth provider.
	*
	* @return the user ID of this o auth provider
	*/
	public long getUserId() {
		return _oAuthProvider.getUserId();
	}

	/**
	* Sets the user ID of this o auth provider.
	*
	* @param userId the user ID of this o auth provider
	*/
	public void setUserId(long userId) {
		_oAuthProvider.setUserId(userId);
	}

	/**
	* Returns the user uuid of this o auth provider.
	*
	* @return the user uuid of this o auth provider
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _oAuthProvider.getUserUuid();
	}

	/**
	* Sets the user uuid of this o auth provider.
	*
	* @param userUuid the user uuid of this o auth provider
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_oAuthProvider.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this o auth provider.
	*
	* @return the user name of this o auth provider
	*/
	public java.lang.String getUserName() {
		return _oAuthProvider.getUserName();
	}

	/**
	* Sets the user name of this o auth provider.
	*
	* @param userName the user name of this o auth provider
	*/
	public void setUserName(java.lang.String userName) {
		_oAuthProvider.setUserName(userName);
	}

	/**
	* Returns the create date of this o auth provider.
	*
	* @return the create date of this o auth provider
	*/
	public java.util.Date getCreateDate() {
		return _oAuthProvider.getCreateDate();
	}

	/**
	* Sets the create date of this o auth provider.
	*
	* @param createDate the create date of this o auth provider
	*/
	public void setCreateDate(java.util.Date createDate) {
		_oAuthProvider.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this o auth provider.
	*
	* @return the modified date of this o auth provider
	*/
	public java.util.Date getModifiedDate() {
		return _oAuthProvider.getModifiedDate();
	}

	/**
	* Sets the modified date of this o auth provider.
	*
	* @param modifiedDate the modified date of this o auth provider
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_oAuthProvider.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the consumer key of this o auth provider.
	*
	* @return the consumer key of this o auth provider
	*/
	public java.lang.String getConsumerKey() {
		return _oAuthProvider.getConsumerKey();
	}

	/**
	* Sets the consumer key of this o auth provider.
	*
	* @param consumerKey the consumer key of this o auth provider
	*/
	public void setConsumerKey(java.lang.String consumerKey) {
		_oAuthProvider.setConsumerKey(consumerKey);
	}

	/**
	* Returns the consumer secret of this o auth provider.
	*
	* @return the consumer secret of this o auth provider
	*/
	public java.lang.String getConsumerSecret() {
		return _oAuthProvider.getConsumerSecret();
	}

	/**
	* Sets the consumer secret of this o auth provider.
	*
	* @param consumerSecret the consumer secret of this o auth provider
	*/
	public void setConsumerSecret(java.lang.String consumerSecret) {
		_oAuthProvider.setConsumerSecret(consumerSecret);
	}

	/**
	* Returns the realm of this o auth provider.
	*
	* @return the realm of this o auth provider
	*/
	public java.lang.String getRealm() {
		return _oAuthProvider.getRealm();
	}

	/**
	* Sets the realm of this o auth provider.
	*
	* @param realm the realm of this o auth provider
	*/
	public void setRealm(java.lang.String realm) {
		_oAuthProvider.setRealm(realm);
	}

	/**
	* Returns the request token u r l of this o auth provider.
	*
	* @return the request token u r l of this o auth provider
	*/
	public java.lang.String getRequestTokenURL() {
		return _oAuthProvider.getRequestTokenURL();
	}

	/**
	* Sets the request token u r l of this o auth provider.
	*
	* @param requestTokenURL the request token u r l of this o auth provider
	*/
	public void setRequestTokenURL(java.lang.String requestTokenURL) {
		_oAuthProvider.setRequestTokenURL(requestTokenURL);
	}

	/**
	* Returns the authorise u r l of this o auth provider.
	*
	* @return the authorise u r l of this o auth provider
	*/
	public java.lang.String getAuthoriseURL() {
		return _oAuthProvider.getAuthoriseURL();
	}

	/**
	* Sets the authorise u r l of this o auth provider.
	*
	* @param authoriseURL the authorise u r l of this o auth provider
	*/
	public void setAuthoriseURL(java.lang.String authoriseURL) {
		_oAuthProvider.setAuthoriseURL(authoriseURL);
	}

	/**
	* Returns the access token u r l of this o auth provider.
	*
	* @return the access token u r l of this o auth provider
	*/
	public java.lang.String getAccessTokenURL() {
		return _oAuthProvider.getAccessTokenURL();
	}

	/**
	* Sets the access token u r l of this o auth provider.
	*
	* @param accessTokenURL the access token u r l of this o auth provider
	*/
	public void setAccessTokenURL(java.lang.String accessTokenURL) {
		_oAuthProvider.setAccessTokenURL(accessTokenURL);
	}

	/**
	* Returns the protocol version of this o auth provider.
	*
	* @return the protocol version of this o auth provider
	*/
	public java.lang.String getProtocolVersion() {
		return _oAuthProvider.getProtocolVersion();
	}

	/**
	* Sets the protocol version of this o auth provider.
	*
	* @param protocolVersion the protocol version of this o auth provider
	*/
	public void setProtocolVersion(java.lang.String protocolVersion) {
		_oAuthProvider.setProtocolVersion(protocolVersion);
	}

	/**
	* Returns the preemptive auth u r l pattern of this o auth provider.
	*
	* @return the preemptive auth u r l pattern of this o auth provider
	*/
	public java.lang.String getPreemptiveAuthURLPattern() {
		return _oAuthProvider.getPreemptiveAuthURLPattern();
	}

	/**
	* Sets the preemptive auth u r l pattern of this o auth provider.
	*
	* @param preemptiveAuthURLPattern the preemptive auth u r l pattern of this o auth provider
	*/
	public void setPreemptiveAuthURLPattern(
		java.lang.String preemptiveAuthURLPattern) {
		_oAuthProvider.setPreemptiveAuthURLPattern(preemptiveAuthURLPattern);
	}

	/**
	* Returns the use h t t p s of this o auth provider.
	*
	* @return the use h t t p s of this o auth provider
	*/
	public boolean getUseHTTPS() {
		return _oAuthProvider.getUseHTTPS();
	}

	/**
	* Returns <code>true</code> if this o auth provider is use h t t p s.
	*
	* @return <code>true</code> if this o auth provider is use h t t p s; <code>false</code> otherwise
	*/
	public boolean isUseHTTPS() {
		return _oAuthProvider.isUseHTTPS();
	}

	/**
	* Sets whether this o auth provider is use h t t p s.
	*
	* @param useHTTPS the use h t t p s of this o auth provider
	*/
	public void setUseHTTPS(boolean useHTTPS) {
		_oAuthProvider.setUseHTTPS(useHTTPS);
	}

	public boolean isNew() {
		return _oAuthProvider.isNew();
	}

	public void setNew(boolean n) {
		_oAuthProvider.setNew(n);
	}

	public boolean isCachedModel() {
		return _oAuthProvider.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_oAuthProvider.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _oAuthProvider.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _oAuthProvider.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_oAuthProvider.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _oAuthProvider.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_oAuthProvider.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new OAuthProviderWrapper((OAuthProvider)_oAuthProvider.clone());
	}

	public int compareTo(OAuthProvider oAuthProvider) {
		return _oAuthProvider.compareTo(oAuthProvider);
	}

	@Override
	public int hashCode() {
		return _oAuthProvider.hashCode();
	}

	public com.liferay.portal.model.CacheModel<OAuthProvider> toCacheModel() {
		return _oAuthProvider.toCacheModel();
	}

	public OAuthProvider toEscapedModel() {
		return new OAuthProviderWrapper(_oAuthProvider.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _oAuthProvider.toString();
	}

	public java.lang.String toXmlString() {
		return _oAuthProvider.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_oAuthProvider.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public OAuthProvider getWrappedOAuthProvider() {
		return _oAuthProvider;
	}

	public OAuthProvider getWrappedModel() {
		return _oAuthProvider;
	}

	public void resetOriginalValues() {
		_oAuthProvider.resetOriginalValues();
	}

	private OAuthProvider _oAuthProvider;
}