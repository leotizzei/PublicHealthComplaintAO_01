package publichealthcomplaint.infrastructuremgt.spec.req;

import java.util.List;

import publichealthcomplaint.distribution.spec.prov.IteratorDsk;

public interface IDistributionMgt {

	public IteratorDsk createIterator(List list);
	
}
