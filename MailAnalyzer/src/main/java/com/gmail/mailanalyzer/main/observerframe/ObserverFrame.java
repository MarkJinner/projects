package com.gmail.mailanalyzer.main.observerframe;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.Caret;

import com.gmail.mailanalyzer.main.exceptions.NoMessageTypeException;
import com.gmail.mailanalyzer.main.logger.Logger;
import com.gmail.mailanalyzer.main.logger.MessageLogger;
import com.gmail.mailanalyzer.main.observerframe.message.FrameMessageFormatter;
import com.gmail.mailanalyzer.main.observerframe.message.Level;
import com.gmail.mailanalyzer.main.observerframe.message.Message;


public class ObserverFrame {
	private static ObserverFrame instance = null;
	private JFrame jframe = new JFrame();
	private JTextArea jtextArea = new JTextArea();
	private JScrollPane pane = new JScrollPane(jtextArea);
	private String initMessage = "Frame initialized";
	private FrameMessageFormatter formatter  = new FrameMessageFormatter();
	private Logger  logger = new MessageLogger();
	private InternalConnectionChecker connectionChecker;

	
	private ObserverFrame() throws IOException {
		initializeFrame();
		
//		
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		ObserverFrame frame = ObserverFrame.getInstance();
		frame.appendText("Test 1");
		Thread.currentThread().sleep(2000);
		frame.appendText("Test 2");
		Thread.currentThread().sleep(2000);
		frame.appendText("Test 3");
		Thread.currentThread().sleep(2000);
		frame.appendText("Test 4");
		Thread.currentThread().sleep(2000);
		frame.appendText("Exception:!!!!!!!");
		
	}
	private void appendFirstLine() throws IOException {
		this.appendText(this.initMessage);
	}

	public static ObserverFrame getInstance() {
		if (instance == null) {
			try {
				instance = new ObserverFrame();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return instance;
	}

	private void initializeFrame() throws IOException {
		setupFrame();
		setupTextArea();
		setupPane();
		setupCaret();
		appendFirstLine();
		connectionChecker  =  new InternalConnectionChecker();
	

	}

	private void setupFrame() {
		jframe.setVisible(true);
		jframe.setSize(600, 300);	
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.addWindowListener(new FrameAdapter());
		
		jframe.add(pane);
	}

	private void setupTextArea() {
		jtextArea.setBackground(Color.black);
		jtextArea.setForeground(Color.white);
		jtextArea.setLineWrap(true);
		jtextArea.setWrapStyleWord(true);
	}
	
	private void setupCaret() {
		Caret caret = jtextArea.getCaret();	
		jtextArea.setCaretColor(Color.white);
		caret.setBlinkRate(200);
		
	}
	
	public void appendText(String str) throws IOException {
		Level temp = null;
		if(str.contains("Connection checked")) {
			temp = Level.Connection;
		}else if(str.toLowerCase().contains("exception")) {
			temp = Level.Exception;
		}else {
			temp = Level.Request;
		}
		
		Message message = null;
			message = formatter.format(str,  temp);

		jtextArea.append(message+""+System.lineSeparator());
		logger.log(message);
		jtextArea.getCaret().setDot(jtextArea.getText().length());//this allows to drop down to the bottom of the list	
	}

	private void setupPane() {
		this.pane.setAutoscrolls(true);
		this.pane.createVerticalScrollBar();
	}
	

	private class FrameAdapter extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
			connectionChecker.thread.interrupt();
		}

	}
	
	
	private class InternalConnectionChecker implements Runnable {
		private Thread thread;


		public InternalConnectionChecker() {

			thread = new Thread(this);
			thread.start();
		}

		private void start() throws IOException {
			while (!thread.isInterrupted()) {
				try {
					instance.appendText("Connection checked...");
					thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
				}
				
			}
			System.out.println("Notification thread interrupted");	
		}

		@Override
		public void run() {
			try {
				start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		


	}

}
