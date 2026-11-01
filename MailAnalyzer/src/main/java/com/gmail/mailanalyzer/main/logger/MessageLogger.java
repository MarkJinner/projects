package com.gmail.mailanalyzer.main.logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.gmail.mailanalyzer.main.jsonconverter.JsonConverter;
import com.gmail.mailanalyzer.main.observerframe.message.Level;
import com.gmail.mailanalyzer.main.observerframe.message.Message;

public class MessageLogger implements Logger {
	private static MessageLogger instance = null;
	private final String logNameFieldKey = "messageLogName";
	private final String folderNameFieldKey = "messageFolderName";
	private final String propertiesName = "loggers.properties";
	private final String logMaxLinesKey= "messageLogMaxLines";
	private LoggerTools tools;
	private Properties loggerProps;
	private LoggerTextSaver textSaver;
	
	private MessageLogger() throws FileNotFoundException, IOException {
		initProperties();
		tools = new LoggerTools(Level.Request, loggerProps.getProperty(folderNameFieldKey));
		textSaver = new LoggerTextSaver(tools,loggerProps, logMaxLinesKey, logNameFieldKey);
	}




	public static MessageLogger getInstance() throws FileNotFoundException, IOException {
		if (instance == null) {
			instance = new MessageLogger();
		}
		
		return instance;
	}



	public static void setInstance(MessageLogger instance) {
		MessageLogger.instance = instance;
	}




	@Override
	public Level getLevel() {
		return tools.getLevel();
	}
	
	private void initProperties() throws IOException, FileNotFoundException {
		loggerProps  = new Properties();
		File configFile = new File(System.getProperty("user.dir") + File.separator + propertiesName);
		loggerProps.load(new FileInputStream(configFile));
	}

	@Override
	public void next(Message message) throws IOException {
	tools.getLoggers().getLoggers().stream().forEach(s -> {
			if (s.getLevel() != this.getLevel()) {
				tools.setNext(s);
				
				if ( tools.getNext().getLevel() == message.getLevel()) {
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
		textSaver.save(message);
	}

	public static void main(String [] args) throws FileNotFoundException, IOException {
		MessageLogger logger = MessageLogger.getInstance();
	}

}
