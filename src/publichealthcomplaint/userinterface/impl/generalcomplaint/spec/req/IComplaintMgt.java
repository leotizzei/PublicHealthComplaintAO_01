package publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req;

import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;

public interface IComplaintMgt {
	
	public int insertComplaint(IComplaintDt complaint) throws ObjectAlreadyInsertedException,	ObjectNotValidException;

}
