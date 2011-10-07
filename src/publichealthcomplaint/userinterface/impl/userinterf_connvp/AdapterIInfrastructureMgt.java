package publichealthcomplaint.userinterface.impl.userinterf_connvp;

import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.datatypes.IHealthUnitDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.exceptionhandling.impl.UpdateEntryException;
import publichealthcomplaint.userinterface.impl.infrastr.spec.req.IInfrastructureMgt;
import publichealthcomplaint.userinterface.spec.req.IFacade;

class AdapterIInfrastructureMgt implements IInfrastructureMgt {

	
	public IteratorDsk getHealthUnitList() throws ObjectNotFoundException {
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		return facade.getHealthUnitList();
	}

	
	public void insert(IEmployeeDt e) throws ObjectAlreadyInsertedException,
			ObjectNotValidException {
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		facade.insert(e);
	}


	public IEmployeeDt searchEmployee(String login)
			throws ObjectNotFoundException {
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		return facade.searchEmployee(login);
	}

	
	public IHealthUnitDt searchHealthUnit(int healthUnitCode)
			throws ObjectNotFoundException {
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		return facade.searchHealthUnit(healthUnitCode);
	}

	
	public void updateEmployee(IEmployeeDt e) throws ObjectNotFoundException,
			ObjectNotValidException, UpdateEntryException {
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		facade.updateEmployee(e);

	}

	
	public void updateHealthUnit(IHealthUnitDt unit)
			throws ObjectNotFoundException, ObjectNotValidException {
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		facade.updateHealthUnit(unit);

	}

}
