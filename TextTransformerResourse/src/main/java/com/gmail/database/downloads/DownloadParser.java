package com.gmail.database.downloads;

import java.io.File;
import java.io.IOException;

public class DownloadParser implements DParser{
	private LinkConverter converter = new LinkConverter();

	public static void main(String [] args) {
		String initLink = "/Users/olegivanov/eclipse-workspace2026/TextTransformerResourse/src/main/webapp/user/users/user21/2026-Jul-05 00-24 toUpperCase";
		System.out.println(initLink);
		DownloadParser parser = new DownloadParser();
		System.out.println(parser.getDate(initLink));
		System.out.println(parser.getTime(initLink));
		System.out.println(parser.getTransformer(initLink));
		System.out.println(parser.getFileSize(initLink));
		System.out.println(parser.getConvertedLink(initLink).getLink());
		
		
		
		
	}
	@Override
	public String getDate(String link) {
//		System.out.println("DATE  "+link);
		return link.substring(link.lastIndexOf("/")+1, link.indexOf(" "));
	}

	@Override
	public String getTime(String link) {
		
		return link.substring(link.indexOf(this.getDate(link))+this.getDate(link).length(),link.lastIndexOf(" ")).trim();
	}

	@Override
	public String getTransformer(String link) {
		return link.substring(link.indexOf(" ",link.lastIndexOf(" "))).trim();
	}

	@Override
	public String getFileSize(String link) {
		File file = new File(link);
		return file.length()+"b";
	}

	@Override
	public ConvertedLink getConvertedLink(String link) {
		ConvertedLink converted = new ConvertedLink();
		try {
			converted = converter.getCompletedLink(link, "styled-button");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return converted;
	}

}
