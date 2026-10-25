package com.gmail.mailanalyzer.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.gmail.mailanalyzer.main.mailobserver.MailObserver;
import com.gmail.mailanalyzer.main.mailobserver.Order;
import com.gmail.mailanalyzer.main.mailobserver.ResponseSender;
import com.gmail.mailanalyzer.main.mailobserver.SenderProperties;
import com.gmail.mailanalyzer.main.mailobserver.SetupProperties;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		MailObserver observer;

		try {
			observer = new MailObserver();
			observer.scanEmailInbox();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		
//		checkResponse();
//		downloadFileFromUsersSpecialFolder();
//		downloadFileFolderNextToJar();
	}
	
	private static void checkResponse() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		String currentDir = System.getProperty("user.dir");
		File configFile = new File(currentDir+File.separator+"mail_properties.properties"); 
		properties.load(new FileInputStream(configFile));
		SetupProperties props = new SenderProperties(properties);
		ResponseSender sender = new ResponseSender(props);
		sender.sendResponse(new Order());
	} 
	

}
