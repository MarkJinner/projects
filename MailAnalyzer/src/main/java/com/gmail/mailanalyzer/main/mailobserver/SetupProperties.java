package com.gmail.mailanalyzer.main.mailobserver;

import java.io.File;
import java.util.Properties;

public abstract class SetupProperties {
	
	public Properties setupProperties(Properties props) {

		putProperties();	
		return props;
	}

	protected abstract void putProperties();

	
	public abstract Properties getProperties();


}
