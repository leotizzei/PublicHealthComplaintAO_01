package publichealthcomplaint.exceptionhandling.aspects;


import publichealthcomplaint.exceptionhandling.impl.InvalidDateException;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;



public abstract aspect AAComplaintMgtEH {
	
    public abstract pointcut dateException(); 
	
	public abstract pointcut objectNotValidExc();
	
	public abstract pointcut objectNotFoundExc(); 

	public abstract pointcut objectAlreadyInsertedExc();
	
	after() throwing (InvalidDateException e):dateException(){
		System.err.println(e.getLocalizedMessage());
		e.printStackTrace();
	}
	
	after() throwing (ObjectNotValidException e):objectNotValidExc(){
		System.err.println(e.getLocalizedMessage());
		e.printStackTrace();
	}
	
	after() throwing (ObjectNotFoundException e):objectNotFoundExc(){
		System.err.println(e.getLocalizedMessage());
		e.printStackTrace();
	}


	after() throwing (ObjectAlreadyInsertedException e):objectAlreadyInsertedExc(){
		System.err.println(e.getLocalizedMessage());
		e.printStackTrace();
	}
}
