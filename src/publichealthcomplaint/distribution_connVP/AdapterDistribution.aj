package publichealthcomplaint.distribution_connVP;

import java.util.List;

import publichealthcomplaint.complaintmgr.aspects.XPIDistribution;
import publichealthcomplaint.distribution.aspects.AADistribution;

public aspect AdapterDistribution extends AADistribution{
	
	
	public pointcut distributionCalls(List list):XPIDistribution.distributionCalls(List) && args(list);
		
	
	
}
