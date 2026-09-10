package com.gmail.queryprocessors;

public enum UpdateError {
	LOGINUSED("Login used"), SAMEPASSWORD("Password couldn't be the same"), EMAILUSED("Email used");

	String message;

	UpdateError(String message) {
		this.message = message;
	}
}
