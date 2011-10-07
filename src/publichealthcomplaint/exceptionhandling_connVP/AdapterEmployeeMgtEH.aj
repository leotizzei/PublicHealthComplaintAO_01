package publichealthcomplaint.exceptionhandling_connVP;

import publichealthcomplaint.exceptionhandling.aspects.AAEmployeeMgtEH;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.exceptionhandling.impl.UpdateEntryException;
import publichealthcomplaint.infrastructuremgt.aspects.XPIEmployeeMgtEH;

public aspect AdapterEmployeeMgtEH extends AAEmployeeMgtEH {
	
	/**
	 * updateEmployee exceptions
	 */
	declare soft: ObjectNotValidException: XPIEmployeeMgtEH.handleObjectNotValidExc();
	declare soft: UpdateEntryException: XPIEmployeeMgtEH.handleUpdateEntryExc();
	declare soft: ObjectNotFoundException: XPIEmployeeMgtEH.handleObjectNotFoundExc();
	declare soft: ObjectAlreadyInsertedException: XPIEmployeeMgtEH.handleObjectAlreadyInsertedExc();
	
	public pointcut handleObjectNotFoundExc():XPIEmployeeMgtEH.handleObjectNotFoundExc();

	public pointcut handleUpdateEntryExc():XPIEmployeeMgtEH.handleUpdateEntryExc();

	public pointcut handleObjectNotValidExc():XPIEmployeeMgtEH.handleObjectNotValidExc();
	
	public pointcut handleObjectAlreadyInsertedExc():XPIEmployeeMgtEH.handleObjectAlreadyInsertedExc();

}
