package com.gmail.mailanalyzer.main.mailobserver;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Order {
	private String transactionId;
	private String address;
	private String quantity;
	private String date;
	private String city;
	

	public Order() {
		
	}

	public Order(String transactionId, String address, String quantity, String date, String city) {
		super();
		this.transactionId = transactionId;
		this.address = address;
		this.quantity = quantity;
		this.date = date;
		this.city = city;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public String getAddress() {
		return address;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getDate() {
		return date;
	}

	public String getCity() {
		return city;
	}
	@XmlElement
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@XmlElement
	public void setAddress(String address) {
		this.address = address;
	}

	@XmlElement
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@XmlElement
	public void setDate(String date) {
		this.date = date;
	}

	@XmlElement
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Order [transactionId=" + transactionId + ", address=" + address + ", quantity=" + quantity + ", date="
				+ date + ", city=" + city + "]";
	}
	
	
	
}
