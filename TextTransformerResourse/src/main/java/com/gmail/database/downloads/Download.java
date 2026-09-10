package com.gmail.database.downloads;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Download implements Comparable<Download>{
	private String date;
	private String time;
	private String transformer;
	private ConvertedLink link;
	private String length;
	 
	public Download() {
		
	}

	public String getDate() {
		return date;
	}
	@XmlElement
	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}
	
	@XmlElement
	public void setTime(String time) {
		this.time = time;
	}

	public String getTransformer() {
		return transformer;
	}
	@XmlElement
	public void setTransformer(String transformer) {
		this.transformer = transformer;
	}

	public ConvertedLink getLink() {
		return link;
	}

	public void setLink(ConvertedLink link) {
		this.link = link;
	}
	
	

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	@Override
	public int compareTo(Download o) {
		if(this.getDate().compareTo(o.getDate())>0) {
			return 1;
		}else if(this.getDate().compareTo(o.getDate())<0) {
			return -1;
		}
		return 0;
	}


	
	
	
	
		
}
