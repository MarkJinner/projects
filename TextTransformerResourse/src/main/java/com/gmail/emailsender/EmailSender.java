package com.gmail.emailsender;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.gmail.database.FileAddresses;
import com.gmail.database.User;
import com.gmail.xmlconverter.XmlUnsaver;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.xml.bind.JAXBException;

	public class EmailSender {
	private SenderProperties senderProperties;
	private HostProperties hostProperties;
	private File propsFile; 
	private File hostPropsFile;
	
	public EmailSender() throws IOException, ParserConfigurationException, JAXBException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, DOMException, InstantiationException, IllegalArgumentException, SAXException {
		propsFile =  new File(FileAddresses.getEmailSender_properties(),"emailSender_properties.xml");
		
		hostPropsFile = new File(FileAddresses.getHost_properties(),"host_properties.xml");
		senderProperties = (SenderProperties) new XmlUnsaver(SenderProperties.class).convertfromXml(propsFile);
		
		hostProperties = (HostProperties)new XmlUnsaver(HostProperties.class).convertfromXml(hostPropsFile);
		
	}
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, DOMException, InstantiationException, IllegalArgumentException, IOException, ParserConfigurationException, JAXBException, SAXException {
		EmailSender sender = new EmailSender();
		System.out.println(sender.hostProperties.getHost());
		System.out.println(sender.propsFile);
		System.out.println(sender.senderProperties.getHost()+"!");
		System.out.println("!!!www!qqeee1");
		
		
	}
	
	
	

	public SenderProperties getSenderProperties() {
		return senderProperties;
	}




	public void setSenderProperties(SenderProperties senderProperties) {
		this.senderProperties = senderProperties;
	}




	public  boolean sendEmail(String address, User user, String messageText) throws MessagingException{
		Properties properties = compileProperties();
		Session session = compileSession(properties);
		Message message;
		try {
			message = compileMessage(session, address, user, messageText);
			Transport.send(message);
			System.out.println("Letter with login and password reminder succesfully sent!");
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		
		return true;
	}
	
	private Properties compileProperties() {
		Properties properties = new Properties();
//		HostProperties hProps = (HostProperties) new XmlUnsaver().convertfromXml(propsFile);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.host", senderProperties.getHost());
		properties.put("mail.smtp.port", "465");
		return properties;
	}
	
	private Session compileSession(Properties properties) {
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderProperties.getSender(), senderProperties.getKey());
			}
		});
		return session;
	}
	
	private Message compileMessage(Session session, String address, User user, String messageText) throws MessagingException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(senderProperties.getSender()));
		if (address != "") {
			message.setRecipient(RecipientType.TO, new InternetAddress(address));
		}

		message.setSubject("Your TextTransformer credentials reminder");
		String htmlString = 
				"<html>"
				
				+ messageText
				+ "</html>";
		message.setContent(htmlString, "text/html");
		return message;
	}
}
