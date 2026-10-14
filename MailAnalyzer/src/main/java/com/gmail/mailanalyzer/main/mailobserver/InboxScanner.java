package com.gmail.mailanalyzer.main.mailobserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.gmail.mailanalyzer.main.observerframe.ObserverFrame;
import com.gmail.mailanalyzer.main.observerframe.message.ExceptionConverter;

import jakarta.mail.BodyPart;
import jakarta.mail.Flags;
import jakarta.mail.Folder;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Part;
import jakarta.mail.Session;
import jakarta.mail.Store;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.search.FlagTerm;

public class InboxScanner {

	private Properties props = new Properties();
	private SetupProperties readerProperties;
	private SetupProperties senderProperties;
	private ResponseSender sender;
	private String emailContent = "";
	private EmailParser parser = new EmailParser();
	private ObserverFrame frame = ObserverFrame.getInstance();
	private int inboxLength = 0;
	private EmailConnectionChecker connectionChecker;
	private ExceptionConverter eConverter;

	public InboxScanner() throws FileNotFoundException, IOException {
//		props.load(InboxScanner.class.getClassLoader().getResourceAsStream("properties.properties"));
		String currentDir = System.getProperty("user.dir");
		File configFile = new File(currentDir+File.separator+"mail_properties.properties"); 
		props.load(new FileInputStream(configFile));
//		props.load(new FileInputStream(new File("")));
		if (props.stringPropertyNames().size()!=0) {
			frame.appendText("Properties loaded successfully");
		}else {
			frame.appendText("Properties load failed");
		}

		readerProperties = new ReaderProperties(props);
		senderProperties = new SenderProperties(props);
		sender = new ResponseSender(senderProperties);
		connectionChecker = new EmailConnectionChecker(readerProperties, frame);
		eConverter = new ExceptionConverter();
	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	private void startScanning() throws IOException {

		while (!Thread.currentThread().isInterrupted()) {
			try {
				
				Session emailSession = Session.getDefaultInstance(props);
				
				Store store = emailSession.getStore((String) props.get("protocol"));
				
				store.connect((String) props.get("imapHost"), (String) props.get("username"),
						(String) props.get("key"));
				Folder emailFolder = store.getFolder("INBOX");
				emailFolder.open(Folder.READ_WRITE);

				Flags unreadFlag = new Flags(Flags.Flag.SEEN);

				FlagTerm unreadTerm = new FlagTerm(unreadFlag, false);

				Message[] messages = emailFolder.search(unreadTerm);
				if (this.inboxLength != messages.length) {

					
					if (inboxLength == 0) {
						frame.appendText("Initial inbox scanning started");

					} else {
						frame.appendText("New letter received. Inbox scanning started");

					}
					InboxPartScanner.ScannerDisplay display = new InboxPartScanner().new ScannerDisplay();

					inboxLength = messages.length;

					if (messages.length > 100) {

						int messagesLength = messages.length;
						int parts = 4;
						int threadSize = messagesLength / parts;
						Thread[] partScanners = new Thread[4];
						for (int i = 0; i < parts; i++) {

							int start = i * threadSize;
							int finish = start + threadSize;
							if (i == parts - 1) {
								if (finish != messages.length - finish) {
									finish = finish + (messages.length - finish);
								}
							}

							partScanners[i] = new Thread(new InboxPartScanner(messages, start, finish));
							partScanners[i].start();

						}

						for (int i = 0; i < partScanners.length; i++) {
							partScanners[i].join();
						}

					} else {
						Thread thr = new Thread(new InboxScanner().new InboxPartScanner(messages, 0, messages.length));
						thr.start();

					}

					display.tr.interrupt();
					frame.appendText("Email scanning finished: " + messages.length + " unread letters in inbox");
				} else {
					frame.appendText("No new email in inbox");
				}
				messages = emailFolder.search(unreadTerm);
				emailFolder.close(false);
				store.close();

			} catch (Exception e) {
				System.err.println("Ошибка при подключении к почте: " + e.getMessage());
				frame.appendText(eConverter.convertToString(e));
				e.printStackTrace();
			}
			System.out.println("Inbox scanned successfully...");
			try {
				Thread.currentThread().sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				frame.appendText(eConverter.convertToString(e));
				e.printStackTrace();
			}
		}

	}

	public void scanInbox() throws InterruptedException, IOException {
		startScanning();

	}

	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		InboxScanner scanner = new InboxScanner();
		scanner.scanInbox();

	}

