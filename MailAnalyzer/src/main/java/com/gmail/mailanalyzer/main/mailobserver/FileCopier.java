package com.gmail.mailanalyzer.main.mailobserver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileCopier {
	private File origin;
	private File folder;

	public FileCopier(File origin, File folder) {
		this.origin = origin;
		this.folder = folder;
	}

	public FileCopier(File origin) {
		this.origin = origin;
	}

	public FileCopier() {

	}

	public File getFile() {
		return origin;
	}

	public void setOriginal(File origin) {
		this.origin = origin;
	}
	
	

	public File getOrigin() {
		return origin;
	}

	public File getFolder() {
		return folder;
	}

	public void setOrigin(File origin) {
		this.origin = origin;
	}

	public void setFolder(File folder) {
		this.folder = folder;
	}

	public File copyFile(InputStream is, String newFileName, File folder) throws FileNotFoundException, IOException {
		
		File temp = new File(folder, newFileName);
		Files.copy(is, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
		return temp;
	}
	
	
}
