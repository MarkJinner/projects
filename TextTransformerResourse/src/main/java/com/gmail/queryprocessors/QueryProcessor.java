package com.gmail.queryprocessors;

import com.gmail.exceptions.UserNotFoundException;
import com.gmail.logger.Logger;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logdisplayerprovider.LogDisplayProvider;

public abstract class QueryProcessor {
	Logger logger = new Logger();
	
	public QueryProcessor() {
	
	}
	
	public abstract String processRequest(HttpServletRequest req, LogDisplayProvider provider);
	
	public abstract String getRequestBody(HttpServletRequest req);
	
	public abstract void processRequest(HttpServletRequest req, HttpServletResponse resp, LogDisplayProvider provider) throws UserNotFoundException, MessagingException;
}
