package publichealthcomplaint.userinterface.impl.feedsmgr.aspects;

import java.io.File;

import publichealthcomplaint.userinterface.impl.feedsmgr.spec.req.IRSSFeedsMgt;

public aspect XPIRSSFeeds {

	public pointcut callGetRSSFile():call(File IRSSFeedsMgt.getRSSFile());

	File around():callGetRSSFile(){
		return null;
	}
	
}
