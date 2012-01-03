package publichealthcomplaint.distribution_connVP;

import java.util.List;

import publichealthcomplaint.distribution.aspects.AADistribution;
import publichealthcomplaint.infrastructuremgt.aspects.XPIDistribution;

aspect AdapterInfrastructureXPIDistribution extends AADistribution {

	public pointcut distributionCalls(List list):XPIDistribution.distributionCalls(List) && args(list);
	
}
