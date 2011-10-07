package publichealthcomplaint.userinterface.impl.userinterf_connvp;

import java.io.File;

import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.datatypes.IHealthUnitDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.userinterface.impl.ComponentFactory;
import publichealthcomplaint.userinterface.spec.prov.IManager;
import publichealthcomplaint.userinterface.spec.req.IFacade;

public aspect AdapterIFacadeXPI {

	/**
	 * Required by the following features: animal, drug, and special complaint.
	 */

	public pointcut callingInsertComplaint(IComplaintDt complaint):
		publichealthcomplaint.userinterface.impl.generalcomplaint.aspects.XPIComplaintMgt.callingInsertComplaint(complaint);

	int around(IComplaintDt complaint):callingInsertComplaint(complaint){
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		
		return facade.insertComplaint(complaint);
		
	}

	
	
	

	
	
	/**
	 * 
	 * Required by the following features: query information
	 * 
	 * */

	public pointcut callGetHealthUnitList():publichealthcomplaint.userinterface.impl.infrastr.aspects.XPIInfrastructureMgt.callGetHealthUnitList();
	
	
	IteratorDsk around():callGetHealthUnitList(){
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		return facade.getComplaintList();
	}

	
	/**
	 * 
	 * Required by the following features: query information
	 * 
	 * */

	public pointcut callInsert(IEmployeeDt employeeDt):publichealthcomplaint.userinterface.impl.infrastr.aspects.XPIInfrastructureMgt.callInsert(employeeDt);
	
	void around(IEmployeeDt employeeDt):callInsert(employeeDt){
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		facade.insert(employeeDt);
	}

	
	/**
	 * 
	 * Required by the following features: query information
	 * 
	 * */

	public pointcut callSearchEmployee(String login): publichealthcomplaint.userinterface.impl.infrastr.aspects.XPIInfrastructureMgt.callSearchEmployee(login);
	
	IEmployeeDt around(String login):callSearchEmployee(login){
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		return facade.searchEmployee(login);
	}

	
	/**
	 * 
	 * Required by the following features: query information
	 * 
	 * */
	public pointcut callSearchHealthUnit(int healthUnitCode):publichealthcomplaint.userinterface.impl.infrastr.aspects.XPIInfrastructureMgt.callSearchHealthUnit(healthUnitCode);
	
	IHealthUnitDt around(int healthUnitCode):callSearchHealthUnit(healthUnitCode){
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		return facade.searchHealthUnit(healthUnitCode);
	}

	
	/**
	 * 
	 * Required by the following features: query information
	 * 
	 * */
	public pointcut callUpdateEmployee(IEmployeeDt e):publichealthcomplaint.userinterface.impl.infrastr.aspects.XPIInfrastructureMgt.callUpdateEmployee(e);
	
	void around(IEmployeeDt e):callUpdateEmployee(e){
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		facade.updateEmployee(e);
	}
	
	/**
	 * 
	 * Required by the following features: query information
	 * 
	 * */
    public pointcut callUpdateHealthUnit(IHealthUnitDt unit):publichealthcomplaint.userinterface.impl.infrastr.aspects.XPIInfrastructureMgt.callUpdateHealthUnit(unit);
	
	void around(IHealthUnitDt unit):callUpdateHealthUnit(unit){
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		facade.updateHealthUnit(unit);
	}
	
	/**
	 * Required by the following features: RSS Feeds 
	 */
    public pointcut callGetRSSFile():publichealthcomplaint.userinterface.impl.feedsmgr.aspects.XPIRSSFeeds.callGetRSSFile();
	
	File around():callGetRSSFile(){
		IManager mgr = ComponentFactory.createInstance();
		IFacade facade = (IFacade) mgr.getRequiredInterface("IFacade");
		return facade.getRSSFile();
	}

}
