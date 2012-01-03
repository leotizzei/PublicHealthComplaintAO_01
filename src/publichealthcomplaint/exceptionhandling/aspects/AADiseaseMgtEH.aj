package publichealthcomplaint.exceptionhandling.aspects;

import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;



public abstract aspect AADiseaseMgtEH {

	public abstract pointcut handleObjectNotFoundExc();
	
	after() throwing(ObjectNotFoundException e):handleObjectNotFoundExc(){
		System.err.println(e.getLocalizedMessage());
		e.printStackTrace();
	}
	
}
