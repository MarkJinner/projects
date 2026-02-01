package com.gmail.servlets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import com.gmail.database.UsersDatabase;
import com.gmail.exceptions.InapropriateCredentialsException;
import com.gmail.exceptions.NoSuchUserException;
import com.gmail.logger.Logger;
import com.gmail.queryparsers.CredentialsParser;
import com.gmail.queryparsers.ParsedCredentials;
import com.gmail.queryprocessors.CredentialsProcessor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CredentialsServlet extends BasicServlet {
	private CredentialsProcessor processor;
	private UsersDatabase users;
	private CredentialsParser parser;
	private String responseMessage = "";
	private Logger logger = super.getLogger();

	public CredentialsServlet() throws FileNotFoundException, ClassNotFoundException, IOException {
		processor = new CredentialsProcessor();
		users = new UsersDatabase();
		parser = new CredentialsParser();
	}

	public static void main(String[] args) {
		System.out.println("!??");
		String sample = "------WebKitFormBoundaryWlM6BSdARUgsL44I\n"
				+ "Content-Disposition: form-data; name=\"login\"\n" + "\n" + "user134\n"
				+ "------WebKitFormBoundaryWlM6BSdARUgsL44I\n" + "Content-Disposition: form-data; name=\"password\"\n"
				+ "\n" + "user1Pass\n" + "------WebKitFormBoundaryWlM6BSdARUgsL44I--";

//		try {
//			CredentialsServlet srv = new CredentialsServlet();
//			String l = srv.getLogin(sample);
//			System.out.println(l + " " + l.length());
//			String p = srv.getPassword(sample);
//			System.out.println(p + " " + p.length());
//		} catch (ClassNotFoundException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("You got your GET CredentialsServlet request");
		System.out.println(req.getHeader("referer"));
		System.out.println(req.getQueryString());
		System.out.println(req.getRequestURI());

	}



	@Note
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) {
		System.out.println("You got your CredentialsServlet request");
		processor.processRequest(request, resp, getInstance());
	}

	@Target(ElementType.METHOD)
	@interface Note {
		String comment() default " Spare version";
	}


}
