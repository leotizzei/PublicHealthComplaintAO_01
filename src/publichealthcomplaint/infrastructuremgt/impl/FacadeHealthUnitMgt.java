package publichealthcomplaint.infrastructuremgt.impl;

import publichealthcomplaint.datatypes.IHealthUnitDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.infrastructuremgt.spec.prov.IHealthUnitMgt;
import publichealthcomplaint.infrastructuremgt.spec.prov.IManager;

class FacadeHealthUnitMgt implements IHealthUnitMgt {

	HealthUnitRepositoryRDB healthUnitRep;
	
	FacadeHealthUnitMgt(IManager mgr) {
		this.healthUnitRep = new HealthUnitRepositoryRDB(mgr);
	}
	
	public IteratorDsk getHealthUnitList() throws ObjectNotFoundException {
		return healthUnitRep.getHealthUnitList();
	}

	
	public IHealthUnitDt partialSearch(int codigo)
			throws ObjectNotFoundException {
		
		return healthUnitRep.partialSearch(codigo);
	}

	
	public IHealthUnitDt searchHealthUnit(int healthUnitCode)
			throws ObjectNotFoundException {
		
		return healthUnitRep.search(healthUnitCode);
	}

	
	public void updateHealthUnit(IHealthUnitDt unit)
			throws ObjectNotFoundException, ObjectNotValidException {
		healthUnitRep.update(unit);

	}

}
