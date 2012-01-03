package publichealthcomplaint.distribution_connVP;

import javax.servlet.http.HttpServletRequest;

import publichealthcomplaint.distribution.aspects.AAServerDistribution;
import publichealthcomplaint.userinterface.aspects.XPIUserInterfaceServlets;

public aspect AdapterServerDistribution extends AAServerDistribution {

	public pointcut registering():XPIUserInterfaceServlets.callingServlets();

	public pointcut callRequest(HttpServletRequest request):XPIUserInterfaceServlets.doPostGetExec(request);
	
	
}
