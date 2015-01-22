package com.wordpress.metaphorm.authProxy.oauthClient;

public class OAuthCommunicationException extends Exception {

	private static final long serialVersionUID = 1L;

	public OAuthCommunicationException(String msg, Throwable e) {
		super(msg, e);
	}
}
