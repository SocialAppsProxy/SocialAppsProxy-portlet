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

/**
 * @author Stian Sigvartsen
 */
public interface HttpConstants {
	
	/**
	 * Key for redirect location header.
	 */
    static final String STRING_LOCATION_HEADER = "Location";
    /**
     * Key for content type header.
     */
    static final String STRING_CONTENT_TYPE_HEADER_NAME = "Content-Type";

    /**
     * Key for content length header.
     */
    static final String STRING_CONTENT_LENGTH_HEADER_NAME = "Content-Length";
    /**
     * Key for host header
     */
    static final String STRING_HOST_HEADER_NAME = "Host";
    /**
     * Key for accept-encoding header
     */
    static final String STRING_ACCEPT_ENCODING_HEADER_NAME = "Accept-Encoding";
    /**
     * Key for accept-encoding header
     */
    static final String STRING_CONTENT_ENCODING_HEADER_NAME = "Content-Encoding";
}
