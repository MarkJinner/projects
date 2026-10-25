package com.gmail.mailanalyzer.main.logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Loggers {
	public static Loggers instance = null;
	private static File mainFolder;
	

	private Loggers() {
		mainFolder = new File("logs");

		if (!mainFolder.exists()) {
			mainFolder.mkdirs();

		} 
	}
	
	

	public static File getMainFolder() {
		return mainFolder;
	}



	public static void setMainFolder(File mainFolder) {
		Loggers.mainFolder = mainFolder;
	}



	public static Loggers getInstance() {
		if (instance == null) {

			instance = new Loggers();
		}
		return instance;
	}

	public static void main(String[] args) throws IOException {
		Loggers instance = Loggers.getInstance();
		System.out.println(instance.getLoggers());
	}

	public List<Logger> getLoggers() throws IOException {
		return new ArrayList<>(List.of(MessageLogger.getInstance(), ConnectionLogger.getInstance(), ExceptionLogger.getInstance()));
	}

	public static void setInstance(Loggers instance) {
		Loggers.instance = instance;
	}



}
