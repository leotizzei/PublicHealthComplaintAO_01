package publichealthcomplaint.userinterface.impl.feedsmgr.impl;

import publichealthcomplaint.userinterface.impl.feedsmgr.spec.prov.IManager;


public class ComponentFactory {
	
	private static IManager mgr;
	
	public static IManager createInstance() {
		if( mgr == null ){
			mgr =  (IManager) new Manager();
		}
	
		return mgr;
	}
}
