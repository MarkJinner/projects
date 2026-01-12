package com.gmail.logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateProvider {
	private SimpleDateFormat sdf = new SimpleDateFormat("YYY-MMM-dd HH:mm:ss", Locale.US);
	
	public static void main(String [] args) {
		System.out.println(new DateProvider().provideData(new Date()));
	}
	
	public DateProvider() {
		
	}
	
	public String provideData(Date date) {
		return sdf.format(date);
	}
}
