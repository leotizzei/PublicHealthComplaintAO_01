package publichealthcomplaint.userinterface.impl.queryinfo.impl;

import publichealthcomplaint.userinterface.impl.queryinfo.spec.prov.IManager;




public class ComponentFactory {
	
	private static IManager mgr;
	
	public static IManager createInstance() {
		if( mgr == null ){
			mgr =  new Manager();
		}
	
		return mgr;
	}
}
