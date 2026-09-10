package com.gmail.queryparsers;

public class ParsedUserName extends Parsed{
	private String userName = "";
	
	@Override
	public String getText() {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public void setText(String text) {
		throw new UnsupportedOperationException("Operation not supported");
		
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
