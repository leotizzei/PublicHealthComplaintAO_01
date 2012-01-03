package publichealthcomplaint.concurrency.aspects;



public aspect XPIPersistence {
	
	public pointcut getCommunicationChannel():call(Object publichealthcomplaint.concurrency.spec.req.IPersistenceMechanism.getCommunicationChannel());
	
	/**
	 * This pointcut does not catch any joinpoint so far
	 */
	public pointcut releaseCommunicationChannel():call(void publichealthcomplaint.concurrency.spec.req.IPersistenceMechanism.releaseCommunicationChannel());


	
}
