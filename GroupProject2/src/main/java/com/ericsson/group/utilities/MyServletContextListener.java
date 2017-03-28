package com.ericsson.group.utilities;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
	
	/**This method will run when the web application starts***/
    public void contextInitialized(ServletContextEvent sce) {
    	/* String path = MyServletContextListener.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    	path = path.substring(0, path.indexOf("classes")) + "upload";
    	StringBuilder sb = new StringBuilder(path);
    	sb.deleteCharAt(0);
    	String path2 = sb.toString();
    	
		// register directory and process its events
        final Path dir = Paths.get(path2);
		
    	new java.util.Timer().schedule( 
    	        new java.util.TimerTask() {
    	            @Override
    	            public void run() {
    	            	try {
    	        			new WatchDir(dir).processEvents();
    	        		} catch (IOException e) {
    	        			e.printStackTrace();
    	        		}
    	            }
    	        }, 
    	        5000 
    	);
    	
    	
    	*/
    }
    
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
