package publichealthcomplaint.infrastructuremgt.aspects;

import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.infrastructuremgt.spec.prov.IEmployeeMgt;

public aspect XPIEmployeeMgtEH {
	
	
	
	public pointcut handleObjectNotFoundExc():(
			call(void IEmployeeMgt.insert(IEmployeeDt)) || 
			call(void IEmployeeMgt.updateEmployee(IEmployeeDt)) ||
			call(IEmployeeDt IEmployeeMgt.searchEmployee(String)) 
	) ;
	
	public pointcut handleUpdateEntryExc():(call(void IEmployeeMgt.updateEmployee(IEmployeeDt) ));
	
	public pointcut handleObjectNotValidExc():( (call(void IEmployeeMgt.updateEmployee(IEmployeeDt) )) || (call(void IEmployeeMgt.insert(IEmployeeDt) )) );

	public pointcut handleObjectAlreadyInsertedExc():(call(void IEmployeeMgt.insert(IEmployeeDt)));
	
}
