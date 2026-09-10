package com.gmail.database.downloads;

public interface DParser {
	
	public default Download getDownload(String link) {//template method pattern applied
		Download download = new Download();
		
		download.setDate(this.getDate(link));
		download.setTime(this.getTime(link));
		download.setTransformer(this.getTransformer(link));
		download.setLink(this.getConvertedLink(link));
		download.setLength(this.getFileSize(link));
		
		return download;
	}
	
	public String getDate(String link);
	
	public String getTime(String link);
	
	public String getTransformer(String link);
	
	public String getFileSize(String link);
	
	public ConvertedLink getConvertedLink(String link);
	
	
}
