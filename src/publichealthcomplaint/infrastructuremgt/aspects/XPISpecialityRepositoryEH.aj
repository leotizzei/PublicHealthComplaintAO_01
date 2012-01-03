package publichealthcomplaint.infrastructuremgt.aspects;

import publichealthcomplaint.datatypes.IMedicalSpecialityDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.infrastructuremgt.spec.prov.ISpecialityRepository;


public aspect XPISpecialityRepositoryEH {
	
	
	declare soft:ObjectNotFoundException:(call (IteratorDsk ISpecialityRepository.getSpecialityList()));//XPISpecialityRepositoryEH.handleObjectNotFoundExc(); 
	
	public pointcut handleObjectNotFoundExc():(call (IteratorDsk ISpecialityRepository.getSpecialityList()));
			
	
	public pointcut handleObjectNotFoundExc2():(call (IMedicalSpecialityDt ISpecialityRepository.search(int))) ;
	
	

	
}
