package com.gmail.mailanalyzer.main.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.gmail.mailanalyzer.main.observerframe.message.Message;

public class LoggerTextSaver {
	
	private LoggerTools tools;
	private Properties loggerProps = new Properties();
	private String logMaxLinesKey;
	private String logNameFieldKey;
		
	public LoggerTextSaver(LoggerTools tools, Properties loggerProps, String logMaxLinesKey,String logNameFieldKey ) {
		this.loggerProps = loggerProps;
		this.tools = tools;
		this.logMaxLinesKey = logMaxLinesKey;
		this.logNameFieldKey = logNameFieldKey;
	}
	
	
	public void save(Message message) throws IOException {
		if (Loggers.getMainFolder().exists()) {
			int number = countFilesInFolder();
			if (number == 0) {
				setAndCreateNewLogFile(0);		
			} else if (number > 0) {
				tools.setLog(getLogFile((number - 1)));
				
				if (countLines(tools.getLog()) > Integer.parseInt((String) loggerProps.get(logMaxLinesKey))) {
					setAndCreateNewLogFile(number);	
				}
			}
			tools.getWriter().setFile(tools.getLog());
			tools.getWriter().write(tools.getConverter().toJson(message));
		}
	}
	
	
	private int countFilesInFolder() {
		return tools.getScanner().countFiles(tools.getFolder(), loggerProps.getProperty(logNameFieldKey));
	}

	private File getLogFile(int number) {
		return new File(tools.getFolder(), loggerProps.getProperty(logNameFieldKey) + number);
	}
	private void setAndCreateNewLogFile(int number) throws IOException {
		tools.setLog(getLogFile(number));
		tools.getLog().createNewFile();
	}

	private int countLines(File file) throws FileNotFoundException, IOException {
		return tools.getReader().read(file).size();
	}


	@Override
	public String toString() {
		return "LoggerMessageSaver [tools=" + tools + ", loggerProps=" + loggerProps + ", logMaxLinesKey="
				+ logMaxLinesKey + ", logNameFieldKey=" + logNameFieldKey + "]";
	}
	
	
}
