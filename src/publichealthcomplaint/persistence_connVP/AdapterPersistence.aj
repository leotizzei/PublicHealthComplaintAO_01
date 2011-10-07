package publichealthcomplaint.persistence_connVP;

import publichealthcomplaint.persistence.aspects.AAPersistence;
import publichealthcomplaint.infrastructuremgt.aspects.XPIPersistence;

public aspect AdapterPersistence extends AAPersistence {

	public pointcut getCommunicationChannel():XPIPersistence.getCommunicationChannel();

	public pointcut releaseCommunicationChannel():XPIPersistence.releaseCommunicationChannel();

}
