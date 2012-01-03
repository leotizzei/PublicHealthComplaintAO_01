package publichealthcomplaint.persistence.impl;

import publichealthcomplaint.persistence.spec.prov.IManager;







public class ComponentFactory {
	
	private static IManager mgr;
	
	public static IManager createInstance() {
		if( mgr == null ){
			mgr =  new Manager();
		}
	
		return mgr;
	}
}
