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

import com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider;

/**
 * The persistence interface for the o auth provider service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author stiansigvartsen
 * @see OAuthProviderPersistenceImpl
 * @see OAuthProviderUtil
 * @generated
 */
public interface OAuthProviderPersistence extends BasePersistence<OAuthProvider> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OAuthProviderUtil} to access the o auth provider persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the o auth provider in the entity cache if it is enabled.
	*
	* @param oAuthProvider the o auth provider
	*/
	public void cacheResult(
		com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider oAuthProvider);

	/**
	* Caches the o auth providers in the entity cache if it is enabled.
	*
	* @param oAuthProviders the o auth providers
	*/
	public void cacheResult(
		java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider> oAuthProviders);

	/**
	* Creates a new o auth provider with the primary key. Does not add the o auth provider to the database.
	*
	* @param providerId the primary key for the new o auth provider
	* @return the new o auth provider
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider create(
		long providerId);

	/**
	* Removes the o auth provider with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param providerId the primary key of the o auth provider
	* @return the o auth provider that was removed
	* @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException if a o auth provider with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider remove(
		long providerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException;

	public com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider updateImpl(
		com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider oAuthProvider,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth provider with the primary key or throws a {@link com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException} if it could not be found.
	*
	* @param providerId the primary key of the o auth provider
	* @return the o auth provider
	* @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException if a o auth provider with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider findByPrimaryKey(
		long providerId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException;

	/**
	* Returns the o auth provider with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param providerId the primary key of the o auth provider
	* @return the o auth provider, or <code>null</code> if a o auth provider with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider fetchByPrimaryKey(
		long providerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth provider where realm = &#63; or throws a {@link com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException} if it could not be found.
	*
	* @param realm the realm
	* @return the matching o auth provider
	* @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException if a matching o auth provider could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider findByRealm(
		java.lang.String realm)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException;

	/**
	* Returns the o auth provider where realm = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param realm the realm
	* @return the matching o auth provider, or <code>null</code> if a matching o auth provider could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider fetchByRealm(
		java.lang.String realm)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth provider where realm = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param realm the realm
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching o auth provider, or <code>null</code> if a matching o auth provider could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider fetchByRealm(
		java.lang.String realm, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the o auth providers.
	*
	* @return the o auth providers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth providers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth providers
	* @param end the upper bound of the range of o auth providers (not inclusive)
	* @return the range of o auth providers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth providers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth providers
	* @param end the upper bound of the range of o auth providers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of o auth providers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the o auth provider where realm = &#63; from the database.
	*
	* @param realm the realm
	* @return the o auth provider that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider removeByRealm(
		java.lang.String realm)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException;

	/**
	* Removes all the o auth providers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth providers where realm = &#63;.
	*
	* @param realm the realm
	* @return the number of matching o auth providers
	* @throws SystemException if a system exception occurred
	*/
	public int countByRealm(java.lang.String realm)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth providers.
	*
	* @return the number of o auth providers
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the o auth connections associated with the o auth provider.
	*
	* @param pk the primary key of the o auth provider
	* @return the o auth connections associated with the o auth provider
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> getOAuthConnections(
		long pk) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth connections associated with the o auth provider.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the o auth provider
	* @param start the lower bound of the range of o auth providers
	* @param end the upper bound of the range of o auth providers (not inclusive)
	* @return the range of o auth connections associated with the o auth provider
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> getOAuthConnections(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth connections associated with the o auth provider.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the o auth provider
	* @param start the lower bound of the range of o auth providers
	* @param end the upper bound of the range of o auth providers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of o auth connections associated with the o auth provider
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> getOAuthConnections(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth connections associated with the o auth provider.
	*
	* @param pk the primary key of the o auth provider
	* @return the number of o auth connections associated with the o auth provider
	* @throws SystemException if a system exception occurred
	*/
	public int getOAuthConnectionsSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the o auth connection is associated with the o auth provider.
	*
	* @param pk the primary key of the o auth provider
	* @param oAuthConnectionPK the primary key of the o auth connection
	* @return <code>true</code> if the o auth connection is associated with the o auth provider; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsOAuthConnection(long pk, long oAuthConnectionPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns <code>true</code> if the o auth provider has any o auth connections associated with it.
	*
	* @param pk the primary key of the o auth provider to check for associations with o auth connections
	* @return <code>true</code> if the o auth provider has any o auth connections associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public boolean containsOAuthConnections(long pk)
		throws com.liferay.portal.kernel.exception.SystemException;
}