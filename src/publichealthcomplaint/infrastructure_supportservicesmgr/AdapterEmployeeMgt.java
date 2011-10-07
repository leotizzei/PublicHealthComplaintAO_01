package publichealthcomplaint.infrastructure_supportservicesmgr;

import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.exceptionhandling.impl.UpdateEntryException;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req.IEmployeeMgt;

class AdapterEmployeeMgt implements IEmployeeMgt {

	
	public void insert(IEmployeeDt e) throws ObjectAlreadyInsertedException,
			ObjectNotValidException {
		IManager manager = ComponentFactory.createInstance();
		publichealthcomplaint.infrastructuremgt.spec.prov.IEmployeeMgt employeeMgt = (publichealthcomplaint.infrastructuremgt.spec.prov.IEmployeeMgt) manager.getRequiredInterface("IEmployeeMgt");
		employeeMgt.insert(e);

	}


	public IEmployeeDt searchEmployee(String login)
			throws ObjectNotFoundException {
		IManager manager = ComponentFactory.createInstance();
		publichealthcomplaint.infrastructuremgt.spec.prov.IEmployeeMgt employeeMgt = (publichealthcomplaint.infrastructuremgt.spec.prov.IEmployeeMgt) manager.getRequiredInterface("IEmployeeMgt");
		return employeeMgt.searchEmployee(login);
	}

	
	public void updateEmployee(IEmployeeDt e) throws ObjectNotFoundException,
			ObjectNotValidException, UpdateEntryException {
		IManager manager = ComponentFactory.createInstance();
		publichealthcomplaint.infrastructuremgt.spec.prov.IEmployeeMgt employeeMgt = (publichealthcomplaint.infrastructuremgt.spec.prov.IEmployeeMgt) manager.getRequiredInterface("IEmployeeMgt");
		employeeMgt.updateEmployee(e);

	}

}
