Social Apps Proxy
=================

What is Social Apps Proxy?
--------------------------

Social Apps Proxy is an open source plug-in for Liferay Portal. 

"If you are building apps that integrate with all the things social, this app implements the most boring and complex part of that integration, and exposes a simplified interface for your app's customers to connect your app with their preferred social networks. It acts as a proxy between your app and any OAuth-based social network (like Twitter, LinkedIn, etc). OAuth is complex and difficult to get right, so this app does the magic for any number of external networks with which you wish to integrate, and does so securely within the Liferay platform!"

[James Falkner, Liferay community manager. https://www.liferay.com/en_GB/web/james.falkner/blog/-/blogs/2014-liferay-marketplace-app-contest-results ]

Getting started
===============

Just go to https://www.liferay.com/marketplace/-/mp/application/41226182 to download and install to your Liferay Portal instance as with any other Marketplace app. Once installed, go to the Liferay Portal control panel and the "Social Apps Proxy Manager" to configure the OAuth service providers of interest to you.

Then just follow the simple on-screen instructions to consume the OAuth services from your app. There's an example hello world style app available here: https://github.com/SocialAppsProxy/SocialAppsProxy-BasicApp-portlet

Inspecting OAuth service responses
==================================

It is often a lot easier to make the correct HTTP request to an OAuth service than it is to interpret the contents of its response. So to get a quick look into what the response looks like you can use a web debugging proxy.

This documentation assumes you are using Charles web debugging proxy, but any HTTP proxy server which is able to act as transparent proxy should work fine too.

Auth proxy supports two types of clients:

* Web application client
* Browser client

Web application client
----------------------

This is the common use case of auth proxy.

Debugging this scenario is straight forward. All HTTP traffic of interest travels between the browser and the auth proxy.

Just configure Liferay Portal to use Charles as HTTP proxy.

You will now see all requests of interest in Charles.

You may want to configure your browser to use the Charles HTTP proxy as well to log the client requests that subsequently generate requests to the OAuth services.


Browser client
--------------

This is a lesser common use case, but useful when you just want to explore an OAuth service without writing any code nor worry about the complexity of OAuth authentication.

Debugging this scenario is a bit tricker because at present the auth proxy isn't currently able to act as a general HTTP proxy.

To work around this configure your web browser to use a .pac file which selectively sends requests from your web browser to the auth proxy. Send only requests for OAuth service resources via the OAuth proxy.

Configure Liferay Portal to use Charles as HTTP proxy.

You will now see both token requests and resource requests logged in Charles.
