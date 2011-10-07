package publichealthcomplaint.persistence_connVP;

import publichealthcomplaint.persistence.aspects.AAPersistence;
import publichealthcomplaint.concurrency.aspects.XPIPersistence;
public aspect AdapterPersistenceConcurrency  extends AAPersistence {

	public pointcut getCommunicationChannel():XPIPersistence.getCommunicationChannel();

	public pointcut releaseCommunicationChannel():XPIPersistence.releaseCommunicationChannel();

}
