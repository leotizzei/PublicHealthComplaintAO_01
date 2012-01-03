package publichealthcomplaint.persistence.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import publichealthcomplaint.persistence.spec.prov.IManager;
import publichealthcomplaint.persistence.spec.prov.IPersistenceMechanism;





class Manager implements IManager {

	private HashMap providedInterfaces;
	private HashMap requiredInterfaces;

	Manager(){
		this.providedInterfaces = new HashMap();
		this.requiredInterfaces = new HashMap();
		IPersistenceMechanism persistenceMech = PersistenceMechanism.getInstance();
		if(persistenceMech == null)
			System.out.println("Manager constructor persistenceMech is null");
		else
			System.out.println("Manager constructor persistenceMech is NOT null");
		this.providedInterfaces.put("IPersistenceMechanism", persistenceMech);
	}


	public Object getProvidedInterface(String name) {
		if( name != null){
			Object res = this.providedInterfaces.get(name);
			System.out.println("[Manager:getProvidedInterfaces()]"+res+ " = providedInterfaces("+name+")");
			return res;
		}
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
		this.requiredInterfaces.put(name, facade);

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
			
			throw new IllegalArgumentException("Null argument!");
			
		}
	}


	
	public void setProvidedInterface(String name, Object facade) {
		this.providedInterfaces.put(name, facade);
		
	}


}
