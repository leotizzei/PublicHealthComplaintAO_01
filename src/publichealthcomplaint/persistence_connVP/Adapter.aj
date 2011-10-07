package publichealthcomplaint.persistence_connVP;

import publichealthcomplaint.persistence.aspects.AAPersistence;
import publichealthcomplaint.supportservices.impl.queryinfomgr.aspects.XPIPersistence;

public aspect Adapter extends AAPersistence{

	public pointcut getCommunicationChannel():XPIPersistence.getCommunicationChannel();
		
	
	public pointcut releaseCommunicationChannel(): XPIPersistence.releaseCommunicationChannel();
		
		
	
}
