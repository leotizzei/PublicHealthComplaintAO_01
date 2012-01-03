package publichealthcomplaint.infrastructuremgt.impl;

import publichealthcomplaint.infrastructuremgt.spec.prov.IManager;






public class ComponentFactory {
	
	private static IManager singleton = null;
	
	public static IManager createInstance() {
		if( singleton == null)
			singleton =  new Manager();
		return singleton;
	}
}
