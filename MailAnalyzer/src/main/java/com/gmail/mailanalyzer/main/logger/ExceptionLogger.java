package com.gmail.mailanalyzer.main.logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.gmail.mailanalyzer.main.jsonconverter.JsonConverter;
import com.gmail.mailanalyzer.main.observerframe.message.Level;
import com.gmail.mailanalyzer.main.observerframe.message.Message;

public class ExceptionLogger implements Logger {

	private static ExceptionLogger instance = null;
	private final String logNameFieldKey = "exceptionLogName";
	private final String folderNameFieldKey = "exceptionFolderName";
	private final String propertiesName = "loggers.properties";
	private final String logMaxLinesKey = "exceptionLogMaxLines";
	private LoggerTools tools;
	private Properties loggerProps;
	private LoggerTextSaver textSaver;

	public static ExceptionLogger getInstance() throws IOException {
		if (instance == null) {
			instance = new ExceptionLogger();
		}
		return instance;
	}

	private ExceptionLogger() throws IOException {
		initProperties();
		tools = new LoggerTools(Level.Exception, loggerProps.getProperty(folderNameFieldKey));
		textSaver = new LoggerTextSaver(tools, loggerProps, logMaxLinesKey, logNameFieldKey);
	}

	private void initProperties() throws IOException, FileNotFoundException {
		loggerProps = new Properties();
		File configFile = new File(System.getProperty("user.dir") + File.separator + propertiesName);
		loggerProps.load(new FileInputStream(configFile));
	}

	public static void setInstance(ExceptionLogger instance) {
		instance = instance;
	}

	@Override
	public Level getLevel() {
		return tools.getLevel();
	}

	@Override
	public void next(Message message) throws IOException {

		tools.getLoggers().getLoggers().stream().forEach(s -> {
			if (s.getLevel() != this.getLevel()) {

				tools.setNext(s);
				if (tools.getNext().getLevel() == message.getLevel()) {
					try {
						tools.getNext().log(message);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		});

	}

	@Override
	public void log(Message message) throws IOException {

		if (message.getLevel() == tools.getLevel()) {
			saveMessage(message);
		} else {
			next(message);
		}

	}

	private void saveMessage(Message message) throws IOException {
		this.textSaver.save(message);
	}

}
