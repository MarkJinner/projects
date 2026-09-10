package com.gmail.queryprocessors;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.gmail.database.Email;
import com.gmail.database.User;
import com.gmail.database.UsersDatabase;
import com.gmail.emailreminder.PasswordReminder;
import com.gmail.emailsender.CredentialsReminder;
import com.gmail.exceptions.EmailNotFoundException;
import com.gmail.exceptions.InappropriateEmailFormatException;
import com.gmail.exceptions.UserNotFoundException;
import com.gmail.logger.Logger;
import com.gmail.queryparsers.ForgottenEmailParser;
import com.gmail.queryparsers.ParsedCredentials;
import com.gmail.queryparsers.ParsedEmail;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.bind.JAXBException;
import logdisplayerprovider.LogDisplayProvider;

public class PasswordReminderProcessor extends QueryProcessor implements CredentialsProcessing {
	private PasswordReminder reminder = new PasswordReminder();
	private CredentialsReminder cReminder;
	private UsersDatabase users;
	private String loggingMessage = "";
	private Logger logger = super.logger;
	private String addr = "";

	public PasswordReminderProcessor() throws FileNotFoundException, ClassNotFoundException, IOException {
		users = UsersDatabase.getInstance();
		 try {
			cReminder = new CredentialsReminder();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String processRequest(HttpServletRequest req, LogDisplayProvider provider) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestBody(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		String line = "";
		try (BufferedReader br = new BufferedReader(req.getReader())) {
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Override
	public void sendErrorCode(HttpServletResponse resp, ParsedCredentials credentials) throws IOException {
		this.loggingMessage = Message.EMAILNOTFOUND+addr;
		resp.setStatus(401);
		resp.setContentType("json");
		resp.getWriter().write("{\"error\": \"" + users.geteMessage() + "\"}");
	}

	@Override
	public void sendSuccessRedirect(HttpServletRequest request, HttpServletResponse resp, ParsedCredentials credentials)
			throws IOException {
		resp.setStatus(200);
		try {
//			reminder.sendReminder(addr, new User(credentials));
			cReminder.sendEmail(addr, new User(credentials));
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.loggingMessage = Message.REMINDERSENT + "" + addr;
	}

	@Override
	public void processRequest(HttpServletRequest req, HttpServletResponse resp, LogDisplayProvider provider) {
		Email address = getAddress(req);
		System.out.println("Your request is here");
		try {
			if (users.emailIsInDatabase(address)) {
				System.out.println("Your address found");
				if (users.findByEmail(address).isPresent()) {
					System.out.println("User is found");
					this.sendSuccessRedirect(req, resp, new ParsedCredentials(users.findByEmail(address).get()));
				}

			} else {
				
				sendErrorCode(resp, new ParsedCredentials());

			}
		} catch (EmailNotFoundException e) {
			logger.log(Message.ERROR401+users.geteMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UserNotFoundException e) {
			logger.log(Message.ERROR401+users.geteMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InappropriateEmailFormatException e) {
			logger.log(Message.ERROR401+users.geteMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		provider.getDisplay().displayLog(loggingMessage);

	}

	private Email getAddress(HttpServletRequest req) {
		String reqBody = this.getRequestBody(req);

		ParsedEmail pEmail = (ParsedEmail) new ForgottenEmailParser().parseQuery(reqBody);
		Email address = pEmail.getEmail();
		this.addr = address.getAddress().get();
		return address;
	}

}
