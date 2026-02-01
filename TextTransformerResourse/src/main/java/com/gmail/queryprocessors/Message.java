package com.gmail.queryprocessors;

public enum Message {
	
	SUCCESSREG("Successful registration attempt for user "),
	FAILEDREG("Failed registration attempt for user "), 
	SUCCESSLOGIN("Successful login attempt for user "),
	FAILEDLOGIN("Failed login attempt for user "),
	LOGOUT("User logged out:"),
	ERROR401("Error 401: ");

	private final String text;


	Message(String txt) {
		text = txt;
	}
	
	
	
	public String getText() {
		return text;
	}
	
	@Override
	public String toString(){
		return this.text;
	}
	
	

	
	

}
