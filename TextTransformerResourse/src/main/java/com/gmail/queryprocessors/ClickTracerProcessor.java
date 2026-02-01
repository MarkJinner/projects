package com.gmail.queryprocessors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import com.gmail.logger.Logger;
import com.gmail.queryparsers.GoToPageFormParser;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logdisplayerprovider.LogDisplayProvider;

public class ClickTracerProcessor extends QueryProcessor {
	private Logger logger = super.logger;
	private GoToPageFormParser parser = new GoToPageFormParser();
	private String loggingMessage = "";

	@Override
	public String processRequest(HttpServletRequest req, LogDisplayProvider provider) {
		String requestBody = getRequestBody(req);
		loggingMessage = parser.parseQuery(requestBody).getText();
		logger.log(loggingMessage);
		provider.getDisplay().displayLog(loggingMessage);
		return "";
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
		String requestBody = getRequestBody(req);
		loggingMessage = parser.parseQuery(requestBody).getText();
		logger.log(loggingMessage);
		provider.getDisplay().displayLog(loggingMessage);

	}
	


}
