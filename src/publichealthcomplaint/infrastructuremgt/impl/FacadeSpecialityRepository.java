package publichealthcomplaint.infrastructuremgt.impl;

import publichealthcomplaint.datatypes.IMedicalSpecialityDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.infrastructuremgt.spec.prov.ISpecialityRepository;

class FacadeSpecialityRepository implements ISpecialityRepository {

	private SpecialityRepositoryRDB specialityRep;
	
	FacadeSpecialityRepository() {
		this.specialityRep = new SpecialityRepositoryRDB();
	}

	public IteratorDsk getSpecialityList() throws ObjectNotFoundException {
		return this.specialityRep.getSpecialityList();
	}
	
	public IMedicalSpecialityDt search(int code) throws ObjectNotFoundException {
		return this.specialityRep.search(code);
	}


}
