package com.gmail.mailanalyzer.main.observerframe.message;

public abstract class Message {
	
	
	
	public abstract String getText();

	public abstract String getDate();

	public abstract int getLevel();
	
	public abstract void setText(String text);

	public abstract void setDate(String date);

	public abstract void setLevel(int level);
	
	
	
	

}
