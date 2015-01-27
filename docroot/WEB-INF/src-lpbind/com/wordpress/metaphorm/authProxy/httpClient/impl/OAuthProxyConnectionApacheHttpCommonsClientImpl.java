package com.wordpress.metaphorm.authProxy.httpClient.impl;
/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 *
 * This class is partly based on another Servlet licensed under Apache License 2.0. Differences include...
 *  
 *  - allow any HTTP endpoint to be proxied
 *  - provide an implementation of com.wordpress.metaphorm.authProxy.httpClient.AuthProxyConnection interface 
 *  - provide a concrete implementation of com.wordpress.metaphorm.authProxy.httpClient.impl.AbstractOAuthProxyConnectionImpl
 * 
 * The original Servlet source code can be found here:
 * https://svn.apache.org/repos/asf/rave/donations/ogce-gadget-container/ishindig-webapp/src/main/java/cgl/shindig/layoutmanager/servlet/ProxyServlet.java
 *
 */

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.wordpress.metaphorm.authProxy.RedirectRequiredException;
import com.wordpress.metaphorm.authProxy.httpClient.HttpConstants;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

/**
 * @author Stian Sigvartsen
 */
public class OAuthProxyConnectionApacheHttpCommonsClientImpl extends AbstractOAuthProxyConnectionImpl {

	/**
	 * Serialization UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * The directory to use to temporarily store uploaded files
     */
    private static final File FILE_UPLOAD_TEMP_DIRECTORY = new File(System.getProperty("java.io.tmpdir"));
    
	/**
	 * The maximum size for uploaded files in bytes. Default value is 5MB.
	 */
	private int intMaxFileUploadSize = 5 * 1024 * 1024;
    
	private HttpServletRequest servletReq;
	private int httpStatusCode;
	private HttpMethod httpMethod;
	private Map<String, List<String>> responseHeaderMap;
	
	public OAuthProxyConnectionApacheHttpCommonsClientImpl(HttpServletRequest servletReq) throws MalformedURLException, IOException {
		
		this.servletReq = servletReq;
		
		prepareHttpMethod(servletReq);
	}

	public boolean isNegotiatingConnection() {
		return true;
	}
	
	public HttpMethod getHttpMethod() {
		return httpMethod;
	}

	private void prepareHttpMethod(HttpServletRequest servletReq)
			throws IOException {
		
		if (servletReq.getMethod().equalsIgnoreCase("GET")) {
			prepareGetMethod(servletReq);
		} else if (servletReq.getMethod().equalsIgnoreCase("POST")) {
			preparePostMethod(servletReq);
		} else {
			throw new IOException("Unsupport HTTP method: " + servletReq.getMethod());
		}
		
		httpStatusCode = 0;
		responseHeaderMap = null;
	}
	
	private void prepareGetMethod(HttpServletRequest httpServletRequest) throws IOException {

		// Create a GET request
		GetMethod getMethodProxyRequest = new GetMethod(this.getProxyURL(httpServletRequest));
		// Forward the request headers
		setProxyRequestHeaders(httpServletRequest, getMethodProxyRequest);	
		
		this.httpMethod = getMethodProxyRequest;
	}
	
	private void preparePostMethod(HttpServletRequest httpServletRequest) throws IOException {
		
    	// Create a standard POST request
    	PostMethod postMethodProxyRequest = new PostMethod(this.getProxyURL(httpServletRequest));
		// Forward the request headers
		setProxyRequestHeaders(httpServletRequest, postMethodProxyRequest);
    	// Check if this is a mulitpart (file upload) POST
    	if(ServletFileUpload.isMultipartContent(httpServletRequest)) {
    		this.handleMultipartPost(postMethodProxyRequest, httpServletRequest);
    	} else {
    		this.handleStandardPost(postMethodProxyRequest, httpServletRequest);
    	}
    	
    	this.httpMethod = postMethodProxyRequest;
	}	
	
	
	@Override
	public URL getRequestedURL() throws MalformedURLException {
		return new URL(servletReq.getRequestURL().toString());
	}
	
	public void sendRequest() throws IOException {
		
		executeProxyRequest(httpMethod);
	}
	
	public void reset() throws IOException {
		
		prepareHttpMethod(this.servletReq);
	}
	
