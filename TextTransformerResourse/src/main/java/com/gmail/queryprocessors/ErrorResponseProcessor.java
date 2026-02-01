package com.gmail.queryprocessors;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.gmail.logger.Logger;
import com.gmail.page.PageBuilder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logdisplayerprovider.LogDisplayProvider;

public class ErrorResponseProcessor extends QueryProcessor {
	private Logger logger = super.logger;
	private int errorCode = 0;
	private String errorSource = "";
//	private PageBuilder builder = new PageBuilder();
	private String home = "http://localhost:8080";
	private String loggingMessage = "";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public String processRequest(HttpServletRequest req, LogDisplayProvider provider) {
		String result = "";
		logError(req);
		provider.getDisplay().displayLog(loggingMessage);
		try {
			result = PageBuilder.Page("error404").getAddress();
			if (errorCode == 404) {
				result = PageBuilder.Page("error404").getAddress();
			} else if (errorCode == 505) {

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	private void logError(HttpServletRequest req) {
		errorCode = (Integer) req.getAttribute("jakarta.servlet.error.status_code");
		errorSource = (String) req.getAttribute("jakarta.servlet.error.request_uri");
		loggingMessage = "Error " + errorCode + ": " + home + errorSource;
		logger.log(loggingMessage);
	}

	@Override
	public String getRequestBody(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = new BufferedReader(req.getReader())) {
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Override
	public void processRequest(HttpServletRequest req, HttpServletResponse resp, LogDisplayProvider provider) {
		throw new UnsupportedOperationException("Operation not supported");
	}

}
