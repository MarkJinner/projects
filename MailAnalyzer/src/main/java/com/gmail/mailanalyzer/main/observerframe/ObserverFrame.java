package com.gmail.mailanalyzer.main.observerframe;

import java.awt.Color;
import java.awt.Image;
import java.awt.Taskbar;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.Caret;

import com.gmail.mailanalyzer.main.Test;
import com.gmail.mailanalyzer.main.logger.Logger;
import com.gmail.mailanalyzer.main.logger.MessageLogger;
import com.gmail.mailanalyzer.main.observerframe.message.FrameMessageFormatter;
import com.gmail.mailanalyzer.main.observerframe.message.Level;
import com.gmail.mailanalyzer.main.observerframe.message.Message;

public class ObserverFrame {
	private static ObserverFrame instance = null;
	private FrameInitializer frameInit = new FrameInitializer();
	private Logger logger = new MessageLogger();
	private Message message = null;
	private Level messageLevel = null;
	private Map<String, Level> levels = new TreeMap<>(
			Map.of("connection", Level.Connection, "exception", Level.Exception));

	private ObserverFrame() throws IOException {
		initializeFrame();
	}

	public Logger getLogger() {
		return logger;
	}

	public Map<String, Level> getLevels() {
		return levels;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void setLevels(Map<String, Level> levels) {
		this.levels = levels;
	}

	public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
		ObserverFrame frame = ObserverFrame.getInstance();
		frame.testAppendTextOne();
		frame.testAppendTextTwo();
		frame.testAppendTextThree();
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
		frameInit.getFrame();
	}

	public void appendText(String str) throws IOException {
		System.out.println(str);
		setupMessageLevel(str);
		formatMessage(str, messageLevel);
		logger.log(message);
		appendMessage(message);
	}
	
	private void setupMessageLevel(String str) {
		messageLevel = levels.entrySet().stream().filter(s -> str.toLowerCase().contains(s.getKey()))
				.map(a -> a.getValue()).findFirst().orElse(Level.Request);

//			System.out.println(messageLevel);
	}

	private void formatMessage(String str, Level messageLevel) {
		this.message = new FrameMessageFormatter().format(str, messageLevel);
	}



	private void appendMessage(Message message) {
		frameInit.jtextArea.append(message + "" + System.lineSeparator());
		frameInit.jtextArea.getCaret().setDot(frameInit.jtextArea.getText().length());// this allows to drop down to the
																						// bottom of the list
	}

	private class FrameAdapter extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(0);
		}

	}

	private class FrameInitializer {
		private JFrame jframe = new JFrame();
		private JTextArea jtextArea = new JTextArea();
		private JScrollPane pane = new JScrollPane(jtextArea);

		public JFrame getFrame() throws IOException {
			setupFrame();
			setupTextArea();
			setupPane();
			setupCaret();
			appendInitiationLine();
			setupIconImage();

			return jframe;
		}

		private void setupFrame() {

			jframe.setVisible(true);
			jframe.setSize(700, 300);
			jframe.setAlwaysOnTop(true);
			jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			jframe.addWindowListener(new FrameAdapter());
			jframe.add(pane);

		}

		private void setupIconImage() {
			URL iconURL = ObserverFrame.class.getResource("/images/inbox.png");
			Image icon = Toolkit.getDefaultToolkit().getImage(iconURL);
			if (Taskbar.isTaskbarSupported()) {
				Taskbar taskbar = Taskbar.getTaskbar();
				taskbar.setIconImage(icon);// works for Apple
			}
			jframe.setIconImage(icon);// doesn't work
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

		private void setupPane() {
			this.pane.setAutoscrolls(true);
			this.pane.createVerticalScrollBar();
		}

		private void appendInitiationLine() throws IOException {
			appendText("Frame initialized");
		}
	}

	@Test
	private void testAppendTextOne() throws IOException {
		String str = "Email connection";
		this.appendText(str);
		if (this.messageLevel == Level.Connection) {
			System.out.println("Connection level successfully tested");
		} else {
			System.out.println("Connection level failed");
		}
	}

	@Test
	private void testAppendTextTwo() throws IOException {
		String str = "Email exception";
		this.appendText(str);
		if (this.messageLevel == Level.Exception) {
			System.out.println("Exception level successfully tested");
		} else {
			System.out.println("Exception level failed");
		}

	}

	@Test
	private void testAppendTextThree() throws IOException {
		String str = "Email test";
		this.appendText(str);
		if (this.messageLevel == Level.Request) {
			System.out.println("Request level successfully tested");
		} else {
			System.out.println("Request level failed");
		}
	}

}
