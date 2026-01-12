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

//	@Note
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse resp) {
//		System.out.println("You got your POST SPARE CredentialsServlet request");
////		processor.processRequest(request, getInstance());
//		String res = getRequestBody(request);
////		ParsedCredentials credentials = this.parseCredentials(res);
//		ParsedCredentials credentials = (ParsedCredentials) this.parser.parseQuery(res);
//		try {
//			if (users.checkCredentials(credentials)) {
//				System.out.println("users.checkCredentials passed succesfully");
//				resp.setStatus(200);
//				request.getSession().setAttribute("message", "Приветствуем в профиле, " + credentials.getLogin());// it is important to reset again after redirect
//																											
//				resp.sendRedirect("loginSuccess.jsp");
//				request.getSession().setAttribute("message", request.getSession().getAttribute("message"));// it is important to reset again after redirect
//			} else {
//															
//					resp.setStatus(401);
//					resp.setContentType("json");
//					resp.getWriter().write("{\"error\": \""+users.geteMessage()+"\"}");// put any text from any error message	
//
//			}
//		} catch (IOException e) {
//			System.out.println("Error message = "+e.getMessage());
//			e.printStackTrace();
//
//		} catch (InapropriateCredentialsException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (NoSuchUserException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	@Note
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) {
		System.out.println("You got your POST SPARE CredentialsServlet request");
		processor.processRequest(request, resp, getInstance());
	}

	@Target(ElementType.METHOD)
	@interface Note {
		String comment() default " Spare version";
	}

//	private ParsedCredentials parseCredentials(String reqBody) {
//		ParsedCredentials crds = new ParsedCredentials();
//		crds.setLogin(this.getLogin(reqBody));
//		crds.setPassword(this.getPassword(reqBody));
//		return crds;
//
//	}
//
//	private String getLogin(String reqBody) {
//		String s1 = reqBody.substring(reqBody.indexOf("login") + 7, reqBody.indexOf("password"));
//		return s1.substring(0, s1.indexOf("------")).strip();
//	}
//
//	private String getPassword(String reqBody) {
//		String s1 = reqBody.substring(reqBody.indexOf("password") + 10, reqBody.lastIndexOf("------"));
//		return s1.strip();
//	}

}
