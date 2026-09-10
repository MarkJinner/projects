package com.gmail.sparefoldercreator;

import java.io.File;
import java.io.IOException;

import com.gmail.database.FileAddresses;;

public class FolderFixer {
	private String userName;
//	private Resources resources;
	private FileAddresses files;
	
	public FolderFixer() throws IOException {
		files  = new FileAddresses(); 
	}
	
//	public static void fixFolder(String userName) {
//		File file  = new File(Resources.USERS_FOLDER_ADDRESS+"", userName);
//		file.mkdirs();
//		System.out.println("spare folder created");
//	}

	public static void fixFolder(String userName) throws IOException {
		File file  = new File(FileAddresses.getUsersFolder(), userName);
		file.mkdirs();
		System.out.println("spare folder created");
		
	}


	
}
