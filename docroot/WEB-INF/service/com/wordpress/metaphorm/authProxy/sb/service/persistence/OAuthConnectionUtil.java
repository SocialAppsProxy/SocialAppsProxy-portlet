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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection;

import java.util.List;

/**
 * The persistence utility for the o auth connection service. This utility wraps {@link OAuthConnectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author stiansigvartsen
 * @see OAuthConnectionPersistence
 * @see OAuthConnectionPersistenceImpl
 * @generated
 */
public class OAuthConnectionUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(OAuthConnection oAuthConnection) {
		getPersistence().clearCache(oAuthConnection);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<OAuthConnection> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OAuthConnection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OAuthConnection> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static OAuthConnection update(OAuthConnection oAuthConnection,
		boolean merge) throws SystemException {
		return getPersistence().update(oAuthConnection, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static OAuthConnection update(OAuthConnection oAuthConnection,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(oAuthConnection, merge, serviceContext);
	}

	/**
	* Caches the o auth connection in the entity cache if it is enabled.
	*
	* @param oAuthConnection the o auth connection
	*/
	public static void cacheResult(
		com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection oAuthConnection) {
		getPersistence().cacheResult(oAuthConnection);
	}

	/**
	* Caches the o auth connections in the entity cache if it is enabled.
	*
	* @param oAuthConnections the o auth connections
	*/
	public static void cacheResult(
		java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> oAuthConnections) {
		getPersistence().cacheResult(oAuthConnections);
	}

	/**
	* Creates a new o auth connection with the primary key. Does not add the o auth connection to the database.
	*
	* @param connectionId the primary key for the new o auth connection
	* @return the new o auth connection
	*/
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection create(
		long connectionId) {
		return getPersistence().create(connectionId);
	}

	/**
	* Removes the o auth connection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param connectionId the primary key of the o auth connection
	* @return the o auth connection that was removed
	* @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException if a o auth connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection remove(
		long connectionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException {
		return getPersistence().remove(connectionId);
	}

	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection updateImpl(
		com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection oAuthConnection,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(oAuthConnection, merge);
	}

	/**
	* Returns the o auth connection with the primary key or throws a {@link com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException} if it could not be found.
	*
	* @param connectionId the primary key of the o auth connection
	* @return the o auth connection
	* @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException if a o auth connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection findByPrimaryKey(
		long connectionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException {
		return getPersistence().findByPrimaryKey(connectionId);
	}

	/**
	* Returns the o auth connection with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param connectionId the primary key of the o auth connection
	* @return the o auth connection, or <code>null</code> if a o auth connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection fetchByPrimaryKey(
		long connectionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(connectionId);
	}

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
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection findByrealm(
		long companyId, long groupId, long userId, java.lang.String realm)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException {
		return getPersistence().findByrealm(companyId, groupId, userId, realm);
	}

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
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection fetchByrealm(
		long companyId, long groupId, long userId, java.lang.String realm)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByrealm(companyId, groupId, userId, realm);
	}

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
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection fetchByrealm(
		long companyId, long groupId, long userId, java.lang.String realm,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByrealm(companyId, groupId, userId, realm,
			retrieveFromCache);
	}

	/**
	* Returns all the o auth connections where companyId = &#63; and groupId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @return the matching o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> findByuserId(
		long companyId, long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByuserId(companyId, groupId, userId);
	}

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
	public static java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> findByuserId(
		long companyId, long groupId, long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByuserId(companyId, groupId, userId, start, end);
	}

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
	public static java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> findByuserId(
		long companyId, long groupId, long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByuserId(companyId, groupId, userId, start, end,
			orderByComparator);
	}

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
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection findByuserId_First(
		long companyId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException {
		return getPersistence()
				   .findByuserId_First(companyId, groupId, userId,
			orderByComparator);
	}

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
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection fetchByuserId_First(
		long companyId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByuserId_First(companyId, groupId, userId,
			orderByComparator);
	}

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
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection findByuserId_Last(
		long companyId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException {
		return getPersistence()
				   .findByuserId_Last(companyId, groupId, userId,
			orderByComparator);
	}

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
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection fetchByuserId_Last(
		long companyId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByuserId_Last(companyId, groupId, userId,
			orderByComparator);
	}

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
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection[] findByuserId_PrevAndNext(
		long connectionId, long companyId, long groupId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException {
		return getPersistence()
				   .findByuserId_PrevAndNext(connectionId, companyId, groupId,
			userId, orderByComparator);
	}

	/**
	* Returns all the o auth connections.
	*
	* @return the o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

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
	public static java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

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
	public static java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection removeByrealm(
		long companyId, long groupId, long userId, java.lang.String realm)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException {
		return getPersistence().removeByrealm(companyId, groupId, userId, realm);
	}

	/**
	* Removes all the o auth connections where companyId = &#63; and groupId = &#63; and userId = &#63; from the database.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByuserId(long companyId, long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByuserId(companyId, groupId, userId);
	}

	/**
	* Removes all the o auth connections from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

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
	public static int countByrealm(long companyId, long groupId, long userId,
		java.lang.String realm)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByrealm(companyId, groupId, userId, realm);
	}

	/**
	* Returns the number of o auth connections where companyId = &#63; and groupId = &#63; and userId = &#63;.
	*
	* @param companyId the company ID
	* @param groupId the group ID
	* @param userId the user ID
	* @return the number of matching o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public static int countByuserId(long companyId, long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByuserId(companyId, groupId, userId);
	}

	/**
	* Returns the number of o auth connections.
	*
	* @return the number of o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static OAuthConnectionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (OAuthConnectionPersistence)PortletBeanLocatorUtil.locate(com.wordpress.metaphorm.authProxy.sb.service.ClpSerializer.getServletContextName(),
					OAuthConnectionPersistence.class.getName());

			ReferenceRegistry.registerReference(OAuthConnectionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(OAuthConnectionPersistence persistence) {
	}

	private static OAuthConnectionPersistence _persistence;
}