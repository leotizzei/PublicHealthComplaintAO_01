package publichealthcomplaint.userinterface.impl.userinterf_connvp;

import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.datatypes.IDiseaseDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IQueryInfoMgt;
import publichealthcomplaint.userinterface.spec.req.IFacade;

class AdapterIQueryInfoMgt implements IQueryInfoMgt {


	public IteratorDsk getDiseaseTypeList() throws ObjectNotFoundException {
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		return facade.getDiseaseTypeList();
	}


	public IteratorDsk getPartialHealthUnitList(){
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		return facade.getPartialHealthUnitList();
	}


	public IteratorDsk getSpecialityList() throws ObjectNotFoundException {
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		return facade.getSpecialityList();
	}


	public IComplaintDt searchComplaint(int code)
	throws ObjectNotFoundException {
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		return facade.searchComplaint(code);
	}


	public IDiseaseDt searchDiseaseType(int code)
	throws ObjectNotFoundException {
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		return facade.searchDiseaseType(code);
	}


	public IteratorDsk searchHealthUnitsBySpeciality(int code)
	throws ObjectNotFoundException {
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		return facade.searchHealthUnitsBySpeciality(code);
	}


	public IteratorDsk searchSpecialitiesByHealthUnit(int code)
	throws ObjectNotFoundException {
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		return facade.searchSpecialitiesByHealthUnit(code);
	}

}
