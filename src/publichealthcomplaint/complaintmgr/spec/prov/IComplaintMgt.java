package publichealthcomplaint.complaintmgr.spec.prov;

import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.InvalidDateException;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;

public interface IComplaintMgt {
	
	public IteratorDsk getComplaintList() throws ObjectNotFoundException, InvalidDateException;
	
	public int insertComplaint(IComplaintDt complaint) throws ObjectAlreadyInsertedException, ObjectNotValidException;
	
	public IComplaintDt search(int complaint) throws ObjectNotFoundException, InvalidDateException;

	public void updateComplaint(IComplaintDt complaint) throws ObjectNotFoundException, ObjectNotValidException;

}
