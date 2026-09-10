package com.gmail.emailprovider;

import com.gmail.emailsender.EmailSender;

public abstract class EmailProvider {
	private EmailSender sender;
	
	public EmailProvider() {
		
	}
	
	public abstract void sendEmail();
	
	
}
