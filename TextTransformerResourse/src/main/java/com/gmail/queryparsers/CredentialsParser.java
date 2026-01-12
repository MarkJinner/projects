package com.gmail.queryparsers;

import java.util.Optional;

public class CredentialsParser extends QueryParser {

	public CredentialsParser() {

	}

	public static void main(String[] args) {
		String querySample = "login=ewew&password=";
		CredentialsParser parser = new CredentialsParser();
		System.out.println(parser.substringLogin(querySample));
		System.out.println(parser.substringPassword(querySample));

	}

	@Override
	public Parsed parseQuery(String query) {
		ParsedCredentials credentials = new ParsedCredentials();
		if (query.contains("WebKitFormBoundary")) {
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

	private Optional<String> getLogin(String reqBody) {
		String s1 = reqBody.substring(reqBody.indexOf("login") + 7, reqBody.indexOf("password"));
		return Optional.ofNullable(s1.substring(0, s1.indexOf("------")).strip());
	}

	private Optional<String> getPassword(String reqBody) {
		String s1 = reqBody.substring(reqBody.indexOf("password") + 10, reqBody.lastIndexOf("------"));
		return Optional.ofNullable(s1.strip());
	}

}
