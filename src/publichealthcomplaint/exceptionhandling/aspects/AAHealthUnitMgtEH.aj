package publichealthcomplaint.exceptionhandling.aspects;

import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;

public abstract aspect AAHealthUnitMgtEH {

	public abstract pointcut handleObjectNotFoundExc();
	
	public abstract pointcut handleObjectNotValidExc();
	
	after() throwing(ObjectNotFoundException e):handleObjectNotFoundExc(){
		System.err.println(e.getLocalizedMessage());
		e.printStackTrace();
	}
	
    after() throwing(ObjectNotValidException e):handleObjectNotValidExc(){
    	System.err.println(e.getLocalizedMessage());
		e.printStackTrace();
	}
}
