package com.gmail.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import com.gmail.queryprocessors.LogoutProcessor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutServlet extends BasicServlet{
	LogoutProcessor processor = new LogoutProcessor();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("You've got your Logout Servlet POST request");
		processor.processRequest(req, getInstance());
		
	}
	
	
	
	public String getRequestBody(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		String line = "";
		try (Reader reader = req.getReader(); BufferedReader bf = new BufferedReader(reader)) {

			while ((line = bf.readLine()) != null) {
				sb.append(line + System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

		return sb.toString();
	}

}
