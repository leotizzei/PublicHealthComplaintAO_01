package publichealthcomplaint.persistence_connVP;

import publichealthcomplaint.persistence.aspects.AAPersistence;
import publichealthcomplaint.complaintmgr.aspects.XPIPersistenceMechanism;
public aspect AdapterPersistenceComplaintMgr extends AAPersistence {

	public pointcut getCommunicationChannel():XPIPersistenceMechanism.getCommunicationChannel();

	public pointcut releaseCommunicationChannel():XPIPersistenceMechanism.releaseCommunicationChannel();

}
