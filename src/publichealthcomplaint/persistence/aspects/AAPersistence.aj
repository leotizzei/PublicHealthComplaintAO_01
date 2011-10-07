package publichealthcomplaint.persistence.aspects;

import publichealthcomplaint.persistence.impl.ComponentFactory;
import publichealthcomplaint.persistence.spec.prov.IManager;
import publichealthcomplaint.persistence.spec.prov.IPersistenceMechanism;


public abstract aspect AAPersistence {
	
	
	public abstract pointcut getCommunicationChannel();
	
	Object around():getCommunicationChannel(){

		IManager mgr = ComponentFactory.createInstance();
		IPersistenceMechanism persistenceMech = (IPersistenceMechanism) mgr.getProvidedInterface("IPersistenceMechanism");
				
		return persistenceMech.getCommunicationChannel();
		
		
	}

	public abstract pointcut releaseCommunicationChannel();

	void around():releaseCommunicationChannel(){

		IManager mgr = ComponentFactory.createInstance();
		IPersistenceMechanism persistenceMech = (IPersistenceMechanism) mgr.getProvidedInterface("IPersistenceMechanism");
		
		persistenceMech.releaseCommunicationChannel();
		
		
	}

}
