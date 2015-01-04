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

package com.wordpress.metaphorm.authProxy.sb.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.service.ServiceContext;
import com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException;
import com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection;
import com.wordpress.metaphorm.authProxy.sb.service.base.OAuthConnectionLocalServiceBaseImpl;
import com.wordpress.metaphorm.authProxy.state.OAuthState;

import java.util.LinkedList;
import java.util.List;

/**
 * The implementation of the o auth connection local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.wordpress.metaphorm.authProxy.sb.service.OAuthConnectionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author stiansigvartsen
 * @see com.wordpress.metaphorm.authProxy.sb.service.base.OAuthConnectionLocalServiceBaseImpl
 * @see com.wordpress.metaphorm.authProxy.sb.service.OAuthConnectionLocalServiceUtil
 */
public class OAuthConnectionLocalServiceImpl
	extends OAuthConnectionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.wordpress.metaphorm.authProxy.sb.service.OAuthConnectionLocalServiceUtil} to access the o auth connection local service.
	 */
	
	public OAuthConnection addOAuthConnection(long companyId, long groupId, long userId, String realm) throws SystemException {

		groupId = 0; // For the moment, all connections are created globally to a portal instance
		
		long connectionId = counterLocalService.increment(OAuthConnection.class.getName());
	
		OAuthConnection conn = super.createOAuthConnection(connectionId);
		conn.setCompanyId(companyId);
		conn.setGroupId(groupId);
		conn.setUserId(userId);
		conn.setRealm(realm);
		conn.setPhase((short)OAuthState.REQUEST_PHASE);
		
		super.addOAuthConnection(conn);
		
		oAuthConnectionPersistence.clearCache(conn);
		
		_log.debug("addOAuthConnection(" + companyId + ", " + groupId + ", " + "\"" + realm + "\") created connection " + connectionId);
		
		return conn;
	}
	
	@Override
	public OAuthConnection updateOAuthConnection(OAuthConnection oAuthConnection)
			throws SystemException {

		OAuthConnection ret = super.updateOAuthConnection(oAuthConnection);
		getOAuthConnectionPersistence().clearCache();
		return ret;
	}
	
	@Override
	public OAuthConnection updateOAuthConnection(
			OAuthConnection oAuthConnection, boolean merge)
			throws SystemException {

		OAuthConnection ret = super.updateOAuthConnection(oAuthConnection, merge);
		getOAuthConnectionPersistence().clearCache();
		return ret;
		
	}
	
	@Override
	public OAuthConnection deleteOAuthConnection(OAuthConnection oAuthConnection)
			throws SystemException {
		
		OAuthConnection ret = super.deleteOAuthConnection(oAuthConnection);
		getOAuthConnectionPersistence().clearCache();
		return ret;
	}
	
	@Override
	public OAuthConnection deleteOAuthConnection(long connectionId)
			throws PortalException, SystemException {
		
		OAuthConnection ret = super.deleteOAuthConnection(connectionId);
		getOAuthConnectionPersistence().clearCache();
		return ret;
	}
	
	public int getOAuthConnectionsCount(long companyId, long groupId, long userId, int oAuthPhase) throws SystemException {
		
		groupId = 0; // For the moment, all connections are created globally to a portal instance
		
		try {
			return findByUser(companyId, groupId, userId).size();
		} catch (NoSuchOAuthConnectionException e) {
			return 0;
		}
	}
	
	public List<OAuthConnection> getOAuthConnections(long companyId, long groupId, long userId, int oAuthPhase, int start, int end)
			throws SystemException {

		groupId = 0; // For the moment, all connections are created globally to a portal instance
		
		try {
			
			List<OAuthConnection> connections = findByUser(companyId, groupId, userId);
			
			if (connections != null) {
				
				if (oAuthPhase != -1) {
					List<OAuthConnection> newConnectionsList = new LinkedList<OAuthConnection>();
					for (OAuthConnection conn : connections) {
						
						if (conn.getPhase() == oAuthPhase) {
							newConnectionsList.add(conn);
						}
					}
					connections = newConnectionsList;
				}
				
				return connections.subList(start, Math.min(connections.size(), end));
			}
			
			return new LinkedList<OAuthConnection>();
		
		} catch (NoSuchOAuthConnectionException e) {

			return new LinkedList<OAuthConnection>();
		}
	}
	
	public OAuthConnection findByRealm(long companyId, long groupId, long userId, String realm) throws SystemException, NoSuchOAuthConnectionException {
		
		groupId = 0; // For the moment, all connections are created globally to a portal instance
		
		_log.debug("findByRealm(" + companyId + ", " + groupId + ", " + "\"" + realm + "\")");
		OAuthConnection conn = oAuthConnectionPersistence.findByrealm(companyId, groupId, userId, realm);
		_log.debug("Returning connection " + conn.getPrimaryKey());
		
		return conn;
	}

	public List<OAuthConnection> findByUser(long companyId, long groupId, long userId) throws SystemException, NoSuchOAuthConnectionException {

		return oAuthConnectionPersistence.findByuserId(companyId, groupId, userId);
	}

	public void deleteAllConnections(ServiceContext serviceContext) {
		
		deleteAllConnections(				
				serviceContext.getCompanyId(),
				0, // For the moment, all connections are created globally to a portal instance  //serviceContext.getScopeGroupId(),
				serviceContext.getUserId());		
	}
	
	public void deleteAllConnections(long companyId, long groupId, long userId) {
		
		groupId = 0; // For the moment, all connections are created globally to a portal instance
		
		try {
			
			List<OAuthConnection> connsList = findByUser(companyId, groupId, userId);
			for (OAuthConnection conn : connsList) {
				super.deleteOAuthConnection(conn);
			}
			
			oAuthConnectionPersistence.clearCache();
			
		} catch (NoSuchOAuthConnectionException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteAllConnections() throws SystemException {
		oAuthConnectionPersistence.removeAll();
	}
	
	private static Log _log = LogFactoryUtil.getLog(OAuthConnectionLocalServiceImpl.class);
}