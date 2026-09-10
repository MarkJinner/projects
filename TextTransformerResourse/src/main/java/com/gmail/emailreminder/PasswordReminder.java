package com.gmail.emailreminder;
import java.util.Properties;

import com.gmail.database.Email;
import com.gmail.database.User;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class PasswordReminder implements ForgottenPasswordSender {
	private String key = "lonKHOjPldv6yQWW";
	private String sender = "zlobinna@ukr.net";
	private String host = "smtp.ukr.net";
	private String address = "";
	private String text = "";

	public PasswordReminder(String address) {
		this.address = address;
	}

	public PasswordReminder(String sender, String key, String host, String address) {
		this.sender = sender;
		this.key = key;
		this.host = host;
		this.address = address;
	}

	public PasswordReminder() {

	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static void main(String[] args) throws AddressException, MessagingException {
		System.out.println("!");
		PasswordReminder reminder = new PasswordReminder();
		User user = new User("Oleg_ua@n21.com", "oleg", "oleg7");
		reminder.sendReminder("Oleg_ua@n21.com", user);

	}

	@Override
	public void sendReminder(Email email, User user) throws MessagingException {

		sendReminder(email.getAddress().get(), user);

	}

	@Override
	public void sendReminder(String address, User user) throws MessagingException {
		System.out.println("You 've got into password reminder");
		Properties props = this.compileProperties();
		Session session = compileSession(props);
		Message message = compileMessage(session, address, user);
		Transport.send(message);
		System.out.println("Letter with login and password reminder succesfully sent!");
	}
	

	private Properties compileProperties() {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "465");

		return props;
	}

	private Session compileSession(Properties props) {
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender, key);

			}

		});

		return session;
	}

	private Message compileMessage(Session session, String address, User user) throws MessagingException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(sender));
		if (address != "") {
			message.setRecipient(RecipientType.TO, new InternetAddress(address));
		}

		message.setSubject("Your TextTransformer credentials reminder");
		message.setText("Hello, "+user.getLogin()+"! "
				+"\n"
				+ "\nYour credentials for access below."
				+ "\nLogin: "+user.getLogin()
				+"\nPassword: "+ user.getPassword()
				+"\n"
				+"\nBest wishes,"
				+"\nyour TextTransformer team");
		return message;
	}
}

