package com.gmail.queryparsers;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Optional;

public class FormdataParser extends QueryParser{
	private String siteAddr = "http://localhost:8080/";
	
	public FormdataParser() {
		
	}
	
	public static void main(String [] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, InvocationTargetException {
		String sample = 
				"------WebKitFormBoundary2RGwbWBX84aF9tjM\n"
				+ "Content-Disposition: form-data; name=\"originalText\"\n"
				+ "\n"
				+ "ewew\n"
				+ "------WebKitFormBoundary2RGwbWBX84aF9tjM\n"
				+ "Content-Disposition: form-data; name=\"transformer\"\n"
				+ "\n"
				+ "http://localhost:8080/toUpperCase\n"
				+ "------WebKitFormBoundary2RGwbWBX84aF9tjM--";
		FormdataParser parser = new FormdataParser();
		System.out.println(parser.parseQuery(sample));
		
	}
		
	@Override
	public ParsedFormData parseQuery(String query) {

		ParsedFormData parsed = new ParsedFormData();
		substringTransformer(query).ifPresent((s)->{
			substringTransformerName(s).ifPresent(x -> parsed.setTransformer(x));
		});
		substringText(query).ifPresent(x->parsed.setText(x));
		return parsed;
	}
	
	private Optional<String>substringTransformer(String query) {
		if(query.split(System.lineSeparator()).length>1) {
			
			return Arrays.stream(query.split(System.lineSeparator())).filter(s->s.startsWith(siteAddr)).findAny();	
		}
		
		return Optional.empty();
		
	}
	
	private Optional<String> substringTransformerName(String trns) {
			if(trns.contains("/")) {
				
				
				return Optional.ofNullable(trns.substring(trns.lastIndexOf("/")+1, trns.length()));	
			}			
//		}
		return Optional.empty();
	}
	
	private Optional<String> substringText(String query){
		StringBuilder sb = new StringBuilder();
		String [] lines = query.split(System.lineSeparator());
		int start = 0;
		int finish = 0; 
		for(int i = 1; i< lines.length;i++) {
			
			if(lines[i].startsWith("Content-Disposition:")) {
				start = i;
			}else if(lines[i].startsWith("------WebKitFormBoundary")) {
				finish = i;
				break;
			}
			
		}
		for(int i = start+1; i< finish;i++) {
			sb.append(lines[i]);
			sb.append(System.lineSeparator());
		}
		
		return Optional.ofNullable(sb.toString().trim());
	}
	
	

}
