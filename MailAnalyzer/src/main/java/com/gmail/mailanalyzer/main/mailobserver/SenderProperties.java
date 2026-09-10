package com.gmail.mailanalyzer.main.mailobserver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class SenderProperties extends SetupProperties {
	private Properties props;
	

	public SenderProperties(Properties props) {
		this.props = props;
		this.setupProperties(props);
	}
	
	

	public Properties getProps() {
		return props;
	}



	public void setProps(Properties props) {
		this.props = props;

	}



	@Override
	protected void putProperties() {	
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.host", props.get("smtpHost"));
		props.put("mail.smtp.port", props.get("portOut"));
	}

//
//	@Override
//	protected void setProperties() {
//		props.setProperty("smtpHost", (String) props.get("smtpHost"));
//		props.setProperty("portOut", (String) props.get("portOut"));
//		props.setProperty("username", (String) props.get("username"));
//		props.setProperty("key", (String) props.get("key"));
//
//	}



	@Override
	public Properties getProperties() {
		// TODO Auto-generated method stub
		return props;
	}

	
	
	
	



}
