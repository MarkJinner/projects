package com.gmail.database.downloads;

public class ConvertedLink {
	private String link;
	
	public ConvertedLink(String link) {
		this.link = link;
	}
	
	public ConvertedLink() {
		
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "ConvertedLink [link=" + link + "]";
	}
	
	
	
	
}
