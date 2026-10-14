package com.gmail.mailanalyzer.main.mailobserver;

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


	@Override
	public Properties getProperties() {
		// TODO Auto-generated method stub
		return props;
	}
	
	



}
