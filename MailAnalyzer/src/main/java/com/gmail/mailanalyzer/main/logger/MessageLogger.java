package com.gmail.mailanalyzer.main.logger;

import java.io.File;
import java.io.IOException;

import com.gmail.mailanalyzer.main.jsonconverter.JsonConverter;
import com.gmail.mailanalyzer.main.observerframe.message.Message;

public class MessageLogger implements Logger {
	private Logger next;
	private int level = 1;
	private Writer writer;
	private File file = new File("log_messages.txt");
	private JsonConverter<MessageLogger> converter = new JsonConverter<>();
	private Loggers loggers;

	public MessageLogger() throws IOException {
		if (!file.exists()) {
			file.createNewFile();
		}
		loggers = Loggers.getInstance();
		writer = new Writer(file);
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public void next(Message message) throws IOException {
		loggers.getLoggers().stream().forEach(s -> {
			if (s.getLevel() != this.getLevel()) {
				
				next = s;
				if (next.getLevel() == message.getLevel()) {
					try {
						next.log(message);
						System.out.println("Message passed to another logger and logged");

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

		if (message.getLevel() == this.level) {
			logMessage(message);
			System.out.println("Message logged in local logger");
		} else {
			next(message);
		}

	}

	private void logMessage(Message message) throws IOException {
		String line = converter.toJson(message);
		writer.write(line);
	}

}
