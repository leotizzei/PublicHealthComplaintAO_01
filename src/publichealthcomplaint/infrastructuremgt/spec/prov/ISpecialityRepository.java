package publichealthcomplaint.infrastructuremgt.spec.prov;

import publichealthcomplaint.datatypes.IMedicalSpecialityDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;

public interface ISpecialityRepository {

	
	

	public IteratorDsk getSpecialityList() throws ObjectNotFoundException;

	public IMedicalSpecialityDt search(int code) throws ObjectNotFoundException;

}
