package publichealthcomplaint.concurrency_connVP;

import publichealthcomplaint.complaintmgr.aspects.XPIComplaintMgt;
import publichealthcomplaint.concurrency.aspects.AATimestamp;
import publichealthcomplaint.concurrency.aspects.ITimestamp;
import publichealthcomplaint.concurrency.impl.Timestamp;
import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.complaintmgr.spec.prov.IComplaintMgt;
import publichealthcomplaint.infrastructuremgt.spec.prov.IEmployeeMgt; 
import publichealthcomplaint.datatypes.Complaint;

public privileged aspect AdapterComplaint extends AATimestamp {
	
	/**
	 * This aspect synchronizes execution of all methods of classes declared synchronized 
	 * classes (in this case, Employees and Complaints)
	 */
	declare parents: Complaint extends Timestamp;
    declare parents: IComplaintDt extends ITimestamp;

	private interface SynchronizedClasses {};

	declare parents: IEmployeeMgt || IComplaintMgt extends SynchronizedClasses;
	
	Object around(Object o): this(o) && execution(* SynchronizedClasses+.*(..)) {
		synchronized(o) {
			return proceed(o);			
		}
	}

	public pointcut insertion(IComplaintDt complaint):XPIComplaintMgt.insertion(IComplaintDt) && args(complaint);

	public pointcut search():XPIComplaintMgt.search();
	
	public pointcut update(IComplaintDt complaint):XPIComplaintMgt.update(IComplaintDt) && args(complaint);

}
