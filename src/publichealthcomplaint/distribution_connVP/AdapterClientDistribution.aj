package publichealthcomplaint.distribution_connVP;

import publichealthcomplaint.distribution.aspects.AAClientDistribution;
import publichealthcomplaint.userinterface.aspects.XPIUserInterfaceServlets;
public aspect AdapterClientDistribution extends AAClientDistribution {

	public pointcut distribution():XPIUserInterfaceServlets.distribution();

}
