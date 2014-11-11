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

package com.wordpress.metaphorm.authProxy.sb.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;

import com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection;

/**
 * The persistence interface for the o auth connection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author stiansigvartsen
 * @see OAuthConnectionPersistenceImpl
 * @see OAuthConnectionUtil
 * @generated
 */
public interface OAuthConnectionPersistence extends BasePersistence<OAuthConnection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OAuthConnectionUtil} to access the o auth connection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the o auth connection in the entity cache if it is enabled.
	*
	* @param oAuthConnection the o auth connection
	*/
	public void cacheResult(
		com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection oAuthConnection);

	/**
	* Caches the o auth connections in the entity cache if it is enabled.
	*
	* @param oAuthConnections the o auth connections
	*/
	public void cacheResult(
		java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> oAuthConnections);

	/**
	* Creates a new o auth connection with the primary key. Does not add the o auth connection to the database.
	*
	* @param connectionId the primary key for the new o auth connection
	* @return the new o auth connection
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection create(
		long connectionId);

	/**
	* Removes the o auth connection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param connectionId the primary key of the o auth connection
	* @return the o auth connection that was removed
	* @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException if a o auth connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection remove(
		long connectionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException;

	public com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection updateImpl(
		com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection oAuthConnection,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth connection with the primary key or throws a {@link com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException} if it could not be found.
	*
	* @param connectionId the primary key of the o auth connection
	* @return the o auth connection
	* @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException if a o auth connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection findByPrimaryKey(
		long connectionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException;

	/**
	* Returns the o auth connection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param connectionId the primary key of the o auth connection
	* @return the o auth connection, or <code>null</code> if a o auth connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection fetchByPrimaryKey(
		long connectionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth connection where companyId = &#63; and groupId = &#63; and userId = &#63; and realm = &#63; or throws a {@link com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException} if it could not be found.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @param realm the realm
	* @return the matching o auth connection
	* @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException if a matching o auth connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection findByrealm(
		long companyId, long groupId, long userId, java.lang.String realm)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException;

	/**
	* Returns the o auth connection where companyId = &#63; and groupId = &#63; and userId = &#63; and realm = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @param realm the realm
	* @return the matching o auth connection, or <code>null</code> if a matching o auth connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection fetchByrealm(
		long companyId, long groupId, long userId, java.lang.String realm)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth connection where companyId = &#63; and groupId = &#63; and userId = &#63; and realm = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @param realm the realm
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching o auth connection, or <code>null</code> if a matching o auth connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection fetchByrealm(
		long companyId, long groupId, long userId, java.lang.String realm,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the o auth connections where companyId = &#63; and groupId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> findByuserId(
		long companyId, long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth connections where companyId = &#63; and groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of o auth connections
	* @param end the upper bound of the range of o auth connections (not inclusive)
	* @return the range of matching o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> findByuserId(
		long companyId, long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth connections where companyId = &#63; and groupId = &#63; and userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @param start the lower bound of the range of o auth connections
	* @param end the upper bound of the range of o auth connections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> findByuserId(
		long companyId, long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first o auth connection in the ordered set where companyId = &#63; and groupId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth connection
	* @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException if a matching o auth connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection findByuserId_First(
		long companyId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException;

	/**
	* Returns the first o auth connection in the ordered set where companyId = &#63; and groupId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth connection, or <code>null</code> if a matching o auth connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection fetchByuserId_First(
		long companyId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last o auth connection in the ordered set where companyId = &#63; and groupId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth connection
	* @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException if a matching o auth connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection findByuserId_Last(
		long companyId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException;

	/**
	* Returns the last o auth connection in the ordered set where companyId = &#63; and groupId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth connection, or <code>null</code> if a matching o auth connection could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection fetchByuserId_Last(
		long companyId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth connections before and after the current o auth connection in the ordered set where companyId = &#63; and groupId = &#63; and userId = &#63;.
	*
	* @param connectionId the primary key of the current o auth connection
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth connection
	* @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException if a o auth connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection[] findByuserId_PrevAndNext(
		long connectionId, long companyId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException;

	/**
	* Returns all the o auth connections.
	*
	* @return the o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth connections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth connections
	* @param end the upper bound of the range of o auth connections (not inclusive)
	* @return the range of o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth connections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth connections
	* @param end the upper bound of the range of o auth connections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the o auth connection where companyId = &#63; and groupId = &#63; and userId = &#63; and realm = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @param realm the realm
	* @return the o auth connection that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection removeByrealm(
		long companyId, long groupId, long userId, java.lang.String realm)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException;

	/**
	* Removes all the o auth connections where companyId = &#63; and groupId = &#63; and userId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByuserId(long companyId, long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o auth connections from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth connections where companyId = &#63; and groupId = &#63; and userId = &#63; and realm = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @param realm the realm
	* @return the number of matching o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public int countByrealm(long companyId, long groupId, long userId,
		java.lang.String realm)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth connections where companyId = &#63; and groupId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public int countByuserId(long companyId, long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth connections.
	*
	* @return the number of o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}