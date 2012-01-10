package publichealthcomplaint.rmiconnector;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;





class Manager implements IManager {

	private HashMap providedInterfaces;
	private HashMap requiredInterfaces;

	Manager(){
		System.out.println("[rmiconnector.Manager] Constructor");
		this.providedInterfaces = new HashMap();
		this.requiredInterfaces = new HashMap();
		
		this.providedInterfaces.put("IFacade", HealthWatcherFacade.getInstance());
	}




	public Object getProvidedInterface(String name) {
		if( name != null)
			return this.providedInterfaces.get(name);
		else{
			System.err.println(this.getClass().getCanonicalName()+":Invalid argument");
			return null;

		}
	}


	public String[] listProvidedInterfaces() {
		return convertListToArray(this.providedInterfaces.values());
	}


	public String[] listRequiredInterfaces() {
		return convertListToArray(this.requiredInterfaces.values());
	}


	public void setRequiredInterface(String name, Object facade) {
		if( (name != null) && ( facade != null)){
			System.out.println("[rmiconnector.Manager.setRequiredInterface()] name ="+name+" object="+facade.toString());
			this.requiredInterfaces.put(name, facade);
		}
		else{
			if( name != null )
				System.out.println("[rmiconnector.Manager.setRequiredInterface()]  Interface name = "+name);
			if( facade != null)
				System.out.println("[rmiconnector.Manager.setRequiredInterface()]  Interface name = "+name);
			throw new IllegalArgumentException("Null argument");
		}
	}

	private String[] convertListToArray(Collection stringCollection){
		String[] stringArray = new String[stringCollection.size()];
		int i=0;
		for (Iterator iter = stringCollection.iterator();iter.hasNext();){
			stringArray[i] = (String)iter.next();
			i++;
		}
		return stringArray;
	}



	public Object getRequiredInterface(String name) {
		if( name != null)
			return this.requiredInterfaces.get(name);
		else{
			System.err.println("Null argument!");
			return null;
		}
	}




	private void setProvidedInterface(String name, Object facade) {
		this.providedInterfaces.put(name, facade);

	}





	public Object getAdapter(String requiredInterfaceName) {
		return this.requiredInterfaces.get(requiredInterfaceName);
	}


}
