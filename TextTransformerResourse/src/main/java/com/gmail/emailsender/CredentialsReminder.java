package com.gmail.emailsender;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.gmail.database.User;

import jakarta.mail.MessagingException;
import jakarta.xml.bind.JAXBException;

public class CredentialsReminder extends Sender{
	private EmailSender emailSender;
	
	public CredentialsReminder() throws IOException, ParserConfigurationException, JAXBException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, DOMException, InstantiationException, IllegalArgumentException, SAXException{

		emailSender = new EmailSender();
	}
	
	
	public static void main(String [] args) throws IOException, MessagingException, ParserConfigurationException, JAXBException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, DOMException, InstantiationException, IllegalArgumentException, SAXException {
		CredentialsReminder reminder = new CredentialsReminder();
		User user = new User("Oleg_ua@n21.com", "Oleg77", "Oleg77");
//		reminder.emailSender.sendEmail(user.getEmail().getAddress().get(), user, "Hello from sender!");
		reminder.sendEmail(user.getEmail().getAddress().get(), user);
	}
	
	@Override
	public void sendEmail(String address, User user) throws MessagingException {
		String message = "Hello, "+user.getLogin()+"! "
				+"\n"
				+ "\nYour credentials for access below."
				+ "\nLogin: "+user.getLogin()
				+"\nPassword: "+ user.getPassword()
				+"\n"
				+"\nBest wishes,"
				+"\nyour TextTransformer team";
		this.emailSender.sendEmail(address, user, message);
		String logMessage = "Credentials reminder sent to "+user.getLogin()+" on email: "+address; 
	}
	
	
	
	
//	public void sendEmail(String address, User user) throws MessagingException {
//		Properties properties = compileProperties();
//		Session session = compileSession(properties);
//		Message message = compileMessage(session, address, user);
//		Transport.send(message);
//		System.out.println("Letter with login and password reminder succesfully sent!");
//	}
//	
//	private Properties compileProperties() {
//		Properties properties = new Properties();
//		properties.put("mail.smtp.auth", "true");
//		properties.put("mail.smtp.ssl.enable", "true");
//		properties.put("mail.smtp.host", props.getHost());
//		properties.put("mail.smtp.port", "465");
//		return properties;
//	}
//	
//	private Session compileSession(Properties properties) {
//		Session session = Session.getInstance(properties, new Authenticator() {
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(props.getSender(), props.getKey());
//			}
//		});
//		return session;
//	}
//	
//	private Message compileMessage(Session session, String address, User user) throws MessagingException {
//		Message message = new MimeMessage(session);
//		message.setFrom(new InternetAddress(props.getSender()));
//		if (address != "") {
//			message.setRecipient(RecipientType.TO, new InternetAddress(address));
//		}
//
//		message.setSubject("Your TextTransformer credentials reminder");
//		String htmlString = 
//				"<html>"
//				
//				+ "<b>Hello!</b>"
//				+ "</html>";
//		message.setContent(htmlString, "text/html");
////		message.setText(	
////				"Hello, "+user.getLogin()+"! "
////				+"\n"
////				+ "\nYour credentials for access below."
////				+ "\nLogin: "+user.getLogin()
////				+"\nPassword: "+ user.getPassword()
////				+"\n"
////				+"\nBest wishes,"
////				+"\nyour TextTransformer team");
////		
//		return message;
//	}
}
