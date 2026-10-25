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
	private final String logNameField = "exceptionLogName";
	private final String folderNameField = "exceptionFolderName";
	private final String propertiesName = "loggers.properties";
	private final String logMaxLines = "exceptionLogMaxLines";
	private LoggerTools tools;
	private Properties loggerProps = new Properties();

	

	

	public static ExceptionLogger getInstance() throws IOException {
		if(instance==null) {
			instance = new ExceptionLogger();
		}
		return instance;
	}
	
	private ExceptionLogger() throws IOException {
		File configFile = new File(System.getProperty("user.dir") + File.separator + propertiesName);
		loggerProps.load(new FileInputStream(configFile));
		tools = new LoggerTools(Level.Exception, loggerProps.getProperty(folderNameField));
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
	
	public static void main(String [] args) throws IOException {
		ExceptionLogger logger = ExceptionLogger.getInstance(); 
		System.out.println(ExceptionLogger.instance.tools);
	}

}