	private class InboxPartScanner implements Runnable {
		private Order order;
		private Email email = new Email();
		private int start;
		private int finish;
		private Message[] messages;

		{

		}

		public InboxPartScanner(Message[] messages, int start, int finish) {
			this.messages = messages;
			this.start = start;
			this.finish = finish;
		}

		public InboxPartScanner() {

		}

		private void scanInboxSection() throws MessagingException, IOException {
			for (int i = start; i < finish; i++) {
				Message message = messages[i];
				System.out.println(message.getFrom()[0]);
				if (message.getFrom()[0].toString().contains(props.getProperty("notificationEmail").toLowerCase())) {
					message.setFlag(Flags.Flag.SEEN, true);

					inboxLength = inboxLength - 1;
					Object content = message.getContent();
					if (content instanceof String) {
						System.out.println("Email content is instance of String");
					} else if (content instanceof MimeMultipart) {
						System.out.println("Email content is instance of MimeMultipart");
						MimeMultipart multipart = (MimeMultipart) content;
						for (int j = 0; j < multipart.getCount(); j++) {
							parseMessageContent(multipart.getBodyPart(j));
						}
					}

					order = parser.parseEmail(email);
					if (order.getAddress() != null) {
						frame.appendText("New email received " + order);
					} else {
						frame.appendText("New email received from " + message.getFrom()[0]);
					}

					sender.sendResponse(order);
					if (order.getAddress() != null) {
						frame.appendText("Response sent on " + order.getAddress());
					} else {
						frame.appendText("Response sent on " + message.getFrom()[0]);
					}

				}
			}

			Thread.currentThread().interrupt();
			System.out.println(Thread.currentThread().getName() + " is interrupting");

		}

		@Override
		public void run() {
			try {
				scanInboxSection();

			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		private String parseFrom(String addrLine) {
			return addrLine.substring(addrLine.indexOf("<") + 1, addrLine.indexOf(">"));
		}

		private void parseMessageContent(Part part) throws MessagingException, IOException {
			StringBuilder sb = new StringBuilder();
			if (part.isMimeType("text/plain")) {
				System.out.println("Text Content: ");
				emailContent = (String) part.getContent();
				email.setContent(emailContent);
			} else if (part.isMimeType("text/html")) {
				System.out.println("HTML Content: " + part.getContent());

			} else if (part.isMimeType("multipart/*")) {
				MimeMultipart mimeMultipart = (MimeMultipart) part.getContent();
				int count = mimeMultipart.getCount();

				for (int i = 0; i < count; i++) {
					BodyPart bodyPart = mimeMultipart.getBodyPart(i);

					if (Part.INLINE.equalsIgnoreCase(bodyPart.getDisposition()) || isInlineResource(bodyPart)) {
						String contentType = bodyPart.getContentType();
						String contentId = ((MimeBodyPart) bodyPart).getContentID();
					} else {
						parseMessageContent(bodyPart);
					}
				}
			}
		}

		private boolean isInlineResource(BodyPart bodyPart) throws MessagingException {
			if (bodyPart instanceof MimeBodyPart) {
				String contentId = ((MimeBodyPart) bodyPart).getContentID();
				return contentId != null && !contentId.trim().isEmpty();
			}
			return false;
		}

		private class ScannerDisplay implements Runnable {

			private Thread tr = new Thread(this);

			public ScannerDisplay() {
				tr.start();
			}

			private void startCheck() {
				while (!tr.isInterrupted()) {

					try {
						frame.appendText("Inbox scanning...");
						Thread.currentThread().sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						break;
					}
				}
			}

			@Override
			public void run() {
				startCheck();

			}

		}

	}

}