	@Override
	public InputStream getInputStream() throws IOException {

		/*
		List<String> compression;
		if ((compression = this.getResponseHeaders().get(HttpConstants.STRING_CONTENT_ENCODING_HEADER_NAME)) != null) {
		
			if (compression.size() > 0 && compression.get(0).contains("gzip")) {
				return new GZIPInputStream(this.httpMethod.getResponseBodyAsStream());
			}
		}
		*/
		
		return this.httpMethod.getResponseBodyAsStream();
	}

	@Override
	public int getResponseCode() throws IOException {
		return this.httpStatusCode;
	}

	@Override
	public String getContentType() {
		
		Header header;
		String contentType = ((header = this.httpMethod.getResponseHeader(HttpConstants.STRING_CONTENT_TYPE_HEADER_NAME)) != null ? header.getValue() : null);
		
		_log.debug("Response header " + HttpConstants.STRING_CONTENT_TYPE_HEADER_NAME + " = " + contentType);
		
		return contentType;
	}
	
	/**
	 * Performs an HTTP GET request
	 * @param httpServletRequest The {@link HttpServletRequest} object passed
	 *                            in by the servlet engine representing the
	 *                            client request to be proxied
	 * @param httpServletResponse The {@link HttpServletResponse} object by which
	 *                             we can send a proxied response to the client 
	 */
	public void doGet (HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
    		throws IOException, ServletException {

		prepareGetMethod(httpServletRequest);
		
		// TODO: Add code to send respond if servlet deployment support is desirable
	}
	
	
	/**
	 * Performs an HTTP POST request
	 * @param httpServletRequest The {@link HttpServletRequest} object passed
	 *                            in by the servlet engine representing the
	 *                            client request to be proxied
	 * @param httpServletResponse The {@link HttpServletResponse} object by which
	 *                             we can send a proxied response to the client 
	 */
	public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
        	throws IOException, ServletException {
		
		preparePostMethod(httpServletRequest);
    }
	
	/**
	 * Sets up the given {@link PostMethod} to send the same multipart POST
	 * data as was sent in the given {@link HttpServletRequest}
	 * @param postMethodProxyRequest The {@link PostMethod} that we are
	 *                                configuring to send a multipart POST request
	 * @param httpServletRequest The {@link HttpServletRequest} that contains
	 *                            the mutlipart POST data to be sent via the {@link PostMethod}
	 */
    @SuppressWarnings("unchecked")
	private void handleMultipartPost(PostMethod postMethodProxyRequest, HttpServletRequest httpServletRequest)
    		throws IOException {
    	
    	_log.debug("handleMultipartPost()");
    	
    	// Create a factory for disk-based file items
    	DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
    	// Set factory constraints
    	diskFileItemFactory.setSizeThreshold(this.getMaxFileUploadSize());
    	diskFileItemFactory.setRepository(FILE_UPLOAD_TEMP_DIRECTORY);
    	// Create a new file upload handler
    	ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
    	// Parse the request
    	try {
    		// Get the multipart items as a list
    		List<FileItem> listFileItems = (List<FileItem>) servletFileUpload.parseRequest(httpServletRequest);
    		// Create a list to hold all of the parts
    		List<Part> listParts = new ArrayList<Part>();
    		// Iterate the multipart items list
    		for(FileItem fileItemCurrent : listFileItems) {
    			// If the current item is a form field, then create a string part
    			if (fileItemCurrent.isFormField()) {
    				StringPart stringPart = new StringPart(
    						fileItemCurrent.getFieldName(), // The field name
    						fileItemCurrent.getString()     // The field value
    				);
    				// Add the part to the list
    				listParts.add(stringPart);
    			} else {
    				// The item is a file upload, so we create a FilePart
    				FilePart filePart = new FilePart(
    						fileItemCurrent.getFieldName(),    // The field name
    						new ByteArrayPartSource(
    								fileItemCurrent.getName(), // The uploaded file name
    								fileItemCurrent.get()      // The uploaded file contents
    						)
    				);
    				// Add the part to the list
    				listParts.add(filePart);
    			}
    		}
    		MultipartRequestEntity multipartRequestEntity = new MultipartRequestEntity(
																listParts.toArray(new Part[] {}),
																postMethodProxyRequest.getParams()
															);
    		postMethodProxyRequest.setRequestEntity(multipartRequestEntity);
    		// The current content-type header (received from the client) IS of
    		// type "multipart/form-data", but the content-type header also
    		// contains the chunk boundary string of the chunks. Currently, this
    		// header is using the boundary of the client request, since we
    		// blindly copied all headers from the client request to the proxy
    		// request. However, we are creating a new request with a new chunk
    		// boundary string, so it is necessary that we re-set the
    		// content-type string to reflect the new chunk boundary string
    		postMethodProxyRequest.setRequestHeader(HttpConstants.STRING_CONTENT_TYPE_HEADER_NAME, multipartRequestEntity.getContentType());
    	} catch (FileUploadException fileUploadException) {
    		throw new IOException(fileUploadException);
    	}
    }
    
	/**
	 * Sets up the given {@link PostMethod} to send the same standard POST
	 * data as was sent in the given {@link HttpServletRequest}
	 * @param postMethodProxyRequest The {@link PostMethod} that we are
	 *                                configuring to send a standard POST request
	 * @param httpServletRequest The {@link HttpServletRequest} that contains
	 *                            the POST data to be sent via the {@link PostMethod}
	 */    
    @SuppressWarnings("unchecked")
	private void handleStandardPost(PostMethod postMethodProxyRequest, HttpServletRequest httpServletRequest) {
    	
    	_log.debug("handleStandardPost()");
    	
		// Get the client POST data as a Map
		Map<String, String[]> mapPostParameters = (Map<String,String[]>) httpServletRequest.getParameterMap();
		// Create a List to hold the NameValuePairs to be passed to the PostMethod
		List<NameValuePair> listNameValuePairs = new ArrayList<NameValuePair>();
		// Iterate the parameter names
		for(String stringParameterName : mapPostParameters.keySet()) {
			
			StringBuffer debugMsg = new StringBuffer();
			debugMsg.append("Post param: " + stringParameterName);
			
			// Iterate the values for each parameter name
			String[] stringArrayParameterValues = mapPostParameters.get(stringParameterName);
			for(String stringParamterValue : stringArrayParameterValues) {

				debugMsg.append(" \"" + stringParamterValue + "\"");
				
				// Create a NameValuePair and store in list
				NameValuePair nameValuePair = new NameValuePair(stringParameterName, stringParamterValue);
				listNameValuePairs.add(nameValuePair);
			}
			_log.debug(debugMsg.toString());
		}
		// Set the proxy request POST data 
		postMethodProxyRequest.setRequestBody(listNameValuePairs.toArray(new NameValuePair[] { }));
    }
    
    /**
     * Executes the {@link HttpMethod} passed in and sends the proxy response
     * back to the client via the given {@link HttpServletResponse}
     * @param httpMethodProxyRequest An object representing the proxy request to be made
     * @param httpServletResponse An object by which we can send the proxied
     *                             response back to the client
     * @throws IOException Can be thrown by the {@link HttpClient}.executeMethod
     * @throws ServletException Can be thrown to indicate that another error has occurred
     */
    private void executeProxyRequest(HttpMethod httpMethodProxyRequest)
    			throws IOException, RedirectRequiredException {
    	
    	//Utils.traceRequest(httpServletRequest);
    	
		// Create a default HttpClient
    	HttpClient httpClient = new HttpClient();
		httpMethodProxyRequest.setFollowRedirects(false);
		
		_log.debug("Sending request to " + httpMethodProxyRequest.getURI());
		
		for (Header header : httpMethodProxyRequest.getRequestHeaders()) {
			_log.debug("  Header \"" + header.getName() + "\" = \"" + header.getValue() + "\"");
		}
		
		// Execute the request
		int intProxyResponseCode = httpClient.executeMethod(httpMethodProxyRequest);

		// Persist the respose headers
		this.responseHeaderMap = new HashMap<String, List<String>>();
		for (Header header : this.httpMethod.getResponseHeaders()) {
		
			responseHeaderMap.put(header.getName(), Arrays.asList(header.getValue()));
		}
		
		
		// Check if the proxy response is a redirect
		// The following code is adapted from org.tigris.noodle.filters.CheckForRedirect
		// Hooray for open source software
		if(intProxyResponseCode >= HttpServletResponse.SC_MULTIPLE_CHOICES /* 300 */
				&& intProxyResponseCode < HttpServletResponse.SC_NOT_MODIFIED /* 304 */) {
			String stringStatusCode = Integer.toString(intProxyResponseCode);
			String stringLocation = httpMethodProxyRequest.getResponseHeader(HttpConstants.STRING_LOCATION_HEADER).getValue();
			if(stringLocation == null) {
					throw new IOException("Recieved status code: " + stringStatusCode 
							+ " but no " +  HttpConstants.STRING_LOCATION_HEADER + " header was found in the response");
			}
			
			throw new RedirectRequiredException(new URL(stringLocation));
			
		} else if (intProxyResponseCode == HttpServletResponse.SC_NOT_MODIFIED) {
			// 304 needs special handling.  See:
			// http://www.ics.uci.edu/pub/ietf/http/rfc1945.html#Code304
			// We get a 304 whenever passed an 'If-Modified-Since'
			// header and the data on disk has not changed; server
			// responds w/ a 304 saying I'm not going to send the
			// body because the file has not changed.
			//httpServletResponse.setIntHeader(HttpConstants.STRING_CONTENT_LENGTH_HEADER_NAME, 0);
			//httpServletResponse.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
		}
		
		// Pass the response code back to the client
		this.httpStatusCode = intProxyResponseCode;

		_log.debug("Response code was " + this.httpStatusCode);

		// Pass response headers back to the client
		// TODO: Implement support for proxying response headers
        //Header[] headerArrayResponse = httpMethodProxyRequest.getResponseHeaders();
        //for(Header header : headerArrayResponse) {
       	//	httpServletResponse.setHeader(header.getName(), header.getValue());
        //}
        
        // Send the content to the client
        //InputStream inputStreamProxyResponse = httpMethodProxyRequest.getResponseBodyAsStream();
        //BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStreamProxyResponse);
        //OutputStream outputStreamClientResponse = httpServletResponse.getOutputStream();
        //int intNextByte;
        //while ( ( intNextByte = bufferedInputStream.read() ) != -1 ) {
        //	outputStreamClientResponse.write(intNextByte);
        //}
    }
    
    /**
     * Retreives all of the headers from the servlet request and sets them on
     * the proxy request
     * 
     * @param httpServletRequest The request object representing the client's
     *                            request to the servlet engine
     * @param httpMethodProxyRequest The request that we are about to send to
     *                                the proxy host
     */
    @SuppressWarnings("unchecked")
	private void setProxyRequestHeaders(HttpServletRequest httpServletRequest, HttpMethod httpMethodProxyRequest) {
    	// Get an Enumeration of all of the header names sent by the client
		Enumeration<String> enumerationOfHeaderNames = httpServletRequest.getHeaderNames();
		while(enumerationOfHeaderNames.hasMoreElements()) {
			String stringHeaderName = (String) enumerationOfHeaderNames.nextElement();
			if(stringHeaderName.equalsIgnoreCase(HttpConstants.STRING_CONTENT_LENGTH_HEADER_NAME))
				continue;
			
			// Support gzip transfer encoding
			if(stringHeaderName.equalsIgnoreCase(HttpConstants.STRING_ACCEPT_ENCODING_HEADER_NAME)) {
				httpMethodProxyRequest.setRequestHeader(stringHeaderName, "gzip");
				continue;
			}
			
		
			// As per the Java Servlet API 2.5 documentation:
			//		Some headers, such as Accept-Language can be sent by clients
			//		as several headers each with a different value rather than
			//		sending the header as a comma separated list.
			// Thus, we get an Enumeration of the header values sent by the client
			Enumeration<String> enumerationOfHeaderValues = httpServletRequest.getHeaders(stringHeaderName);
			while(enumerationOfHeaderValues.hasMoreElements()) {
				String stringHeaderValue = (String) enumerationOfHeaderValues.nextElement();
				// In case the proxy host is running multiple virtual servers,
				// rewrite the Host header to ensure that we get content from
				// the correct virtual server
				
				//if(stringHeaderName.equalsIgnoreCase(HttpConstants.STRING_HOST_HEADER_NAME)){
				//	stringHeaderValue = getProxyHostAndPort();
				//}
				
				Header header = new Header(stringHeaderName, stringHeaderValue);
				// Set the same header on the proxy request
				httpMethodProxyRequest.setRequestHeader(header);
			}
		}
    }
    
	// Accessors
    private String getProxyURL(HttpServletRequest httpServletRequest) throws MalformedURLException {

    	return deriveURL(httpServletRequest).toString();
    }
    
    
	private int getMaxFileUploadSize() {
		return this.intMaxFileUploadSize;
	}
	private void setMaxFileUploadSize(int intMaxFileUploadSizeNew) {
		this.intMaxFileUploadSize = intMaxFileUploadSizeNew;
	}

	@Override
	public Map<String, List<String>> getResponseHeaders()
			throws IOException {
		
		return responseHeaderMap;		
	}
	
	private static Log _log = LogFactoryUtil.getLog(OAuthProxyConnectionApacheHttpCommonsClientImpl.class);
}
