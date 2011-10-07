package publichealthcomplaint.complaintmgr.aspects;


import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.complaintmgr.spec.prov.IComplaintMgt;


public aspect XPIComplaintMgt {
	
	public pointcut insertion(IComplaintDt complaint): execution(int IComplaintMgt.insertComplaint(IComplaintDt)) && args(complaint);
	
	public pointcut search():call(IComplaintDt IComplaintMgt.search(int));
	
	public pointcut update(IComplaintDt complaint):execution(void IComplaintMgt.updateComplaint(IComplaintDt)) && args(complaint);

}
