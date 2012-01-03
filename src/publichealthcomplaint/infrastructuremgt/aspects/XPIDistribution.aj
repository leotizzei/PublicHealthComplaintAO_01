package publichealthcomplaint.infrastructuremgt.aspects;

import java.util.List;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;

public aspect XPIDistribution {
	
	public pointcut distributionCalls(List list):
		call(IteratorDsk publichealthcomplaint.infrastructuremgt.spec.req.IDistributionMgt.createIterator(List)) &&
		args(list) && within(publichealthcomplaint.infrastructuremgt.impl.*);
	
	/*IteratorDsk around(List list):distributionCalls(list){
		return null;
	}*/
	

}
