package com.gmail.servlets;

import com.gmail.queryprocessors.LogoutProcessor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutServlet extends BasicServlet{
	LogoutProcessor processor = new LogoutProcessor();
	


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("You've got your Logout Servlet GET request");
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("You've got your Logout Servlet POST request");
		processor.processRequest(req, resp,getInstance());
		
	}
	
	public static void main(String [] args) {
		System.out.println("!!!1111");
	}


}
