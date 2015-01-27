package com.wordpress.metaphorm.authProxy.httpClient.impl;

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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Stian Sigvartsen
 */
public class OAuthProxyConnectionHttpURLConnectionImpl extends AbstractOAuthProxyConnectionImpl {
	
	private HttpServletRequest servletReq;
	private HttpURLConnection uRLConn;
	
	public OAuthProxyConnectionHttpURLConnectionImpl(HttpServletRequest servletReq) throws MalformedURLException, IOException {
		URL uRL = deriveURL(servletReq);
		
		this.servletReq = servletReq;
		
		this.uRLConn = (HttpURLConnection)uRL.openConnection();
	}
	
	public boolean isNegotiatingConnection() {
		return true;
	}
	
	public HttpURLConnection getHttpURLConnection() {
		return this.uRLConn;
	}
	
	@Override
	public URL getRequestedURL() throws MalformedURLException {
		return new URL(servletReq.getRequestURL().toString());
	}
		
	public void sendRequest() throws IOException {
		uRLConn.connect();
	}
	
	public void reset() throws IOException {
		this.uRLConn = (HttpURLConnection)this.uRLConn.getURL().openConnection();
	}
	
	@Override
	public InputStream getInputStream() throws IOException {
		return uRLConn.getInputStream();
	}

	@Override
	public int getResponseCode() throws IOException {
		return uRLConn.getResponseCode();
	}

	@Override
	public String getContentType() {
		return uRLConn.getContentType();
	}

	@Override
	public Map<String, List<String>> getResponseHeaders()
			throws IOException {
		
		Map<String, List<String>> headerMap = new HashMap<String, List<String>>();
		
		Map<String, List<String>> connHeadersMap = this.uRLConn.getHeaderFields();
		for (Map.Entry<String, List<String>> entry : connHeadersMap.entrySet()) {			
			headerMap.put(entry.getKey(), entry.getValue());
		}
		
		return headerMap;
	}

