package com.wordpress.metaphorm.authProxy.hook;

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

import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.wordpress.metaphorm.authProxy.jaxb.ThemeDisplayFactory;
import com.wordpress.metaphorm.authProxy.jaxb.ThemeDisplayWrapper;

import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * @author Stian Sigvartsen
 */
public class MarshallThemeDisplayAsXMLHook extends Action {

	public MarshallThemeDisplayAsXMLHook() {
		super();
	}

	public void run(HttpServletRequest servletReq, HttpServletResponse servletResp) throws ActionException {

		_log.debug("run()");

		ThemeDisplay themeDisplay = (ThemeDisplay)servletReq.getAttribute(WebKeys.THEME_DISPLAY);		
		ThemeDisplayWrapper themeDisplayWrapper = ThemeDisplayFactory.convert(themeDisplay);
		
		try {
			
			String themeDisplayXMLStr = getThemeDisplayAsXMLString(themeDisplayWrapper);
			servletReq.setAttribute("themeDisplayXML", themeDisplayXMLStr);
			
		} catch (JAXBException e) {			
			e.printStackTrace();
		}
	}

	private String getThemeDisplayAsXMLString(ThemeDisplayWrapper themeDisplay) throws JAXBException {
		
		StringWriter sw = new StringWriter();

		JAXBContext jaxbContext = JAXBContext.newInstance(ThemeDisplayWrapper.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(themeDisplay, sw);
		
		return sw.toString();		
	}
	
	private static Log _log = LogFactoryUtil.getLog(MarshallThemeDisplayAsXMLHook.class);
}