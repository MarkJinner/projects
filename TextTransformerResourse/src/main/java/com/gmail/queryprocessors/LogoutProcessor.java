package com.gmail.queryprocessors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import com.gmail.logger.Logger;
import com.gmail.queryparsers.LogoutUserNameParser;
import com.gmail.queryparsers.ParsedUserName;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logdisplayerprovider.LogDisplayProvider;

public class LogoutProcessor extends QueryProcessor{
	private Logger logger = super.logger;
	private LogoutUserNameParser parser = new LogoutUserNameParser();
	private String loggingMessage = "";

	@Override
	public String processRequest(HttpServletRequest req, LogDisplayProvider provider) {
		String reqBody = getRequestBody(req);
		System.out.println(reqBody);
		ParsedUserName userName = (ParsedUserName) parser.parseQuery(reqBody);
		if(userName.getUserName()!=" ") {
			System.out.println("User is: "+req.getSession().getAttribute("user"));
			loggingMessage = Message.LOGOUT+""+userName.getUserName();
			logger.log(loggingMessage);
			provider.getDisplay().displayLog(loggingMessage);
			
			req.getSession().removeAttribute("user");
			req.getSession().invalidate();
			System.out.println("User is(in logout processor): "+req.getSession().getAttribute("user"));
		}
		return null;
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

	@Override
	public void processRequest(HttpServletRequest req, HttpServletResponse resp, LogDisplayProvider provider) {

//		if() {
//			
//		}
		
		
	}

}
