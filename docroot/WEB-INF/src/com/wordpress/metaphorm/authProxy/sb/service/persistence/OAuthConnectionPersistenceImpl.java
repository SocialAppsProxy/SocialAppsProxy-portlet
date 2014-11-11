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
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
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

import com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException;
import com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection;
import com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthConnectionImpl;
import com.wordpress.metaphorm.authProxy.sb.model.impl.OAuthConnectionModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The persistence implementation for the o auth connection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author stiansigvartsen
 * @see OAuthConnectionPersistence
 * @see OAuthConnectionUtil
 * @generated
 */
public class OAuthConnectionPersistenceImpl extends BasePersistenceImpl<OAuthConnection>
	implements OAuthConnectionPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link OAuthConnectionUtil} to access the o auth connection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = OAuthConnectionImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_FETCH_BY_REALM = new FinderPath(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionModelImpl.FINDER_CACHE_ENABLED,
			OAuthConnectionImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByrealm",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				String.class.getName()
			},
			OAuthConnectionModelImpl.COMPANYID_COLUMN_BITMASK |
			OAuthConnectionModelImpl.GROUPID_COLUMN_BITMASK |
			OAuthConnectionModelImpl.USERID_COLUMN_BITMASK |
			OAuthConnectionModelImpl.REALM_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_REALM = new FinderPath(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByrealm",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				String.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID = new FinderPath(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionModelImpl.FINDER_CACHE_ENABLED,
			OAuthConnectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByuserId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID =
		new FinderPath(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionModelImpl.FINDER_CACHE_ENABLED,
			OAuthConnectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByuserId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			OAuthConnectionModelImpl.COMPANYID_COLUMN_BITMASK |
			OAuthConnectionModelImpl.GROUPID_COLUMN_BITMASK |
			OAuthConnectionModelImpl.USERID_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_USERID = new FinderPath(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByuserId",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionModelImpl.FINDER_CACHE_ENABLED,
			OAuthConnectionImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionModelImpl.FINDER_CACHE_ENABLED,
			OAuthConnectionImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	/**
	 * Caches the o auth connection in the entity cache if it is enabled.
	 *
	 * @param oAuthConnection the o auth connection
	 */
	public void cacheResult(OAuthConnection oAuthConnection) {
		EntityCacheUtil.putResult(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionImpl.class, oAuthConnection.getPrimaryKey(),
			oAuthConnection);

		FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REALM,
			new Object[] {
				Long.valueOf(oAuthConnection.getCompanyId()),
				Long.valueOf(oAuthConnection.getGroupId()),
				Long.valueOf(oAuthConnection.getUserId()),
				
			oAuthConnection.getRealm()
			}, oAuthConnection);

		oAuthConnection.resetOriginalValues();
	}

	/**
	 * Caches the o auth connections in the entity cache if it is enabled.
	 *
	 * @param oAuthConnections the o auth connections
	 */
	public void cacheResult(List<OAuthConnection> oAuthConnections) {
		for (OAuthConnection oAuthConnection : oAuthConnections) {
			if (EntityCacheUtil.getResult(
						OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
						OAuthConnectionImpl.class,
						oAuthConnection.getPrimaryKey()) == null) {
				cacheResult(oAuthConnection);
			}
			else {
				oAuthConnection.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all o auth connections.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(OAuthConnectionImpl.class.getName());
		}

		EntityCacheUtil.clearCache(OAuthConnectionImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the o auth connection.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(OAuthConnection oAuthConnection) {
		EntityCacheUtil.removeResult(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionImpl.class, oAuthConnection.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(oAuthConnection);
	}

	@Override
	public void clearCache(List<OAuthConnection> oAuthConnections) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (OAuthConnection oAuthConnection : oAuthConnections) {
			EntityCacheUtil.removeResult(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
				OAuthConnectionImpl.class, oAuthConnection.getPrimaryKey());

			clearUniqueFindersCache(oAuthConnection);
		}
	}

	protected void clearUniqueFindersCache(OAuthConnection oAuthConnection) {
		FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_REALM,
			new Object[] {
				Long.valueOf(oAuthConnection.getCompanyId()),
				Long.valueOf(oAuthConnection.getGroupId()),
				Long.valueOf(oAuthConnection.getUserId()),
				
			oAuthConnection.getRealm()
			});
	}

	/**
	 * Creates a new o auth connection with the primary key. Does not add the o auth connection to the database.
	 *
	 * @param connectionId the primary key for the new o auth connection
	 * @return the new o auth connection
	 */
	public OAuthConnection create(long connectionId) {
		OAuthConnection oAuthConnection = new OAuthConnectionImpl();

		oAuthConnection.setNew(true);
		oAuthConnection.setPrimaryKey(connectionId);

		return oAuthConnection;
	}

	/**
	 * Removes the o auth connection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param connectionId the primary key of the o auth connection
	 * @return the o auth connection that was removed
	 * @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException if a o auth connection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConnection remove(long connectionId)
		throws NoSuchOAuthConnectionException, SystemException {
		return remove(Long.valueOf(connectionId));
	}

	/**
	 * Removes the o auth connection with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the o auth connection
	 * @return the o auth connection that was removed
	 * @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException if a o auth connection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthConnection remove(Serializable primaryKey)
		throws NoSuchOAuthConnectionException, SystemException {
		Session session = null;

		try {
			session = openSession();

			OAuthConnection oAuthConnection = (OAuthConnection)session.get(OAuthConnectionImpl.class,
					primaryKey);

			if (oAuthConnection == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchOAuthConnectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(oAuthConnection);
		}
		catch (NoSuchOAuthConnectionException nsee) {
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
	protected OAuthConnection removeImpl(OAuthConnection oAuthConnection)
		throws SystemException {
		oAuthConnection = toUnwrappedModel(oAuthConnection);

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.delete(session, oAuthConnection);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		clearCache(oAuthConnection);

		return oAuthConnection;
	}

	@Override
	public OAuthConnection updateImpl(
		com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection oAuthConnection,
		boolean merge) throws SystemException {
		oAuthConnection = toUnwrappedModel(oAuthConnection);

		boolean isNew = oAuthConnection.isNew();

		OAuthConnectionModelImpl oAuthConnectionModelImpl = (OAuthConnectionModelImpl)oAuthConnection;

		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, oAuthConnection, merge);

			oAuthConnection.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !OAuthConnectionModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((oAuthConnectionModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(oAuthConnectionModelImpl.getOriginalCompanyId()),
						Long.valueOf(oAuthConnectionModelImpl.getOriginalGroupId()),
						Long.valueOf(oAuthConnectionModelImpl.getOriginalUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);

				args = new Object[] {
						Long.valueOf(oAuthConnectionModelImpl.getCompanyId()),
						Long.valueOf(oAuthConnectionModelImpl.getGroupId()),
						Long.valueOf(oAuthConnectionModelImpl.getUserId())
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_USERID, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID,
					args);
			}
		}

		EntityCacheUtil.putResult(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
			OAuthConnectionImpl.class, oAuthConnection.getPrimaryKey(),
			oAuthConnection);

		if (isNew) {
			FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REALM,
				new Object[] {
					Long.valueOf(oAuthConnection.getCompanyId()),
					Long.valueOf(oAuthConnection.getGroupId()),
					Long.valueOf(oAuthConnection.getUserId()),
					
				oAuthConnection.getRealm()
				}, oAuthConnection);
		}
		else {
			if ((oAuthConnectionModelImpl.getColumnBitmask() &
					FINDER_PATH_FETCH_BY_REALM.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						Long.valueOf(oAuthConnectionModelImpl.getOriginalCompanyId()),
						Long.valueOf(oAuthConnectionModelImpl.getOriginalGroupId()),
						Long.valueOf(oAuthConnectionModelImpl.getOriginalUserId()),
						
						oAuthConnectionModelImpl.getOriginalRealm()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_REALM, args);

				FinderCacheUtil.removeResult(FINDER_PATH_FETCH_BY_REALM, args);

				FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REALM,
					new Object[] {
						Long.valueOf(oAuthConnection.getCompanyId()),
						Long.valueOf(oAuthConnection.getGroupId()),
						Long.valueOf(oAuthConnection.getUserId()),
						
					oAuthConnection.getRealm()
					}, oAuthConnection);
			}
		}

		return oAuthConnection;
	}

	protected OAuthConnection toUnwrappedModel(OAuthConnection oAuthConnection) {
		if (oAuthConnection instanceof OAuthConnectionImpl) {
			return oAuthConnection;
		}

		OAuthConnectionImpl oAuthConnectionImpl = new OAuthConnectionImpl();

		oAuthConnectionImpl.setNew(oAuthConnection.isNew());
		oAuthConnectionImpl.setPrimaryKey(oAuthConnection.getPrimaryKey());

		oAuthConnectionImpl.setConnectionId(oAuthConnection.getConnectionId());
		oAuthConnectionImpl.setCompanyId(oAuthConnection.getCompanyId());
		oAuthConnectionImpl.setGroupId(oAuthConnection.getGroupId());
		oAuthConnectionImpl.setUserId(oAuthConnection.getUserId());
		oAuthConnectionImpl.setUserName(oAuthConnection.getUserName());
		oAuthConnectionImpl.setCreateDate(oAuthConnection.getCreateDate());
		oAuthConnectionImpl.setModifiedDate(oAuthConnection.getModifiedDate());
		oAuthConnectionImpl.setToken(oAuthConnection.getToken());
		oAuthConnectionImpl.setTokenSecret(oAuthConnection.getTokenSecret());
		oAuthConnectionImpl.setRealm(oAuthConnection.getRealm());
		oAuthConnectionImpl.setPhase(oAuthConnection.getPhase());

		return oAuthConnectionImpl;
	}

	/**
	 * Returns the o auth connection with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth connection
	 * @return the o auth connection
	 * @throws com.liferay.portal.NoSuchModelException if a o auth connection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthConnection findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the o auth connection with the primary key or throws a {@link com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException} if it could not be found.
	 *
	 * @param connectionId the primary key of the o auth connection
	 * @return the o auth connection
	 * @throws com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException if a o auth connection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConnection findByPrimaryKey(long connectionId)
		throws NoSuchOAuthConnectionException, SystemException {
		OAuthConnection oAuthConnection = fetchByPrimaryKey(connectionId);

		if (oAuthConnection == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + connectionId);
			}

			throw new NoSuchOAuthConnectionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				connectionId);
		}

		return oAuthConnection;
	}

	/**
	 * Returns the o auth connection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the o auth connection
	 * @return the o auth connection, or <code>null</code> if a o auth connection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public OAuthConnection fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the o auth connection with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param connectionId the primary key of the o auth connection
	 * @return the o auth connection, or <code>null</code> if a o auth connection with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConnection fetchByPrimaryKey(long connectionId)
		throws SystemException {
		OAuthConnection oAuthConnection = (OAuthConnection)EntityCacheUtil.getResult(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
				OAuthConnectionImpl.class, connectionId);

		if (oAuthConnection == _nullOAuthConnection) {
			return null;
		}

		if (oAuthConnection == null) {
			Session session = null;

			boolean hasException = false;

			try {
				session = openSession();

				oAuthConnection = (OAuthConnection)session.get(OAuthConnectionImpl.class,
						Long.valueOf(connectionId));
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				if (oAuthConnection != null) {
					cacheResult(oAuthConnection);
				}
				else if (!hasException) {
					EntityCacheUtil.putResult(OAuthConnectionModelImpl.ENTITY_CACHE_ENABLED,
						OAuthConnectionImpl.class, connectionId,
						_nullOAuthConnection);
				}

				closeSession(session);
			}
		}

		return oAuthConnection;
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
	public OAuthConnection findByrealm(long companyId, long groupId,
		long userId, String realm)
		throws NoSuchOAuthConnectionException, SystemException {
		OAuthConnection oAuthConnection = fetchByrealm(companyId, groupId,
				userId, realm);

		if (oAuthConnection == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(", realm=");
			msg.append(realm);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (_log.isWarnEnabled()) {
				_log.warn(msg.toString());
			}

			throw new NoSuchOAuthConnectionException(msg.toString());
		}

		return oAuthConnection;
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
	public OAuthConnection fetchByrealm(long companyId, long groupId,
		long userId, String realm) throws SystemException {
		return fetchByrealm(companyId, groupId, userId, realm, true);
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
	public OAuthConnection fetchByrealm(long companyId, long groupId,
		long userId, String realm, boolean retrieveFromCache)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, groupId, userId, realm };

		Object result = null;

		if (retrieveFromCache) {
			result = FinderCacheUtil.getResult(FINDER_PATH_FETCH_BY_REALM,
					finderArgs, this);
		}

		if (result instanceof OAuthConnection) {
			OAuthConnection oAuthConnection = (OAuthConnection)result;

			if ((companyId != oAuthConnection.getCompanyId()) ||
					(groupId != oAuthConnection.getGroupId()) ||
					(userId != oAuthConnection.getUserId()) ||
					!Validator.equals(realm, oAuthConnection.getRealm())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_OAUTHCONNECTION_WHERE);

			query.append(_FINDER_COLUMN_REALM_COMPANYID_2);

			query.append(_FINDER_COLUMN_REALM_GROUPID_2);

			query.append(_FINDER_COLUMN_REALM_USERID_2);

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

			query.append(OAuthConnectionModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				qPos.add(userId);

				if (realm != null) {
					qPos.add(realm);
				}

				List<OAuthConnection> list = q.list();

				result = list;

				OAuthConnection oAuthConnection = null;

				if (list.isEmpty()) {
					FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REALM,
						finderArgs, list);
				}
				else {
					oAuthConnection = list.get(0);

					cacheResult(oAuthConnection);

					if ((oAuthConnection.getCompanyId() != companyId) ||
							(oAuthConnection.getGroupId() != groupId) ||
							(oAuthConnection.getUserId() != userId) ||
							(oAuthConnection.getRealm() == null) ||
							!oAuthConnection.getRealm().equals(realm)) {
						FinderCacheUtil.putResult(FINDER_PATH_FETCH_BY_REALM,
							finderArgs, oAuthConnection);
					}
				}

				return oAuthConnection;
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
				return (OAuthConnection)result;
			}
		}
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
	public List<OAuthConnection> findByuserId(long companyId, long groupId,
		long userId) throws SystemException {
		return findByuserId(companyId, groupId, userId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
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
	public List<OAuthConnection> findByuserId(long companyId, long groupId,
		long userId, int start, int end) throws SystemException {
		return findByuserId(companyId, groupId, userId, start, end, null);
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
	public List<OAuthConnection> findByuserId(long companyId, long groupId,
		long userId, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] { companyId, groupId, userId };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_USERID;
			finderArgs = new Object[] {
					companyId, groupId, userId,
					
					start, end, orderByComparator
				};
		}

		List<OAuthConnection> list = (List<OAuthConnection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (OAuthConnection oAuthConnection : list) {
				if ((companyId != oAuthConnection.getCompanyId()) ||
						(groupId != oAuthConnection.getGroupId()) ||
						(userId != oAuthConnection.getUserId())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_OAUTHCONNECTION_WHERE);

			query.append(_FINDER_COLUMN_USERID_COMPANYID_2);

			query.append(_FINDER_COLUMN_USERID_GROUPID_2);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(OAuthConnectionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				qPos.add(userId);

				list = (List<OAuthConnection>)QueryUtil.list(q, getDialect(),
						start, end);
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
	public OAuthConnection findByuserId_First(long companyId, long groupId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchOAuthConnectionException, SystemException {
		OAuthConnection oAuthConnection = fetchByuserId_First(companyId,
				groupId, userId, orderByComparator);

		if (oAuthConnection != null) {
			return oAuthConnection;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOAuthConnectionException(msg.toString());
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
	public OAuthConnection fetchByuserId_First(long companyId, long groupId,
		long userId, OrderByComparator orderByComparator)
		throws SystemException {
		List<OAuthConnection> list = findByuserId(companyId, groupId, userId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public OAuthConnection findByuserId_Last(long companyId, long groupId,
		long userId, OrderByComparator orderByComparator)
		throws NoSuchOAuthConnectionException, SystemException {
		OAuthConnection oAuthConnection = fetchByuserId_Last(companyId,
				groupId, userId, orderByComparator);

		if (oAuthConnection != null) {
			return oAuthConnection;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", groupId=");
		msg.append(groupId);

		msg.append(", userId=");
		msg.append(userId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchOAuthConnectionException(msg.toString());
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
	public OAuthConnection fetchByuserId_Last(long companyId, long groupId,
		long userId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByuserId(companyId, groupId, userId);

		List<OAuthConnection> list = findByuserId(companyId, groupId, userId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	public OAuthConnection[] findByuserId_PrevAndNext(long connectionId,
		long companyId, long groupId, long userId,
		OrderByComparator orderByComparator)
		throws NoSuchOAuthConnectionException, SystemException {
		OAuthConnection oAuthConnection = findByPrimaryKey(connectionId);

		Session session = null;

		try {
			session = openSession();

			OAuthConnection[] array = new OAuthConnectionImpl[3];

			array[0] = getByuserId_PrevAndNext(session, oAuthConnection,
					companyId, groupId, userId, orderByComparator, true);

			array[1] = oAuthConnection;

			array[2] = getByuserId_PrevAndNext(session, oAuthConnection,
					companyId, groupId, userId, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected OAuthConnection getByuserId_PrevAndNext(Session session,
		OAuthConnection oAuthConnection, long companyId, long groupId,
		long userId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_OAUTHCONNECTION_WHERE);

		query.append(_FINDER_COLUMN_USERID_COMPANYID_2);

		query.append(_FINDER_COLUMN_USERID_GROUPID_2);

		query.append(_FINDER_COLUMN_USERID_USERID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(OAuthConnectionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(companyId);

		qPos.add(groupId);

		qPos.add(userId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(oAuthConnection);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<OAuthConnection> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the o auth connections.
	 *
	 * @return the o auth connections
	 * @throws SystemException if a system exception occurred
	 */
	public List<OAuthConnection> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	public List<OAuthConnection> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
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
	public List<OAuthConnection> findAll(int start, int end,
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

		List<OAuthConnection> list = (List<OAuthConnection>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_OAUTHCONNECTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_OAUTHCONNECTION.concat(OAuthConnectionModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (orderByComparator == null) {
					list = (List<OAuthConnection>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<OAuthConnection>)QueryUtil.list(q,
							getDialect(), start, end);
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
	 * Removes the o auth connection where companyId = &#63; and groupId = &#63; and userId = &#63; and realm = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @param realm the realm
	 * @return the o auth connection that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public OAuthConnection removeByrealm(long companyId, long groupId,
		long userId, String realm)
		throws NoSuchOAuthConnectionException, SystemException {
		OAuthConnection oAuthConnection = findByrealm(companyId, groupId,
				userId, realm);

		return remove(oAuthConnection);
	}

	/**
	 * Removes all the o auth connections where companyId = &#63; and groupId = &#63; and userId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByuserId(long companyId, long groupId, long userId)
		throws SystemException {
		for (OAuthConnection oAuthConnection : findByuserId(companyId, groupId,
				userId)) {
			remove(oAuthConnection);
		}
	}

	/**
	 * Removes all the o auth connections from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (OAuthConnection oAuthConnection : findAll()) {
			remove(oAuthConnection);
		}
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
	public int countByrealm(long companyId, long groupId, long userId,
		String realm) throws SystemException {
		Object[] finderArgs = new Object[] { companyId, groupId, userId, realm };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_REALM,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_OAUTHCONNECTION_WHERE);

			query.append(_FINDER_COLUMN_REALM_COMPANYID_2);

			query.append(_FINDER_COLUMN_REALM_GROUPID_2);

			query.append(_FINDER_COLUMN_REALM_USERID_2);

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

				qPos.add(companyId);

				qPos.add(groupId);

				qPos.add(userId);

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
	 * Returns the number of o auth connections where companyId = &#63; and groupId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param groupId the group ID
	 * @param userId the user ID
	 * @return the number of matching o auth connections
	 * @throws SystemException if a system exception occurred
	 */
	public int countByuserId(long companyId, long groupId, long userId)
		throws SystemException {
		Object[] finderArgs = new Object[] { companyId, groupId, userId };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_USERID,
				finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_OAUTHCONNECTION_WHERE);

			query.append(_FINDER_COLUMN_USERID_COMPANYID_2);

			query.append(_FINDER_COLUMN_USERID_GROUPID_2);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				qPos.add(groupId);

				qPos.add(userId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_USERID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of o auth connections.
	 *
	 * @return the number of o auth connections
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_OAUTHCONNECTION);

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
	 * Initializes the o auth connection persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<OAuthConnection>> listenersList = new ArrayList<ModelListener<OAuthConnection>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<OAuthConnection>)InstanceFactory.newInstance(
							listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(OAuthConnectionImpl.class.getName());
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
	private static final String _SQL_SELECT_OAUTHCONNECTION = "SELECT oAuthConnection FROM OAuthConnection oAuthConnection";
	private static final String _SQL_SELECT_OAUTHCONNECTION_WHERE = "SELECT oAuthConnection FROM OAuthConnection oAuthConnection WHERE ";
	private static final String _SQL_COUNT_OAUTHCONNECTION = "SELECT COUNT(oAuthConnection) FROM OAuthConnection oAuthConnection";
	private static final String _SQL_COUNT_OAUTHCONNECTION_WHERE = "SELECT COUNT(oAuthConnection) FROM OAuthConnection oAuthConnection WHERE ";
	private static final String _FINDER_COLUMN_REALM_COMPANYID_2 = "oAuthConnection.companyId = ? AND ";
	private static final String _FINDER_COLUMN_REALM_GROUPID_2 = "oAuthConnection.groupId = ? AND ";
	private static final String _FINDER_COLUMN_REALM_USERID_2 = "oAuthConnection.userId = ? AND ";
	private static final String _FINDER_COLUMN_REALM_REALM_1 = "oAuthConnection.realm IS NULL";
	private static final String _FINDER_COLUMN_REALM_REALM_2 = "oAuthConnection.realm = ?";
	private static final String _FINDER_COLUMN_REALM_REALM_3 = "(oAuthConnection.realm IS NULL OR oAuthConnection.realm = ?)";
	private static final String _FINDER_COLUMN_USERID_COMPANYID_2 = "oAuthConnection.companyId = ? AND ";
	private static final String _FINDER_COLUMN_USERID_GROUPID_2 = "oAuthConnection.groupId = ? AND ";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "oAuthConnection.userId = ?";
	private static final String _ORDER_BY_ENTITY_ALIAS = "oAuthConnection.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No OAuthConnection exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No OAuthConnection exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(OAuthConnectionPersistenceImpl.class);
	private static OAuthConnection _nullOAuthConnection = new OAuthConnectionImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<OAuthConnection> toCacheModel() {
				return _nullOAuthConnectionCacheModel;
			}
		};

	private static CacheModel<OAuthConnection> _nullOAuthConnectionCacheModel = new CacheModel<OAuthConnection>() {
			public OAuthConnection toEntityModel() {
				return _nullOAuthConnection;
			}
		};
}