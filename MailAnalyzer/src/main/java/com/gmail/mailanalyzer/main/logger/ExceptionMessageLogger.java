package com.gmail.mailanalyzer.main.logger;

import java.io.File;
import java.io.IOException;

import com.gmail.mailanalyzer.main.jsonconverter.JsonConverter;
import com.gmail.mailanalyzer.main.observerframe.message.Level;
import com.gmail.mailanalyzer.main.observerframe.message.Message;

public class ExceptionMessageLogger implements Logger {
	private Logger next;
	private Level level = Level.Exception;
	private Writer writer;
	private File file = new File("exception_messages.txt");
	private JsonConverter<ExceptionMessageLogger> converter = new JsonConverter<>();
	private Loggers loggers = Loggers.getInstance();

	
	public ExceptionMessageLogger() throws IOException {
		if (!file.exists()) {
			file.createNewFile();
			System.out.println("Exception_messages file created");
		}
		writer = new Writer(file);
	}

	@Override
	public Level getLevel() {
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
			saveMessage(message);
		} else {
			next(message);
		}

	}

	private void saveMessage(Message message) throws IOException {
		writer.write(converter.toJson(message));
	}

}
