package com.gmail.mailanalyzer.main.mailobserver;

import java.io.File;
import java.util.Properties;

public abstract class SetupProperties {
	
	public Properties setupProperties(Properties props) {
//		setProperties();
		putProperties();	
		return props;
	}

	protected abstract void putProperties();

//	protected abstract void setProperties();
	
	public abstract Properties getProperties();


}
