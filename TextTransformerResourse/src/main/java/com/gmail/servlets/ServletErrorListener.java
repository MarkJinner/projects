package com.gmail.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import com.gmail.queryprocessors.ErrorResponseProcessor;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/errorServlet")
public class ServletErrorListener extends BasicServlet {
	private ErrorResponseProcessor errorProcessor = new ErrorResponseProcessor();
	public ServletErrorListener() {

	}

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
////		Integer statusCode = (Integer) req.getAttribute("jakarta.servlet.error.status_code");
////		System.out.println(statusCode);
////		String requestUri = (String) req.getAttribute("jakarta.servlet.error.request_uri");
////		System.out.println(requestUri);
//	
//		try {
//			resp.sendRedirect(errorProcessor.processRequest(req, super.getInstance()));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}

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
		
		
		System.out.println(sb.toString());
		return sb.toString();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println("You got your POST ServletErrorListener request");
		Integer statusCode = (Integer) req.getAttribute("jakarta.servlet.error.status_code");
		System.out.println(statusCode);
		if(statusCode==404 || statusCode==501) {
			String requestUri = (String) req.getAttribute("jakarta.servlet.error.request_uri");
			System.out.println(requestUri);
//			System.out.println("Error from error listener: "+getRequestBody(req));
			ServletContext context = req.getServletContext();
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error404.jsp");
			
			try {
//				resp.sendRedirect(errorProcessor.processRequest(req, super.getInstance()));
//				resp.sendRedirect("/error404.jsp");
//				resp.sendError(404);
				dispatcher.forward(req, resp);
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(statusCode==401) {
			String requestUri = (String) req.getAttribute("jakarta.servlet.error.request_uri");
			System.out.println(requestUri);
//			System.out.println("Error from error listener: "+getRequestBody(req));
//			ServletContext context = req.getServletContext();
//			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			
//			try {
////				resp.sendRedirect(errorProcessor.processRequest(req, super.getInstance()));
////				resp.sendRedirect("/error404.jsp");
////				dispatcher.forward(req, resp);
			try {
				resp.sendRedirect("login.jsp");
//				resp.sendError(401);
//				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//				
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			} 
		}
		


	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println("You got your GET ServletErrorListener request");
		Integer statusCode = (Integer) req.getAttribute("jakarta.servlet.error.status_code");
		System.out.println(statusCode);
		
		String requestUri = (String) req.getAttribute("jakarta.servlet.error.request_uri");
		System.out.println(requestUri);
//		System.out.println("Error from error listener: "+getRequestBody(req));
		ServletContext context = req.getServletContext();
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error404.jsp");
		
		try {
//			resp.sendRedirect(errorProcessor.processRequest(req, super.getInstance()));
//			resp.sendRedirect("/error404.jsp");
			dispatcher.forward(req, resp);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
