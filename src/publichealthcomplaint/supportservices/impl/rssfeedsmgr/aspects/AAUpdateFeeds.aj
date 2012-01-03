package publichealthcomplaint.supportservices.impl.rssfeedsmgr.aspects;

import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.supportservices.impl.rssfeedsmgr.impl.ComponentFactory;
import publichealthcomplaint.supportservices.impl.rssfeedsmgr.spec.prov.IManager;
import publichealthcomplaint.supportservices.impl.rssfeedsmgr.spec.prov.IRSSFeedsMgt;

public abstract aspect AAUpdateFeeds {

	public abstract pointcut update(IComplaintDt complaint);//:call(void IComplaintMgt.updateComplaint(IComplaintDt)) && args(complaint);

	after(IComplaintDt complaint):update(complaint){
		System.out.println("[Update:update()]");
		IManager mgr = ComponentFactory.createInstance();
		IRSSFeedsMgt rssFeedsMgr = (IRSSFeedsMgt) mgr.getProvidedInterface("IRSSFeedsMgt"); 
		
		
		rssFeedsMgr.updateFeeds(complaint);
		
	}

	
	
	

}
