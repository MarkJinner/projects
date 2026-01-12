package com.gmail.queryprocessors;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

import com.gmail.database.UsersDatabase;
import com.gmail.exceptions.InapropriateCredentialsException;
import com.gmail.exceptions.NoSuchUserException;
import com.gmail.logger.Logger;
import com.gmail.queryparsers.CredentialsParser;
import com.gmail.queryparsers.ParsedCredentials;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logdisplayerprovider.LogDisplayProvider;

public class CredentialsProcessor extends QueryProcessor {
	private CredentialsParser parser = new CredentialsParser();
	private Logger logger = super.logger;
	private String success = "Successfull login attempt for user ";
	private String failure = "Failed login attempt for user ";
	private UsersDatabase users;
	private String loggingMessage = "";
	private String errorMesssage  = "Error 404: ";

	public CredentialsProcessor() throws FileNotFoundException, ClassNotFoundException, IOException {
		users = new UsersDatabase();
	}

	@Override
	public String processRequest(HttpServletRequest req, LogDisplayProvider provider) {
		throw new UnsupportedOperationException();	
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse resp, LogDisplayProvider provider) {
		String res = getRequestBody(request);
		ParsedCredentials credentials = (ParsedCredentials) this.parser.parseQuery(res);
		
		try {
			if (users.checkCredentials(credentials)) {
				System.out.println("users.checkCredentials passed succesfully");
				resp.setStatus(200);
				this.loggingMessage = success+credentials.getLogin();
				logger.log(loggingMessage);
				request.getSession().setAttribute("user", credentials.getLogin().strip());
//				request.getSession().setAttribute("message", "Приветствуем в профиле, " + credentials.getLogin());// it is important to reset again after redirect																							
				resp.sendRedirect("userIndex.jsp");
//				request.getSession().setAttribute("message", request.getSession().getAttribute("message"));// it is important to reset again after redirect
				request.getSession().setAttribute("user", request.getSession().getAttribute("user"));
			} else {
					this.loggingMessage = failure+credentials.getLogin()+": "+users.geteMessage();
					logger.log(loggingMessage);
					resp.setStatus(401);
					resp.setContentType("json");
					resp.getWriter().write("{\"error\": \""+users.geteMessage()+"\"}");// put any text from any error message	

			}
		} catch (IOException e) {
			e.printStackTrace();

		} catch (InapropriateCredentialsException e) {
			logger.log(errorMesssage+users.geteMessage());
			e.printStackTrace();
		} catch (NoSuchUserException e) {

			logger.log(errorMesssage+users.geteMessage());
			e.printStackTrace();
		}
		
		provider.getDisplay().displayLog(loggingMessage);
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

	private boolean credentialsIsCorrect(ParsedCredentials credentials) throws InapropriateCredentialsException, NoSuchUserException {
		if (users.checkCredentials(credentials)) {
			return true;
		}
		return false;
	}

}
