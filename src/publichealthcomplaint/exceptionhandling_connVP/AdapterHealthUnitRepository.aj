package publichealthcomplaint.exceptionhandling_connVP;

import publichealthcomplaint.exceptionhandling.aspects.AAHealthUnitRepositoryEH;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.supportservices.impl.queryinfomgr.aspects.XPIHealthUnitRepositoryEH;

public aspect AdapterHealthUnitRepository extends AAHealthUnitRepositoryEH {

	
	
	public pointcut handleObjectNotFoundExc():XPIHealthUnitRepositoryEH.handleObjectNotFoundExc();
	
	declare soft: ObjectNotFoundException: XPIHealthUnitRepositoryEH.handleObjectNotFoundExc();
	
}
