package com.gmail.servlets;

import com.gmail.queryprocessors.CopyToClipboardQueryProcessor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletClipboarder extends BasicServlet{
	private CopyToClipboardQueryProcessor processor = new CopyToClipboardQueryProcessor();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("You got your POST ServletClipboarder request");
		
		if(req.getRequestURI().equals("/copyToClipboard")) {
			processor.processRequest(req, super.getInstance());
		}
		
		
		
		
	}

}
