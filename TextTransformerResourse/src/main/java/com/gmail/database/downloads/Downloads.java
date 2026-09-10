package com.gmail.database.downloads;

import java.util.ArrayList;
import java.util.List;

import com.gmail.database.User;

import jakarta.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Downloads {
	private List<Download> downloads = new ArrayList<>();
	
	public Downloads() {
		
	}

	public List<Download> getDownloads() {
		return downloads;
	}
 
	public void setDownloads(List<Download> downloads) {
		this.downloads = downloads;
	}
	
	

	
}
