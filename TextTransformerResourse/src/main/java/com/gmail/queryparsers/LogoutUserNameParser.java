package com.gmail.queryparsers;

public class LogoutUserNameParser extends QueryParser {
	private ParsedUserName userName = new ParsedUserName();

	public LogoutUserNameParser() {

	}

	public static void main(String[] args) {
		String sample = "{\"usrNme\":\"user1\"}";
		LogoutUserNameParser parser = new LogoutUserNameParser();
		parser.parseQuery(sample);
		System.out.println(parser.userName.getUserName());
		
		System.out.println(parser.getUserName(sample));
	}

	@Override
	public Parsed parseQuery(String query) {
		String name = this.getUserName(query); 
		this.userName.setUserName(name);
		return userName;
	}

	private String getUserName(String query) {
		return query.substring(query.indexOf(":") + 2, query.lastIndexOf("\""));
	}

}
