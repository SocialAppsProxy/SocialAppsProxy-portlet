package com.wordpress.metaphorm.authProxy;

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

import java.io.IOException;
import java.net.URL;

/**
 * @author Stian Sigvartsen
 */
public class RedirectRequiredException extends IOException {

	private static final long serialVersionUID = 1L;

	private URL url;
	
	public RedirectRequiredException(URL url) {
		super();
		this.url = url;
	}
	
	public URL getURL() {
		return url;
	}
}
