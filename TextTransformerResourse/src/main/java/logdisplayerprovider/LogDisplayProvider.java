package logdisplayerprovider;

import com.gmail.logframe.DisplayToJFrame;
import com.gmail.logger.Logger;

public class LogDisplayProvider implements Runnable {
	private static LogDisplayProvider instance;
	private DisplayToJFrame display = DisplayToJFrame.getToFrameDisplayer();
	private Logger logger = new Logger();
	private TimerChecker timer;
	private Thread provider;
	private String last = "";
//	private 

	private LogDisplayProvider() {
		timer = new TimerChecker();
		provider = new Thread(this);
		provider.setDaemon(true);
		provider.start();
	}
	
	public static void main() {
		System.out.println("?");
	}
	
	

	public DisplayToJFrame getDisplay() {
		return display;
	}

	public void setDisplay(DisplayToJFrame display) {
		this.display = display;
	}

	public static LogDisplayProvider getInstance() {

		if (instance == null) {
			instance = new LogDisplayProvider();

		}
		return instance;
	}

	private void runDisplay() {

		while (!provider.isInterrupted()) {

			try {
				this.provider.sleep(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Display stopped");
				timer.checker.interrupt();
				display.dispose();
				break;	
			}
		}
		
		
	}
	

	@Override
	public void run() {

			runDisplay();


	}
	
	private class TimerChecker implements Runnable{
		private String message = "connection checked...";
		private Thread checker;
		
		
		public TimerChecker() {
			checker = new Thread(this);
			checker.setDaemon(true);
			checker.start();
		}
		
		private void runTimerChecker() {
			
			while(!provider.isInterrupted()) {
				display.displayLog(message);
				try {
					provider.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					display.dispose();
					checker.interrupt();
					break;
				}
			}
			System.out.println("Timer stopped");
		}


		@Override
		public void run() {
			runTimerChecker();
			
		}
		
	}
	

}
