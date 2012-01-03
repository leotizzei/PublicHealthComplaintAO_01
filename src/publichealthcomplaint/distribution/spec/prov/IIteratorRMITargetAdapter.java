package publichealthcomplaint.distribution.spec.prov;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IIteratorRMITargetAdapter extends Remote {
	Object[] getNext() throws RemoteException;

	void close() throws RemoteException;
}