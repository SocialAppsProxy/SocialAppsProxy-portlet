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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.util.PortalUtil;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.copy.HierarchicalStreamCopier;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.SequenceInputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Stian Sigvartsen
 */
public class Utils {

	public static boolean isLocalInterfaceIPAddress(String iPAddress) throws SocketException {
		
		_log.debug("Checking if " + iPAddress + " is a local interface...");
		
		Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
	
		while (interfaces.hasMoreElements()){
		    NetworkInterface current = interfaces.nextElement();
		    //_log.debug(current);
		    Enumeration<InetAddress> addresses = current.getInetAddresses();
		    while (addresses.hasMoreElements()){
		        InetAddress current_addr = addresses.nextElement();
		        //_log.debug(" " + current_addr.getHostAddress());
		        
		        if (current_addr.getHostAddress().equals(iPAddress)) return true;
		    }		    
		}
		return false;
	}

	public static boolean isLiferayLocalResource(HttpServletRequest servletReq) {
				
		try {
			InetAddress address = InetAddress.getByName(servletReq.getServerName()); 
			if (isLocalInterfaceIPAddress(address.getHostAddress())) return true; 			
		
		} catch (UnknownHostException e) {
			// Hopefully the localhost is known (!) so consider any issues with IP lookup to be remote hosts
			e.printStackTrace();
			return false;
		} catch (SocketException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	
	public static void writeJSONAsXML(String json, PrintWriter writer) throws IOException {
		
		HierarchicalStreamDriver driver = new JettisonMappedXmlDriver();
		
		HierarchicalStreamReader hsr = driver.createReader(new SequenceInputStream(
		        Collections.enumeration(Arrays.asList(
		                new InputStream[] {
		                        new ByteArrayInputStream("{\"json\": {\"object\": ".getBytes()),
		                        new ByteArrayInputStream(json.getBytes()), //FileInputStream(file),
		                        new ByteArrayInputStream("}}".getBytes())
		                    }))
		            ));
	
		new HierarchicalStreamCopier().copy(hsr, new PrettyPrintWriter(writer));

	}
	
	public static void traceRequest(PortletRequest renderReq) {
		
		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(renderReq);
		String requestAuthenticationToken = ParamUtil.getString(httpRequest, "p_auth");
		_log.debug("p_auth = " + requestAuthenticationToken);
		
		for (String key : renderReq.getParameterMap().keySet()) {
			_log.debug("Param " + key + " = " + renderReq.getParameter(key));
		}
		
		Enumeration<String> attrNames = renderReq.getAttributeNames(); 		
		while (attrNames.hasMoreElements()) {
			String attr = attrNames.nextElement();
			_log.debug("Attr " + attr + " = " + renderReq.getAttribute(attr));
		}
		
		Enumeration<String> propNames = renderReq.getPropertyNames(); 		
		while (propNames.hasMoreElements()) {
			String prop = propNames.nextElement();
			_log.debug("Prop " + propNames + " = " + renderReq.getProperty(prop));
		}
		
		
		Enumeration<String> sessionAttrNames = renderReq.getPortletSession().getAttributeNames();
		while (sessionAttrNames.hasMoreElements()) {
			String attr = sessionAttrNames.nextElement();
			_log.debug("Session Attr " + attr + " = " + renderReq.getPortletSession().getAttribute(attr));
		}
	}	
	
	public static void traceRequest(HttpServletRequest servletReq) {
		
		_log.debug(servletReq.getMethod() + " " + servletReq.getRequestURI() + (servletReq.getQueryString() != null ? "?" + servletReq.getQueryString() : ""));
		_log.debug("Headers received:");
		Enumeration<String> headers = servletReq.getHeaderNames();
		while (headers.hasMoreElements()) {
			String header = headers.nextElement();
			_log.debug("  " + header + ": " + servletReq.getHeader(header));
		}
	}
	
	private static Log _log = LogFactoryUtil.getLog(Utils.class);
}
