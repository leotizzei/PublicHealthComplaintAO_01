package publichealthcomplaint.userinterface.impl.queryinfo_util;

public class ComponentFactory {
	
	private static IManager mgr;
	
	public static IManager createInstance() {
		if( mgr == null ){
			mgr =  new Manager();
		}
	
		return mgr;
	}
}
