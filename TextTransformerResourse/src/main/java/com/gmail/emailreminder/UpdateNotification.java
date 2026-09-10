package com.gmail.emailreminder;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.gmail.database.Email;
import com.gmail.emailsender.SenderProperties;

import jakarta.xml.bind.JAXBException;

public class UpdateNotification implements UpdateNotificationSender{
	private SenderProperties props;
	
	public UpdateNotification() throws ParserConfigurationException, JAXBException, IOException, NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException, DOMException, InstantiationException, IllegalArgumentException, SAXException {
		props = new SenderProperties();
		
	}

	@Override
	public void sendSuccessUpdateNotification(String address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendSuccessUpdateNotification(Email address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendFailedUpdateNotification(Email address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendFailedUpdateNotification(String address) {
		// TODO Auto-generated method stub
		
	}

}
