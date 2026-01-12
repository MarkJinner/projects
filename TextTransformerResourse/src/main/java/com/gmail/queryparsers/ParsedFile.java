package com.gmail.queryparsers;

public class ParsedFile extends Parsed {
	private String text;

	public ParsedFile(String txt) {
		this.text = txt;
	}

	@Override
	public String getText() {
		return text;
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
