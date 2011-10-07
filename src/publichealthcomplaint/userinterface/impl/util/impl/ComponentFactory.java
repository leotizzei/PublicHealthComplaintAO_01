package publichealthcomplaint.userinterface.impl.util.impl;

import publichealthcomplaint.userinterface.impl.util.spec.prov.IManager;





public class ComponentFactory {
	
	private static IManager mgr;
	
	public static IManager createInstance() {
		if( mgr == null ){
			mgr =  new Manager();
		}
	
		return mgr;
	}
}
