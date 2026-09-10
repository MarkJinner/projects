package com.gmail.mailanalyzer.main.mailobserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	private File properties = new File("properties.properties");
	private SetupProperties readerProperties;
	private SetupProperties senderProperties;
	private ResponseSender sender;
	private String emailContent = "";
	private Email email = new Email();
	private EmailParser parser = new EmailParser();
	private Order order;

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

					for (int i = 0; i < messages.length - inboxLength; i++) {
						Message message = messages[messages.length - 1 - i];
						if (message.getFrom()[0].toString().contains("Oleg Ivanov")) {
//							System.out.println(message.getFrom()[0]+" "+message.getSubject());	
							if (message.getFrom()[0].toString().contains("oleg_ua@n21.com")) {
								messages[messages.length - 1 - i].setFlag(Flags.Flag.SEEN, true);
								System.out.println("Letter from Oleg received: " + message.getContent().toString());
								Object content = message.getContent();
								System.out.println(content.getClass());
								if (content instanceof String) {
									System.out.println("Email content is instance of String");
								} else if (content instanceof MimeMultipart) {
									System.out.println("Email content is instance of MimeMultipart");
									MimeMultipart multipart = (MimeMultipart) content;
									for(int j =0; j<  multipart.getCount();j++) {
										this.parseMessageContent(multipart.getBodyPart(j));									
									}

								}
//								System.out.println("emailContent: "+ emailContent);

							}
//							System.out.println(email);
							order = parser.parseEmail(email);
							System.out.println(order);
							
						}
					}
					
					this.inboxLength = messages.length;
				} else {
					System.out.println("Equal");
				}
				
				
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
	
    public void parseMessageContent(Part part) throws MessagingException, IOException {
    	StringBuilder sb = new StringBuilder();
        // 1. Check if the content is plain text or HTML
        if (part.isMimeType("text/plain")) {
        	System.out.println("Text Content: ");
//            System.out.println("Text Content: " + part.getContent());
        	emailContent = (String)part.getContent();
        	email.setContent(emailContent);
//            System.out.println(part.getContent()+"END OF TEXT CONTENT");
            
//            return (String)part.getContent().toString();
        } 
        else if (part.isMimeType("text/html")) {
            System.out.println("HTML Content: " + part.getContent());

        } 
        // 2. Handle Multipart content (including multipart/related)
        else if (part.isMimeType("multipart/*")) {
            MimeMultipart mimeMultipart = (MimeMultipart) part.getContent();
            int count = mimeMultipart.getCount();

            for (int i = 0; i < count; i++) {
                BodyPart bodyPart = mimeMultipart.getBodyPart(i);
                
                // Check if this specific body part is an inline asset (like an image)
                if (Part.INLINE.equalsIgnoreCase(bodyPart.getDisposition()) || isInlineResource(bodyPart)) {
                    String contentType = bodyPart.getContentType();
                    String contentId = ((MimeBodyPart) bodyPart).getContentID();
                    System.out.println("Found Inline Asset: " + contentType + " | CID: " + contentId);
                    // You can use bodyPart.getInputStream() to save or process the image bytes
                } else {
                    // Recursively drill down to find the main text/HTML bodies
                    parseMessageContent(bodyPart);
                }
            }
        }
    }

	private static boolean isInlineResource(BodyPart bodyPart) throws MessagingException {
		if (bodyPart instanceof MimeBodyPart) {
			String contentId = ((MimeBodyPart) bodyPart).getContentID();
			// If it has a Content-ID (CID), it's meant to be referenced inside the HTML body
			return contentId != null && !contentId.trim().isEmpty();
		}
		return false;
	}

	public void scanInbox() throws InterruptedException {
		startScanning();
		sender.sendResponse(order);

	}

}
