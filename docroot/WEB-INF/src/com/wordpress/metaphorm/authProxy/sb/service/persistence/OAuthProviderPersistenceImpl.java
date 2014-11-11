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

import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQuery;
import com.liferay.portal.kernel.dao.jdbc.MappingSqlQueryFactoryUtil;
import com.liferay.portal.kernel.dao.jdbc.RowMapper;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.ResourcePersistence;
import com.liferay.portal.service.persistence.UserPersistence;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException;
import com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider;
import com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthProviderImpl;
import com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthProviderModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the o auth provider service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author stiansigvartsen
 * @see OAuthProviderPersistence
 * @see OAuthProviderUtil
 * @generated
 */
public class OAuthProviderPersistenceImpl extends BasePersistenceImpl<OAuthProvider>
	implements OAuthProviderPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OAuthProviderUtil} to access the o auth provider persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OAuthProviderImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_REALM = new FinderPath(OAuthProviderModelImpl.ENTITY_CACHE_ENABLED,
			OAuthProviderModelImpl.FINDER_CACHE_ENABLED,
			OAuthProviderImpl.class, FINDER_CLASS_NAME_ENTITY, "fetchByRealm",
			new String[] { String.class.getName() },
			OAuthProviderModelImpl.REALM_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_REALM = new FinderPath(OAuthProviderModelImpl.ENTITY_CACHE_ENABLED,
			OAuthProviderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByRealm",
			new String[] { String.class.getName() });
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OAuthProviderModelImpl.ENTITY_CACHE_ENABLED,
			OAuthProviderModelImpl.FINDER_CACHE_ENABLED,
			OAuthProviderImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OAuthProviderModelImpl.ENTITY_CACHE_ENABLED,
			OAuthProviderModelImpl.FINDER_CACHE_ENABLED,
			OAuthProviderImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OAuthProviderModelImpl.ENTITY_CACHE_ENABLED,
			OAuthProviderModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the o auth provider in the entity cache if it is enabled.
	 *
	 * @param oAuthProvider the o auth provider
	 */
	public void cacheResult(OAuthProvider oAuthProvider) {
		EntityCacheUtil.putResult(OAuthProviderModelImpl.ENTITY_CACHE_ENABLED,
			OAuthProviderImpl.class, oAuthProvider.getPrimaryKey(),
			oAuthProvider);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REALM,
			new Object[] { oAuthProvider.getRealm() }, oAuthProvider);

		oAuthProvider.resetOriginalValues();
	}

	/**
	 * Caches the o auth providers in the entity cache if it is enabled.
	 *
	 * @param oAuthProviders the o auth providers
	 */
	public void cacheResult(List<OAuthProvider> oAuthProviders) {
		for (OAuthProvider oAuthProvider : oAuthProviders) {
			if (EntityCacheUtil.getResult(
						OAuthProviderModelImpl.ENTITY_CACHE_ENABLED,
						OAuthProviderImpl.class, oAuthProvider.getPrimaryKey()) == null) {
				cacheResult(oAuthProvider);
			}
			else {
				oAuthProvider.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all o auth providers.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(OAuthProviderImpl.class.getName());
		}

		EntityCacheUtil.clearCache(OAuthProviderImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the o auth provider.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OAuthProvider oAuthProvider) {
		EntityCacheUtil.removeResult(OAuthProviderModelImpl.ENTITY_CACHE_ENABLED,
			OAuthProviderImpl.class, oAuthProvider.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(oAuthProvider);
	}

	@Override
	public void clearCache(List<OAuthProvider> oAuthProviders) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OAuthProvider oAuthProvider : oAuthProviders) {
			EntityCacheUtil.removeResult(OAuthProviderModelImpl.ENTITY_CACHE_ENABLED,
				OAuthProviderImpl.class, oAuthProvider.getPrimaryKey());

			clearUniqueFindersCache(oAuthProvider);
		}
	}

	protected void clearUniqueFindersCache(OAuthProvider oAuthProvider) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_REALM,
			new Object[] { oAuthProvider.getRealm() });
	}

	/**
	 * Creates a new o auth provider with the primary key. Does not add the o auth provider to the database.
	 *
	 * @param providerId the primary key for the new o auth provider
	 * @return the new o auth provider
	 */
	public OAuthProvider create(long providerId) {
		OAuthProvider oAuthProvider = new OAuthProviderImpl();

		oAuthProvider.setNew(true);
		oAuthProvider.setPrimaryKey(providerId);

		return oAuthProvider;
	}

	/**
	 * Removes the o auth provider with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param providerId the primary key of the o auth provider
	 * @return the o auth provider that was removed
	 * @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException if a o auth provider with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthProvider remove(long providerId)
		throws NoSuchOAuthProviderException, SystemException {
		return remove(Long.valueOf(providerId));
	}

	/**
	 * Removes the o auth provider with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the o auth provider
	 * @return the o auth provider that was removed
	 * @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException if a o auth provider with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthProvider remove(Serializable primaryKey)
		throws NoSuchOAuthProviderException, SystemException {
		Session session = null;

		try {
			session = openSession();

			OAuthProvider oAuthProvider = (OAuthProvider)session.get(OAuthProviderImpl.class,
					primaryKey);

			if (oAuthProvider == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOAuthProviderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(oAuthProvider);
		}
		catch (NoSuchOAuthProviderException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected OAuthProvider removeImpl(OAuthProvider oAuthProvider)
		throws SystemException {
		oAuthProvider = toUnwrappedModel(oAuthProvider);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, oAuthProvider);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(oAuthProvider);

		return oAuthProvider;
	}

	@Override
	public OAuthProvider updateImpl(
		com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider oAuthProvider,
		boolean merge) throws SystemException {
		oAuthProvider = toUnwrappedModel(oAuthProvider);

		boolean isNew = oAuthProvider.isNew();

		OAuthProviderModelImpl oAuthProviderModelImpl = (OAuthProviderModelImpl)oAuthProvider;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, oAuthProvider, merge);

			oAuthProvider.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !OAuthProviderModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		EntityCacheUtil.putResult(OAuthProviderModelImpl.ENTITY_CACHE_ENABLED,
			OAuthProviderImpl.class, oAuthProvider.getPrimaryKey(),
			oAuthProvider);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REALM,
				new Object[] { oAuthProvider.getRealm() }, oAuthProvider);
		}
		else {
			if ((oAuthProviderModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_REALM.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						oAuthProviderModelImpl.getOriginalRealm()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REALM, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_REALM, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REALM,
					new Object[] { oAuthProvider.getRealm() }, oAuthProvider);
			}
		}

		return oAuthProvider;
	}

	protected OAuthProvider toUnwrappedModel(OAuthProvider oAuthProvider) {
		if (oAuthProvider instanceof OAuthProviderImpl) {
			return oAuthProvider;
		}

		OAuthProviderImpl oAuthProviderImpl = new OAuthProviderImpl();

		oAuthProviderImpl.setNew(oAuthProvider.isNew());
		oAuthProviderImpl.setPrimaryKey(oAuthProvider.getPrimaryKey());

		oAuthProviderImpl.setProviderId(oAuthProvider.getProviderId());
		oAuthProviderImpl.setUserId(oAuthProvider.getUserId());
		oAuthProviderImpl.setUserName(oAuthProvider.getUserName());
		oAuthProviderImpl.setCreateDate(oAuthProvider.getCreateDate());
		oAuthProviderImpl.setModifiedDate(oAuthProvider.getModifiedDate());
		oAuthProviderImpl.setConsumerKey(oAuthProvider.getConsumerKey());
		oAuthProviderImpl.setConsumerSecret(oAuthProvider.getConsumerSecret());
		oAuthProviderImpl.setRealm(oAuthProvider.getRealm());
		oAuthProviderImpl.setRequestTokenURL(oAuthProvider.getRequestTokenURL());
		oAuthProviderImpl.setAuthoriseURL(oAuthProvider.getAuthoriseURL());
		oAuthProviderImpl.setAccessTokenURL(oAuthProvider.getAccessTokenURL());
		oAuthProviderImpl.setProtocolVersion(oAuthProvider.getProtocolVersion());
		oAuthProviderImpl.setPreemptiveAuthURLPattern(oAuthProvider.getPreemptiveAuthURLPattern());
		oAuthProviderImpl.setUseHTTPS(oAuthProvider.isUseHTTPS());

		return oAuthProviderImpl;
	}

	/**
	 * Returns the o auth provider with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth provider
	 * @return the o auth provider
	 * @throws com.liferay.portal.NoSuchModelException if a o auth provider with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthProvider findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the o auth provider with the primary key or throws a {@link com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException} if it could not be found.
	 *
	 * @param providerId the primary key of the o auth provider
	 * @return the o auth provider
	 * @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException if a o auth provider with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthProvider findByPrimaryKey(long providerId)
		throws NoSuchOAuthProviderException, SystemException {
		OAuthProvider oAuthProvider = fetchByPrimaryKey(providerId);

		if (oAuthProvider == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + providerId);
			}

			throw new NoSuchOAuthProviderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				providerId);
		}

		return oAuthProvider;
	}

	/**
	 * Returns the o auth provider with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth provider
	 * @return the o auth provider, or <code>null</code> if a o auth provider with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthProvider fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the o auth provider with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param providerId the primary key of the o auth provider
	 * @return the o auth provider, or <code>null</code> if a o auth provider with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthProvider fetchByPrimaryKey(long providerId)
		throws SystemException {
		OAuthProvider oAuthProvider = (OAuthProvider)EntityCacheUtil.getResult(OAuthProviderModelImpl.ENTITY_CACHE_ENABLED,
				OAuthProviderImpl.class, providerId);

		if (oAuthProvider == _nullOAuthProvider) {
			return null;
		}

		if (oAuthProvider == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				oAuthProvider = (OAuthProvider)session.get(OAuthProviderImpl.class,
						Long.valueOf(providerId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (oAuthProvider != null) {
					cacheResult(oAuthProvider);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(OAuthProviderModelImpl.ENTITY_CACHE_ENABLED,
						OAuthProviderImpl.class, providerId, _nullOAuthProvider);
				}

				closeSession(session);
			}
		}

		return oAuthProvider;
	}

	/**
	 * Returns the o auth provider where realm = &#63; or throws a {@link com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException} if it could not be found.
	 *
	 * @param realm the realm
	 * @return the matching o auth provider
	 * @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException if a matching o auth provider could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthProvider findByRealm(String realm)
		throws NoSuchOAuthProviderException, SystemException {
		OAuthProvider oAuthProvider = fetchByRealm(realm);

		if (oAuthProvider == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("realm=");
			msg.append(realm);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchOAuthProviderException(msg.toString());
		}

		return oAuthProvider;
	}

	/**
	 * Returns the o auth provider where realm = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param realm the realm
	 * @return the matching o auth provider, or <code>null</code> if a matching o auth provider could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthProvider fetchByRealm(String realm) throws SystemException {
		return fetchByRealm(realm, true);
	}

	/**
	 * Returns the o auth provider where realm = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param realm the realm
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching o auth provider, or <code>null</code> if a matching o auth provider could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthProvider fetchByRealm(String realm, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { realm };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_REALM,
					finderArgs, this);
		}

		if (result instanceof OAuthProvider) {
			OAuthProvider oAuthProvider = (OAuthProvider)result;

			if (!Validator.equals(realm, oAuthProvider.getRealm())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_OAUTHPROVIDER_WHERE);

			if (realm == null) {
				query.append(_FINDER_COLUMN_REALM_REALM_1);
			}
			else {
				if (realm.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REALM_REALM_3);
				}
				else {
					query.append(_FINDER_COLUMN_REALM_REALM_2);
				}
			}

			query.append(OAuthProviderModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (realm != null) {
					qPos.add(realm);
				}

				List<OAuthProvider> list = q.list();

				result = list;

				OAuthProvider oAuthProvider = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REALM,
						finderArgs, list);
				}
				else {
					oAuthProvider = list.get(0);

					cacheResult(oAuthProvider);

					if ((oAuthProvider.getRealm() == null) ||
							!oAuthProvider.getRealm().equals(realm)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REALM,
							finderArgs, oAuthProvider);
					}
				}

				return oAuthProvider;
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (result == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_REALM,
						finderArgs);
				}

				closeSession(session);
			}
		}
		else {
			if (result instanceof List<?>) {
				return null;
			}
			else {
				return (OAuthProvider)result;
			}
		}
	}

	/**
	 * Returns all the o auth providers.
	 *
	 * @return the o auth providers
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthProvider> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

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
	public List<OAuthProvider> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

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
	public List<OAuthProvider> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = new Object[] { start, end, orderByComparator };

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<OAuthProvider> list = (List<OAuthProvider>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_OAUTHPROVIDER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OAUTHPROVIDER.concat(OAuthProviderModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<OAuthProvider>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<OAuthProvider>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(finderPath, finderArgs);
				}
				else {
					cacheResult(list);

					FinderCacheUtil.putResult(finderPath, finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes the o auth provider where realm = &#63; from the database.
	 *
	 * @param realm the realm
	 * @return the o auth provider that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthProvider removeByRealm(String realm)
		throws NoSuchOAuthProviderException, SystemException {
		OAuthProvider oAuthProvider = findByRealm(realm);

		return remove(oAuthProvider);
	}

	/**
	 * Removes all the o auth providers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (OAuthProvider oAuthProvider : findAll()) {
			remove(oAuthProvider);
		}
	}

	/**
	 * Returns the number of o auth providers where realm = &#63;.
	 *
	 * @param realm the realm
	 * @return the number of matching o auth providers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRealm(String realm) throws SystemException {
		Object[] finderArgs = new Object[] { realm };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_REALM,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_OAUTHPROVIDER_WHERE);

			if (realm == null) {
				query.append(_FINDER_COLUMN_REALM_REALM_1);
			}
			else {
				if (realm.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REALM_REALM_3);
				}
				else {
					query.append(_FINDER_COLUMN_REALM_REALM_2);
				}
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (realm != null) {
					qPos.add(realm);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_REALM,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of o auth providers.
	 *
	 * @return the number of o auth providers
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OAUTHPROVIDER);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns all the o auth connections associated with the o auth provider.
	 *
	 * @param pk the primary key of the o auth provider
	 * @return the o auth connections associated with the o auth provider
	 * @throws SystemException if a system exception occurred
	 */
	public List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> getOAuthConnections(
		long pk) throws SystemException {
		return getOAuthConnections(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

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
	public List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> getOAuthConnections(
		long pk, int start, int end) throws SystemException {
		return getOAuthConnections(pk, start, end, null);
	}

	public static final FinderPath FINDER_PATH_GET_OAUTHCONNECTIONS = new FinderPath(com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthConnectionModelImpl.FINDER_CACHE_ENABLED,
			com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthConnectionImpl.class,
			com.wordpress.metaphorm.authProxy.sb.service.persistence.OAuthConnectionPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"getOAuthConnections",
			new String[] {
				Long.class.getName(), "java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});

	static {
		FINDER_PATH_GET_OAUTHCONNECTIONS.setCacheKeyGeneratorCacheName(null);
	}

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
	public List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> getOAuthConnections(
		long pk, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, start, end, orderByComparator };

		List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> list = (List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection>)FinderCacheUtil.getResult(FINDER_PATH_GET_OAUTHCONNECTIONS,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETOAUTHCONNECTIONS.concat(ORDER_BY_CLAUSE)
												  .concat(orderByComparator.getOrderBy());
				}
				else {
					sql = _SQL_GETOAUTHCONNECTIONS.concat(com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthConnectionModelImpl.ORDER_BY_SQL);
				}

				SQLQuery q = session.createSQLQuery(sql);

				q.addEntity("OAuthProxy_OAuthConnection",
					com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthConnectionImpl.class);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				list = (List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection>)QueryUtil.list(q,
						getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					FinderCacheUtil.removeResult(FINDER_PATH_GET_OAUTHCONNECTIONS,
						finderArgs);
				}
				else {
					oAuthConnectionPersistence.cacheResult(list);

					FinderCacheUtil.putResult(FINDER_PATH_GET_OAUTHCONNECTIONS,
						finderArgs, list);
				}

				closeSession(session);
			}
		}

		return list;
	}

	public static final FinderPath FINDER_PATH_GET_OAUTHCONNECTIONS_SIZE = new FinderPath(com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthConnectionModelImpl.FINDER_CACHE_ENABLED,
			com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthConnectionImpl.class,
			com.wordpress.metaphorm.authProxy.sb.service.persistence.OAuthConnectionPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"getOAuthConnectionsSize", new String[] { Long.class.getName() });

	static {
		FINDER_PATH_GET_OAUTHCONNECTIONS_SIZE.setCacheKeyGeneratorCacheName(null);
	}

	/**
	 * Returns the number of o auth connections associated with the o auth provider.
	 *
	 * @param pk the primary key of the o auth provider
	 * @return the number of o auth connections associated with the o auth provider
	 * @throws SystemException if a system exception occurred
	 */
	public int getOAuthConnectionsSize(long pk) throws SystemException {
		Object[] finderArgs = new Object[] { pk };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_GET_OAUTHCONNECTIONS_SIZE,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				SQLQuery q = session.createSQLQuery(_SQL_GETOAUTHCONNECTIONSSIZE);

				q.addScalar(COUNT_COLUMN_NAME,
					com.liferay.portal.kernel.dao.orm.Type.LONG);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(pk);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_GET_OAUTHCONNECTIONS_SIZE,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public static final FinderPath FINDER_PATH_CONTAINS_OAUTHCONNECTION = new FinderPath(com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthConnectionModelImpl.FINDER_CACHE_ENABLED,
			com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthConnectionImpl.class,
			com.wordpress.metaphorm.authProxy.sb.service.persistence.OAuthConnectionPersistenceImpl.FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"containsOAuthConnection",
			new String[] { Long.class.getName(), Long.class.getName() });

	/**
	 * Returns <code>true</code> if the o auth connection is associated with the o auth provider.
	 *
	 * @param pk the primary key of the o auth provider
	 * @param oAuthConnectionPK the primary key of the o auth connection
	 * @return <code>true</code> if the o auth connection is associated with the o auth provider; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsOAuthConnection(long pk, long oAuthConnectionPK)
		throws SystemException {
		Object[] finderArgs = new Object[] { pk, oAuthConnectionPK };

		Boolean value = (Boolean)FinderCacheUtil.getResult(FINDER_PATH_CONTAINS_OAUTHCONNECTION,
				finderArgs, this);

		if (value == null) {
			try {
				value = Boolean.valueOf(containsOAuthConnection.contains(pk,
							oAuthConnectionPK));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (value == null) {
					value = Boolean.FALSE;
				}

				FinderCacheUtil.putResult(FINDER_PATH_CONTAINS_OAUTHCONNECTION,
					finderArgs, value);
			}
		}

		return value.booleanValue();
	}

	/**
	 * Returns <code>true</code> if the o auth provider has any o auth connections associated with it.
	 *
	 * @param pk the primary key of the o auth provider to check for associations with o auth connections
	 * @return <code>true</code> if the o auth provider has any o auth connections associated with it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsOAuthConnections(long pk) throws SystemException {
		if (getOAuthConnectionsSize(pk) > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Initializes the o auth provider persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<OAuthProvider>> listenersList = new ArrayList<ModelListener<OAuthProvider>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<OAuthProvider>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}

		containsOAuthConnection = new ContainsOAuthConnection();
	}

	public void destroy() {
		EntityCacheUtil.removeCache(OAuthProviderImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@BeanReference(type = OAuthConnectionPersistence.class)
	protected OAuthConnectionPersistence oAuthConnectionPersistence;
	@BeanReference(type = OAuthProviderPersistence.class)
	protected OAuthProviderPersistence oAuthProviderPersistence;
	@BeanReference(type = ResourcePersistence.class)
	protected ResourcePersistence resourcePersistence;
	@BeanReference(type = UserPersistence.class)
	protected UserPersistence userPersistence;
	protected ContainsOAuthConnection containsOAuthConnection;

	protected class ContainsOAuthConnection {
		protected ContainsOAuthConnection() {
			_mappingSqlQuery = MappingSqlQueryFactoryUtil.getMappingSqlQuery(getDataSource(),
					_SQL_CONTAINSOAUTHCONNECTION,
					new int[] { java.sql.Types.BIGINT, java.sql.Types.BIGINT },
					RowMapper.COUNT);
		}

		protected boolean contains(long providerId, long connectionId) {
			List<Integer> results = _mappingSqlQuery.execute(new Object[] {
						new Long(providerId), new Long(connectionId)
					});

			if (results.size() > 0) {
				Integer count = results.get(0);

				if (count.intValue() > 0) {
					return true;
				}
			}

			return false;
		}

		private MappingSqlQuery<Integer> _mappingSqlQuery;
	}

	private static final String _SQL_SELECT_OAUTHPROVIDER = "SELECT oAuthProvider FROM OAuthProvider oAuthProvider";
	private static final String _SQL_SELECT_OAUTHPROVIDER_WHERE = "SELECT oAuthProvider FROM OAuthProvider oAuthProvider WHERE ";
	private static final String _SQL_COUNT_OAUTHPROVIDER = "SELECT COUNT(oAuthProvider) FROM OAuthProvider oAuthProvider";
	private static final String _SQL_COUNT_OAUTHPROVIDER_WHERE = "SELECT COUNT(oAuthProvider) FROM OAuthProvider oAuthProvider WHERE ";
	private static final String _SQL_GETOAUTHCONNECTIONS = "SELECT {OAuthProxy_OAuthConnection.*} FROM OAuthProxy_OAuthConnection INNER JOIN OAuthProxy_OAuthProvider ON (OAuthProxy_OAuthProvider.providerId = OAuthProxy_OAuthConnection.providerId) WHERE (OAuthProxy_OAuthProvider.providerId = ?)";
	private static final String _SQL_GETOAUTHCONNECTIONSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM OAuthProxy_OAuthConnection WHERE providerId = ?";
	private static final String _SQL_CONTAINSOAUTHCONNECTION = "SELECT COUNT(*) AS COUNT_VALUE FROM OAuthProxy_OAuthConnection WHERE providerId = ? AND connectionId = ?";
	private static final String _FINDER_COLUMN_REALM_REALM_1 = "oAuthProvider.realm IS NULL";
	private static final String _FINDER_COLUMN_REALM_REALM_2 = "oAuthProvider.realm = ?";
	private static final String _FINDER_COLUMN_REALM_REALM_3 = "(oAuthProvider.realm IS NULL OR oAuthProvider.realm = ?)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "oAuthProvider.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OAuthProvider exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OAuthProvider exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(OAuthProviderPersistenceImpl.class);
	private static OAuthProvider _nullOAuthProvider = new OAuthProviderImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<OAuthProvider> toCacheModel() {
				return _nullOAuthProviderCacheModel;
			}
		};

	private static CacheModel<OAuthProvider> _nullOAuthProviderCacheModel = new CacheModel<OAuthProvider>() {
			public OAuthProvider toEntityModel() {
				return _nullOAuthProvider;
			}
		};
}