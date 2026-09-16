package com.gmail.mailanalyzer.main.observerframe.message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.gmail.mailanalyzer.main.exceptions.NoMessageTypeException;

public class FrameMessageFormatter {
	private SimpleDateFormat formatter;
	private Map<Integer, Message> messagesTypes = new HashMap<>(Map.of(1, new LogMessage(),2, new ConnectionCheckMessage(),3,new ExceptionMessage()));
	
	public FrameMessageFormatter() {
		this.setupFormatter();

	}
	
	public Message format(String str, Level level){
		Message temp = null;
		if(messagesTypes.containsKey(level.getLevel())) {
			temp = messagesTypes.get(level.getLevel());
		}
		
		if(temp!=null) {
			temp.setDate(this.formatter.format(new Date()));
			temp.setText(str);
			temp.setLevel(level);
		}
		return temp;
	}
	

	private void setupFormatter() {
		if(formatter==null) {
			formatter = new SimpleDateFormat("dd MMM hh:mm:ss ", Locale.US);
		}
		
	}
	
	public static void main(String [] args) throws NoMessageTypeException {
		FrameMessageFormatter message = new  FrameMessageFormatter();
		System.out.println(message.format("Hello!",Level.Request));
	}

	public SimpleDateFormat getFormatter() {
		return formatter;
	}


	public void setFormatter(SimpleDateFormat formatter) {
		this.formatter = formatter;
	}



	
}
