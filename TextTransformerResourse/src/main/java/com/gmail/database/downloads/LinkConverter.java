package com.gmail.database.downloads;

import java.io.File;
import java.io.IOException;

import com.gmail.database.FileAddresses;
import com.gmail.database.Image;

public class LinkConverter {
	private String completedLink = "";
	private DownloadLinkBuilder linkBuilder;
	
	public LinkConverter() {
		linkBuilder = new DownloadLinkBuilder();
	}
	
	public static void main(String [] args) throws IOException {
		String initLink = "/Users/olegivanov/eclipse-workspace2026/TextTransformerResourse/src/main/webapp/user/users/user21/2026-May-30 22-12 toUpperCase";
		String finalExample = "<a class=\"styled-button\" href=\"http://localhost:8080/user/users/user21/2026-May-30 22-12 toUpperCase\" download><img src=\"/images/1904659-arrow-backup-down-download-save-storage-transfer_122509.png\"style=\"max-width: 21.5px; height: auto;\"></a>";
		
		LinkConverter conv = new LinkConverter();
		
		ConvertedLink link = conv.getCompletedLink(initLink, "styled-button");
		System.out.println("example: "+finalExample);
		System.out.println("res:     "+link.getLink()); 
		System.out.println(link.getLink().equals(finalExample));

	}
	

	
	
	public ConvertedLink getCompletedLink(String initLink,String buttonClass) throws IOException {
		ConvertedLink link = new ConvertedLink(linkBuilder.buildLink(initLink, buttonClass));
		return link;		
	}
	

	
	
	
}
