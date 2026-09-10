package com.gmail.queryprocessors;

public enum Message {
	
	SUCCESSREG("Successful registration attempt for user "),
	FAILEDREG("Failed registration attempt for user "), 
	SUCCESSLOGIN("Successful login attempt for user "),
	FAILEDLOGIN("Failed login attempt for user "),
	LOGOUT("User logged out:"),
	ERROR401("Error 401: "),
	TEXTTRANSFORMED("Text transformed on page "),
	TEXTSAVED("Text saved in user's folder"),
	EMAILNOTFOUND("Email not found in database: "),
	REMINDERSENT("Credentials reminder sent on address: "),
	SPAREFOLDERCREATED("Spare folder created"),
	EMAIL_UPDATED("Email updated for user "),
	LOGIN_UPDATED("Login updated for user "),
	PASSWORD_UPDATED("Email updated for user "),
	UPDATE_FAILED("Update failed: ");
	

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
