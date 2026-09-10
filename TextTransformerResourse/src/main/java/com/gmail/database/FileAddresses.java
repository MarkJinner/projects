package com.gmail.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class FileAddresses {

	private static File usersFolder = null;
	private static File users_database = null;
	private static File log_address = null;
	private static File emailSender_properties = null;
	private static File host_properties = null;
	private static String site_address = null;

//	 
	public FileAddresses() throws IOException {
		usersFolder = new File(readFile("/Users/olegivanov/eclipse-workspace2026/TextTransformerResourse/src/main/webapp/resource/usersFolder.txt"));
		users_database = new File(readFile(Resources.USERS_DATABASE.toString()));
//		log_address = new File(readFile(Resources.LOG_ADDRESS.toString()));
		log_address = new File(readFile("/Users/olegivanov/eclipse-workspace2026/TextTransformerResourse/src/main/webapp/resource/logAddress.txt"));
		emailSender_properties = new File(readFile(Resources.EMAIL_SENDER_ADDRESS.toString()));
		host_properties = new File(readFile(Resources.HOST_PROPERTIES_ADDRESS.toString()));
		site_address = readFile(Resources.SITE_ADDRESS.toString());
		
	}
	

	

	
	public static File getHost_properties() throws IOException {
//		System.out.println("p="+readFile(Resources.HOST_PROPERTIES_ADDRESS.toString()));
		if (host_properties == null) {	
			host_properties = new File(readFile(Resources.HOST_PROPERTIES_ADDRESS.val));
			host_properties.createNewFile();
		}
		return host_properties;
	}



	public static void setHost_properties(File host_properties) {
		FileAddresses.host_properties = host_properties;
	}
	
	



	public static File getUsersFolder() throws IOException {
		if (usersFolder == null) {
			usersFolder = new File(readFile(Resources.USERS_FOLDER_ADDRESS.toString()));
			usersFolder.createNewFile();
		}
		return usersFolder;
	}

	public static void setUsersFolder(File usersFolder) {
		FileAddresses.usersFolder = usersFolder;
	}
	
	

	public static File getUsers_database() throws IOException {
		if (users_database == null) {
			users_database = new File(readFile(Resources.USERS_DATABASE.val));
			users_database.createNewFile();
		}
		return users_database;
	}

	public static void setUsers_database(File users_database) {
		FileAddresses.users_database = users_database;
	}
	
	

	public static File getLog_address() {
		
		if (log_address == null) {
			try {
				log_address = new File(readFile(Resources.LOG_ADDRESS.toString()));
				log_address.createNewFile();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return log_address;
	}

	public static void setLog_address(File log_address) {
		FileAddresses.log_address = log_address;
	}

	private static String readFile(String address) throws FileNotFoundException {
//		System.out.println(address);
		StringBuilder sb = new StringBuilder();
		try (Scanner sc = new Scanner(new File(address))) {
			while (sc.hasNextLine()) {
				sb.append(sc.next());
			}
		}

		return sb.toString();
	}
	
	

	public static File getEmailSender_properties() throws IOException {
		if (emailSender_properties == null) {
			emailSender_properties = new File(readFile(Resources.EMAIL_SENDER_ADDRESS.val));
			emailSender_properties.createNewFile();
		}	
		return emailSender_properties;
	}

	public static void setEmailSender_properties(File emailSender_properties) {
		FileAddresses.emailSender_properties = emailSender_properties;
	}
	
	

	public static String getSite_address() throws IOException {
		if(FileAddresses.site_address==null) {
			FileAddresses.site_address = readFile(Resources.SITE_ADDRESS.val);
		}

		return site_address;
	}




	public static void setSite_address(String site_address) {
		FileAddresses.site_address = site_address;

	}




	public static void main(String[] args) throws IOException {
		FileAddresses files = new FileAddresses();
		System.out.println(files.getSite_address());
		


		
	}
}
