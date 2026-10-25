package com.gmail.mailanalyzer.main.logger;

import java.io.File;

public class FolderScanner {

	public FolderScanner() {

	}

	public int countFiles(File folder, String fileName) {
		int result = 0;
		if (folder.isDirectory()) {
			result = folder.listFiles((s) -> s.getName().contains(fileName)).length;
		}
		return result;
	}

	public static void main(String [] args) {
		FolderScanner scanner = new FolderScanner();
		int size = scanner.countFiles(new File("/Users/olegivanov/Desktop/Mail/logs"), "log_messages");
		System.out.println(size);	
	}
}
