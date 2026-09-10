package com.gmail.servlets;

import com.gmail.logframe.DisplayToJFrame;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import logdisplayerprovider.LogDisplayProvider;

@WebListener//important annotation - add automation to web app listening 
public class AppContextListener implements ServletContextListener{
    
	@Override
    public void contextInitialized(ServletContextEvent sce) {
        // Код выполняется при запуске приложения
        // (например, подключение к БД, запуск планировщика)
        System.out.println("Веб-приложение успешно запущено!");
        
    }

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		LogDisplayProvider provider = LogDisplayProvider.getInstance();
		DisplayToJFrame frame = DisplayToJFrame.getInstance();
        java.awt.Window[] windows = java.awt.Window.getWindows();
               for (java.awt.Window window : windows) {
        	System.out.println(window.getName());

        	
            if(frame.isDisposeFlag()==true){
            	provider.getTimer().setCheckerOn(false);
            	window.dispose();//option which is causing the whole app dumb while clicking the window closing... 
            }
            
            
            
//            window.dispose();
    	
            System.out.println("Window disposed");    
        }
}
}
