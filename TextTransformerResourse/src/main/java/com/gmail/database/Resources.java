package com.gmail.database;

public enum Resources {
	RESOURSES_FOLDER("/Users/olegivanov/eclipse-workspace2026/TextTransformerResourse/src/main/webapp/resource"),
	
	USERS_FOLDER_ADDRESS(
			"/Users/olegivanov/eclipse-workspace2026/TextTransformerResourse/src/main/webapp/resource/usersFolder.txt"),
	
	USERS_DATABASE("/Users/olegivanov/eclipse-workspace2026/TextTransformerResourse/src/main/webapp/resource/usersDatabase.txt"),
	
	LOG_ADDRESS("/Users/olegivanov/eclipse-workspace2026/TextTransformerResourse/src/main/webapp/resource/logAddress.txt"),
	
	EMAIL_SENDER_ADDRESS("/Users/olegivanov/git/projects/TextTransformerResourse/src/main/webapp/resource/EmailSenderPropertiesAddress.txt"),
	
	HOST_PROPERTIES_ADDRESS("/Users/olegivanov/git/projects/TextTransformerResourse/src/main/webapp/resource/HostPropertiesAddress.txt"),
	SITE_ADDRESS("/Users/olegivanov/eclipse-workspace2026/TextTransformerResourse/src/main/webapp/resource/siteAddress.txt");

	String val;

	Resources(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return this.val;
	}
}
