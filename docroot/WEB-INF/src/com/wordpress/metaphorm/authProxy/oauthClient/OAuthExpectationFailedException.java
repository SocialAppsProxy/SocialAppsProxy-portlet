package com.wordpress.metaphorm.authProxy.oauthClient;

public class OAuthExpectationFailedException extends Exception {

	private static final long serialVersionUID = 1L;

	public OAuthExpectationFailedException(String msg, Throwable e) {
		super(msg, e);
	}
}
