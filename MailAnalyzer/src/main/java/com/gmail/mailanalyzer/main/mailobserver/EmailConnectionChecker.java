package com.gmail.mailanalyzer.main.mailobserver;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.gmail.mailanalyzer.main.Test;
import com.gmail.mailanalyzer.main.observerframe.ObserverFrame;
import com.gmail.mailanalyzer.main.observerframe.message.ExceptionConverter;

import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Store;

public class EmailConnectionChecker implements Runnable{
	 
	private SetupProperties	props;
	private ObserverFrame frame;
	private Thread tr;

	public EmailConnectionChecker(SetupProperties props, ObserverFrame frame) {
		this.props = props;
		this.frame = frame;
		tr = new Thread(this);
		tr.start();
	}
	
	
	




	public SetupProperties getProps() {
		return props;
	}




	public ObserverFrame getFrame() {
		return frame;
	}




	public Thread getTr() {
		return tr;
	}







	public void setProps(SetupProperties props) {
		this.props = props;
	}




	public void setFrame(ObserverFrame frame) {
		this.frame = frame;
	}




	public void setTr(Thread tr) {
		this.tr = tr;
	}




	private  void runCheckEmailConnection() throws  IOException, InterruptedException {
		while(!Thread.currentThread().isInterrupted()) {
			Session emailSession = Session.getDefaultInstance(props.getProperties());
			Store store = null;
			try {
				store = emailSession.getStore((String) props.getProperties().get("protocol"));
				store.connect((String) props.getProperties().get("imapHost"), (String) props.getProperties().get("username"),
						(String) props.getProperties().get("key"));
				frame.appendText("Email connection successfully checked");	
			} catch (MessagingException e) {
				frame.appendText("Email connection failed"+System.lineSeparator()+new ExceptionConverter().convertToString(e));
				e.printStackTrace();
			}
			tr.sleep(30000);		
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
		properties.load(EmailConnectionChecker.class.getClassLoader().getResourceAsStream("properties.properties"));
//		
		ReaderProperties props = new ReaderProperties(properties);
		EmailConnectionChecker checker = new EmailConnectionChecker(props, frame);
		checker.testEmailConnection();
//		Thread tr = new Thread(checker);
//		tr.start();
		
	}
	
	
	
	@Test
	private void testEmailConnection() throws IOException, InterruptedException {
		this.runCheckEmailConnection();
	}
	
	
}
