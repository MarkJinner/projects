package com.gmail.mailanalyzer.main.observerframe.message;

public class ExceptionConverter {
	private Throwable e;
	
	public ExceptionConverter(Throwable e) {
		this.e = e;
	}
	
	public ExceptionConverter() {
		
	}


	public Throwable getE() {
		return e;
	}



	public void setE(Throwable e) {
		this.e = e;
	}
	
	public String convertToString(Throwable e) {
		StringBuilder sb = new StringBuilder();
		sb.append(e+System.lineSeparator());
		appendStackTrace(e, sb);
		
		return sb.toString();
	}

	private void appendStackTrace(Throwable e, StringBuilder sb) {
		StackTraceElement[] tr = e.getStackTrace();
		for(int i = 0; i< tr.length;i++) {
			if(i<tr.length-1) {
				sb.append(tr[i]+System.lineSeparator());	
			}else {
				sb.append(tr[i]);
			}	
		}
	}
	
	
}
