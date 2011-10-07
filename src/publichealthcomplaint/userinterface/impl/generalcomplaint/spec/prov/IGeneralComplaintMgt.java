package publichealthcomplaint.userinterface.impl.generalcomplaint.spec.prov;

import javax.servlet.http.HttpServletRequest;

import publichealthcomplaint.datatypes.IComplaintDt;

public interface IGeneralComplaintMgt {
	
	public void readGeneralComplaintData(HttpServletRequest request, IComplaintDt complaint);

}
