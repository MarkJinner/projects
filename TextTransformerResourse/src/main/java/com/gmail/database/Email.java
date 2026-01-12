package com.gmail.database;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.gmail.exceptions.InappropriateEmailFormatException;

public class Email implements Serializable{
	@Override
	public int hashCode() {
		return Objects.hash(address);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		return Objects.equals(address, other.address);
	}

	private List<String> domains = new ArrayList<>(List.of("com", "yahoo", "net", "ukr"));
	private String address;

	public Email(String address) throws InappropriateEmailFormatException {
		this.setAddress(address);
	}

	public Email() {

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) throws InappropriateEmailFormatException {
		if (address.contains("@")) {
			if (ifDomainIsProper(address)) {
				this.address = address;
				return;
			}
		}
		throw new InappropriateEmailFormatException("Wrong format of email");
	}

	private boolean ifDomainIsProper(String address) {
		if (checkEndings(address)) {
			return true;
		}
		return false;
	}

	private boolean checkEndings(String address) {
		if (this.domains.contains(this.getDomain(address))) {
			return true;
		}
		return false;
	}

	private String getDomain(String address) {
		String res = address.substring(address.lastIndexOf(".") + 1, address.length());
		return res;
	}

	@Override
	public String toString() {
		return this.address;
	}

	public static void main(String[] args) throws InappropriateEmailFormatException {
		Email mail = new Email();
//		mail.email = "olegYU";
		mail.setAddress("uouoi@.com");
		
		Email mail2 = new Email("uouoi@.com");
		if(mail.equals(mail2)) {
			System.out.println("!");
		}
//		System.out.println(mail);

	}

}
