package com.gmail.mailanalyzer.main.mailobserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import jakarta.mail.MessagingException;
import jakarta.mail.Session;

public class MailObserver {
	Properties props = new Properties();
	Session session = Session.getDefaultInstance(props);
	private InboxScanner scanner;
	
	public MailObserver() throws FileNotFoundException, IOException {
	scanner	 = new InboxScanner();
	}

	public void scanEmailInbox() throws FileNotFoundException, IOException, InterruptedException {
		scanner.scanInbox();
	}





	public static void main(String[] args) throws MessagingException, FileNotFoundException, IOException, InterruptedException {
		MailObserver observer = new MailObserver();
		observer.scanEmailInbox();
	}

}
