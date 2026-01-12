package com.gmail.logframe;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestInterruption {

	public static void main(String[] args) {
		Randomizer ran = new TestInterruption().new Randomizer();
	}

	private class Timer implements Runnable {
		private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		private Thread tr;

		public Timer() {
			tr = new Thread(this);
			tr.start();
		}

		private void showTime() {

			while (!tr.isInterrupted()) {
				System.out.println(sdf.format(new Date()));
				try {
					tr.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Timer stopped");
					break;

				}
				

			}
			
		}

		@Override
		public void run() {
			showTime();

		}

	}

	private class Randomizer implements Runnable {
		private Thread t;
		private Timer timer;

		public Randomizer() {
			timer = new Timer();
			t = new Thread(this);
			t.start();
		}

		private int getRandom() {
			return 1 * (int) (Math.random() * 10);
		}

		private void runRandom() throws InterruptedException {
			int x = getRandom();

			while (x != 7) {
			
				t.sleep(1000);
				x = getRandom();
				System.out.println(x);
				if (x == 7) {
					
					System.out.println("Randomizer stopped");
					timer.tr.interrupt();
					t.interrupt();
				}

	
			}
		}

		@Override
		public void run() {
			try {
				runRandom();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
