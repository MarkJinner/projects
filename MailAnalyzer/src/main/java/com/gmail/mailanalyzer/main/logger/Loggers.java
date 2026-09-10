package com.gmail.mailanalyzer.main.logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Loggers {
	public static Loggers instance = null;
	private List<Logger> loggers;

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
	}

	public List<Logger> getLoggers() throws IOException {
		List<Logger> loggers = new ArrayList<>(List.of(new MessageLogger(), new ConnectionCheckMessageLogger()));
		return loggers;
	}

	public static void setInstance(Loggers instance) {
		Loggers.instance = instance;
	}

	public void setLoggers(List<Logger> loggers) {
		this.loggers = loggers;
	}

}
