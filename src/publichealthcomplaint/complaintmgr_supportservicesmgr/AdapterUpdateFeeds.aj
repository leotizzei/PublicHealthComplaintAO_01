package publichealthcomplaint.complaintmgr_supportservicesmgr;

import publichealthcomplaint.complaintmgr.aspects.XPIComplaintMgt;
import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.supportservices.impl.rssfeedsmgr.aspects.AAUpdateFeeds;

public aspect AdapterUpdateFeeds extends AAUpdateFeeds {

	
	
	public pointcut update(IComplaintDt complaint):XPIComplaintMgt.update(complaint); 

}
