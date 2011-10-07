package publichealthcomplaint.distribution.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import publichealthcomplaint.distribution.spec.prov.IIteratorRMITargetAdapter;
import publichealthcomplaint.distribution.spec.prov.LocalIterator;


class IteratorRMITargetAdapter extends UnicastRemoteObject implements	IIteratorRMITargetAdapter {

	private LocalIterator iterator;

	private int cacheSize;

	public IteratorRMITargetAdapter(LocalIterator i, int cacheSize) throws RemoteException {

		/*  better to use this... in thesis.
		 public IteratorRMITargetAdapter(Iterator i, int cacheSize)
		 throws RemoteException {        
		 iterator = (LocalIterator)  i;
		 */

		iterator = i;
		this.cacheSize = cacheSize;

		/*
		 This registration could be done for robustness
		 Naming.rebind("/Iterator", this);

		 */
	}

	/* correction: CommunicationException should be thrown to avoid this  
	 Iterator/LocalIterator problem. We would use Iterator everywhere and no exception 
	 handling would be needed.
	 */
	public Object[] getNext() throws RemoteException /*,CommunicationException*/{

		Object[] cache = new Object[cacheSize];

		// System.out.println("Cache built at server");

		for (int i = 0; (i < cacheSize) && iterator.hasNext(); i++) {
			cache[i] = iterator.next();

			//  System.out.println("+" + cache[i]);
		}

		return cache;
	}

	public void close() throws RemoteException {

		iterator = null;

	}
}
