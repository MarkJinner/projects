package com.gmail.queryprocessors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

import com.gmail.database.UsersDatabase;
import com.gmail.exceptions.InapropriateCredentialsException;
import com.gmail.exceptions.NoSuchUserException;
import com.gmail.logger.Logger;
import com.gmail.queryparsers.CredentialsParser;
import com.gmail.queryparsers.ParsedCredentials;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logdisplayerprovider.LogDisplayProvider;

public class CredentialsProcessor extends QueryProcessor implements CredentialsProcessing {
	private CredentialsParser parser = new CredentialsParser();
	private Logger logger = super.logger;
	private UsersDatabase users;
	private String loggingMessage = "";

	public CredentialsProcessor() throws FileNotFoundException, ClassNotFoundException, IOException {
		users = new UsersDatabase();
	}

	@Override
	public String processRequest(HttpServletRequest req, LogDisplayProvider provider) {
		throw new UnsupportedOperationException("Operation not supported");
	}

	public void processRequest(HttpServletRequest request, HttpServletResponse resp, LogDisplayProvider provider) {
		String res = getRequestBody(request);
		ParsedCredentials credentials = (ParsedCredentials) this.parser.parseQuery(res);
		System.out.println(credentials.getLogin());
		System.out.println(credentials.getEmail());
		System.out.println(credentials.getPassword());
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
		} catch (NoSuchUserException e) {

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
		System.out.println("Another session id: " + request.getSession().getId());
		resp.setStatus(200);
		this.loggingMessage = Message.SUCCESSLOGIN + credentials.getLogin();
		HttpSession session = request.getSession(false);                 
		createTempFile(credentials.getLogin(), session);
//		System.out.println("Session ID for :"+credentials.getLogin()+" "+newSession.getId());
		session.setAttribute("user", credentials.getLogin().strip());// it is important to reset again after redirect
		resp.sendRedirect("user/index.jsp");
//		newSession.setAttribute("user", newSession.getAttribute("user"));// it is important to reset again after
																			// redirect
		

	}
	
	private void createTempFile(String userName,HttpSession session) throws IOException {
		File file = new File(new File(users.getUsersFolder()+"/"+userName),"temp");
		file.createNewFile();
		if(file.length()>0) {
			file.delete();
		}
		file.createNewFile();
		try(PrintWriter pw = new PrintWriter(file)){
			pw.print(session.getId());
		}
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
