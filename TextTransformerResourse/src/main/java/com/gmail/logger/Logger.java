package com.gmail.logger;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

public class Logger implements LogRequest {
	private File log = new File("/Users/olegivanov/Desktop/logs/log.txt");
	private DateProvider dateProvider = new DateProvider();
	private static int requestNumber = 0;
	private String loggingDate = dateProvider.provideData(new Date());

	public Logger() {

	}
	
	





	public File getLog() {
		return log;
	}

	public void setLog(File log) {
		this.log = log;
	}

	public DateProvider getDateProvider() {
		return dateProvider;
	}

	public void setDateProvider(DateProvider dateProvider) {
		this.dateProvider = dateProvider;
	}

	public String getLoggingDate() {
		return loggingDate;
	}

	public void setLoggingDate(String loggingDate) {
		this.loggingDate = loggingDate;
	}

	public static void main(String[] args) {
		Logger logger = new Logger();
		String test = "This is test";
		logger.log(test);

		System.out.println(logger.loadLoag());

	}

	public static int getRequestNumber() {
		return requestNumber;
	}

	public static void setRequestNumber(int requestNumber) {
		Logger.requestNumber = requestNumber;
	}

	@Override
	public void log(String query) {

		ByteBuffer bb = ByteBuffer.allocate(1024);
		Path path = Paths.get(log.getAbsolutePath());
		
		try (ByteChannel channel = Files.newByteChannel(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND,
				StandardOpenOption.WRITE)) {
			bb.put((System.lineSeparator() + dateProvider.provideData(new Date())+": " + query).getBytes());
			System.out.println(bb.capacity());
			bb.rewind();
			channel.write(bb);
			bb.clear();
			System.out.println("Query logged");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@Override
	public String loadLoag() {
		StringBuilder sb = new StringBuilder();
		Path path = Paths.get(log.getAbsolutePath());
		try (ByteChannel channel = Files.newByteChannel(path, StandardOpenOption.READ)) {
			ByteBuffer buffer = ByteBuffer.allocate(128);
			while (channel.read(buffer) != -1) {
				buffer.flip();
				while (buffer.hasRemaining()) {
					sb.append((char) buffer.get());
				}
				buffer.clear();

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sb.toString();
	}

}
