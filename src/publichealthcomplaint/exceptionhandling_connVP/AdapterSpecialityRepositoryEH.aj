package publichealthcomplaint.exceptionhandling_connVP;


import publichealthcomplaint.exceptionhandling.aspects.AASpecialityRepositoryEH;
//import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.infrastructuremgt.aspects.XPISpecialityRepositoryEH;


public abstract aspect AdapterSpecialityRepositoryEH extends
		AASpecialityRepositoryEH {
	
	
	public pointcut handleObjectNotFoundExc():XPISpecialityRepositoryEH.handleObjectNotFoundExc();

}
