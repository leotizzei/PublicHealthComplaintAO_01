package publichealthcomplaint.userinterface.impl.infrastr.spec.req;

import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.datatypes.IHealthUnitDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.exceptionhandling.impl.UpdateEntryException;

public interface IInfrastructureMgt {

	public IteratorDsk getHealthUnitList() throws ObjectNotFoundException;
	
	public void insert(IEmployeeDt e) throws ObjectAlreadyInsertedException, ObjectNotValidException;
	
	public IEmployeeDt searchEmployee(String login) throws ObjectNotFoundException;
	
	public IHealthUnitDt searchHealthUnit(int healthUnitCode) throws ObjectNotFoundException;
	
	public void updateEmployee(IEmployeeDt e) throws ObjectNotFoundException, ObjectNotValidException, UpdateEntryException;
	
	public void updateHealthUnit(IHealthUnitDt unit) throws ObjectNotFoundException, ObjectNotValidException;
}
