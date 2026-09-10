package com.gmail.servlets;

import com.gmail.exceptions.UserNotFoundException;
import com.gmail.queryprocessors.ChatRequestProcessor;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ChatRequestServlet extends BasicServlet{
	private ChatRequestProcessor processor = new ChatRequestProcessor();
	
	public ChatRequestServlet() {
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("YOU got your Chat request");
		
		try {
			processor.processRequest(req, resp, getInstance());
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
