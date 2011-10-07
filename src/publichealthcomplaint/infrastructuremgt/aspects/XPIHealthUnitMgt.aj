package publichealthcomplaint.infrastructuremgt.aspects;

import publichealthcomplaint.datatypes.IHealthUnitDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.infrastructuremgt.spec.prov.IHealthUnitMgt;

public aspect XPIHealthUnitMgt {
	
	
	
	public pointcut handleObjectNotFoundExc(): ( 
			call(IteratorDsk IHealthUnitMgt.getHealthUnitList()) ||
			call(IHealthUnitDt IHealthUnitMgt.partialSearch(int)) ||
			call(IHealthUnitDt IHealthUnitMgt.searchHealthUnit(int)) || 
			call(void IHealthUnitMgt.updateHealthUnit(IHealthUnitDt))
			);
	
	public pointcut handleObjectNotValidExc(): call(void IHealthUnitMgt.updateHealthUnit(IHealthUnitDt));
	
}
