package publichealthcomplaint.exceptionhandling_connVP;


import publichealthcomplaint.complaintmgr.spec.prov.IComplaintMgt;
import publichealthcomplaint.exceptionhandling.impl.*;
import publichealthcomplaint.infrastructuremgt.spec.prov.IHealthUnitMgt;
import publichealthcomplaint.infrastructuremgt.spec.prov.ISpecialityRepository;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.prov.IDiseaseRepository;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.prov.IHealthUnitRepository;
import publichealthcomplaint.persistence.spec.prov.IPersistenceMechanism;
/**
 * This aspect intercepts Record creations and inserts Records with the proper repository
 * instance, depending if it is persistent or not
 */
aspect HWDataCollection {
	
		
	
   
   
    declare soft: RepositoryException : execution(* IDiseaseRepository.*(..));
    
    declare soft: RepositoryException : execution(* ISpecialityRepository.*(..));
    
    declare soft: RepositoryException : execution(* IHealthUnitRepository.*(..));
    
    declare soft: RepositoryException : execution(* IHealthUnitMgt.*(..));
    
    declare soft: RepositoryException : execution(* IComplaintMgt.*(..));
  
	
    declare soft: TransactionException : 
        call(void IPersistenceMechanism.beginTransaction())    || 
        call(void IPersistenceMechanism.rollbackTransaction()) || 
        call(void IPersistenceMechanism.commitTransaction());
}
