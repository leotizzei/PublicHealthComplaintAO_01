package publichealthcomplaint.userinterface.impl.generalcomplaint.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.prov.IGeneralComplaintMgt;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.prov.IManager;





class Manager implements IManager {

	private HashMap providedInterfaces;
	private HashMap requiredInterfaces;

	Manager(){
		this.providedInterfaces = new HashMap();
		this.requiredInterfaces = new HashMap();
		
		IGeneralComplaintMgt generalComp = new FacadeGeneralComplaint();
		providedInterfaces.put("IGeneralComplaintMgt", generalComp);
		
		
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
		
		if( (name != null) && (facade != null) )
			System.out.println("[Manager:setRequiredInterface] name="+name+" facade="+facade.toString());
		else{
			if ( name != null )
				System.out.println("[Manager:setRequiredInterface] name="+name+" facade = null");
			else
				System.out.println("[Manager:setRequiredInterface] name= null facade = "+ facade.toString());
		}
			
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
			return requiredInterfaces.get(name);
		else{
			System.err.println("Null argument!");
			return null;
		}
	}


	
	public void setProvidedInterface(String name, Object facade) {
		this.providedInterfaces.put(name, facade);
		
	}


}
