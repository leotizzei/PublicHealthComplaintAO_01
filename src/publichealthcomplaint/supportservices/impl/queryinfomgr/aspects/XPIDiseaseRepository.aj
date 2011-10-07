package publichealthcomplaint.supportservices.impl.queryinfomgr.aspects;

import publichealthcomplaint.datatypes.IDiseaseDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.prov.IDiseaseRepository;

public aspect XPIDiseaseRepository {
	
	

	
	public pointcut handleObjectNotFoundExc(): 
		(call(IDiseaseDt IDiseaseRepository.search(int)) ||
				call(IteratorDsk IDiseaseRepository.getDiseaseTypeList()));
	
}
