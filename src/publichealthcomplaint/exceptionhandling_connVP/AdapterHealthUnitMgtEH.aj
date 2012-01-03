package publichealthcomplaint.exceptionhandling_connVP;

import publichealthcomplaint.exceptionhandling.aspects.AAHealthUnitMgtEH;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.infrastructuremgt.aspects.XPIHealthUnitMgt;

public aspect AdapterHealthUnitMgtEH extends AAHealthUnitMgtEH {

	
	
	
	declare soft: ObjectNotFoundException: XPIHealthUnitMgt.handleObjectNotFoundExc();
	declare soft: ObjectNotValidException: XPIHealthUnitMgt.handleObjectNotValidExc();
	
	public pointcut handleObjectNotFoundExc():XPIHealthUnitMgt.handleObjectNotFoundExc();

	public pointcut handleObjectNotValidExc():XPIHealthUnitMgt.handleObjectNotValidExc();

}
