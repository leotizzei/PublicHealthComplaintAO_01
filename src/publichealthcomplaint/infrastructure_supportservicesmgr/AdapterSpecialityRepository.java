package publichealthcomplaint.infrastructure_supportservicesmgr;

import publichealthcomplaint.datatypes.IMedicalSpecialityDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req.ISpecialityRepository;

 class AdapterSpecialityRepository implements ISpecialityRepository {

	public IteratorDsk getSpecialityList() throws ObjectNotFoundException {
		IManager manager = ComponentFactory.createInstance();
		publichealthcomplaint.infrastructuremgt.spec.prov.ISpecialityRepository specialityRepository = (publichealthcomplaint.infrastructuremgt.spec.prov.ISpecialityRepository) manager.getRequiredInterface("ISpecialityRepository");
		return specialityRepository.getSpecialityList();
	}

	
	public IMedicalSpecialityDt search(int code) throws ObjectNotFoundException {
		IManager manager = ComponentFactory.createInstance();
		publichealthcomplaint.infrastructuremgt.spec.prov.ISpecialityRepository specialityRepository = (publichealthcomplaint.infrastructuremgt.spec.prov.ISpecialityRepository) manager.getRequiredInterface("ISpecialityRepository");
		return specialityRepository.search(code);
	}

}
