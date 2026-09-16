package com.gmail.mailanalyzer.main.logger;

import java.io.IOException;

import com.gmail.mailanalyzer.main.observerframe.message.Level;
import com.gmail.mailanalyzer.main.observerframe.message.Message;

public interface Logger {

	public Level getLevel();
	
	public void log(Message message) throws IOException;

	public void next(Message message) throws IOException;
}
