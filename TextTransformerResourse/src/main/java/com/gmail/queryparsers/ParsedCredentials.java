package com.gmail.queryparsers;

public class ParsedCredentials extends Parsed {
	private String login = "";
	private String password = "";

	public ParsedCredentials() {

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getText() {

		throw new UnsupportedOperationException("Operation is not supported");
	}

	@Override
	public void setText(String text) {
		throw new UnsupportedOperationException("Operation is not supported");

	}

	@Override
	public String toString() {
		return "Login: " + login + " Password: " + password;
	}

}
