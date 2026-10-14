package com.gmail.mailanalyzer.main.logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Loggers {
	public static Loggers instance = null;

	private Loggers() {

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
		return new ArrayList<>(List.of(new MessageLogger(), new ConnectionCheckMessageLogger(), new ExceptionMessageLogger()));
	}

	public static void setInstance(Loggers instance) {
		Loggers.instance = instance;
	}



}
