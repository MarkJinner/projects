package com.gmail.mailanalyzer.main.mailobserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Properties;

import org.apache.pdfbox.io.IOUtils;

import com.gmail.mailanalyzer.main.pdfmarker.Marker;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Authenticator;
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
	private FileCopier copier;
	private static File folder;
	private Marker marker;

	public ResponseSender(SetupProperties properties) {
		this.props = properties;
		copier = new FileCopier();
		folder = new File("sent_mails");
		marker = new Marker();
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		
	}
	
	public ResponseSender() {
		folder = new File("sent_mails");
		marker = new Marker();
		if(!folder.exists()) {
			folder.mkdirs();
		}
	}
	

	public void sendResponse(Order order) throws FileNotFoundException, IOException {

		Session session = Session.getInstance(props.getProperties(), new Authenticator() {
			
		
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {		
				return new PasswordAuthentication(props.getProperties().getProperty("username"), props.getProperties().getProperty("key"));
			}
		});
		
		if(order!=null) {
			String transactionId = "";
			
			if(order.getTransactionId()!=null) {
				transactionId = order.getTransactionId();
			}
		}

		

		Message message;
		try {
//			message = compileMessage(session, "Oleg_ua@n21.com", "Hello");// this works well - personalize
			message = compileMultyMessage(session, "Oleg_ua@n21.com", "Hello");
			Transport.send(message);

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

		message.setSubject("Test message from Email observer");//personalize this
		String htmlString = "<html>"

				+ messageText + "</html>";//personalize this
		message.setContent(htmlString, "text/html");//personalize this
		return message;
	}
	
	
	public static void main(String [] args) throws IOException {
		ResponseSender sender = new ResponseSender();
//		Properties props = sender.loadProperties();
			
//		SenderProperties senderProps = new SenderProperties(props);
//		sender = new ResponseSender(senderProps);
//		sender.sendResponse(senderProps);
		
	    

	}
	
	public void sendResponse(SenderProperties properties) throws IOException {
		Session session = Session.getInstance(properties.getProperties(), new Authenticator() {
			
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {		
				return new PasswordAuthentication(properties.getProperties().getProperty("username"), properties.getProperties().getProperty("key"));
			}
		});
		
		Message message;
		try {
			message = compileMultyMessage(session, "Oleg_ua@n21.com", "Hello");//personalize this
			Transport.send(message);
			System.out.println("Message successfully sent");

		} catch (MessagingException e) {
			e.printStackTrace();

		}
		
		
		
		
	}
	
	
	private Message compileMultyMessage(Session session, String address, String messageText) throws MessagingException, FileNotFoundException, IOException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(props.getProperties().getProperty("username")));//fix this
		if (address != "") {
			message.setRecipient(RecipientType.TO, new InternetAddress(address));
		}

		message.setSubject("Test multymessage from Email observer");//personalize this
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(messageText);
		
		MimeBodyPart attachPart = new MimeBodyPart();

		InputStream originSource = ResponseSender.class.getClassLoader().getResourceAsStream("document.pdf");
//		
		String currentDir = System.getProperty("user.dir");
		File tempFile = new File(currentDir+File.separator+"copy.pdf"); 
		
		
//		File tempFile = File.createTempFile("document", ".pdf");
//		tempFile.deleteOnExit();
//		FileOutputStream out = new FileOutputStream(tempFile);
//		IOUtils.copy(originSource, out);
		
//		File temp = copier.copyFile(originSource, "copied_test_document2.pdf", folder);
		File temp = marker.mark(tempFile, "1234567_90", folder);
		System.out.println(temp.getAbsolutePath());
		Path copyPath = Path.of(temp.getAbsolutePath());
		
		DataSource source = new FileDataSource(copyPath);
		attachPart.setDataHandler(new DataHandler(source));
		attachPart.setFileName("ticket.pdf");
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		multipart.addBodyPart(attachPart);
		message.setContent(multipart);	
		return message;
	}
	
	
//
//	public Properties loadProperties() throws IOException {
//		Properties props = new Properties();
//		
//		props.load(ResponseSender.class.getClassLoader().getResourceAsStream("properties.properties"));
//		if (props.stringPropertyNames().size()!=0) {
//			System.out.println("Properties loaded successfully");
//		}else {
//			System.out.println("Properties load failed");
//		}
//		return props;
//	}
	
	

	
	
}
