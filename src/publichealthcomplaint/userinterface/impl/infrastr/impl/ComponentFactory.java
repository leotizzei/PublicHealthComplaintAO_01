package publichealthcomplaint.userinterface.impl.infrastr.impl;

import publichealthcomplaint.userinterface.impl.infrastr.spec.prov.IManager;






public class ComponentFactory {
	
	private static IManager mgr;
	
	public static IManager createInstance() {
		if( mgr == null ){
			mgr =  new Manager();
		}
	
		return mgr;
	}
}
