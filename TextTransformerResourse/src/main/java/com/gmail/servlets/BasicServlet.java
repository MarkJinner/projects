package com.gmail.servlets;
import com.gmail.logger.Logger;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logdisplayerprovider.LogDisplayProvider;

public abstract class BasicServlet extends HttpServlet{

	private LogDisplayProvider instance  = LogDisplayProvider.getInstance();
	private Logger logger = new Logger();
	
	public BasicServlet() {
		
	}
	
	public static void main(String [] args) {
		System.out.println("!!!!");
	}
	
	
	
	
	
	public void setLogger(Logger logger) {
		this.logger = logger;
	}





	public LogDisplayProvider getInstance() {
		return instance;
	}





	public void setInstance(LogDisplayProvider instance) {
		this.instance = instance;
	}





	public Logger getLogger() {
		return logger;
	}




	@Override
	protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp);
	
	@Override
	protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp);
}