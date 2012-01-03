package publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req;

import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.exceptionhandling.impl.UpdateEntryException;

public interface IEmployeeMgt {

	
	

	public void insert(IEmployeeDt e) throws ObjectAlreadyInsertedException, ObjectNotValidException;
	
	public IEmployeeDt searchEmployee(String login) throws ObjectNotFoundException;
	
	public void updateEmployee(IEmployeeDt e) throws ObjectNotFoundException, ObjectNotValidException, UpdateEntryException;

	
}
