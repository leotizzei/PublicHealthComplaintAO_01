package publichealthcomplaint.distribution.aspects;

import java.util.List;

import publichealthcomplaint.distribution.impl.ComponentFactory;
import publichealthcomplaint.distribution.spec.prov.IManager;
import publichealthcomplaint.rmiconnector.HealthWatcherFacade;


public abstract aspect AADistribution {

	/**
	 * Forces the facade to implement a remote interface, needed for RMI
	 */
	declare parents: HealthWatcherFacade implements publichealthcomplaint.distribution.aspects.IRemoteFacade;

	
	public abstract pointcut distributionCalls(List list);
	
	Object around(List list): distributionCalls(list){
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.distribution.spec.prov.IDistributionMgt dist = (publichealthcomplaint.distribution.spec.prov.IDistributionMgt) mgr.getProvidedInterface("IDistributionMgt");
		return dist.createIterator(list);
	}
	
}
