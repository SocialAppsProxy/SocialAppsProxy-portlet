<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider" %>
<%@ page import="com.wordpress.metaphorm.authProxy.sb.service.OAuthProviderLocalServiceUtil"%>

<portlet:defineObjects />

<%
    OAuthProvider provider = (OAuthProvider)request.getAttribute("provider");

	if (provider == null) {
		
	    long providerId = ParamUtil.getLong(request, "providerId");
	
	    if (providerId > 0) {
	    	provider = OAuthProviderLocalServiceUtil.getOAuthProvider(providerId);
	    }
	}

    String redirect = ParamUtil.getString(request, "redirect");
    final String OAUTH_VERSION_1_0A = "1.0a";
%>

<aui:model-context bean="<%= provider %>" model="<%= OAuthProvider.class %>" />
<portlet:renderURL var="viewProviderURL" />
<portlet:actionURL name='<%= provider == null ? "addProvider" : "updateProvider" %>' var="editProviderURL" windowState="normal" />

<liferay-ui:header
    backURL="<%= viewProviderURL %>"
    title='<%= (provider != null) ? provider.getRealm() : "New OAuth Service Provider" %>'
/>

<aui:form action="<%= editProviderURL %>" method="POST" name="fm">
    <aui:fieldset>
        <aui:input name="redirect" type="hidden" value="<%= redirect %>" />

        <aui:input name="providerId" type="hidden" value='<%= provider == null ? "" : provider.getProviderId() %>'/>
		
        <aui:input name="realm" label="oauth-realm" first="true" 
        	helpMessage="If left blank, the proxy becomes reliant upon the service provider describing its OAuth realm in HTTP 401 responses"/>
        
        <aui:input name="preemptiveAuthURLPattern" label="oauth-realm-url-pattern" 
        	helpMessage="A regular expression for matching URLs known to be for OAuth secured resources. If left blank, the proxy becomes reliant upon the service provider describing its OAuth realm in HTTP 401 responses"/>

		<aui:input name="useHTTPS" label="use-https" type="checkbox" value="<%= (provider != null ? provider.getUseHTTPS() : false) %>"
			helpMessage="Note: Only applies if a resource URL pattern is defined. Converts HTTP requests to HTTPS requests. Useful for OAuth services that only accept HTTPS requests"/>		

        <aui:input name="consumerKey" label="oauth-consumer-key"/>

        <aui:input name="consumerSecret" label="oauth-consumer-secret" />

        <aui:input name="requestTokenURL" label="oauth-request-token-url" />

        <aui:input name="authoriseURL" label="oauth-authorise-token-url" />

        <aui:input name="accessTokenURL" label="oauth-access-token-url" />
        
		<aui:select name="protocolVersion" label="oauth-version"
				helpMessage="At the moment this plugin only supports OAuth 1.0a. Later versions will support 2.0 as well">
			<aui:option selected="<%= provider != null && provider.getProtocolVersion() != null && provider.getProtocolVersion().equals(OAUTH_VERSION_1_0A) %>" value="<%= OAUTH_VERSION_1_0A %>">1.0a</aui:option>
		</aui:select>

    </aui:fieldset>

    <aui:button-row>
        <aui:button type="submit" />

        <aui:button onClick="<%= viewProviderURL %>"  type="cancel" />
    </aui:button-row>
</aui:form>