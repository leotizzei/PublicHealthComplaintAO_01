package publichealthcomplaint.distribution.spec.prov;

import java.util.List;

public interface IDistributionMgt {

	public IteratorDsk createIterator(List list);
	
	public IteratorDsk createIterator(IIteratorRMITargetAdapter targetAdapter,LocalIterator iterator, int cacheSize);
	
	public IIteratorRMITargetAdapter createIteratorRMITarget(LocalIterator iterator, int cacheSize);
	
	public Object invoke(Object target, String methodName, Object[] params);
	
	
}
