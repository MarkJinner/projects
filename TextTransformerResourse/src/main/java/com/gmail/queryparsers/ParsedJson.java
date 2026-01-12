package com.gmail.queryparsers;

public class ParsedJson extends Parsed{
	private String text;
	
	public ParsedJson() {
		
	}

	@Override
	public String getText() {

		return this.text;
	}

	@Override
	public void setText(String text) {
		 this.text = text;
		
	}
	
	@Override
	public String toString() {
		return this.text;
	}

}
