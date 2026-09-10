package com.gmail.mailanalyzer.main.mailobserver;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReaderProperties extends SetupProperties {
	private Properties props;
	

	public ReaderProperties(Properties props) {
		this.props = props;
		this.setupProperties(props);
	}

	@Override
	protected void putProperties() {	
		props.put("mail.store.protocol", props.get("protocol"));
		props.put("mail.imap.host", props.get("imapHost"));
		props.put("mail.imap.port", props.get("portIn"));
		props.put("mail.imap.ssl.enable", "true");
	}


//	@Override
//	protected void setProperties() {
//		props.setProperty("imapHost", (String) props.get("imapHost"));
//		props.setProperty("portIn", (String) props.get("portIn"));
//		props.setProperty("username", (String) props.get("username"));
//		props.setProperty("key", (String) props.get("key"));
//		props.setProperty("protocol", (String) props.get("protocol"));	
//	}

	@Override
	public Properties getProperties() {
		// TODO Auto-generated method stub
		return props;
	}
	
	



}
