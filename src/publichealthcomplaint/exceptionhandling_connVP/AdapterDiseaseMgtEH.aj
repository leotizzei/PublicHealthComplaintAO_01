package publichealthcomplaint.exceptionhandling_connVP;

import publichealthcomplaint.exceptionhandling.aspects.AADiseaseMgtEH;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.supportservices.impl.queryinfomgr.aspects.XPIDiseaseRepository;

public aspect AdapterDiseaseMgtEH extends AADiseaseMgtEH {

	
	
	declare soft: ObjectNotFoundException: XPIDiseaseRepository.handleObjectNotFoundExc();

	
	public pointcut handleObjectNotFoundExc():XPIDiseaseRepository.handleObjectNotFoundExc();

}
