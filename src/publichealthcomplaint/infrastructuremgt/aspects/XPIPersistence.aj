package publichealthcomplaint.infrastructuremgt.aspects;



public aspect XPIPersistence {
	
	public pointcut getCommunicationChannel():call(Object publichealthcomplaint.infrastructuremgt.spec.req.IPersistenceMechanism.getCommunicationChannel());
	
	
	public pointcut releaseCommunicationChannel():call(void publichealthcomplaint.infrastructuremgt.spec.req.IPersistenceMechanism.releaseCommunicationChannel());

	
	
}
