package com.gmail.database.downloads;

import java.io.IOException;

import com.gmail.database.FileAddresses;
import com.gmail.database.Image;

public class DownloadLinkBuilder implements LinkBuilder{
	
	public DownloadLinkBuilder() {
		
	}
	
	
	
	public String buildLink(String initLink,String buttonClass) throws IOException {
		String openingTag = "<a class=\"";
		String startEl = buttonClass;
		String refTag = "\" href=";
		String siteAddr = "\""+FileAddresses.getSite_address();
		String usersFolder = initLink.substring(initLink.indexOf("webapp")+7, initLink.length());
		String buttonTag = "\" download>";
		
		String imageOpeningTag = "<img src=\"/";
		String image = Image.Download.getVal();
		
		String style = "\"style=\"max-width: 21.5px; height: auto;\">";	
		String closingTag = "</a>";		
		return openingTag+startEl+refTag+siteAddr+usersFolder+buttonTag+imageOpeningTag+image+style+closingTag;
	}
	
	



}
