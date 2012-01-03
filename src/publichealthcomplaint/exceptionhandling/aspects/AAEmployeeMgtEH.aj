package publichealthcomplaint.exceptionhandling.aspects;

import publichealthcomplaint.exceptionhandling.impl.*;


public abstract aspect AAEmployeeMgtEH {
	
	public abstract pointcut handleObjectNotFoundExc();
	
	public abstract pointcut handleUpdateEntryExc();
	
	public abstract pointcut handleObjectNotValidExc();
	
	public abstract pointcut handleObjectAlreadyInsertedExc();
	
	after() throwing(ObjectNotFoundException e):handleObjectNotFoundExc(){
		e.printStackTrace();
	}

    after() throwing(ObjectNotFoundException e):handleUpdateEntryExc(){
    	e.printStackTrace();
	}

    after() throwing(ObjectNotFoundException e):handleObjectNotValidExc(){
    	e.printStackTrace();
    }
    
    after() throwing(ObjectAlreadyInsertedException e):handleObjectAlreadyInsertedExc(){
    	e.printStackTrace();
    }
}
