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

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.wordpress.metaphorm.authProxy.sb.NoSuchOAuthProviderException;
import com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection;
import com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider;
import com.wordpress.metaphorm.authProxy.sb.service.OAuthProviderLocalServiceUtil;
import com.wordpress.metaphorm.authProxy.sb.service.base.OAuthProviderLocalServiceBaseImpl;
import com.wordpress.metaphorm.authProxy.sb.service.persistence.OAuthProviderPersistenceImpl;

import java.net.URL;
import java.util.List;

/**
 * The implementation of the o auth provider local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.wordpress.metaphorm.authProxy.sb.service.OAuthProviderLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author stiansigvartsen
 * @see com.wordpress.metaphorm.authProxy.sb.service.base.OAuthProviderLocalServiceBaseImpl
 * @see com.wordpress.metaphorm.authProxy.sb.service.OAuthProviderLocalServiceUtil
 */
public class OAuthProviderLocalServiceImpl
	extends OAuthProviderLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.wordpress.metaphorm.authProxy.sb.service.OAuthProviderLocalServiceUtil} to access the o auth provider local service.
	 */
	
	public OAuthProvider getProviderForRealm(String realm) 
			throws NoSuchOAuthProviderException, SystemException {
		
		return oAuthProviderPersistence.findByRealm(realm);
	}
	
	public OAuthProvider addOAuthProvider(long userId, String realm, String consumerKey, String consumerSecret, String requestTokenURL, String authoriseURL, String accessTokenURL) throws SystemException {

		long providerId = counterLocalService.increment(OAuthProvider.class.getName());
	
		OAuthProvider provider = super.createOAuthProvider(providerId);
		provider.setRealm(realm);
		provider.setConsumerKey(consumerKey);
		provider.setConsumerSecret(consumerSecret);
		provider.setRequestTokenURL(requestTokenURL);
		provider.setAuthoriseURL(authoriseURL);
		provider.setAccessTokenURL(accessTokenURL);
		
		updateOAuthProvider(provider);
		
		return provider;
	}
	
	public List<OAuthProvider> getAllOAuthProviders() 
			throws SystemException {
		
		return oAuthProviderPersistence.findAll();
	}
	
	public OAuthProvider getMatchingOAuthProvider(URL urlObj) throws NoSuchOAuthProviderException, SystemException {
		
		List<com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider> sbOAuthProvidersList = getAllOAuthProviders();
		
		String urlStr = urlObj.toString();
		
		for (com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider provider : sbOAuthProvidersList) {
			String urlPatternStr = provider.getPreemptiveAuthURLPattern();
			
			if (urlPatternStr != null && urlStr.matches(urlPatternStr)) return provider;
		}		
		
		throw new NoSuchOAuthProviderException("Unable to determine an appropriate OAuth provider for the given URL alone");
	}		
	
	private static Log _log = LogFactoryUtil.getLog(OAuthProviderLocalServiceImpl.class);
}