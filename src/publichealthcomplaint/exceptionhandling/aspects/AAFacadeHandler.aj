package publichealthcomplaint.exceptionhandling.aspects;

import publichealthcomplaint.exceptionhandling.impl.PersistenceMechanismException;
import publichealthcomplaint.exceptionhandling.impl.TransactionException;


public abstract aspect AAFacadeHandler {
	
	
	public abstract pointcut handlerPersistenceMechanismException(); 
		
	public abstract pointcut handlerTransactionException(); 
	
	
	after() throwing (PersistenceMechanismException e):handlerPersistenceMechanismException(){
		System.out.println("PersistenceMechanismException"+e.getLocalizedMessage());
		e.printStackTrace();
		
	}
	
	after() throwing (TransactionException e):handlerTransactionException(){
		System.out.println("TransactionExceptionException"+e.getLocalizedMessage());
		e.printStackTrace();
		
	}
	
	
}
