package com.gmail.mailanalyzer.main.mailobserver;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class ResponseSender {
	private SetupProperties props;

	public ResponseSender(SetupProperties properties) {
		this.props = properties;
		
		
	}
	

	public void sendResponse(Order order) {

		Session session = Session.getInstance(props.getProperties(), new Authenticator() {
			
		
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {		
				return new PasswordAuthentication(props.getProperties().getProperty("username"), props.getProperties().getProperty("key"));//fix this
			}
		});

		Message message;
		try {
			message = compileMessage(session, "Oleg_ua@n21.com", "Hello");//fix this
			Transport.send(message);
			System.out.println("Letter  succesfully sent!");//fix this

		} catch (MessagingException e) {
			e.printStackTrace();

		}
	}
	
	private Message compileMessage(Session session, String address, String messageText) throws MessagingException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("Zlobinna@ukr.net"));//fix this
		if (address != "") {
			message.setRecipient(RecipientType.TO, new InternetAddress(address));
		}

		message.setSubject("Test message from Email observer");
		String htmlString = "<html>"

				+ messageText + "</html>";
		message.setContent(htmlString, "text/html");
		return message;
	}

	
	
}
