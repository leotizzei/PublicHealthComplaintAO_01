package publichealthcomplaint.rmiconnector;

public interface IManager {
	
	public Object getProvidedInterface(String name);
	
	public Object getRequiredInterface(String name);
	
	public void setRequiredInterface(String name, Object facade);
	
	
	
	public String[] listRequiredInterfaces();
	
	public String[] listProvidedInterfaces();
	
	public Object getAdapter(String requiredInterfaceName);
}
