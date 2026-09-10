package com.gmail.emailsender;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HostProperties {
	private String auth;
	private String authFlag;
	private String ssl;
	private String sslFlag;
	private String host;
	private String port;
	private String portValue;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public HostProperties() {

	}
	
	static {
		System.out.println("Host properties initatied");
		
	}

	public String getAuthFlag() {
		return authFlag;
	}

	@XmlElement
	public void setAuthFlag(String authFlag) {
		this.authFlag = authFlag;
	}

	public String getSslFlag() {
		return sslFlag;
	}

	@XmlElement
	public void setSslFlag(String sslFlag) {
		this.sslFlag = sslFlag;
	}

	public String getPortValue() {
		return portValue;
	}

	@XmlElement
	public void setPortValue(String portValue) {
		this.portValue = portValue;
	}

	public String getAuth() {
		return auth;
	}

	@XmlElement
	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getSsl() {
		return ssl;
	}

	@XmlElement
	public void setSsl(String ssl) {
		this.ssl = ssl;
	}

	public String getHost() {
		return host;
	}

	@XmlElement
	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	@XmlElement
	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "HostProperties [auth=" + auth + ", authFlag=" + authFlag + ", ssl=" + ssl + ", sslFlag=" + sslFlag
				+ ", host=" + host + ", port=" + port + ", portValue=" + portValue + "]";
	}
	
	

}
