package publichealthcomplaint.complaintmgr.impl;

import publichealthcomplaint.complaintmgr.spec.prov.IManager;





public class ComponentFactory {
	
	private static IManager singleton = null;
	
	public static IManager createInstance() {
		if( singleton == null)
			singleton =  new Manager();
		return singleton;
	}
}
