package com.gmail.servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.gmail.database.UsersDatabase;
import com.gmail.emailsender.SenderProperties;
import com.gmail.logger.Logger;
import com.gmail.queryparsers.CredentialsParser;
import com.gmail.queryprocessors.CredentialsProcessor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logdisplayerprovider.LogDisplayProvider;

public class CredentialsServlet extends BasicServlet {
	private CredentialsProcessor processor;
	private UsersDatabase users;
	private CredentialsParser parser;
	private String responseMessage = "";
	private Logger logger = super.getLogger();

	public CredentialsServlet() throws FileNotFoundException, ClassNotFoundException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, DOMException, InstantiationException, IllegalArgumentException, SAXException {
		processor = new CredentialsProcessor();
		users = new UsersDatabase();
		parser = new CredentialsParser();
	}

	public static void main(String[] args) {
		System.out.println("!??");
		String sample = "------WebKitFormBoundaryWlM6BSdARUgsL44I\n"
				+ "Content-Disposition: form-data; name=\"login\"\n" + "\n" + "user134\n"
				+ "------WebKitFormBoundaryWlM6BSdARUgsL44I\n" + "Content-Disposition: form-data; name=\"password\"\n"
				+ "\n" + "user1Pass\n" + "------WebKitFormBoundaryWlM6BSdARUgsL44I--";

		SenderProperties props = null;
		try {
			props = new SenderProperties();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		System.out.println(props.getHost());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("You got your GET CredentialsServlet request");
		System.out.println(req.getHeader("referer"));
		System.out.println(req.getQueryString());
		System.out.println(req.getRequestURI());

	}




    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) {
		System.out.println("You got your POST CredentialsServlet request");
		LogDisplayProvider provider = LogDisplayProvider.getInstance();
		processor.processRequest(request, resp, provider);
	}



}