	/*
	@Override
	public void disconnect() {
		
	}

	@Override
	public boolean usingProxy() {
		return false;
	}
	
    @Override 
    public final InputStream getInputStream() throws IOException {
    	return uRLConn.getInputStream();
    }
    
    @Override
    public void addRequestProperty(String key, String value) {
    	uRLConn.addRequestProperty(key, value);
    }
    
    @Override
    public boolean getAllowUserInteraction() {
    	return uRLConn.getAllowUserInteraction();
    }
    
    @Override
    public int getConnectTimeout() {
    	return uRLConn.getConnectTimeout();
    }
    
    @Override
    public Object getContent() throws IOException {
    	return uRLConn.getContent();
    }
    
    @Override
    public Object getContent(Class[] classes) throws IOException {
    	return uRLConn.getContent(classes);
    }
    
    @Override
    public String getContentEncoding() {
    	return uRLConn.getContentEncoding();
    }
    
    @Override
    public int getContentLength() {
    	return uRLConn.getContentLength();
    }
    
    @Override
    public String getContentType() {
    	return uRLConn.getContentType();
    }
    
    @Override
    public long getDate() {
    	return uRLConn.getDate();
    }
    
    @Override
    public boolean getDefaultUseCaches() {
    	// TODO Auto-generated method stub
    	return uRLConn.getDefaultUseCaches();
    }
    
    @Override
    public boolean getDoInput() {
    	// TODO Auto-generated method stub
    	return uRLConn.getDoInput();
    }
    
    @Override
    public boolean getDoOutput() {
    	// TODO Auto-generated method stub
    	return uRLConn.getDoOutput();
    }
    
    @Override
    public InputStream getErrorStream() {
    	// TODO Auto-generated method stub
    	return uRLConn.getErrorStream();
    }

	@Override
	public String getHeaderField(int n) {
		// TODO Auto-generated method stub
		return uRLConn.getHeaderField(n);
	}

	@Override
	public long getHeaderFieldDate(String name, long Default) {
		// TODO Auto-generated method stub
		return uRLConn.getHeaderFieldDate(name, Default);
	}

	@Override
	public String getHeaderFieldKey(int n) {
		// TODO Auto-generated method stub
		return uRLConn.getHeaderFieldKey(n);
	}

	@Override
	public boolean getInstanceFollowRedirects() {
		// TODO Auto-generated method stub
		return uRLConn.getInstanceFollowRedirects();
	}

	@Override
	public Permission getPermission() throws IOException {
		// TODO Auto-generated method stub
		return uRLConn.getPermission();
	}

	@Override
	public String getRequestMethod() {
		// TODO Auto-generated method stub
		return uRLConn.getRequestMethod();
	}

	@Override
	public int getResponseCode() throws IOException {
		// TODO Auto-generated method stub
		return uRLConn.getResponseCode();
	}

	@Override
	public String getResponseMessage() throws IOException {
		// TODO Auto-generated method stub
		return uRLConn.getResponseMessage();
	}

	@Override
	public void setChunkedStreamingMode(int chunklen) {
		// TODO Auto-generated method stub
		uRLConn.setChunkedStreamingMode(chunklen);
	}

	@Override
	public void setFixedLengthStreamingMode(int contentLength) {
		// TODO Auto-generated method stub
		uRLConn.setFixedLengthStreamingMode(contentLength);
	}

	@Override
	public void setInstanceFollowRedirects(boolean followRedirects) {
		// TODO Auto-generated method stub
		uRLConn.setInstanceFollowRedirects(followRedirects);
	}

	@Override
	public void setRequestMethod(String method) throws ProtocolException {
		// TODO Auto-generated method stub
		uRLConn.setRequestMethod(method);
	}

	@Override
	public long getExpiration() {
		// TODO Auto-generated method stub
		return uRLConn.getExpiration();
	}

	@Override
	public String getHeaderField(String name) {
		// TODO Auto-generated method stub
		return uRLConn.getHeaderField(name);
	}

	@Override
	public int getHeaderFieldInt(String name, int Default) {
		// TODO Auto-generated method stub
		return uRLConn.getHeaderFieldInt(name, Default);
	}

	@Override
	public Map<String, List<String>> getHeaderFields() {
		// TODO Auto-generated method stub
		return uRLConn.getHeaderFields();
	}

	@Override
	public long getIfModifiedSince() {
		// TODO Auto-generated method stub
		return uRLConn.getIfModifiedSince();
	}

	@Override
	public long getLastModified() {
		// TODO Auto-generated method stub
		return uRLConn.getLastModified();
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		return uRLConn.getOutputStream();
	}

	@Override
	public int getReadTimeout() {
		// TODO Auto-generated method stub
		return uRLConn.getReadTimeout();
	}

	@Override
	public Map<String, List<String>> getRequestProperties() {
		// TODO Auto-generated method stub
		return uRLConn.getRequestProperties();
	}

	@Override
	public String getRequestProperty(String key) {
		// TODO Auto-generated method stub
		return uRLConn.getRequestProperty(key);
	}

	@Override
	public boolean getUseCaches() {
		// TODO Auto-generated method stub
		return uRLConn.getUseCaches();
	}

	@Override
	public void setAllowUserInteraction(boolean allowuserinteraction) {
		// TODO Auto-generated method stub
		uRLConn.setAllowUserInteraction(allowuserinteraction);
	}

	@Override
	public void setConnectTimeout(int timeout) {
		// TODO Auto-generated method stub
		uRLConn.setConnectTimeout(timeout);
	}

	@Override
	public void setDefaultUseCaches(boolean defaultusecaches) {
		// TODO Auto-generated method stub
		uRLConn.setDefaultUseCaches(defaultusecaches);
	}

	@Override
	public void setDoInput(boolean doinput) {
		// TODO Auto-generated method stub
		uRLConn.setDoInput(doinput);
	}

	@Override
	public void setDoOutput(boolean dooutput) {
		// TODO Auto-generated method stub
		uRLConn.setDoOutput(dooutput);
	}

	@Override
	public void setIfModifiedSince(long ifmodifiedsince) {
		// TODO Auto-generated method stub
		uRLConn.setIfModifiedSince(ifmodifiedsince);
	}

	@Override
	public void setReadTimeout(int timeout) {
		// TODO Auto-generated method stub
		uRLConn.setReadTimeout(timeout);
	}

	@Override
	public void setRequestProperty(String key, String value) {
		// TODO Auto-generated method stub
		uRLConn.setRequestProperty(key, value);
	}

	@Override
	public void setUseCaches(boolean usecaches) {
		// TODO Auto-generated method stub
		uRLConn.setUseCaches(usecaches);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return uRLConn.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return uRLConn.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return uRLConn.hashCode();
	}
	*/
	
	private static Log _log = LogFactoryUtil.getLog(OAuthProxyConnectionHttpURLConnectionImpl.class);
}
