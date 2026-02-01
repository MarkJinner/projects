package com.gmail.queryparsers;

import java.util.Optional;

import com.gmail.exceptions.InappropriateEmailFormatException;

public class CredentialsParser extends QueryParser {
	private String test = "------WebKitFormBoundary3g3SdQJBfYUZM9Yo\n"
			+ "Content-Disposition: form-data; name=\"email\"\n" + "\n" + "q@q\n"
			+ "------WebKitFormBoundary3g3SdQJBfYUZM9Yo\n" + "Content-Disposition: form-data; name=\"login\"\n" + "\n"
			+ "qa\n" + "------WebKitFormBoundary3g3SdQJBfYUZM9Yo\n"
			+ "Content-Disposition: form-data; name=\"password\"\n" + "\n" + "qa\n"
			+ "------WebKitFormBoundary3g3SdQJBfYUZM9Yo--\n" + "\n" + "Login: qa Password: qa";

	public CredentialsParser() {
		
	}

	public static void main(String[] args) {
		String querySample = "login=ewew&password=";
		CredentialsParser parser = new CredentialsParser();
		System.out.println(parser.getEmail(parser.test));
		System.out.println(parser.substringLogin(querySample));
		System.out.println(parser.substringPassword(querySample));

	}

	@Override
	public Parsed parseQuery(String query) {
		ParsedCredentials credentials = new ParsedCredentials();

		if (query.contains("WebKitFormBoundary")) {
			if (query.contains("email")) {
				credentials.setEmail(getEmail(query).orElse(" "));

			}
			credentials.setLogin(getLogin(query).orElse(" "));
			credentials.setPassword(getPassword(query).orElse(" "));

		} else {
			credentials.setLogin(substringLogin(query).orElse(" "));
			credentials.setPassword(substringPassword(query).orElse(" "));
		}

		return credentials;
	}

	private Optional<String> substringLogin(String query) {
		String substringed = query.substring(0, query.indexOf("password"));
		return Optional
				.ofNullable(substringed.substring(substringed.indexOf("login=") + 6, substringed.lastIndexOf("&")));
	}

	private Optional<String> substringPassword(String query) {
		String substringed = query.substring(query.indexOf("password="), query.length());

		return Optional
				.ofNullable(substringed.substring(substringed.indexOf("password=") + 8, substringed.length() - 1));
	}

	private Optional<String> getEmail(String reqBody) {
		String s1 = reqBody.substring(reqBody.indexOf("email") + 7, reqBody.indexOf("login"));
		return Optional.ofNullable(s1.substring(0, s1.indexOf("------")).strip());
	}

	private Optional<String> getLogin(String reqBody) {
		String s1 = reqBody.substring(reqBody.indexOf("login") + 7, reqBody.indexOf("password"));
		return Optional.ofNullable(s1.substring(0, s1.indexOf("------")).strip());
	}

	private Optional<String> getPassword(String reqBody) {
		String s1 = reqBody.substring(reqBody.indexOf("password") + 10, reqBody.lastIndexOf("------"));
		return Optional.ofNullable(s1.strip());
	}

}
