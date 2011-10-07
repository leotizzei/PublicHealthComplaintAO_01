package publichealthcomplaint.distribution.impl;

import java.rmi.RemoteException;
import java.util.List;

import publichealthcomplaint.distribution.spec.prov.IDistributionMgt;
import publichealthcomplaint.distribution.spec.prov.IIteratorRMITargetAdapter;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.distribution.spec.prov.LocalIterator;

class FacadeDistribution implements IDistributionMgt {

	
	public IteratorDsk createIterator(List list) {
		if( list == null)
			throw new IllegalArgumentException("Null argument");
		else{
			return new ConcreteIterator(list);
		}
			
	}

	
	public IIteratorRMITargetAdapter createIteratorRMITarget(
			LocalIterator iterator, int cacheSize) {
		IIteratorRMITargetAdapter iteratorTargetRMI = null;
		try {
			iteratorTargetRMI = new IteratorRMITargetAdapter(iterator, cacheSize);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return iteratorTargetRMI;
	}


	
	public IteratorDsk createIterator(IIteratorRMITargetAdapter targetAdapter,
			LocalIterator iterator, int cacheSize) {
		return new IteratorRMISourceAdapter(targetAdapter, iterator, cacheSize);
	}


	
	public Object invoke(Object target, String methodName, Object[] params) {
		return MethodExecutor.invoke(target, methodName, params);
	}

}
