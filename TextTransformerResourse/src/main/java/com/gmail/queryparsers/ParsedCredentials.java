package com.gmail.queryparsers;

import com.gmail.database.Email;
import com.gmail.exceptions.InappropriateEmailFormatException;

public class ParsedCredentials extends Parsed {
	private Email email = new Email();
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

	public Email getEmail() {
		return email;
	}

	public void setEmail(String address) {
		this.email.setAddress(address);
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
		if (email.getAddress().isPresent()) {
			return "Email: " + email.getAddress().get() + " Login: " + login + " Password: " + password;
		} else {
			return " Login: " + login + " Password: " + password;
		}

	}

}
