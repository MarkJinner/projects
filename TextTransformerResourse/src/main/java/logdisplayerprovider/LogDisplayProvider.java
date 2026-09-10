package logdisplayerprovider;

import com.gmail.logframe.DisplayToJFrame;
import com.gmail.logger.Logger;

public class LogDisplayProvider{
	private static LogDisplayProvider instance = null;
	private DisplayToJFrame display = DisplayToJFrame.getInstance();
	private Logger logger = new Logger();
	private Thread provider;
	private String last = "";
	private TimerChecker timer;

	private LogDisplayProvider() {
		timer = new TimerChecker();
//		provider = new Thread(this);
//		provider.setDaemon(true);
//		provider.start();
//		runDisplay();
		
		
	}
	
	
	
//	static {
//		instance.runDisplay();
	
	
	
//	}
	
	
	
	public TimerChecker getTimer() {
		return timer;
	}



	public void setTimer(TimerChecker timer) {
		this.timer = timer;
	}



	public Thread getProvider() {
		return provider;
	}



	public void setProvider(Thread provider) {
		this.provider = provider;
	}



	public static void main(String [] args) {
		LogDisplayProvider provider = new LogDisplayProvider();
		
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
	


	public void runDisplay() {
		Thread.currentThread().setDaemon(true);
//		display.setpInstance(this);
		String message = "connection checked...";
		while (!Thread.currentThread().isInterrupted()) {
			try {
				display.displayLog(message);
				System.out.println("Display provider is working");
				Thread.currentThread().sleep(10000);		
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				break;	
			}
		}
		System.out.println("Thread display log interrupted");
	}
	
//	@Override
//	public void run() {
//		runDisplay();
//		
//		
//	}

	
	public class TimerChecker implements Runnable {
		private String message = "connection checked...";
		private Thread checker;
		private boolean checkerOn = true;
		
		
		public TimerChecker() {
			checker = new Thread(this);
			checker.start();
			
		}
		
		
		
		public boolean isCheckerOn() {
			return checkerOn;
		}



		public void setCheckerOn(boolean checkerOn) {
			this.checkerOn = checkerOn;
		}



		
		
		public void runTimerChecker() {
			
			while(checkerOn==true) {	

				
				
				try {
					display.displayLog(message);
					
					checker.currentThread().sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();			
					System.out.println("Display stopped");
					break;
	
				}
//			}
			
		}



		}

		@Override
		public void run() {
			runTimerChecker();
			
		}
		
	}
//
//
//	
//
}
