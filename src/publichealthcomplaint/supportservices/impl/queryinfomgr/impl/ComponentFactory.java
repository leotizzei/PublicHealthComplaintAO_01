package publichealthcomplaint.supportservices.impl.queryinfomgr.impl;

import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.prov.IManager;



public class ComponentFactory {
	
	private static IManager singleton = null;
	
	public static IManager createInstance() {
		if( singleton == null)
			singleton =  new Manager();
		return singleton;
	}
}
