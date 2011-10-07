package publichealthcomplaint.infrastructuremgt.impl;

import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.exceptionhandling.impl.UpdateEntryException;
import publichealthcomplaint.infrastructuremgt.spec.prov.IEmployeeMgt;

class FacadeEmployeeMgt implements IEmployeeMgt {

	EmployeeRepositoryRDB employeeRep;
	
	FacadeEmployeeMgt() {
		employeeRep = new EmployeeRepositoryRDB();
	}

	
	public void insert(IEmployeeDt e) throws ObjectAlreadyInsertedException,
			ObjectNotValidException {
		System.out.println("[FacadeInfrastructureMgt:insert()]");
		employeeRep.insert(e);
		
	}


	
	public void updateEmployee(IEmployeeDt e) throws ObjectNotFoundException,
			ObjectNotValidException, UpdateEntryException {
		employeeRep.update(e);
		
	}


	
	public IEmployeeDt searchEmployee(String login)
			throws ObjectNotFoundException {
		if(login == null){
			throw new IllegalArgumentException("FacadeEmployeeMgt:searchEmployee => Null argument");
			
		}
		else
			return employeeRep.search(login);
	}


	
	
	
	

}
