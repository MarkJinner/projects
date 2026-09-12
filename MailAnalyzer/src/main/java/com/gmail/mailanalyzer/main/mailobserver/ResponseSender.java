package com.gmail.mailanalyzer.main.mailobserver;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
import jakarta.mail.BodyPart;
import jakarta.mail.Message;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class ResponseSender {
	private SetupProperties props;

	public ResponseSender(SetupProperties properties) {
		this.props = properties;
		
		
	}
	

	public void sendResponse(Order order) {

		Session session = Session.getInstance(props.getProperties(), new Authenticator() {
			
		
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {		
				return new PasswordAuthentication(props.getProperties().getProperty("username"), props.getProperties().getProperty("key"));
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
		message.setFrom(new InternetAddress(props.getProperties().getProperty("username")));//fix this
		if (address != "") {
			message.setRecipient(RecipientType.TO, new InternetAddress(address));
		}

		message.setSubject("Test message from Email observer");
		String htmlString = "<html>"

				+ messageText + "</html>";
		message.setContent(htmlString, "text/html");
		return message;
	}
	
	private Message compileContentedMessage(Session session, String address, String messageText) throws MessagingException, MessagingException {
		Message message = new MimeMessage(session);
		if(address!="") {
			BodyPart messageBodyPart = new MimeBodyPart();
			message.setRecipient(RecipientType.TO, new InternetAddress(address));
			message.setSubject("Test message with content from Email observer");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			MimeBodyPart attachmentPart = new MimeBodyPart();
			String filename = "document.pdf";
			DataSource source = new FileDataSource(filename);
			attachmentPart.setDataHandler(new DataHandler(source));
			attachmentPart.setFileName(filename);
			multipart.addBodyPart(attachmentPart);
			message.setContent(multipart);
		}
		return message;
	}

	
	
}
