package publichealthcomplaint.concurrency.aspects;

import publichealthcomplaint.concurrency.impl.ComponentFactory;
import publichealthcomplaint.concurrency.spec.prov.IConcurrencyMgt;
import publichealthcomplaint.concurrency.spec.prov.IManager;
import publichealthcomplaint.datatypes.IEmployeeDt;

public abstract aspect AASyncManager {
	

	public abstract pointcut synchronizationPoints(IEmployeeDt employee);
	
	before(IEmployeeDt employee) : synchronizationPoints(employee) {
		IManager mgr = ComponentFactory.createInstance();
		IConcurrencyMgt concurrencyMgr = (IConcurrencyMgt) mgr.getProvidedInterface("IConcurrencyMgt");
		concurrencyMgr.beginExecution(employee.getLogin());
	}
	
	before(IEmployeeDt employee) : synchronizationPoints(employee) {
		IManager mgr = ComponentFactory.createInstance();
		IConcurrencyMgt concurrencyMgr = (IConcurrencyMgt) mgr.getProvidedInterface("IConcurrencyMgt");
		concurrencyMgr.endExecution(employee);
	
	}

}
