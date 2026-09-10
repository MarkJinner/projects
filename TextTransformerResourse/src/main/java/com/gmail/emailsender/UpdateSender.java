package com.gmail.emailsender;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;

import com.gmail.database.User;

import jakarta.mail.MessagingException;
import jakarta.xml.bind.JAXBException;



public class UpdateSender extends Sender {
	private EmailSender emailSender;
	private String updated = "";
	private String logMessage = "";
	
	public UpdateSender()  {
		try {
			emailSender = new EmailSender();
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
	
	
	
	
	
	public EmailSender getEmailSender() {
		return emailSender;
	}





	public void setEmailSender(EmailSender emailSender) {
		this.emailSender = emailSender;
	}





	public static void main(String [] args) throws IOException, IllegalArgumentException, IllegalAccessException, MessagingException, ParserConfigurationException, JAXBException, NoSuchMethodException, SecurityException, InvocationTargetException, DOMException, InstantiationException, SAXException {
		UpdateSender updateSender = new UpdateSender();
		
//		System.out.println(sender.updatedToCapital("hello"));
		updateSender.updated = "login";
				
		User user = new User("Oleg_ua@n21.com", "user77", "user77");
		updateSender.sendEmail(user.getEmail().getAddress().get(), user);
//		System.out.println("RESULT:");
		System.out.println(updateSender.defineUpdated(user, "email"));
		
	}

	


	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	@Override
	public void sendEmail(String address, User user){
		try {
			try {
				if(emailSender.sendEmail(address, user, compileMessage(user, updated))) {
					logMessage = "Message on successful " +updated +" update sent on "+user.getEmail().getAddress().get(); 	
				}
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logMessage = e.getMessage();
				
			}
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.getLogger().log(logMessage);
		super.getProvider().getDisplay().displayLog(logMessage);
		
		
	}
	
	private String compileMessage(User user,String updated) throws IllegalArgumentException, IllegalAccessException {
		
		String result = updatedToCapital(updated)+" successfully updated: "+defineUpdated(user, updated);
		return result;
	}
	
	private String updatedToCapital(String updated) {
		String result = ""; 
		char[] upd =  updated.toCharArray();
		for(int i = 0;i< upd.length;i++) {
			if(i==0) {
				result = String.valueOf(upd[i]).toUpperCase();
			}else {
				result = result+upd[i];
			}
		}
		return result;
	}
	
	private String defineUpdated(User user, String updated ) throws IllegalArgumentException, IllegalAccessException {
		Field[] fields = user.getClass().getDeclaredFields();
		String result = "";
		for(int i = 0; i< fields.length;i++) {
			if(fields[i].getName().contains(updated)) {
				fields[i].setAccessible(true);
				if(fields[i].getName().equals("email")) {
					result = user.getEmail().getAddress().get();
				}else {
					result = (String) fields[i].get(user);
				}
				
				break;
			}
		}
		
		return result;
	}
	
}
