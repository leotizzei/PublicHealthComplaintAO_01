package publichealthcomplaint.complaintmgr.aspects;



public aspect XPIPersistenceMechanism {
	
	public pointcut getCommunicationChannel():call(Object publichealthcomplaint.complaintmgr.spec.req.IPersistenceMechanism.getCommunicationChannel());
	
	/**
	 * This pointcut does not catch any joinpoint so far
	 */
	public pointcut releaseCommunicationChannel():call(void publichealthcomplaint.complaintmgr.spec.req.IPersistenceMechanism.releaseCommunicationChannel());


	
}
