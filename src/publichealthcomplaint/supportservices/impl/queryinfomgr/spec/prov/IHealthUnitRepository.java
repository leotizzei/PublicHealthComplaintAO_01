package publichealthcomplaint.supportservices.impl.queryinfomgr.spec.prov;

import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;

public interface IHealthUnitRepository {
	
	public IteratorDsk getPartialHealthUnitList() throws ObjectNotFoundException;

	public IteratorDsk getHealthUnitListBySpeciality(int codEspecialidade) throws ObjectNotFoundException;
	
	public IteratorDsk searchSpecialitiesByHealthUnit(int code) throws ObjectNotFoundException;

}
