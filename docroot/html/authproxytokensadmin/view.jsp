<%@page import="java.util.LinkedList"%>
<%@page import="com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection"%>
<%@page import="java.util.List"%>
<%@page import="com.wordpress.metaphorm.authProxy.state.OAuthState"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.wordpress.metaphorm.authProxy.sb.service.OAuthConnectionLocalServiceUtil"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<portlet:defineObjects />
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<p>Apps and Services that you have connected to your user account are shown here. You can remove any connections you no longer require.</p>   

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
	final String truncateMiddleSequence = " ... ";
	final String YES = "Yes";
	final String NO = "No";

	String redirect = PortalUtil.getCurrentURL(renderRequest);
	SimpleDateFormat sDF = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	long companyId = PortalUtil.getCompanyId(request);
	long scopeGroupId = PortalUtil.getScopeGroupId(request);
	long userId = PortalUtil.getUserId(request);
%>

<liferay-ui:search-container delta="10" emptyResultsMessage="no-oauth-tokens">
	<liferay-ui:search-container-results 
		results="<%= OAuthConnectionLocalServiceUtil.getOAuthConnections(companyId, scopeGroupId, userId, OAuthState.RESOURCE_PHASE, searchContainer.getStart(), searchContainer.getEnd()) %>" 
		resultsVar="oAuthConnections" 
		total="<%=OAuthConnectionLocalServiceUtil.getOAuthConnectionsCount(companyId, scopeGroupId, userId, OAuthState.RESOURCE_PHASE) %>" 
		totalVar="oAuthConnectionsTotal">
	</liferay-ui:search-container-results>
	
	<liferay-ui:search-container-row 
		className="com.wordpress.metaphorm.authProxy.sb.model.OAuthConnection" 
		keyProperty="connectionId" 
		modelVar="oAuthConnection">
		
		<liferay-ui:search-container-column-text 
			name="oauth-realm" 
			value="<%= oAuthConnection.getRealm() %>"/>

		<% if (1 == 0) { %>
		<liferay-ui:search-container-column-text 
			name="oauth-phase" 
			value="<%= (
					oAuthConnection.getPhase() == OAuthState.REQUEST_PHASE ? \"REQUEST\" : 
						oAuthConnection.getPhase() == OAuthState.AUTHORISE_PHASE ? \"AUTHORISE\" : 
							oAuthConnection.getPhase() == OAuthState.RESOURCE_PHASE ? \"RESOURCE\" : \"UNKNOWN\") %>"/>
		<% } %>

		<liferay-ui:search-container-column-text 
			name="oauth-token" 
			value="<%= getTruncatedString(oAuthConnection.getToken(), 35, 20, 20, truncateMiddleSequence) %>"/>

		<liferay-ui:search-container-column-text 
			name="oauth-token-secret" 
			value="<%= getTruncatedString(oAuthConnection.getTokenSecret(), 35, 20, 20, truncateMiddleSequence) %>"/>
			
		<% if (1 == 0) { %>
		<liferay-ui:search-container-column-text 
			name="oauth-token-create-date" 
			value="<%= (oAuthConnection.getCreateDate() != null ? sDF.format(oAuthConnection.getCreateDate()) : \"Unknown\") %>"/>
		<% } %>

		<liferay-ui:search-container-column-jsp
			align="right"
			path="/html/authproxytokensadmin/token_actions.jsp"/>
			
	</liferay-ui:search-container-row>
	
	<liferay-ui:search-iterator />
	
</liferay-ui:search-container>