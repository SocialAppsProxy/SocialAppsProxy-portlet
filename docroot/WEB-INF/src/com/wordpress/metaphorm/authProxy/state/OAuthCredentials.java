package com.wordpress.metaphorm.authProxy.state;

import java.io.Serializable;

public class OAuthCredentials implements Serializable {

	private static final long serialVersionUID = 1L;

	private String consumerKey;
	private String consumerSecret;
	private String token;
	private String tokenSecret;
	
	public OAuthCredentials(String consumerKey, String consumerSecret) {
		this.consumerKey = consumerKey;
		this.consumerSecret = consumerSecret;
	}
	
	public String getConsumerKey() {
		return consumerKey;
	}
	
	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}
	
	public String getConsumerSecret() {
		return consumerSecret;
	}
	
	public void setConsumerSecret(String consumerSecret) {
		this.consumerSecret = consumerSecret;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getTokenSecret() {
		return tokenSecret;
	}
	
	public void setTokenSecret(String tokenSecret) {
		this.tokenSecret = tokenSecret;
	}
	
	public String toString() {
		
		return "[consumerKey=" + getConsumerKey() + ", consumerSecret=" + getConsumerSecret() + ", token=" + getToken() + ", tokenSecret=" + getTokenSecret() + "]";
	}
}
