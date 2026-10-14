package com.gmail.scanner;

import java.io.IOException;
import java.util.Properties;

import jakarta.mail.Session;


public class MailSender {
	Properties props = new Properties();
	
	public MailSender() throws IOException {
		props.load(MailSender.class.getClassLoader().getResourceAsStream("properties.properties"));
	}
	
	public void sendMail() {
		Session emailSession = Session.getDefaultInstance(props);
		
		System.out.println(props);
	}
}
