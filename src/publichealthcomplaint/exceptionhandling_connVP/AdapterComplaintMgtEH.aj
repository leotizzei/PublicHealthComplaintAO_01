package publichealthcomplaint.exceptionhandling_connVP;

import publichealthcomplaint.complaintmgr.aspects.XPIException;
import publichealthcomplaint.exceptionhandling.aspects.AAComplaintMgtEH;
import publichealthcomplaint.exceptionhandling.impl.InvalidDateException;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;

public aspect AdapterComplaintMgtEH extends AAComplaintMgtEH {
	
	/**
	 * getComplaintList exceptions
	 */
	declare soft: InvalidDateException: XPIException.dateException();
	declare soft: ObjectNotFoundException: XPIException.objectNotFoundExc();
	
	/**
	 * search exceptions
	 */
	declare soft: InvalidDateException: XPIException.dateException();
	declare soft: ObjectNotFoundException: XPIException.objectNotFoundExc();
	
	/**
	 * insertComplaint exceptions
	 */
	declare soft: ObjectAlreadyInsertedException: XPIException.objectAlreadyInsertedExc();
	declare soft: ObjectNotValidException: XPIException.objectNotValidExc();
	
	/**
	 * updateComplaint exceptions
	 */
	declare soft: ObjectNotFoundException: XPIException.objectNotFoundExc();
	declare soft: ObjectNotValidException: XPIException.objectNotValidExc();

	public pointcut dateException():XPIException.dateException();

	public pointcut objectNotValidExc():XPIException.objectNotValidExc();

	public pointcut objectNotFoundExc():XPIException.objectNotFoundExc();
	
	public pointcut objectAlreadyInsertedExc():XPIException.objectAlreadyInsertedExc();

}
