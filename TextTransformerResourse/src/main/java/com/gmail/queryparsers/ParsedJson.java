package com.gmail.queryparsers;

public class ParsedJson extends Parsed{
	private String text;
	private String key;
	private String value;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

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
