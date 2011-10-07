package publichealthcomplaint.persistence.aspects;

import publichealthcomplaint.datatypes.Constants;
import publichealthcomplaint.exceptionhandling.impl.PersistenceMechanismException;
import publichealthcomplaint.persistence.impl.ComponentFactory;
import publichealthcomplaint.persistence.spec.prov.IManager;
import publichealthcomplaint.persistence.spec.prov.IPersistenceMechanism;

public abstract aspect AAConnectionDB {

	private IPersistenceMechanism pm = null;

	private boolean pmCreated = false;

	IPersistenceMechanism getPm() {
		if (!pmCreated) {
			pm = pmInit();
			if (pm != null) {
				pmCreated = true;
			}
		}
		return pm;
	}

	IPersistenceMechanism pmInit() {
		IPersistenceMechanism persistenceMech = null;
		if (isPersistent()) {
			try {
				IManager mgr = ComponentFactory.createInstance();
				persistenceMech = (IPersistenceMechanism) mgr.getProvidedInterface("IPersistenceMechanism");
				
				//begin debug
				if(persistenceMech == null)
					System.out.println("AAconnectionDB persistenceMech is null");
				else
					System.out.println("AAconnectionDB persistenceMech is NOT null");
				//end debug
				persistenceMech.connect();
			} catch (PersistenceMechanismException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// Persistence mechanism desconnection for resource release
				System.out.println("[HWPersistence: pmInit()] Exception:" + e.getLocalizedMessage());
				e.printStackTrace();
				/*try {
					if (getPm() != null) {
						getPm().disconnect();
					}
				} catch (PersistenceMechanismException mpe) {
					mpe.printStackTrace();
				}*/
			}
		}
		return persistenceMech;
	}

	boolean isPersistent() {
		return Constants.isPersistent();
	}


	public abstract pointcut persistenceInit();

	/**
	 * Before initializing a facade, init the pm
	 */
	before() : persistenceInit(){
		getPm();
	}

	before(): persistenceInit() {
		IPersistenceMechanism pm = getPm();
		pm.beginTransaction();
	}

	after() returning: persistenceInit() {
		IPersistenceMechanism pm = getPm();
		pm.commitTransaction();
	}

	after() throwing: persistenceInit(){
		IPersistenceMechanism pm = getPm();
		pm.rollbackTransaction();
	}


}
