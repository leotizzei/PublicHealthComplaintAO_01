package publichealthcomplaint.userinterface.impl.userinterf_connvp;

import java.io.File;

import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.datatypes.IHealthUnitDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.userinterface.spec.req.IFacade;


public abstract aspect AdapterVP {

	
	
	/**
	 * 
	 * Required by the following features: query information
	 * 
	 * */
	public abstract IteratorDsk IFacade.getHealthUnitList();
	
	
	
	/**
	 * 
	 * Required by the following features: query information
	 * 
	 * */
	public abstract void IFacade.insert(IEmployeeDt e) ;

	
	
	/**
	 * 
	 * Required by the following features: query information
	 * 
	 * */
	public abstract IEmployeeDt IFacade.searchEmployee(String login) ;

	
	
	/**
	 * 
	 * Required by the following features: query information
	 * 
	 * */
	public abstract IHealthUnitDt IFacade.searchHealthUnit(int healthUnitCode) ;
	

	/**
	 * 
	 * Required by the following features: query information
	 * 
	 * */
	public abstract void IFacade.updateEmployee(IEmployeeDt e) ;
	

	/**
	 * 
	 * Required by the following features: query information
	 * 
	 * */
	public abstract void IFacade.updateHealthUnit(IHealthUnitDt unit) ;
	
	
	
	
	/**
	 * Required by the following features: RSS Feeds 
	 */
	public abstract File IFacade.getRSSFile() ;
	
	
	
	
}
