package publichealthcomplaint.concurrency_connVP;

import publichealthcomplaint.concurrency.aspects.AASyncManager;
import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.infrastructuremgt.aspects.XPIEmployee;

public aspect AdapterEmployee extends AASyncManager {

	public pointcut synchronizationPoints(IEmployeeDt employee):XPIEmployee.synchronizationPoints(IEmployeeDt) && args(employee);

}
