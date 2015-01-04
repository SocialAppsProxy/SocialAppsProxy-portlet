package com.wordpress.metaphorm.authProxy.httpClient;

/**
 * Copyright (c) 2014-present Stian Sigvartsen. All rights reserved.
 *
 * This file is part of Social Apps Proxy.
 *
 * Social Apps Proxy is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Social Apps Proxy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Social Apps Proxy.  If not, see <http://www.gnu.org/licenses/>.
 */

import com.wordpress.metaphorm.authProxy.OAuthProviderConfigurationException;
import com.wordpress.metaphorm.authProxy.RedirectRequiredException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author Stian Sigvartsen
 */
public interface AuthProxyConnection {

	public void sendRequest() throws IOException;
	public void connect() throws RedirectRequiredException, OAuthProviderConfigurationException, IOException;
	public InputStream getInputStream() throws IOException;
	public int getResponseCode() throws IOException;
	public String getContentType();
	public Map<String, List<String>> getResponseHeaders() throws IOException;
}
