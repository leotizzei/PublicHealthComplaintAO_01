package publichealthcomplaint.complaintmgr.impl;

import publichealthcomplaint.complaintmgr.spec.prov.IComplaintMgt;
import publichealthcomplaint.complaintmgr.spec.prov.IManager;
import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.InvalidDateException;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;

class FacadeComplaintMgt implements IComplaintMgt {

	ComplaintRepositoryRDB complaintRep;
	
	FacadeComplaintMgt(IManager mgr) {
		this.complaintRep = new ComplaintRepositoryRDB(mgr);
	}
	
	public int insertComplaint(IComplaintDt complaint)	throws ObjectAlreadyInsertedException, ObjectNotValidException {
		
		if( complaint != null){
			return this.complaintRep.insert(complaint);
		}
		System.err.println("It was not possible to insert the new complaint.");
	    return -1;
	}

	
	public void updateComplaint(IComplaintDt complaint)
			throws ObjectNotFoundException, ObjectNotValidException {
	
		if( complaint != null){
			this.complaintRep.update(complaint);
			System.out.println("[HealthWatcherFacade:updateComplaint()] the complaint was successfully updated...");
		}
		else{
			System.err.println("Complaint is null");
			throw new IllegalArgumentException("Null argument");
		}
		
	}

	
	public IteratorDsk getComplaintList() throws ObjectNotFoundException, InvalidDateException {
	
		return complaintRep.getComplaintList();
	}

	
	public IComplaintDt search(int complaint) throws ObjectNotFoundException, InvalidDateException {
		System.out.println("[FacadeComplaintMgt:search("+complaint+")]");
		return complaintRep.search(complaint);
	}

	


}
