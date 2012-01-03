package publichealthcomplaint.distribution.spec.prov;

import publichealthcomplaint.exceptionhandling.impl.CommunicationException;

public interface IteratorDsk {

	public void close() throws CommunicationException;

	public boolean hasNext() throws CommunicationException;

	public Object next() throws CommunicationException;

	public void remove() throws CommunicationException;
	
	
}
