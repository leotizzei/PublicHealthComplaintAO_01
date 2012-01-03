package publichealthcomplaint.distribution_connVP;


import java.util.List;
import publichealthcomplaint.complaintmgr.aspects.XPIDistribution;
import publichealthcomplaint.distribution.aspects.AADistribution;

aspect AdapterInfraDistribution extends AADistribution {

	public pointcut distributionCalls(List list):XPIDistribution.distributionCalls(List) && args(list);

}
