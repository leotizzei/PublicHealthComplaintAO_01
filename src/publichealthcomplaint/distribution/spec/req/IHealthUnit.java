package publichealthcomplaint.distribution.spec.req;

import java.rmi.Remote;
import java.util.List;

import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.RepositoryException;

public interface IHealthUnit extends Remote{
	
	public List searchSpecialitiesByHealthUnit(int code) throws ObjectNotFoundException, RepositoryException ;

}
