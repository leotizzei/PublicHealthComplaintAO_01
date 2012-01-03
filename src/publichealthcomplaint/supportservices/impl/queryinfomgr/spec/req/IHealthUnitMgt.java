package publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req;

import publichealthcomplaint.datatypes.IHealthUnitDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;

public interface IHealthUnitMgt {
	
	public IteratorDsk getHealthUnitList() throws ObjectNotFoundException ;
	
	public IHealthUnitDt partialSearch(int codigo) throws ObjectNotFoundException;
	
	public IHealthUnitDt searchHealthUnit(int healthUnitCode) throws ObjectNotFoundException ;
	
	public void updateHealthUnit(IHealthUnitDt unit) throws ObjectNotFoundException, ObjectNotValidException ;

}
