package publichealthcomplaint.distribution_connVP;

import java.util.List;
import publichealthcomplaint.distribution.aspects.AADistribution;
import publichealthcomplaint.supportservices.impl.queryinfomgr.aspects.XPIDistribution;

public aspect AdapterSupportDistribution extends AADistribution{
	
	
	public pointcut distributionCalls(List list):XPIDistribution.distributionCalls(List) && args(list);
		
		
	
}
