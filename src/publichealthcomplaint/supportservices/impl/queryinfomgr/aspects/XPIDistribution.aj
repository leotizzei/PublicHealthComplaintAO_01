package publichealthcomplaint.supportservices.impl.queryinfomgr.aspects;

import java.util.List;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req.IDistributionMgt;

public aspect XPIDistribution {
	
	public pointcut distributionCalls(List list):
		call(IteratorDsk IDistributionMgt.createIterator(List)) &&
		args(list) && within(publichealthcomplaint.supportservices.impl.queryinfomgr.impl.*);
	
	

}
