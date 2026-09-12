package com.gmail.mailanalyzer.main.mailobserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.Properties;

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
	private int inboxLength = 0;

	private Properties props = new Properties();
	private String notificationAddress = "Oleg_ua@n21.com";
	private File properties = new File("properties.properties");
	private SetupProperties readerProperties;
	private SetupProperties senderProperties;
	private ResponseSender sender;
	private String emailContent = "";
	private EmailParser parser = new EmailParser();



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
				if (this.inboxLength != messages.length) {
					System.out.println("Not equal: new letter received");
					int messagesLength = messages.length;
					int parts = 5;
					int threadSize = messagesLength/parts;
					Thread[] partScanners = new Thread[parts];
					long startTime =  System.currentTimeMillis();			
//					Thread tr = new Thread(new InboxScanner().new InboxPartScanner(messages, 0, messagesLength) );
//					tr.start();
//					tr.join();
					for(int i = 0; i< partScanners.length;i++) {
						int start = i*threadSize;
						int finish = start+threadSize;
						partScanners[i] = new Thread(new InboxScanner().new InboxPartScanner(messages, start, finish));
						partScanners[i].start();	
					}
					
					for(int i = 0; i< partScanners.length;i++) {
						partScanners[i].join();
					}
					long finishTime = System.currentTimeMillis();
					System.out.println("Check finished in "+ (finishTime-startTime)+" ms");
					
					
//					InboxPartScanner partScanner = new InboxScanner().new InboxPartScanner(messages, 0, messagesLength);
//					Thread partScanner1 = new Thread(partScanner);
//					partScanner1.start();
					
//					partScanner1.join();
					
//					for (int i = 0; i < messages.length - inboxLength; i++) {
//						Message message = messages[messages.length - 1 - i];
//						if (message.getFrom()[0].toString().contains(props.getProperty("notificationEmail").toLowerCase())) {
//							System.out.println(this.parseFrom(message.getFrom()[0].toString()));	
//								messages[messages.length - 1 - i].setFlag(Flags.Flag.SEEN, true);
//								System.out.println("Letter from Oleg received: " + message.getContent().toString());
//								Object content = message.getContent();
//								System.out.println(content.getClass());
//								if (content instanceof String) {
//									System.out.println("Email content is instance of String");
//								} else if (content instanceof MimeMultipart) {
//									System.out.println("Email content is instance of MimeMultipart");
//									MimeMultipart multipart = (MimeMultipart) content;
//									for (int j = 0; j < multipart.getCount(); j++) {
//										this.parseMessageContent(multipart.getBodyPart(j));
//									}
//								}
//							order = parser.parseEmail(email);
//							System.out.println(order);
//						}
//					}

					this.inboxLength = messages.length;
				} else {
					System.out.println("Equal");
				}

				System.out.println("Всего писем в ящике: " + messages.length);
//				emailFolder.close(false);
//				store.close();

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
	
	public static void main(String [] args) throws FileNotFoundException, IOException, InterruptedException {
		InboxScanner scanner = new InboxScanner();
		scanner.scanInbox();
		

	}
	
	private class InboxPartScanner implements Runnable{
		private Order order;
		private Email email = new Email();
		private int start;
		private int finish;
		private Message[] messages;
		
		public InboxPartScanner(Message[] messages, int start, int finish) {
			this.messages = messages;
			this.start = start;
			this.finish = finish;
		}
		//1. Split email checker on 10 different threads; - rewrite function from InboxPartScanner to follow start-finish and if letter from Oleg_ua@n21.com found to parse and send response;
//		private void scanInboxSection() throws MessagingException, IOException {
//			for (int i = 0; i < messages.length - inboxLength; i++) {
//				Message message = messages[messages.length - 1 - i];
//				if (message.getFrom()[0].toString().contains(props.getProperty("notificationEmail").toLowerCase())) {
////					System.out.println(parseFrom(message.getFrom()[0].toString()));	
//						messages[messages.length - 1 - i].setFlag(Flags.Flag.SEEN, true);
//						System.out.println("Letter from Oleg received: " + message.getContent().toString());
//						Object content = message.getContent();
//						System.out.println(content.getClass());
//						if (content instanceof String) {
//							System.out.println("Email content is instance of String");
//						} else if (content instanceof MimeMultipart) {
//							System.out.println("Email content is instance of MimeMultipart");
//							MimeMultipart multipart = (MimeMultipart) content;
//							for (int j = 0; j < multipart.getCount(); j++) {
//								parseMessageContent(multipart.getBodyPart(j));
//							}
//						}
//					order = parser.parseEmail(email);
//					sender.sendResponse(order);
//					System.out.println(order);
//				}
//			}
//		}
		
		
		private void scanInboxSection() throws MessagingException, IOException {
			System.out.println(start+" "+ finish);
			for (int i = start; i < finish; i++) {
				Message message = messages[i];
				System.out.println(Thread.currentThread().getName() +" Message "+i+": "+message.getFrom()[0] +" "+message.getReceivedDate());
				if (message.getFrom()[0].toString().contains(props.getProperty("notificationEmail").toLowerCase())) {
//				if (message.getFrom()[0].toString().contains("sales@rozetka.com.ua".toLowerCase())) {
					message.setFlag(Flags.Flag.SEEN, true);
//						System.out.println("Letter from Oleg received: " + message.getContent().toString());
						Object content = message.getContent();
//						System.out.println(content.getClass());
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
				}
			}

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
			return addrLine.substring(addrLine.indexOf("<")+1, addrLine.indexOf(">"));
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

		private  boolean isInlineResource(BodyPart bodyPart) throws MessagingException {
			if (bodyPart instanceof MimeBodyPart) {
				String contentId = ((MimeBodyPart) bodyPart).getContentID();
				return contentId != null && !contentId.trim().isEmpty();
			}
			return false;
		}
		
		
	}

}
