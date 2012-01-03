package publichealthcomplaint.infrastructuremgt.aspects;

import publichealthcomplaint.datatypes.IEmployeeDt;

public aspect XPIEmployee {

	public pointcut synchronizationPoints(IEmployeeDt employee):execution(void publichealthcomplaint.infrastructuremgt.spec.prov.IEmployeeMgt.insert(IEmployeeDt)) && args(employee);
	
}
