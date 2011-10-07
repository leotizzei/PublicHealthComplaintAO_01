package publichealthcomplaint.userinterface.impl.userinterf_connvp;

public interface IManager {
	
	public Object getProvidedInterface(String name);
	
	public Object getRequiredInterface(String name);
	
	public void setRequiredInterface(String name, Object facade);
	
	public void setProvidedInterface(String name, Object facade);
	
	public String[] listRequiredInterfaces();
	
	public String[] listProvidedInterfaces();
}
