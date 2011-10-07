package publichealthcomplaint.userinterface.impl.userinterf_connvp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IComplaintMgt;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IUpdate;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IQueryInfoMgt;


class Manager implements IManager {

	private HashMap providedInterfaces;
	private HashMap requiredInterfaces;

	Manager(){
		this.providedInterfaces = new HashMap();
		this.requiredInterfaces = new HashMap();
		
		IComplaintMgt complaintMgt = new AdapterIComplaintMgt();
		providedInterfaces.put("IComplaintMgt", complaintMgt);
		
		IUpdate updateMgt = new AdapterIUpdate();
		providedInterfaces.put("IUpdate", updateMgt);	
		
		IQueryInfoMgt query = new AdapterIQueryInfoMgt();
		providedInterfaces.put("IQueryInfoMgt", query);
		
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
