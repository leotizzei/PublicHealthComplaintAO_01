package publichealthcomplaint.infrastructuremgt.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import publichealthcomplaint.infrastructuremgt.spec.prov.IEmployeeMgt;
import publichealthcomplaint.infrastructuremgt.spec.prov.IHealthUnitMgt;
import publichealthcomplaint.infrastructuremgt.spec.prov.IManager;
import publichealthcomplaint.infrastructuremgt.spec.prov.ISpecialityRepository;





class Manager implements IManager {

	private HashMap providedInterfaces;
	private HashMap requiredInterfaces;

	Manager(){
		this.providedInterfaces = new HashMap();
		this.requiredInterfaces = new HashMap();
		
		IEmployeeMgt employee = new FacadeEmployeeMgt();
		this.providedInterfaces.put("IEmployeeMgt", employee);
		
		ISpecialityRepository specRep = new FacadeSpecialityRepository();
		this.providedInterfaces.put("ISpecialityRepository", specRep);
		
		IHealthUnitMgt healthUnitMgt = new FacadeHealthUnitMgt(this);
		this.providedInterfaces.put("IHealthUnitMgt", healthUnitMgt);
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

	
	public Object getProvidedInterface(String name) {
		if( name != null)
			return this.providedInterfaces.get(name);
		else{
			System.err.println(this.getClass().getCanonicalName()+":Invalid argument");
			return null;

		}
	}

	
	public Object getRequiredInterface(String name) {
		if( name != null)
			return this.requiredInterfaces.get(name);
		else{
			System.err.println("Null argument!");
			return null;
		}
	}

	
	public String[] listProvidedInterfaces() {
		return convertListToArray(this.providedInterfaces.values());
	}

	public String[] listRequiredInterfaces() {
		return convertListToArray(this.requiredInterfaces.values());
	}


	
	public void setProvidedInterface(String name, Object facade) {
		this.providedInterfaces.put(name, facade);
		
	}


	
	public void setRequiredInterface(String name, Object facade) {
		this.requiredInterfaces.put(name, facade);

	}


}
