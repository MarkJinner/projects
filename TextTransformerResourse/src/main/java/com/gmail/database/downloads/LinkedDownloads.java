package com.gmail.database.downloads;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.gmail.database.User;
import com.gmail.database.UsersDatabase;
import com.gmail.exceptions.EmailNotFoundException;
import com.gmail.exceptions.InappropriateEmailFormatException;
import com.gmail.exceptions.InapropriateCredentialsException;
import com.gmail.exceptions.UserNotFoundException;

public class LinkedDownloads {
	private UsersDatabase users; 
	private List<Map<String, String>> linkedDownloads;
	
	
	public LinkedDownloads() throws FileNotFoundException, ClassNotFoundException, IOException {
		linkedDownloads = new ArrayList<>();
		users = UsersDatabase.getInstance();
	}
	
//	public List<Map<String, String>>  getLinkedDownLoads(User user) throws FileNotFoundException, ClassNotFoundException, IOException, EmailNotFoundException, InappropriateEmailFormatException, InapropriateCredentialsException, UserNotFoundException{
//		DownloadsList downloads = new DownloadsList(user);
//		List<String> res = downloads.getFormattedDownloadsList(user);
//		
////		System.out.println(res);
//
//		
//		return linkedDownloads;
//	}
	
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException, UserNotFoundException {
		LinkedDownloads linked = new LinkedDownloads();
		Optional<User> optUser = linked.users.findByLogin("user21");
		
//		optUser.ifPresent(s->{
//			try {
//				linked.getLinkedDownLoads(optUser.get());
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (EmailNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InappropriateEmailFormatException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InapropriateCredentialsException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (UserNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		});
		
		
	}
	
	
}
