package com.gmail.servlets;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.gmail.queryprocessors.PasswordReminderProcessor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletPasswordReminder extends BasicServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		PasswordReminderProcessor processor = null;
		try {
			processor = new PasswordReminderProcessor();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("You've got your Password Reminder Servlet POST request");
		processor.processRequest(req, resp, getInstance());
		
	}

}
