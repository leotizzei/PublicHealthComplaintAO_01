package publichealthcomplaint.userinterface.impl.userinterf_connvp;

import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IUpdate;
import publichealthcomplaint.userinterface.spec.req.IFacade;

class AdapterIUpdate implements IUpdate {

	
	public IteratorDsk getComplaintList() throws ObjectNotFoundException {
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		return facade.getComplaintList();
	}

	
	public IComplaintDt searchComplaint(int code)
			throws ObjectNotFoundException {
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		return facade.searchComplaint(code);
	}

	
	public void updateComplaint(IComplaintDt complaint)
			throws ObjectNotFoundException, ObjectNotValidException {
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		facade.updateComplaint(complaint);
		
	}
}
