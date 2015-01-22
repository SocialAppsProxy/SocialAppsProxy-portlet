package com.wordpress.metaphorm.authProxy.oauthClient;

public class OAuthNotAuthorizedException extends Exception {

	private static final long serialVersionUID = 1L;

	public OAuthNotAuthorizedException(String msg, Throwable e) {
		super(msg, e);
	}
}
