package com.gmail.queryprocessors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gmail.database.UsersDatabase;
import com.gmail.exceptions.FileNotSavedException;
import com.gmail.exceptions.InappropriateEmailFormatException;
import com.gmail.exceptions.InapropriateCredentialsException;
import com.gmail.exceptions.UserNotFoundException;
import com.gmail.logger.Logger;
import com.gmail.queryparsers.FormdataParser;
import com.gmail.queryparsers.ParsedFormData;
import com.gmail.sparefoldercreator.FolderFixer;
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
	private String text = "";
	private UsersDatabase users = UsersDatabase.getInstance();

	public TransformQueryProcessor() throws ClassNotFoundException, IOException {
		storage = new QueryStorage();
		saver = new TransformationSaver();

	}

	@Override
	public String processRequest(HttpServletRequest req, LogDisplayProvider provider) {
		ParsedFormData parsed = formParser.parseQuery(getRequestBody(req));
		String trns = parsed.getTransformer();
		loggingMessage = Message.TEXTTRANSFORMED + req.getHeader("referer");
		logMessage(loggingMessage, provider);
		addTextToTextStorage(parsed);
		getConvertedText(parsed, trns);
		saveIfUserRegistered(req, provider, trns);		
		return text;
	}

	private void saveIfUserRegistered(HttpServletRequest req, LogDisplayProvider provider, String trns) {
		System.out.println("User is registered");
		if (req.getSession().getAttribute("user") != null) {
			saveUsersText(req, trns, provider);
			loggingMessage = Message.TEXTSAVED+""+req.getSession().getAttribute("user");
			
			logMessage(loggingMessage, provider);
		}
	}
	
	private void logMessage(String loggingMessage,LogDisplayProvider provider) {
		logger.log(loggingMessage);
		provider.getDisplay().displayLog(loggingMessage);
	}

	private void getConvertedText(ParsedFormData parsed, String trns) {
		if (trns.equals("toUpperCase")) {
			text = parsed.getText().toUpperCase();
		} else if (trns.equals("toLowerCase")) {
			text = parsed.getText().toLowerCase();
		} else if (trns.equals("removePunctuations")) {
			text = removePunctuations(parsed.getText());
		} else if (trns.equals("removeLineBreaks")) {

		}
	}

	private void saveUsersText(HttpServletRequest req, String trns,LogDisplayProvider provider) {
		System.out.println("USER's text saved");
		try {
			users.findByLogin(req.getSession().getAttribute("user").toString()).ifPresent((s) -> {
				String userName = req.getSession().getAttribute("user").toString();
				try {
					
					saver.saveTextIntoFile(users.findByLogin(req.getSession().getAttribute("user").toString()).get(),
							trns, text);
				} catch (InapropriateCredentialsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UserNotFoundException e) {
					// TODO Auto-generated catch block
					try {
						FolderFixer.fixFolder(userName);
						this.saveUsersText(req, trns,provider);	
						this.logMessage(Message.SPAREFOLDERCREATED+" for user "+userName, provider);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
			});

		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
	}

	private void addTextToTextStorage(ParsedFormData parsed) {
		try {
			storage.add(logger.getLoggingDate(), parsed.getTransformer(), parsed.getText());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String removePunctuations(String text) {
		StringBuilder sb = new StringBuilder();
		List<String> pncts = new ArrayList<>(List.of(".", ",", ":", "?", "!", "-", " - "));

		Arrays.stream(text.split("")).forEach((s) -> {
			if (pncts.stream().anyMatch((r) -> s.equals(r))) {
				sb.append(" ");
			} else {
				sb.append(s + "");
			}
		});
		return sb.toString();
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		TransformQueryProcessor proc = new TransformQueryProcessor();
		String example = "This is: test!";
		System.out.println(proc.removePunctuations(example));
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
