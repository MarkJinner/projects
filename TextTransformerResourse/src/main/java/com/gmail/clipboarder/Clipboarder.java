package com.gmail.clipboarder;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

public class Clipboarder {
	public Clipboarder() {
		
	}
	public static void main(String[] args) {
		Clipboarder clipper = new Clipboarder();
		clipper.copyToClipboard("test");

	}
	
	public void copyToClipboard(String line) {
		Clipboard cp = Toolkit.getDefaultToolkit().getSystemClipboard();
		Transferable trf =new StringSelection(line); 
		cp.setContents(trf, null);
	}

}
