package publichealthcomplaint.complaintmgr.aspects;

import java.util.List;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;

public aspect XPIDistribution {
	
	public pointcut distributionCalls(List list):
		call(IteratorDsk publichealthcomplaint.complaintmgr.spec.req.IDistributionMgt.createIterator(List)) &&
		args(list) && within(publichealthcomplaint.complaintmgr.impl.*);

}
