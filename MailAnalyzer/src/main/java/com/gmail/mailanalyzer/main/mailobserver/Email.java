package com.gmail.mailanalyzer.main.mailobserver;

public class Email {
	private String content;	
	public Email() {
		
	}

	public Email(String content) {
		super();
		this.content = content;
	}

	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}





	@Override
	public String toString() {
		return "Email [content=" + content + "]";
	}
	
	
}
