package publichealthcomplaint.userinterface.impl.generalcomplaint.impl;

import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.prov.IManager;





public class ComponentFactory {
	
	private static IManager mgr;
	
	public static IManager createInstance() {
		if( mgr == null ){
			mgr =  (IManager) new Manager();
		}
	
		return mgr;
	}
}
