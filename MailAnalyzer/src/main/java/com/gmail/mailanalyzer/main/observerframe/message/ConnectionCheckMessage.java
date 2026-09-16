package com.gmail.mailanalyzer.main.observerframe.message;

public  class ConnectionCheckMessage extends Message{
	private String date;
	private String text;
	private Level level;
	
	public ConnectionCheckMessage() {
		
	}
	

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}

	@Override
	public String getDate() {
		// TODO Auto-generated method stub
		return date;
	}

	@Override
	public Level getLevel() {
		// TODO Auto-generated method stub
		return level;
	}

	@Override
	public void setText(String text) {
		 this.text = text;
		
	}

	@Override
	public void setDate(String date) {
		// TODO Auto-generated method stub
		this.date = date;
	}

	@Override
	public void setLevel(Level level) {
		// TODO Auto-generated method stub
		this.level = level;
	}
	
	@Override
	public String toString() {
		return this.getDate()+" "+this.getText();
	}
	
	

	
	
	
	

}
