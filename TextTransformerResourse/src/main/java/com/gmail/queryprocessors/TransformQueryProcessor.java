package com.gmail.queryprocessors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Optional;

import com.gmail.database.User;
import com.gmail.database.UsersDatabase;
import com.gmail.exceptions.FileNotSavedException;
import com.gmail.exceptions.InappropriateEmailFormatException;
import com.gmail.exceptions.InapropriateCredentialsException;
import com.gmail.exceptions.NoSuchUserException;
import com.gmail.logger.Logger;
import com.gmail.queryparsers.FormdataParser;
import com.gmail.queryparsers.ParsedFormData;
import com.gmail.storage.QueryStorage;
import com.gmail.transformationsaver.TransformationSaver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logdisplayerprovider.LogDisplayProvider;

public class TransformQueryProcessor extends QueryProcessor {
	private FormdataParser formParser = new FormdataParser();
	private Logger logger = super.logger;
	private QueryStorage storage;
	private String loggingMessage = "";
	private TransformationSaver saver;
	private UsersDatabase users = UsersDatabase.getInstance();

	public TransformQueryProcessor() throws ClassNotFoundException, IOException {
		storage = new QueryStorage();
		saver = new TransformationSaver();

	}

	@Override
	public String processRequest(HttpServletRequest req, LogDisplayProvider provider) {

		ParsedFormData parsed = formParser.parseQuery(getRequestBody(req));
		String trns = parsed.getTransformer();
		if (parsed.getText() != "") {
			loggingMessage = "Text transformed on page " + req.getHeader("referer");
			logger.log(loggingMessage);
			provider.getDisplay().displayLog(loggingMessage);
			if (req.getSession().getAttribute("user") != null) {
				Optional<User> optUser = null;
				try {
					optUser = users.findByLogin(req.getSession().getAttribute("user").toString());
					if (optUser.isPresent()) {
						try {
							saver.saveTextIntoFile(optUser.get(), parsed.getText());
						} catch (InapropriateCredentialsException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchUserException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InappropriateEmailFormatException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (FileNotSavedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (NoSuchUserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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

	@Override
	public void processRequest(HttpServletRequest req, HttpServletResponse resp, LogDisplayProvider provider) {
		throw new UnsupportedOperationException("Operation not supported");
	}

}
