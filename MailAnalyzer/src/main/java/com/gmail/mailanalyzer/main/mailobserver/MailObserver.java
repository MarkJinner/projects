package com.gmail.mailanalyzer.main.mailobserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MailObserver {
	Properties props = new Properties();
	Session session = Session.getDefaultInstance(props);
	

	public MailObserver() {

	}

	public void scanEmailInbox() throws FileNotFoundException, IOException, InterruptedException {
		InboxScanner scanner = new InboxScanner();
		scanner.scanInbox();
	}





	public static void main(String[] args) throws MessagingException, FileNotFoundException, IOException, InterruptedException {
		MailObserver observer = new MailObserver();
		observer.scanEmailInbox();
	}

}
