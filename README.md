Debugging HTTP traffic
======================

Auth proxy supports two types of clients:

* Web application client
* Browser client

This documentation assumes you are using Charles web debugging proxy, but any HTTP proxy server which is able to act as transparent proxy should work fine too.

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