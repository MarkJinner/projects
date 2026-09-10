package com.gmail.servlets;

import com.gmail.queryprocessors.ClickTracerProcessor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletClickTracerListener extends BasicServlet{
	private ClickTracerProcessor processor = new ClickTracerProcessor();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {

//		if(req.getRequestURI().equals("/goToPage")) {
//			processor.processRequest(req, super.getInstance());
//		}else if(req.getRequestURI().equals("/clicked")) {
//			processor.processRequest(req, super.getInstance());
//		}
		if(req.getRequestURI().equals("/goToPage")) {
		processor.processRequest(req, super.getInstance());
	}else if(req.getRequestURI().equals("/clicked")) {
		processor.processRequest(req, super.getInstance());
	}
		
		
	}

}
