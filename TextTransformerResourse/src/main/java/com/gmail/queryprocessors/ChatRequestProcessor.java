package com.gmail.queryprocessors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.gmail.exceptions.UserNotFoundException;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logdisplayerprovider.LogDisplayProvider;

public class ChatRequestProcessor extends QueryProcessor{

	@Override
	public String processRequest(HttpServletRequest req, LogDisplayProvider provider) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRequestBody(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()))){
			String line = "";
			while((line = br.readLine())!=null) {
				sb.append(line);
				if(line.isEmpty()) {
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return sb.toString();
	}

	@Override
	public void processRequest(HttpServletRequest req, HttpServletResponse resp, LogDisplayProvider provider)
			throws UserNotFoundException, MessagingException {
		System.out.println(getRequestBody(req));
		
	}

}
