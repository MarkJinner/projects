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

public class DisplayToJFrame extends JFrame {
	
	private static DisplayToJFrame instance;
	private JTextArea textArea = new JTextArea("Jframe displayer for TextTransformer resourse initiated at " +new Date()+ "\n");
	private JScrollPane pane = new JScrollPane(textArea);



	private DisplayToJFrame() {
		this.addWindowListener(new MyWindowAdapter());
		setupTextArea();
		setupCaret();
		setupFrame();
		setupPane();
		
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
		this.setDefaultCloseOperation(instance.ICONIFIED);
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

	public static DisplayToJFrame getToFrameDisplayer() {
		if (instance == null) {
			instance = new DisplayToJFrame();
		}
		return instance;
	}

	public void displayLog(String message) {
		if (getState() == NORMAL) {
			setVisible(true);
		}
		this.textArea.append(new Logger().getLoggingDate()+": "+ message + "\n");
		textArea.getCaret().setDot(textArea.getText().length());	
	}

	public static void main(String[] args) throws InterruptedException {
		DisplayToJFrame dtj = new DisplayToJFrame();
		Thread.sleep(1000);
		dtj.displayLog("first line request");
		Thread.sleep(1000);
		Thread.sleep(1000);
		dtj.displayLog("second line request");

		Thread.sleep(2000);
		dtj.displayLog("third line request");
		Thread.sleep(2000);
		dtj.displayLog("fourth line request");
		


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
			System.exit(0);
		}
	}
	
	


}