package publichealthcomplaint.supportservices.impl.queryinfomgr.aspects;

import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req.IPersistenceMechanism;


public aspect XPIPersistence {
	
	public pointcut getCommunicationChannel():call(Object IPersistenceMechanism.getCommunicationChannel());
	
	public pointcut releaseCommunicationChannel():call(void IPersistenceMechanism.releaseCommunicationChannel());

	
	
}
