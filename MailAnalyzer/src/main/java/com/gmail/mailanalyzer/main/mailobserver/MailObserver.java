package com.gmail.mailanalyzer.main.mailobserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import jakarta.mail.MessagingException;

public class MailObserver {
	private InboxScanner scanner;
	
	public MailObserver() throws FileNotFoundException, IOException {
		scanner	 = new InboxScanner();
	}
	
	

	public InboxScanner getScanner() {
		return scanner;
	}



	public void setScanner(InboxScanner scanner) {
		this.scanner = scanner;
	}



	public void scanEmailInbox() throws FileNotFoundException, IOException, InterruptedException {
		scanner.scanInbox();
	}
	
	private boolean findProperties() {
//		String userHome = System.getProperty("user.home");
		if(MailObserver.class.getClassLoader().getResourceAsStream("properties.properties")!=null) {
			return true;
		}
		
		return false;
	}
	
	private void checkExternalFiles() {
		
	}





	public static void main(String[] args) throws MessagingException, FileNotFoundException, IOException, InterruptedException {
		MailObserver observer = new MailObserver();
		String currentDir = System.getProperty("user.dir");
		File [] folder = new File(currentDir).listFiles();
		System.out.println(Arrays.stream(folder).anyMatch(s->s.getName().equals("mail_properties.properties")));
		System.out.println(currentDir);
//		observer.scanEmailInbox();
//		System.out.println(observer.findProperties());
//		File path = ifMail(new File("copied_properties.properties"));
//		
//		if(path==null) {
//			InputStream is = MailObserver.class.getClassLoader().getResourceAsStream("properties.properties");
////			System.out.println("Yes");
//			File copied = new FileCopier().copyFile(is, "copied_properties.properties", new File(System.getProperty("user.home")));
//			System.out.println("File successfully created "+copied.getAbsolutePath());
//			
//		}else {
//			System.out.println(path.getAbsolutePath());
//		}
		
		
		
		
		
	}
	
	private static File ifMail(File file) {
		File temp = null;
		if(file.isDirectory()) {
			File [] home = file.listFiles();
			for(File folderItem: home) {
				ifMail(folderItem);
			}
		}else {
			if(file.getName().equals("mail_properties.properties")) {
				temp = file;
			}
		}
		return temp;
		
	}

}
