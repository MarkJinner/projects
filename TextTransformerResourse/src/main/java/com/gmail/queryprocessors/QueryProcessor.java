package com.gmail.queryprocessors;

import com.gmail.logger.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logdisplayerprovider.LogDisplayProvider;

public abstract class QueryProcessor {
	Logger logger = new Logger();
	
	
	
	public abstract String processRequest(HttpServletRequest req, LogDisplayProvider provider);
	
	public abstract String getRequestBody(HttpServletRequest req);
	
	public abstract void processRequest(HttpServletRequest req, HttpServletResponse resp, LogDisplayProvider provider);
}
