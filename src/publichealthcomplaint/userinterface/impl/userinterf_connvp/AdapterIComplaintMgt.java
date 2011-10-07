package publichealthcomplaint.userinterface.impl.userinterf_connvp;

import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.userinterface.impl.ComponentFactory;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IComplaintMgt;
import publichealthcomplaint.userinterface.spec.prov.IManager;
import publichealthcomplaint.userinterface.spec.req.IFacade;

class AdapterIComplaintMgt implements IComplaintMgt{

	public int insertComplaint(IComplaintDt complaint)
			throws ObjectAlreadyInsertedException, ObjectNotValidException {
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		return facade.insertComplaint(complaint);
	}

	

}
