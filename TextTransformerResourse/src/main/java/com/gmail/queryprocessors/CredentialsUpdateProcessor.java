package com.gmail.queryprocessors;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.gmail.database.User;
import com.gmail.database.UsersDatabase;
import com.gmail.emailsender.UpdateSender;
import com.gmail.exceptions.InappropriateEmailFormatException;
import com.gmail.exceptions.InapropriateCredentialsException;
import com.gmail.exceptions.UsedCredentialException;
import com.gmail.exceptions.UserNotFoundException;
import com.gmail.logger.Logger;
import com.gmail.queryparsers.CredentialsQueryParser;
import com.gmail.queryparsers.ParsedCredentials;
import com.gmail.queryparsers.ParsedJson;
import com.gmail.queryparsers.QueryParser;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.xml.bind.JAXBException;
import logdisplayerprovider.LogDisplayProvider;

public class CredentialsUpdateProcessor extends QueryProcessor implements CredentialsProcessing {
	private String loggingMessage = "";
	private Logger logger = super.logger;
	private UpdateSender updateSender;
	private UsersDatabase users;
	private QueryParser parser;
	private String errorMessage;

	public CredentialsUpdateProcessor() throws FileNotFoundException, ClassNotFoundException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, DOMException, InstantiationException, IllegalArgumentException, SAXException {
		users = UsersDatabase.getInstance();
		parser = new CredentialsQueryParser();
		updateSender = new UpdateSender();

	}
	
	
	




	public static void main(String [] args) {
		CredentialsUpdateProcessor proc = null;

			try {
				proc = new CredentialsUpdateProcessor();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
		UpdateSender upd = proc.updateSender;
		upd.sendEmail("", new User());
		System.out.println("!!!?");
	}

	@Override
	public void processRequest(HttpServletRequest req, HttpServletResponse resp, LogDisplayProvider provider)
			throws UserNotFoundException, MessagingException {
		System.out.println("You got your POST CredentialsUpdateprocessor request");

		String reqBody = this.getRequestBody(req);
		String userName = (String) req.getSession().getAttribute("user");
		System.out.println("User's name= " + userName);
		Optional<User> optUser = users.findByLogin(userName);
		User user = null;
		if (optUser.isPresent()) {
			System.out.println("User " + userName + " found in database ");
			user = optUser.get();
		}
		ParsedJson parsed = (ParsedJson) parser.parseQuery(reqBody);

		ParsedCredentials credentials = new ParsedCredentials(user);
		System.out.println(credentials);
		try {
			if (processCredentials(parsed, user, req)) {
				this.sendSuccessRedirect(req, resp, credentials);
			} else {
				sendErrorCode(resp, credentials);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		provider.getDisplay().displayLog(loggingMessage);
		logger.log(loggingMessage);

	}

//	private boolean processCredentials(ParsedJson parsed, User user, HttpServletRequest req) {
//
//		if (parsed.getKey().equals("login")) {
//			try {
//				try {
//					if (users.updateLogin(user, parsed.getValue())) {
//						HttpSession session = req.getSession(false);
//						session.setAttribute("user", parsed.getValue().strip());
//						loggingMessage = Message.LOGIN_UPDATED + user.getLogin();
//						return true;
//					}
//				} catch (UsedCredentialException e) {
//					System.out.println("login=" + e.getMessage());
//					errorMessage = e.getMessage();
//					e.printStackTrace();
//				}
//			} catch (FileNotFoundException e) {
//
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InapropriateCredentialsException e) {
//				
//				errorMessage = e.getMessage();
//				e.printStackTrace();
//			}
//		} else if (parsed.getKey().equals("password")) {
//
//			try {
//				if (users.updatePassword(user, parsed.getValue())) {
//					loggingMessage = Message.PASSWORD_UPDATED + user.getPassword();
//					return true;
//				}
//
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InapropriateCredentialsException e) {
//				errorMessage = e.getMessage();
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} else if (parsed.getKey().equals("email")) {
//			try {
//				try {
//					if (users.updateEmail(user, parsed.getValue())) {
//						loggingMessage = Message.EMAIL_UPDATED + user.getEmail().getAddress().get();
//						return true;
//					}
//				} catch (UsedCredentialException e) {
//					errorMessage = e.getMessage();
//					e.printStackTrace();
//				} catch(InappropriateEmailFormatException e){
//	errorMessage = e.getMessage();
//	// TODO Auto-generated catch block
//	e.printStackTrace();
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InapropriateCredentialsException e) {
//				errorMessage = e.getMessage();
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return false;
//	}

	private boolean processCredentials(ParsedJson parsed, User user, HttpServletRequest req) throws MessagingException {
		
		try {
			if (parsed.getKey().equals("login")) {

				if (users.updateLogin(user, parsed.getValue())) {
					HttpSession session = req.getSession(false);
					session.setAttribute("user", parsed.getValue().strip());
					loggingMessage = Message.LOGIN_UPDATED + user.getLogin();
					updateSender.setUpdated(parsed.getKey());
					updateSender.sendEmail(user.getEmail().getAddress().get(), user);
					return true;
				}

			} else if (parsed.getKey().equals("password")) {
				if (users.updatePassword(user, parsed.getValue())) {
					loggingMessage = Message.PASSWORD_UPDATED + user.getPassword();
					updateSender.setUpdated(parsed.getKey());
					updateSender.sendEmail(user.getEmail().getAddress().get(), user);
					return true;
				}
			} else if (parsed.getKey().equals("email")) {

				if (users.updateEmail(user, parsed.getValue())) {
					loggingMessage = Message.EMAIL_UPDATED + user.getEmail().getAddress().get();
					System.out.println(parsed.getKey());
					updateSender.setUpdated(parsed.getKey());
					updateSender.sendEmail(user.getEmail().getAddress().get(), user);
					return true;
				}

			}
		}

		catch (UsedCredentialException e) {
			errorMessage = e.getMessage();
			e.printStackTrace();
		} catch (InappropriateEmailFormatException e) {
			errorMessage = e.getMessage();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InapropriateCredentialsException e) {
			errorMessage = e.getMessage();
			e.printStackTrace();
		}

		return false;
	}

	private void sendTempResponce(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setStatus(401);
		resp.setContentType("application/json");
		resp.getWriter().write("Unsuccessfull request");
	}

	@Override
	public void sendErrorCode(HttpServletResponse resp, ParsedCredentials credentials) throws IOException {
		this.loggingMessage = Message.UPDATE_FAILED + errorMessage;
		resp.setStatus(401);
		resp.setContentType("json");
		resp.getWriter().write("{\"error\": \"" + errorMessage + "\"}");

	}

	@Override
	public void sendSuccessRedirect(HttpServletRequest request, HttpServletResponse resp, ParsedCredentials credentials)
			throws IOException {
		resp.setStatus(200);
		resp.setContentType("json");
		resp.getWriter().write("{\"Data updated\": \"" + users.geteMessage() + "\"}");

	}

	@Override
	public String processRequest(HttpServletRequest req, LogDisplayProvider provider) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestBody(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();

		try (InputStreamReader br = new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8)) {
			char[] buffer = new char[128];
			br.read(buffer);
			for (int i = 0; i < buffer.length; i++) {
				sb.append(buffer[i]);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

}
