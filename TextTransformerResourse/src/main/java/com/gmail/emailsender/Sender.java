package com.gmail.emailsender;

import com.gmail.database.User;
import com.gmail.logger.Logger;

import jakarta.mail.MessagingException;
import logdisplayerprovider.LogDisplayProvider;


public abstract class Sender {
	private Logger logger;
	private LogDisplayProvider provider;
	
	public Sender() {
		logger = new Logger();
		provider = LogDisplayProvider.getInstance();
	}
	
	
	

	public Logger getLogger() {
		return logger;
	}




	public void setLogger(Logger logger) {
		this.logger = logger;
	}




	public LogDisplayProvider getProvider() {
		return provider;
	}




	public void setProvider(LogDisplayProvider provider) {
		this.provider = provider;
	}




	public abstract void sendEmail(String address,User user) throws MessagingException;
	

}
