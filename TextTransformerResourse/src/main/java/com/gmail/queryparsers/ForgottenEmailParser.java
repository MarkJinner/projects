package com.gmail.queryparsers;

import java.util.Optional;

import com.gmail.exceptions.InappropriateEmailFormatException;

public class ForgottenEmailParser extends QueryParser{
	
	
	public static void main(String [] args) {
		String sample = "------WebKitFormBoundaryBAsAQWTm6Qv738Kg\n"
				+ "Content-Disposition: form-data; name=\"email\"\n"
				+ "\n"
				+ "user7@gmail.com\n"
				+ "------WebKitFormBoundaryBAsAQWTm6Qv738Kg--";
		ForgottenEmailParser parser = new ForgottenEmailParser();
		
		System.out.println(parser.getAddress(sample));
		
	}

	@Override
	public Parsed parseQuery(String query) {
		ParsedEmail pEmail = new ParsedEmail();
		this.getAddress(query).ifPresent((s)->{
			try {
				pEmail.setEmail(this.getAddress(query).get());
			} catch (InappropriateEmailFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		return pEmail;
	}
	
	private Optional<String> getAddress(String query) {
		return Optional.ofNullable(query.substring(query.indexOf("email")+7, query.lastIndexOf("------WebKitFormBoundary")).strip());
	}

}
