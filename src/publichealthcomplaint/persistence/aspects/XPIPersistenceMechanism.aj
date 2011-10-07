package publichealthcomplaint.persistence.aspects;

import publichealthcomplaint.persistence.spec.prov.IPersistenceMechanism;

public aspect XPIPersistenceMechanism {
	
	
	
	
	public pointcut communication(): ((call (void IPersistenceMechanism.connect())) ||
			(call (void IPersistenceMechanism.disconnect())) || 
			(call (Object IPersistenceMechanism.getCommunicationChannel())) ||
			(call (void IPersistenceMechanism.releaseCommunicationChannel()))) ; 
	
	public pointcut transaction():( (call(void IPersistenceMechanism.beginTransaction()) ) ||
			(call(void IPersistenceMechanism.commitTransaction()) ) ||
			(call(void IPersistenceMechanism.rollbackTransaction()) ) );
	
	
	

}
