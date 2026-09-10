package com.gmail.servlets;

import com.gmail.exceptions.UserNotFoundException;
import com.gmail.queryprocessors.QueryProcessor;
import com.gmail.queryprocessors.UserRegistrationProcessor;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserRegistrationServlet extends BasicServlet{
	
	private QueryProcessor processor = new UserRegistrationProcessor();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) {
		System.out.println("You've got your UserRegistration Servlet POST request");
		try {
			processor.processRequest(request, resp, getInstance());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
