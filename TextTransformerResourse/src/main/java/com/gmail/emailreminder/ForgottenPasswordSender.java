package com.gmail.emailreminder;

import com.gmail.database.Email;
import com.gmail.database.User;

import jakarta.mail.MessagingException;



public interface ForgottenPasswordSender {
	
	public void sendReminder(Email email, User user) throws MessagingException;

	public void sendReminder(String address, User user) throws MessagingException;

}
