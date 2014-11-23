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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.GroupedModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the OAuthConnection service. Represents a row in the &quot;OAuthProxy_OAuthConnection&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthConnectionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthConnectionImpl}.
 * </p>
 *
 * @author stiansigvartsen
 * @see OAuthConnection
 * @see com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthConnectionImpl
 * @see com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthConnectionModelImpl
 * @generated
 */
public interface OAuthConnectionModel extends BaseModel<OAuthConnection>,
	GroupedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a o auth connection model instance should use the {@link OAuthConnection} interface instead.
	 */

	/**
	 * Returns the primary key of this o auth connection.
	 *
	 * @return the primary key of this o auth connection
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this o auth connection.
	 *
	 * @param primaryKey the primary key of this o auth connection
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the connection ID of this o auth connection.
	 *
	 * @return the connection ID of this o auth connection
	 */
	public long getConnectionId();

	/**
	 * Sets the connection ID of this o auth connection.
	 *
	 * @param connectionId the connection ID of this o auth connection
	 */
	public void setConnectionId(long connectionId);

	/**
	 * Returns the company ID of this o auth connection.
	 *
	 * @return the company ID of this o auth connection
	 */
	public long getCompanyId();

	/**
	 * Sets the company ID of this o auth connection.
	 *
	 * @param companyId the company ID of this o auth connection
	 */
	public void setCompanyId(long companyId);

	/**
	 * Returns the group ID of this o auth connection.
	 *
	 * @return the group ID of this o auth connection
	 */
	public long getGroupId();

	/**
	 * Sets the group ID of this o auth connection.
	 *
	 * @param groupId the group ID of this o auth connection
	 */
	public void setGroupId(long groupId);

	/**
	 * Returns the user ID of this o auth connection.
	 *
	 * @return the user ID of this o auth connection
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this o auth connection.
	 *
	 * @param userId the user ID of this o auth connection
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this o auth connection.
	 *
	 * @return the user uuid of this o auth connection
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this o auth connection.
	 *
	 * @param userUuid the user uuid of this o auth connection
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this o auth connection.
	 *
	 * @return the user name of this o auth connection
	 */
	@AutoEscape
	public String getUserName();

	/**
	 * Sets the user name of this o auth connection.
	 *
	 * @param userName the user name of this o auth connection
	 */
	public void setUserName(String userName);

	/**
	 * Returns the create date of this o auth connection.
	 *
	 * @return the create date of this o auth connection
	 */
	public Date getCreateDate();

	/**
	 * Sets the create date of this o auth connection.
	 *
	 * @param createDate the create date of this o auth connection
	 */
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this o auth connection.
	 *
	 * @return the modified date of this o auth connection
	 */
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this o auth connection.
	 *
	 * @param modifiedDate the modified date of this o auth connection
	 */
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the token of this o auth connection.
	 *
	 * @return the token of this o auth connection
	 */
	@AutoEscape
	public String getToken();

	/**
	 * Sets the token of this o auth connection.
	 *
	 * @param token the token of this o auth connection
	 */
	public void setToken(String token);

	/**
	 * Returns the token secret of this o auth connection.
	 *
	 * @return the token secret of this o auth connection
	 */
	@AutoEscape
	public String getTokenSecret();

	/**
	 * Sets the token secret of this o auth connection.
	 *
	 * @param tokenSecret the token secret of this o auth connection
	 */
	public void setTokenSecret(String tokenSecret);

	/**
	 * Returns the realm of this o auth connection.
	 *
	 * @return the realm of this o auth connection
	 */
	@AutoEscape
	public String getRealm();

	/**
	 * Sets the realm of this o auth connection.
	 *
	 * @param realm the realm of this o auth connection
	 */
	public void setRealm(String realm);

	/**
	 * Returns the phase of this o auth connection.
	 *
	 * @return the phase of this o auth connection
	 */
	public int getPhase();

	/**
	 * Sets the phase of this o auth connection.
	 *
	 * @param phase the phase of this o auth connection
	 */
	public void setPhase(int phase);

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public Serializable getPrimaryKeyObj();

	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(OAuthConnection oAuthConnection);

	public int hashCode();

	public CacheModel<OAuthConnection> toCacheModel();

	public OAuthConnection toEscapedModel();

	public String toString();

	public String toXmlString();
}