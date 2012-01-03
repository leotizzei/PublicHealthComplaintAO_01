package publichealthcomplaint.exceptionhandling_connVP;

import publichealthcomplaint.exceptionhandling.aspects.AAFacadeHandler;
import publichealthcomplaint.exceptionhandling.impl.PersistenceMechanismException;
import publichealthcomplaint.exceptionhandling.impl.TransactionException;
import publichealthcomplaint.persistence.aspects.XPIPersistenceMechanism;

public aspect AdapterPersistenceMechanism extends AAFacadeHandler {


	
	declare soft: PersistenceMechanismException: XPIPersistenceMechanism.communication(); 
	
	declare soft: TransactionException: XPIPersistenceMechanism.transaction(); 
	
	public pointcut handlerPersistenceMechanismException():XPIPersistenceMechanism.communication(); 
	
	public pointcut handlerTransactionException():XPIPersistenceMechanism.transaction(); 
	
	
}
