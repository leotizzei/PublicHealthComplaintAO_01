package publichealthcomplaint.supportservices.impl.queryinfomgr.impl;

import publichealthcomplaint.datatypes.IDiseaseDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.prov.IDiseaseRepository;


class FacadeDiseaseRepository implements IDiseaseRepository {

	private DiseaseTypeRepositoryRDB diseaseRep;
	
	
	
	FacadeDiseaseRepository() {
		
		this.diseaseRep = new DiseaseTypeRepositoryRDB();
	}
	
	
	
	public IteratorDsk getDiseaseTypeList() throws ObjectNotFoundException {
		return this.diseaseRep.getDiseaseTypeList();
	}

	

	
	public IDiseaseDt search(int code) throws ObjectNotFoundException {
		return this.diseaseRep.search(code);
	}

	

}
