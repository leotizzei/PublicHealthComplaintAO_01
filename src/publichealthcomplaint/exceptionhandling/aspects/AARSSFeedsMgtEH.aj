package publichealthcomplaint.exceptionhandling.aspects;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.sun.syndication.io.FeedException;

public abstract aspect AARSSFeedsMgtEH {
	
	
	
	public abstract pointcut callUpdateFeeds();
	
	after() throwing(IOException e):callUpdateFeeds(){
		System.err.println(e.getLocalizedMessage());
		e.printStackTrace();
	}

    after() throwing(FeedException e):callUpdateFeeds(){
    	System.err.println(e.getLocalizedMessage());
		e.printStackTrace();
	}
    
    public abstract pointcut callGetRSSFile();
    
    after() throwing(FileNotFoundException e):callUpdateFeeds(){
    	System.err.println(e.getLocalizedMessage());
		e.printStackTrace();
	}
}
