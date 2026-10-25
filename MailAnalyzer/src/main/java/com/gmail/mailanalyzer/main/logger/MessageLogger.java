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
//	private Logger next;
//	private Level level = Level.Request;
//	private Writer writer;
//	private File file;
//	private JsonConverter<MessageLogger> converter = new JsonConverter<>();
//	private Loggers loggers;
//	private File logs;
//
	private static MessageLogger instance = null;
	private final String logNameField = "messageLogName";
	private final String folderNameField = "messageFolderName";
	private final String propertiesName = "loggers.properties";
	private final String logMaxLines = "messageLogMaxLines";
	private LoggerTools tools;
	private Properties loggerProps = new Properties();
	
	private MessageLogger() throws FileNotFoundException, IOException {
		File configFile = new File(System.getProperty("user.dir") + File.separator + propertiesName);
		loggerProps.load(new FileInputStream(configFile));
		tools = new LoggerTools(Level.Request, loggerProps.getProperty(folderNameField));
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
		if (Loggers.getMainFolder().exists()) {
			int number = countFilesInFolder();
			if (number == 0) {
				setAndCreateNewLogFile(0);		
			} else if (number > 0) {
				tools.setLog(getLogFile((number - 1)));
				if (countLines(tools.getLog()) > Integer.parseInt((String) loggerProps.get(logMaxLines))) {
					setAndCreateNewLogFile(number);	
				}
			}
			tools.getWriter().setFile(tools.getLog());
			tools.getWriter().write(tools.getConverter().toJson(message));
		}
	}
	
	private int countFilesInFolder() {
		return tools.getScanner().countFiles(tools.getFolder(), loggerProps.getProperty(logNameField));
	}

	private File getLogFile(int number) {
		return new File(tools.getFolder(), loggerProps.getProperty(logNameField) + number);
	}
	private void setAndCreateNewLogFile(int number) throws IOException {
		tools.setLog(getLogFile(number));
		tools.getLog().createNewFile();
	}

	private int countLines(File file) throws FileNotFoundException, IOException {
		return tools.getReader().read(file).size();
	}
	
	public static void main(String [] args) throws FileNotFoundException, IOException {
		MessageLogger logger = MessageLogger.getInstance();
	}

}
