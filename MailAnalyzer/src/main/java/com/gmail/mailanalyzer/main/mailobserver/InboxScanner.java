package com.gmail.mailanalyzer.main.mailobserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.gmail.mailanalyzer.main.observerframe.ObserverFrame;

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
	private File properties = new File("properties.properties");
	private SetupProperties readerProperties;
	private SetupProperties senderProperties;
	private ResponseSender sender;
	private String emailContent = "";
	private EmailParser parser = new EmailParser();
	private ObserverFrame frame = ObserverFrame.getInstance();
	private int inboxLength = 0;
	

	public InboxScanner() throws FileNotFoundException, IOException {
		props.load(new FileInputStream(properties));
		readerProperties = new ReaderProperties(props);
		senderProperties = new SenderProperties(props);
		sender = new ResponseSender(senderProperties);

	}

	public Properties getProps() {
		return props;
	}

	public void setProps(Properties props) {
		this.props = props;
	}

	private void startScanning() throws InterruptedException {
		System.out.println(props);

		while (!Thread.currentThread().isInterrupted()) {
			try {
				// Создание сессии и подключение к серверу
				Session emailSession = Session.getDefaultInstance(props);
				Store store = emailSession.getStore((String) props.get("protocol"));
				store.connect((String) props.get("imapHost"), (String) props.get("username"),
						(String) props.get("key"));

				// Открытие папки "Входящие" (INBOX)
				Folder emailFolder = store.getFolder("INBOX");
				emailFolder.open(Folder.READ_WRITE);

				Flags unreadFlag = new Flags(Flags.Flag.SEEN);

				FlagTerm unreadTerm = new FlagTerm(unreadFlag, false);

				Message[] messages = emailFolder.search(unreadTerm);
				System.out.println("inboxLength= "+inboxLength+" "+"Messages length = " + messages.length);
				if (this.inboxLength != messages.length) {
					inboxLength = messages.length;
					System.out.println("inboxLength= "+inboxLength);
					frame.appendText("Email scanning started");
					System.out.println("Not equal: new letter received");
					System.out.println();
					if (messages.length > 100) {
						
						int messagesLength = messages.length;
						int parts = 4;
						int threadSize = messagesLength / parts;
						Thread[] partScanners = new Thread[4];
						long startTime = System.currentTimeMillis();
						System.out.println("inboxLenght before launching thread  "+this.inboxLength);
						for (int i = 0; i < parts; i++) {
							
							int start = i * threadSize;
							int finish = start + threadSize;
							if (i == parts - 1) {
								if (finish != messages.length - finish) {
									finish = finish + (messages.length - finish);
								}
							}
							
							partScanners[i] = new Thread(
									new InboxPartScanner(messages, start, finish));
							partScanners[i].start();
						}

						for (int i = 0; i < partScanners.length; i++) {
							partScanners[i].join();
						}

						long finishTime = System.currentTimeMillis();
						System.out.println("Check finished in " + (finishTime - startTime) + " ms");
					} else {
						Thread thr = new Thread(new InboxScanner().new InboxPartScanner(messages, 0, messages.length));
						thr.start();

					}
					frame.appendText("Email scanning finished");
				} else {
					System.out.println("Equal");
				}
				messages = emailFolder.search(unreadTerm);
				System.out.println("Всего писем в ящике: " + messages.length);
				emailFolder.close(false);
				store.close();

			} catch (Exception e) {
				System.err.println("Ошибка при подключении к почте: " + e.getMessage());
				e.printStackTrace();
			}
			System.out.println("Inbox scanned successfully...");
			Thread.currentThread().sleep(10000);
		}

	}

	public void scanInbox() throws InterruptedException {
		startScanning();
//		sender.sendResponse(order);

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
			System.out.println("inboxLength = "+inboxLength);
		}

		public InboxPartScanner(Message[] messages, int start, int finish) {
			this.messages = messages;
			this.start = start;
			this.finish = finish;
		}

		private void scanInboxSection() throws MessagingException, IOException {
			System.out.println(start + " " + finish);

			for (int i = start; i < finish; i++) {
				Message message = messages[i];
				System.out.println(Thread.currentThread().getName() 
						+ " Message " + i + ": " + message.getFrom()[0] + " " + message.getReceivedDate());
				if (message.getFrom()[0].toString().contains(props.getProperty("notificationEmail").toLowerCase())) {
//				if (message.getFrom()[0].toString().contains("info@members.netflix.com".toLowerCase())) {
					message.setFlag(Flags.Flag.SEEN, true);
					
//					System.out.println("Yes, it is contains");
					System.out.println("NOT Updated inboxLength "+inboxLength);
					inboxLength = inboxLength - 1;
					System.out.println("Updated inboxLength "+inboxLength);
//					if (InboxScanner.this.inboxLength > 0) {
						
						
//						System.out.println("Updated inboxLength "+inboxLength);
//					}
//						System.out.println("Letter from Oleg received: " + message.getContent().toString());
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
					sender.sendResponse(order);
					System.out.println(order);
					frame.appendText("New email received " + order);
				}
			}
			
			Thread.currentThread().interrupt();
			System.out.println(Thread.currentThread().getName()+" is interrupting");

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

	}

}
