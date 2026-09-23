package com.gmail.mailanalyzer.main.mailobserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.gmail.mailanalyzer.main.exceptions.WrongCredentialsException;
import com.gmail.mailanalyzer.main.observerframe.ObserverFrame;
import com.gmail.mailanalyzer.main.observerframe.message.ExceptionConverter;

import jakarta.mail.MessagingException;
import jakarta.mail.NoSuchProviderException;
import jakarta.mail.Session;
import jakarta.mail.Store;

public class EmailConnectionChecker implements Runnable{
	private ExceptionConverter eConverter = new ExceptionConverter(); 
	private SetupProperties	props;
	private ObserverFrame frame;
	private Thread tr;

	public EmailConnectionChecker(SetupProperties props, ObserverFrame frame) {
		this.props = props;
		this.frame = frame;
		tr = new Thread(this);
		tr.start();
	}
	
	
	private  void runCheckEmailConnection() throws  IOException, InterruptedException {
		while(!Thread.currentThread().isInterrupted()) {
			Session emailSession = Session.getDefaultInstance(props.getProperties());
			Store store = null;
			try {
				store = emailSession.getStore((String) props.getProperties().get("protocol"));
			} catch (NoSuchProviderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				frame.appendText(eConverter.convertToString(e));	
			}
			try {
				store.connect((String) props.getProperties().get("imapHost"), (String) props.getProperties().get("username"),
						(String) props.getProperties().get("key"));
				frame.appendText("Email connection successfully checked");	
				
			} catch (MessagingException e) {
				frame.appendText(eConverter.convertToString(e));			
				e.printStackTrace();
			}
			Thread.currentThread().sleep(30000);
			
		}
	}
	
	@Override
	public void run() {
		try {
			runCheckEmailConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String [] args) throws FileNotFoundException, IOException, InterruptedException {
		ObserverFrame frame = ObserverFrame.getInstance();
		Properties properties = new Properties();
		properties.load(new FileInputStream(new File("properties.properties")));
//		
		ReaderProperties props = new ReaderProperties(properties);
		EmailConnectionChecker checker = new EmailConnectionChecker(props, frame);
//		checker.testEmailConnection();
//		Thread tr = new Thread(checker);
//		tr.start();
		
	}
	
	
	
	@Test
	private void testEmailConnection() throws IOException, InterruptedException {
		this.runCheckEmailConnection();
	}
	
	
}
