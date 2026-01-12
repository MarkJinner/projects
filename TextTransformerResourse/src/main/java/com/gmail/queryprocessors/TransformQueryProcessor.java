package com.gmail.queryprocessors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import com.gmail.logger.Logger;
import com.gmail.queryparsers.FormdataParser;
import com.gmail.queryparsers.ParsedFormData;
import com.gmail.storage.QueryStorage;

import jakarta.servlet.http.HttpServletRequest;
import logdisplayerprovider.LogDisplayProvider;

public class TransformQueryProcessor extends QueryProcessor {
	private FormdataParser formParser = new FormdataParser();
	private Logger logger = super.logger;
	private QueryStorage storage;
	private String loggingMessage = "";

	public TransformQueryProcessor() throws ClassNotFoundException, IOException {
		storage = new QueryStorage();
	}

	@Override
	public String processRequest(HttpServletRequest req, LogDisplayProvider provider) {

		ParsedFormData parsed = formParser.parseQuery(getRequestBody(req));
		String trns = parsed.getTransformer();
		if (parsed.getText() != "") {
			loggingMessage = "Text transformed on page " + req.getHeader("referer");
			logger.log(loggingMessage);
			provider.getDisplay().displayLog(loggingMessage);
		}

		try {
			storage.add(logger.getLoggingDate(), parsed.getTransformer(), parsed.getText());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (trns.equals("toUpperCase")) {
			return parsed.getText().toUpperCase();
		}
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(sb.toString());

		return sb.toString();
	}
	
	

}
