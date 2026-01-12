package com.gmail.queryprocessors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import com.gmail.clipboarder.Clipboarder;
import com.gmail.logger.Logger;
import com.gmail.queryparsers.FormdataParser;

import jakarta.servlet.http.HttpServletRequest;
import logdisplayerprovider.LogDisplayProvider;

public class CopyToClipboardQueryProcessor extends QueryProcessor{
	private Logger logger = super.logger;
	private Clipboarder clipper = new Clipboarder();
	private FormdataParser formParser = new FormdataParser();
	private String loggingMessage = "";

	@Override
	public String processRequest(HttpServletRequest req, LogDisplayProvider provider) {
		String requestBody = getRequestBody(req);
//		System.out.println("Text copied on "+req.getHeader("referer"));
		String copied = formParser.parseQuery(requestBody).getText();
		if(copied!="") {
			clipper.copyToClipboard(formParser.parseQuery(requestBody).getText());
			loggingMessage = "Transformed text copied to clipboard on "+req.getHeader("referer");
			logger.log(loggingMessage);
			provider.getDisplay().displayLog(loggingMessage);	
		}

		System.out.println("copied= "+copied);
		return copied;
	}

	@Override
	public String getRequestBody(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		String line = "";
		try (Reader reader = req.getReader();BufferedReader bf = new BufferedReader(reader)) {
			
			while ((line = bf.readLine()) != null) {
				sb.append(line + System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

}
