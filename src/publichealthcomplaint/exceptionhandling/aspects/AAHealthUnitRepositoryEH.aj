package publichealthcomplaint.exceptionhandling.aspects;

import publichealthcomplaint.exceptionhandling.impl.*;


public abstract aspect AAHealthUnitRepositoryEH {

	public abstract pointcut handleObjectNotFoundExc();
	
	
	after() throwing (ObjectNotFoundException e):handleObjectNotFoundExc(){
		System.err.println(e.getLocalizedMessage());
		e.printStackTrace();
	}
}
