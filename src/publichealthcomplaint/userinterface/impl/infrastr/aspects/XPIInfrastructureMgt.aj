package publichealthcomplaint.userinterface.impl.infrastr.aspects;

import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.datatypes.IHealthUnitDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.userinterface.impl.infrastr.spec.req.IInfrastructureMgt;

public aspect XPIInfrastructureMgt {

	
	public pointcut callGetHealthUnitList():call(IteratorDsk IInfrastructureMgt.getHealthUnitList() );
	
	public pointcut callInsert(IEmployeeDt employeeDt):call(void IInfrastructureMgt.insert(IEmployeeDt) ) && args(employeeDt);
	
	public pointcut callSearchEmployee(String login):call(IEmployeeDt IInfrastructureMgt.searchEmployee(String)) && args(login);
	
	public pointcut callSearchHealthUnit(int healthUnitCode):call (IHealthUnitDt IInfrastructureMgt.searchHealthUnit(int)) && args(healthUnitCode);
	
	public pointcut callUpdateEmployee(IEmployeeDt e):call(void IInfrastructureMgt.updateEmployee(IEmployeeDt)) && args(e);
	
	public pointcut callUpdateHealthUnit(IHealthUnitDt unit):call(void IInfrastructureMgt.updateHealthUnit(IHealthUnitDt)) && args(unit);
	
}
