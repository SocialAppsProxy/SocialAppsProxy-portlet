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

package com.wordpress.metaphorm.authProxy.sb.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the o auth connection local service. This utility wraps {@link com.wordpress.metaphorm.authProxy.sb.service.impl.OAuthConnectionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author stiansigvartsen
 * @see OAuthConnectionLocalService
 * @see com.wordpress.metaphorm.authProxy.sb.service.base.OAuthConnectionLocalServiceBaseImpl
 * @see com.wordpress.metaphorm.authProxy.sb.service.impl.OAuthConnectionLocalServiceImpl
 * @generated
 */
public class OAuthConnectionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.wordpress.metaphorm.authProxy.sb.service.impl.OAuthConnectionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the o auth connection to the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConnection the o auth connection
	* @return the o auth connection that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection addOAuthConnection(
		com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection oAuthConnection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addOAuthConnection(oAuthConnection);
	}

	/**
	* Creates a new o auth connection with the primary key. Does not add the o auth connection to the database.
	*
	* @param connectionId the primary key for the new o auth connection
	* @return the new o auth connection
	*/
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection createOAuthConnection(
		long connectionId) {
		return getService().createOAuthConnection(connectionId);
	}

	/**
	* Deletes the o auth connection with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param connectionId the primary key of the o auth connection
	* @return the o auth connection that was removed
	* @throws PortalException if a o auth connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection deleteOAuthConnection(
		long connectionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteOAuthConnection(connectionId);
	}

	/**
	* Deletes the o auth connection from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConnection the o auth connection
	* @return the o auth connection that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection deleteOAuthConnection(
		com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection oAuthConnection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteOAuthConnection(oAuthConnection);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection fetchOAuthConnection(
		long connectionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchOAuthConnection(connectionId);
	}

	/**
	* Returns the o auth connection with the primary key.
	*
	* @param connectionId the primary key of the o auth connection
	* @return the o auth connection
	* @throws PortalException if a o auth connection with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection getOAuthConnection(
		long connectionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getOAuthConnection(connectionId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
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
	public static java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> getOAuthConnections(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOAuthConnections(start, end);
	}

	/**
	* Returns the number of o auth connections.
	*
	* @return the number of o auth connections
	* @throws SystemException if a system exception occurred
	*/
	public static int getOAuthConnectionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getOAuthConnectionsCount();
	}

	/**
	* Updates the o auth connection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param oAuthConnection the o auth connection
	* @return the o auth connection that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection updateOAuthConnection(
		com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection oAuthConnection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateOAuthConnection(oAuthConnection);
	}

	/**
	* Updates the o auth connection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param oAuthConnection the o auth connection
	* @param merge whether to merge the o auth connection with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the o auth connection that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection updateOAuthConnection(
		com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection oAuthConnection,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateOAuthConnection(oAuthConnection, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection addOAuthConnection(
		long companyId, long groupId, long userId, java.lang.String realm)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addOAuthConnection(companyId, groupId, userId, realm);
	}

	public static int getOAuthConnectionsCount(long companyId, long groupId,
		long userId, int oAuthPhase)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOAuthConnectionsCount(companyId, groupId, userId,
			oAuthPhase);
	}

	public static java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> getOAuthConnections(
		long companyId, long groupId, long userId, int oAuthPhase, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getOAuthConnections(companyId, groupId, userId, oAuthPhase,
			start, end);
	}

	public static com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection findByRealm(
		long companyId, long groupId, long userId, java.lang.String realm)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException {
		return getService().findByRealm(companyId, groupId, userId, realm);
	}

	public static java.util.List<com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection> findByUser(
		long companyId, long groupId, long userId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthConnectionException {
		return getService().findByUser(companyId, groupId, userId);
	}

	public static void deleteAllConnections(
		com.liferay.portal.service.ServiceContext serviceContext) {
		getService().deleteAllConnections(serviceContext);
	}

	public static void deleteAllConnections(long companyId, long groupId,
		long userId) {
		getService().deleteAllConnections(companyId, groupId, userId);
	}

	public static void deleteAllConnections()
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAllConnections();
	}

	public static void clearService() {
		_service = null;
	}

	public static OAuthConnectionLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					OAuthConnectionLocalService.class.getName());

			if (invokableLocalService instanceof OAuthConnectionLocalService) {
				_service = (OAuthConnectionLocalService)invokableLocalService;
			}
			else {
				_service = new OAuthConnectionLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(OAuthConnectionLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(OAuthConnectionLocalService service) {
	}

	private static OAuthConnectionLocalService _service;
}