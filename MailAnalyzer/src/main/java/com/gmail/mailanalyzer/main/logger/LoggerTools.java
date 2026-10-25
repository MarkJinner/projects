package com.gmail.mailanalyzer.main.logger;

import java.io.File;

import com.gmail.mailanalyzer.main.jsonconverter.JsonConverter;
import com.gmail.mailanalyzer.main.observerframe.message.Level;

public class LoggerTools {
	private Level level;
	private Writer writer;
	private Reader reader;
	private File folder;
	private File log;
	public File getLog() {
		return log;
	}

	public void setLog(File log) {
		this.log = log;
	}

	private JsonConverter<Logger> converter;
	private Loggers loggers;
	private FolderScanner scanner;
	private Logger next;

	public LoggerTools(Level level, File writerFile, String loggerFolderName) {
		this.level = level;
		this.writer = new Writer(writerFile);
		this.reader = new Reader();
		this.loggers = Loggers.getInstance();
		this.folder = new File(loggers.getMainFolder(), loggerFolderName);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		this.converter = new JsonConverter<>();
		scanner = new FolderScanner();
	}
	
	public LoggerTools() {
		
	}
	
	public LoggerTools(Level level, String loggerFolderName) {
		this.level = level;
		this.reader = new Reader();
		this.writer = new Writer();
		this.loggers = Loggers.getInstance();
		this.folder = new File(loggers.getMainFolder(), loggerFolderName);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		this.converter = new JsonConverter<>();
		scanner = new FolderScanner();
	}

	public Level getLevel() {
		return level;
	}

	public Writer getWriter() {
		return writer;
	}

	public Reader getReader() {
		return reader;
	}

	public File getFolder() {
		return folder;
	}

	public JsonConverter<Logger> getConverter() {
		return converter;
	}

	public Loggers getLoggers() {
		return loggers;
	}

	public FolderScanner getScanner() {
		return scanner;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public void setFolder(File folder) {
		this.folder = folder;
	}

	public void setConverter(JsonConverter<Logger> converter) {
		this.converter = converter;
	}

	public void setLoggers(Loggers loggers) {
		this.loggers = loggers;
	}

	public void setScanner(FolderScanner scanner) {
		this.scanner = scanner;
	}
	
	

	public Logger getNext() {
		return next;
	}

	public void setNext(Logger next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "LoggerTools [level=" + level + ", writer=" + writer + ", reader=" + reader + ", folder=" + folder
				+ ", converter=" + converter + ", loggers=" + loggers + ", scanner=" + scanner + "]";
	}
	
	
	
	

}
