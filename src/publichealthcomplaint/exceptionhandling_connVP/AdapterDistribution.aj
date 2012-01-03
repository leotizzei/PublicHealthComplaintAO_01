package publichealthcomplaint.exceptionhandling_connVP;

import java.rmi.RemoteException;

import publichealthcomplaint.distribution.aspects.AAClientDistribution;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.CommunicationException;

public aspect AdapterDistribution {
	
	// Makes soft the communication exceptions in the clients
	declare soft : CommunicationException : call(* IteratorDsk+.*(..));

	// Makes soft remote exceptions raised in this aspect
	declare soft : RemoteException : within(AAClientDistribution+);
	
	
}
