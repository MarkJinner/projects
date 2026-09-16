package com.gmail.mailanalyzer.main.observerframe.message;

public enum Level {
	Request(1), Connection(2), Exception(3);

	private int level;

	Level(int level) {
		this.level = level;
	}
	
	Level(){
		
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
