package publichealthcomplaint.infrastructure_supportservicesmgr;

import publichealthcomplaint.datatypes.IHealthUnitDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req.IHealthUnitMgt;

class AdapterHealthUnitMgt implements IHealthUnitMgt {

	
	public IteratorDsk getHealthUnitList() {
		IManager manager = ComponentFactory.createInstance();
		publichealthcomplaint.infrastructuremgt.spec.prov.IHealthUnitMgt healthUnitMgt = (publichealthcomplaint.infrastructuremgt.spec.prov.IHealthUnitMgt) manager.getRequiredInterface("IHealthUnitMgt");
		return healthUnitMgt.getHealthUnitList();
	}

	
	public IHealthUnitDt partialSearch(int codigo) {
		IManager manager = ComponentFactory.createInstance();
		publichealthcomplaint.infrastructuremgt.spec.prov.IHealthUnitMgt healthUnitMgt = (publichealthcomplaint.infrastructuremgt.spec.prov.IHealthUnitMgt) manager.getRequiredInterface("IHealthUnitMgt");
		return healthUnitMgt.partialSearch(codigo);
	}


	public IHealthUnitDt searchHealthUnit(int healthUnitCode) {
		IManager manager = ComponentFactory.createInstance();
		publichealthcomplaint.infrastructuremgt.spec.prov.IHealthUnitMgt healthUnitMgt = (publichealthcomplaint.infrastructuremgt.spec.prov.IHealthUnitMgt) manager.getRequiredInterface("IHealthUnitMgt");
		return healthUnitMgt.searchHealthUnit(healthUnitCode);
	}

	
	public void updateHealthUnit(IHealthUnitDt unit) {
		IManager manager = ComponentFactory.createInstance();
		publichealthcomplaint.infrastructuremgt.spec.prov.IHealthUnitMgt healthUnitMgt = (publichealthcomplaint.infrastructuremgt.spec.prov.IHealthUnitMgt) manager.getRequiredInterface("IHealthUnitMgt");
		healthUnitMgt.updateHealthUnit(unit);

	}

}
