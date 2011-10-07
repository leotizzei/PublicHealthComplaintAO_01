package publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req;

import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;

public interface IUpdate {
	
	public IteratorDsk getComplaintList() throws ObjectNotFoundException;
	
	public IComplaintDt searchComplaint(int code) throws ObjectNotFoundException;
	
	public void updateComplaint(IComplaintDt complaint) throws ObjectNotFoundException, ObjectNotValidException;

}
