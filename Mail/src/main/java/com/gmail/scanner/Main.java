package com.gmail.scanner;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		try {
			MailSender sender = new MailSender();
			sender.sendMail();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
