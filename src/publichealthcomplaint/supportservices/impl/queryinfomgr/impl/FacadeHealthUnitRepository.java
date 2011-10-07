package publichealthcomplaint.supportservices.impl.queryinfomgr.impl;

import publichealthcomplaint.datatypes.IHealthUnitDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.prov.IHealthUnitRepository;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.prov.IManager;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req.IDistributionMgt;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req.IHealthUnitMgt;


class FacadeHealthUnitRepository implements IHealthUnitRepository {



	private HealthUnitRepositoryRDB healthUnitRepRDB;

	private IDistributionMgt distributionMgt;

	private IHealthUnitMgt healthUnitMgt;
	
	public FacadeHealthUnitRepository(IManager mgr) {
		this.healthUnitRepRDB = new HealthUnitRepositoryRDB(mgr);
	}


	public IteratorDsk searchSpecialitiesByHealthUnit(int code)	throws ObjectNotFoundException {
		IManager mgr = ComponentFactory.createInstance();
		healthUnitMgt = (IHealthUnitMgt) mgr.getRequiredInterface("IHealthUnitMgt");
		IHealthUnitDt us = this.healthUnitMgt.searchHealthUnit(code);
		IteratorDsk iterator = distributionMgt.createIterator(us.getSpecialities());
		return iterator;

	}


	public IteratorDsk getPartialHealthUnitList() throws ObjectNotFoundException {
		System.out.println("[FacadeHealthUnitRepository:getPartialHealthUnitList()] calling help classes...");
		return healthUnitRepRDB.getPartialHealthUnitList();
	}



	public IteratorDsk getHealthUnitListBySpeciality(int codEspecialidade)
	throws ObjectNotFoundException {
		return this.healthUnitRepRDB.getHealthUnitListBySpeciality(codEspecialidade);
	}





}
