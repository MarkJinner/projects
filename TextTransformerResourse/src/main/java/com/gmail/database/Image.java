package com.gmail.database;

public enum Image {
	Profile("/Users/olegivanov/eclipse-workspace2026/TextTransformerResourse/src/main/webapp/images/free-icon-avatar-6386976.png"),
	Download("images/1904659-arrow-backup-down-download-save-storage-transfer_122509.png");
	
	String val;
	
	Image(String val){
			this.val = val;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
	
	
}
