<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.wordpress.metaphorm.authProxy.sb.service.OAuthProviderLocalServiceUtil"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<p><liferay-ui:message key="oauth-service-provider-intro" /></p>
<p><liferay-ui:message key="oauth-service-provider-help" /></p>

<%!
	public String getTruncatedString(String fullString, int maxDisplayCharCount, int keepHeadCharCount, int maxWordLength, String truncateMiddleSequence) {
	
		if (fullString == null) return null;
		
		if (fullString.length() < maxDisplayCharCount) {
						
			StringBuffer sb = new StringBuffer();
			
			for (String word : fullString.split("\\s")) {
				
				if (word.length() / 2 > maxWordLength) {				
					sb.append(word.substring(0, word.length() - ((word.length() / 2) + (truncateMiddleSequence.length() / 2))));
					sb.append(truncateMiddleSequence);
					sb.append(word.substring((word.length() / 2) + (truncateMiddleSequence.length() / 2)));
				} else
					sb.append(word);
			}
				
			return sb.toString();
		}

		int keepTailCharCount = maxDisplayCharCount - keepHeadCharCount - truncateMiddleSequence.length();
	
		return fullString.substring(0, keepHeadCharCount) + truncateMiddleSequence + fullString.substring(fullString.length() - keepTailCharCount, fullString.length());
	}

%>

<%
    String redirect = PortalUtil.getCurrentURL(renderRequest);

	final String truncateMiddleSequence = " ... ";
	final String YES = "Yes";
	final String NO = "No";
%>

<portlet:actionURL name="deleteAllConnections" var="deleteConnectionsURL">
    <portlet:param name="redirect" value="<%= redirect %>" />
</portlet:actionURL>

<aui:button-row>
    <portlet:renderURL var="addProviderURL">
        <portlet:param name="mvcPath" value="/html/authproxyprovidersadmin/edit_provider.jsp" />
        <portlet:param name="redirect" value="<%= redirect %>" />
    </portlet:renderURL>

    <aui:button onClick="<%= addProviderURL.toString() %>" value="add-provider" />
    
	<liferay-ui:icon-delete label="true" message="Delete all user connections to all providers" url="<%= deleteConnectionsURL.toString() %>" />

</aui:button-row>

<liferay-ui:search-container delta="10" emptyResultsMessage="no-oauth-providers">
	<liferay-ui:search-container-results 
		results="<%= OAuthProviderLocalServiceUtil.getOAuthProviders(searchContainer.getStart(), searchContainer.getEnd()) %>" 
		resultsVar="oAuthProviders" 
		total="<%=OAuthProviderLocalServiceUtil.getOAuthProvidersCount() %>" 
		totalVar="oAuthProvidersTotal">
	</liferay-ui:search-container-results>
	
	<liferay-ui:search-container-row 
		className="com.wordpress.metaphorm.authProxy.sb.model.OAuthProvider" 
		keyProperty="providerId" 
		modelVar="oAuthProvider">
		
		<liferay-ui:search-container-column-text 
			name="oauth-realm-url-pattern" 
			value="<%= getTruncatedString(oAuthProvider.getPreemptiveAuthURLPattern(), 35, 20, 20, truncateMiddleSequence) %>"/>

		<liferay-ui:search-container-column-text 
			name="use-https" 
			value="<%= (oAuthProvider.getUseHTTPS() ? YES : NO) %>"/>

		<liferay-ui:search-container-column-text 
			name="oauth-realm" 
			value="<%= oAuthProvider.getRealm() %>"/>

		<liferay-ui:search-container-column-text 
			name="oauth-consumer-key" 
			value="<%= getTruncatedString(oAuthProvider.getConsumerKey(), 35, 20, 20, truncateMiddleSequence) %>"/>
			
		<liferay-ui:search-container-column-text 
			name="oauth-consumer-secret" 
			value="<%= getTruncatedString(oAuthProvider.getConsumerSecret(), 35, 20, 20, truncateMiddleSequence) %>"/>

		<liferay-ui:search-container-column-text 
			name="oauth-version" 
			value="<%= oAuthProvider.getProtocolVersion() %>"/>
			
		<liferay-ui:search-container-column-jsp
			align="right"
			path="/html/authproxyprovidersadmin/provider_actions.jsp"/>
			
	</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator />
	
</liferay-ui:search-container>

<h2>Getting started</h2>
<p><liferay-ui:message key="oauth-service-provider-getting-started-intro" /></p>
<ol>
	<li><liferay-ui:message key="oauth-service-provider-getting-started-step-1" /></li>
	<li><liferay-ui:message key="oauth-service-provider-getting-started-step-2" /></li>
	<li><liferay-ui:message key="oauth-service-provider-getting-started-step-3" /></li>
	<li><liferay-ui:message key="oauth-service-provider-getting-started-step-4" /></li>
	<li><liferay-ui:message key="oauth-service-provider-getting-started-step-5" /></li>
	<li><liferay-ui:message key="oauth-service-provider-getting-started-step-6" /></li>
</ol>

<h2>Integration with your portlet</h2>
<p><liferay-ui:message key="integration-info-intro" /></p>
<ol>
	<li><liferay-ui:message key="integration-info-configure-http-client" /></li>
	<li><liferay-ui:message key="integration-info-configure-http-make-request" /></li>
	<li><liferay-ui:message key="integration-info-http-headers" />

		<div class="results-grid aui-searchcontainer-content" style="margin-top: 0.5em; margin-bottom: 0.5em;">
			<table class="taglib-search-iterator">
				<tbody>
					<tr class="portlet-section-header results-header">
						<th class="col-1 first">HTTP header</th>
						<th class="col-2 last">Description</th>
					</tr>
					<tr class="portlet-section-body results-row">
						<td class="align-left col-1 first valign-top">userToken</td>
						<td class="align-left col-2 last valign-top"><liferay-ui:message key="integration-info-http-header-userToken" /></td>
					</tr>
					<tr class="portlet-section-alternate results-row alt">
						<td class="align-left col-1 first valign-top">oauth_callback</td>
						<td class="align-left col-2 last valign-top"><liferay-ui:message key="integration-info-http-header-oauth_callback" /></td>
					</tr>
				</tbody>
			</table>
		</div>
		
	</li>
	<li><liferay-ui:message key="integration-info-redirect" />
		<div style="border: 0; padding: 0.25em; color: darkblue; background-color: #fefefe; font-family: courier; margin-top: 0.5em; margin-bottom: 0.5em;">
			&lt;oauth&gt;<br/>
			&nbsp;&nbsp;&lt;authorisation_needed&gt;<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&lt;url&gt;http://serviceprovider.com/authorisation&lt;/url&gt;<br/>
			&nbsp;&nbsp;&lt;/authorisation_needed&gt;<br/>
			&lt;/oauth&gt;
		</div>
	</li>
	<li><liferay-ui:message key="integration-info-oauth-resource" /></li>
</ol>

<h2>Feedback and assistance</h2>
<p><liferay-ui:message key="contact-info" /></p>