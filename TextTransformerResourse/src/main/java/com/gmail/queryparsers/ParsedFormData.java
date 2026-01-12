package com.gmail.queryparsers;

public class ParsedFormData extends Parsed{
	private String text;
	private String transformer;
	

	public ParsedFormData(String trns, String text) {
		this.transformer = trns;
		this.text = text;

	}
	
	public ParsedFormData() {
		
	}

	public String getTransformer() {
		return transformer;
	}

	public void setTransformer(String transformer) {
		this.transformer = transformer;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return this.transformer + ":" + this.text;
	}

}
