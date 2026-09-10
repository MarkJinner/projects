package com.gmail.queryprocessors;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.gmail.database.UsersDatabase;
import com.gmail.exceptions.InapropriateCredentialsException;
import com.gmail.exceptions.UserNotFoundException;
import com.gmail.logger.Logger;
import com.gmail.queryparsers.CredentialsParser;
import com.gmail.queryparsers.ParsedCredentials;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logdisplayerprovider.LogDisplayProvider;

public class CredentialsProcessor extends QueryProcessor implements CredentialsProcessing {
	private CredentialsParser parser = new CredentialsParser();
	private Logger logger = super.logger;
	private UsersDatabase users;
	private String loggingMessage = "";
	private CredentialsUpdateProcessor updateProcessor;
	public CredentialsProcessor() throws FileNotFoundException, ClassNotFoundException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, DOMException, InstantiationException, IllegalArgumentException, SAXException {
		users = UsersDatabase.getInstance();
		updateProcessor = new CredentialsUpdateProcessor();
	}
	 
	
	public static void main(String [] args) {

	}

	@Override
	public String processRequest(HttpServletRequest req, LogDisplayProvider provider) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse resp, LogDisplayProvider provider) {
		
	
//		
		if(request.getRequestURI().equals("/credentialsUpdate")) {
			try {
				updateProcessor.processRequest(request, resp, provider);
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		String res = getRequestBody(request);

		ParsedCredentials credentials = (ParsedCredentials) this.parser.parseQuery(res);
		try {
			if (users.checkCredentials(credentials)) {
				sendSuccessRedirect(request, resp, credentials);
			} else {
				sendErrorCode(resp, credentials);

			}
			logger.log(loggingMessage);
		} catch (IOException e) {
			e.printStackTrace();

		} catch (InapropriateCredentialsException e) {
			logger.log(Message.ERROR401 + users.geteMessage());
			e.printStackTrace();
		} catch (UserNotFoundException e) {

			logger.log(Message.ERROR401 + users.geteMessage());
			e.printStackTrace();
		}

		provider.getDisplay().displayLog(loggingMessage);
	}


	public void sendErrorCode(HttpServletResponse resp, ParsedCredentials credentials) throws IOException {
		this.loggingMessage = Message.FAILEDLOGIN + credentials.getLogin() + ": " + users.geteMessage();
		resp.setStatus(401);
		resp.setContentType("json");
		resp.getWriter().write("{\"error\": \"" + users.geteMessage() + "\"}");// put any text from any error message
	}

	public void sendSuccessRedirect(HttpServletRequest request, HttpServletResponse resp, ParsedCredentials credentials)
			throws IOException {
		resp.setStatus(200);
		this.loggingMessage = Message.SUCCESSLOGIN + credentials.getLogin();
		HttpSession session = request.getSession(false);                 
//		createTempFile(credentials.getLogin(), session);
//		System.out.println("Session ID for :"+credentials.getLogin()+" "+newSession.getId());
		session.setAttribute("user", credentials.getLogin().strip());// it is important to reset again after redirect
		resp.sendRedirect("user/index.jsp");
//		newSession.setAttribute("user", newSession.getAttribute("user"));// it is important to reset again after
																			// redirect
		

	}


	@Override
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
