package publichealthcomplaint.exceptionhandling_connVP;

import java.io.FileNotFoundException;
import java.io.IOException;

import publichealthcomplaint.exceptionhandling.aspects.AARSSFeedsMgtEH;
import publichealthcomplaint.supportservices.impl.rssfeedsmgr.aspects.XPIRSSFeedsMgt;

import com.sun.syndication.io.FeedException;

public aspect AdapterRSSFeedsMgtEH extends AARSSFeedsMgtEH {

	declare soft: IOException: XPIRSSFeedsMgt.callUpdateFeeds();
	
	declare soft: FeedException: XPIRSSFeedsMgt.callUpdateFeeds();
	
	declare soft: FileNotFoundException: XPIRSSFeedsMgt.callGetRSSFile();
	
	public pointcut callUpdateFeeds():XPIRSSFeedsMgt.callUpdateFeeds();
	
	public pointcut callGetRSSFile():XPIRSSFeedsMgt.callGetRSSFile();

}
