package publichealthcomplaint.supportservices.impl.queryinfomgr.aspects;

import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.prov.IHealthUnitRepository;

public aspect XPIHealthUnitRepositoryEH {

	
	public pointcut handleObjectNotFoundExc():(
			call(IteratorDsk IHealthUnitRepository.getPartialHealthUnitList()) ||
			call(IteratorDsk IHealthUnitRepository.getHealthUnitListBySpeciality(int)) ||
			call(IteratorDsk IHealthUnitRepository.searchSpecialitiesByHealthUnit(int))
			);
	
}
