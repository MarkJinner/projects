package com.gmail.queryparsers;

import com.gmail.database.Email;
import com.gmail.exceptions.InappropriateEmailFormatException;

public class ParsedEmail extends Parsed {
	private Email email;

	public ParsedEmail() {

	}

	public ParsedEmail(String address) {
		try {
			this.email = new Email(address);
		} catch (InappropriateEmailFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Email getEmail() {
		return email;
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public void setEmail(String address) throws InappropriateEmailFormatException {
		this.email = new Email(address);
	}

	@Override
	public String getText() {
		throw new UnsupportedOperationException("Operation not supported");
	}

	@Override
	public void setText(String text) {
		throw new UnsupportedOperationException("Operation not supported");

	}

}
