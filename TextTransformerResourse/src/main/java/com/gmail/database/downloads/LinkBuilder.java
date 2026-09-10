package com.gmail.database.downloads;

import java.io.IOException;

public interface  LinkBuilder {
	
	public String buildLink(String initLink,String buttonClass) throws IOException;
}
