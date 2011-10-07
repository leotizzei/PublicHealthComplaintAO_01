package publichealthcomplaint.supportservices.impl.rssfeedsmgr.aspects;

import publichealthcomplaint.supportservices.impl.rssfeedsmgr.spec.prov.IRSSFeedsMgt;
import publichealthcomplaint.datatypes.IComplaintDt;
import java.io.File;

public aspect XPIRSSFeedsMgt {
	
	public pointcut callUpdateFeeds():call (void IRSSFeedsMgt.updateFeeds(IComplaintDt));
	
	public pointcut callGetRSSFile():call (File IRSSFeedsMgt.getRSSFile());

}
