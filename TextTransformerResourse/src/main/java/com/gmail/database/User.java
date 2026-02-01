package com.gmail.database;

import java.io.Serializable;

import com.gmail.exceptions.InappropriateEmailFormatException;
import com.gmail.queryparsers.ParsedCredentials;

public class User implements Serializable{
	private Email email;
	private String login;
	private String password;

	public User(Email email, String login, String password) {
		this.email = email;
		this.login = login;
		this.password = password;
	}
	
	public User(ParsedCredentials credentials) {
		this.email = credentials.getEmail();
		this.login = credentials.getLogin();
		this.password = credentials.getPassword();
	}
	
	public User(String emailAddress, String login, String password) {
		this.email = new Email();
		this.email.setAddress(emailAddress);
		this.login = login;
		this.password = password;
	}

	public User(String login, String password) {

		this.login = login;
		this.password = password;
	}

	public User() {

	}

	public static void main(String[] args)  {
		User test = new User();
		test.setEmail("qw@qwee.com");

	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(String address) {
		this.email = new Email();
		this.email.setAddress(address);
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
	public String toString() {
		return "Login: " + login + " password: " + password + " email:" + email;
	}
}
