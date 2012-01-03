package publichealthcomplaint.complaintmgr.aspects;

import publichealthcomplaint.complaintmgr.spec.prov.IComplaintMgt;
import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;


public aspect XPIException {
	
	
	public pointcut dateException():(call(IteratorDsk IComplaintMgt.getComplaintList()) || call(IComplaintDt IComplaintMgt.search(int))); 
	
	public pointcut objectNotValidExc():( call(int IComplaintMgt.insertComplaint(IComplaintDt)) || call(void IComplaintMgt.updateComplaint(IComplaintDt)) );
	
	
	
	public pointcut objectNotFoundExc():( 
			call(IteratorDsk IComplaintMgt.getComplaintList()) || 
			call(IComplaintDt IComplaintMgt.search(int)) ||
			call(void IComplaintMgt.updateComplaint(IComplaintDt))
			);
	
	public pointcut objectAlreadyInsertedExc():( call(int IComplaintMgt.insertComplaint(IComplaintDt)) );

}
