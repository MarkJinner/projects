package com.gmail.queryprocessors;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.gmail.database.UsersDatabase;
import com.gmail.exceptions.InappropriateEmailFormatException;
import com.gmail.exceptions.InapropriateCredentialsException;
import com.gmail.exceptions.NoSuchUserException;
import com.gmail.exceptions.UsedCredentialException;
import com.gmail.logger.Logger;
import com.gmail.queryparsers.CredentialsParser;
import com.gmail.queryparsers.ParsedCredentials;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logdisplayerprovider.LogDisplayProvider;

public class UserRegistrationProcessor extends QueryProcessor implements CredentialsProcessing {
	private CredentialsParser parser = new CredentialsParser();
	private Logger logger = super.logger;
	private UsersDatabase users;
	private String loggingMessage = "";

	 
	
	public UserRegistrationProcessor() {
		try {
			users  = UsersDatabase.getInstance();
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
	}



	@Override
	public String processRequest(HttpServletRequest req, LogDisplayProvider provider) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public String getRequestBody(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(req.getReader())) {
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s);
				sb.append(System.lineSeparator());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Override
	public void processRequest(HttpServletRequest req, HttpServletResponse resp, LogDisplayProvider provider) {
		ParsedCredentials credentials = (ParsedCredentials) this.parser.parseQuery(getRequestBody(req));
		
		try {
			if(users.add(credentials)) {
				sendSuccessRedirect(req,  resp,credentials);
				
			}else {
				sendErrorCode(resp, credentials);
			}
			logger.log(loggingMessage);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UsedCredentialException e) {
			// TODO Auto-generated catch block
			logger.log(Message.ERROR401+users.geteMessage());
			e.printStackTrace();
		}
		catch (IOException e) {
			logger.log(Message.ERROR401+users.geteMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InappropriateEmailFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		provider.getDisplay().displayLog(loggingMessage);
	}
	
	@Override
	public void sendErrorCode(HttpServletResponse resp, ParsedCredentials credentials) throws IOException {
		try {

			this.loggingMessage = Message.FAILEDREG+credentials.getLogin()+": "+users.geteMessage();
			resp.setStatus(401);
			resp.setContentType("json");
			resp.getWriter().write("{\"error\": \""+users.geteMessage()+"\"}");
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}

	@Override
	public void sendSuccessRedirect(HttpServletRequest request, HttpServletResponse resp, ParsedCredentials credentials)
			throws IOException {
		resp.setStatus(200);
		this.loggingMessage = Message.SUCCESSREG+credentials.getLogin();
		request.getSession().setAttribute("user", credentials.getLogin().strip());// it is important to reset again after redirect																	
		resp.sendRedirect("userIndex.jsp");			
		request.getSession().setAttribute("user", request.getSession().getAttribute("user"));// it is important to reset again after redirect
	}
	


}
