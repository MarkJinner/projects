package com.gmail.logframe;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.Caret;

import com.gmail.logger.Logger;

import logdisplayerprovider.LogDisplayProvider;

public class DisplayToJFrame extends JFrame {
	
	private static DisplayToJFrame instance = null;
	private JTextArea textArea = new JTextArea("Jframe displayer for TextTransformer resourse initiated at " +new Date()+ "\n");
	private JScrollPane pane = new JScrollPane(textArea);
	private boolean disposeFlag = true;



	private DisplayToJFrame() {
		this.addWindowListener(new MyWindowAdapter());
		setupTextArea();
		setupCaret();
		setupFrame();
		setupPane();
		
		
	}
	
	
	
	
	




	public boolean isDisposeFlag() {
		return disposeFlag;
	}









	public void setDisposeFlag(boolean disposeFlag) {
		this.disposeFlag = disposeFlag;
	}









	public static void setInstance(DisplayToJFrame instance) {
		DisplayToJFrame.instance = instance;
	}









	private void setupTextArea() {
		
		textArea.setBackground(Color.black);
		textArea.setForeground(Color.white);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
	}
	
	private void setupFrame() {
		this.add(pane);
		this.setAlwaysOnTop(true);
//		this.setDefaultCloseOperation(instance.ICONIFIED);
		this.setDefaultCloseOperation(instance.DISPOSE_ON_CLOSE);
		this.setTitle("TextTransformer log");
		this.setSize(600, 300);
		this.setVisible(true);
	} 
	private void setupPane() {
		this.pane.setAutoscrolls(true);
		this.pane.createVerticalScrollBar();
		
		
	}
	private void setupCaret() {
		Caret caret = textArea.getCaret();	
		textArea.setCaretColor(Color.white);
		caret.setBlinkRate(200);
		
	}

	public static DisplayToJFrame getInstance() {
		if (instance == null) {
			instance = new DisplayToJFrame();
			   
			
		}
		return instance;
	}
	
//	private void display() {
//		while(!Thread.currentThread().isInterrupted()) {
//			
//		}
//	}

	public void displayLog(String message) {
		if (getState() == NORMAL) {
			setVisible(true);
		}
		this.textArea.append(new Logger().getLoggingDate()+": "+ message + "\n");
		textArea.getCaret().setDot(textArea.getText().length());	
	}

	public static void main(String[] args) throws InterruptedException {
		DisplayToJFrame dtj = DisplayToJFrame.getInstance();
		Thread.sleep(1000);
		dtj.displayLog("first line request");
		Thread.sleep(1000);
		Thread.sleep(1000);
		dtj.displayLog("second line request");

		Thread.sleep(2000);
		dtj.displayLog("third line request");
		Thread.sleep(2000);
		dtj.displayLog("fourth line request");
//		dtj.dispose();
		
		
		


	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(JTextArea textArea) {
		this.textArea = textArea;
	}
	
	private class MyWindowAdapter extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent we) {
			disposeFlag=false;
			System.exit(0);
		}
	}
	
	


}