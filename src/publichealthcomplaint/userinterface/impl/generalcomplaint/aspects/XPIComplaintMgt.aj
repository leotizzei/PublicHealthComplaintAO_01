package publichealthcomplaint.userinterface.impl.generalcomplaint.aspects;

import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IComplaintMgt;
import publichealthcomplaint.datatypes.IComplaintDt;

public aspect XPIComplaintMgt {
	
	public pointcut callingInsertComplaint(IComplaintDt complaint):call(int IComplaintMgt.insertComplaint(IComplaintDt))
	&& args(complaint);

}
