package com.gmail.database;

import java.io.Serializable;
import java.util.Optional;

import com.gmail.exceptions.InappropriateEmailFormatException;

public class User implements Serializable{
	private Email email;
	private String login;
	private String password;

	public User(Email email, String login, String password) {
		this.email = email;
		this.login = login;
		this.password = password;
	}
	
	public User(String emailAddress, String login, String password) throws InappropriateEmailFormatException {
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

	public static void main(String[] args) throws InappropriateEmailFormatException {
		User test = new User();
		test.setEmail("qw@qwee.com");

	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(String address) throws InappropriateEmailFormatException {
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
