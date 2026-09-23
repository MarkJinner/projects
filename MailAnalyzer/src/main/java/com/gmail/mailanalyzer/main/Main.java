package com.gmail.mailanalyzer.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.gmail.mailanalyzer.main.mailobserver.MailObserver;
import com.gmail.mailanalyzer.main.observerframe.ObserverFrame;

public class Main {

	public static void main(String[] args) {
		MailObserver observer;
		try {
			observer = new MailObserver();
			observer.scanEmailInbox();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
