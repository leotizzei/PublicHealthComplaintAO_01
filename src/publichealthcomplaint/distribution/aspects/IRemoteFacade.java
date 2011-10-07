package publichealthcomplaint.distribution.aspects;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.datatypes.IDiseaseDt;
import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.datatypes.IHealthUnitDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.exceptionhandling.impl.RepositoryException;
import publichealthcomplaint.exceptionhandling.impl.UpdateEntryException;

/**
 * A remote facade for use with rmi
 * This is just a copy of IFacade but throwing remote exception in every method
 */
public interface IRemoteFacade extends Remote, Serializable {

	public IteratorDsk getDiseaseTypeList() throws ObjectNotFoundException, RemoteException;

	public IteratorDsk getHealthUnitList() throws ObjectNotFoundException, RemoteException;
	
	public IteratorDsk getPartialHealthUnitList() throws RepositoryException, ObjectNotFoundException, RemoteException;

	public IteratorDsk getComplaintList() throws ObjectNotFoundException, RemoteException;
	
	public int insertComplaint(IComplaintDt complaint) throws ObjectAlreadyInsertedException,	ObjectNotValidException, RemoteException;

	public void insert(IEmployeeDt e) throws ObjectAlreadyInsertedException, ObjectNotValidException, RemoteException;
	
	public IteratorDsk getSpecialityList() throws ObjectNotFoundException, RemoteException;

	public IComplaintDt searchComplaint(int code) throws ObjectNotFoundException, RemoteException;

	public IDiseaseDt searchDiseaseType(int code) throws ObjectNotFoundException, RemoteException;

	public IEmployeeDt searchEmployee(String login) throws ObjectNotFoundException, RemoteException;
	
	public IHealthUnitDt searchHealthUnit(int healthUnitCode) throws ObjectNotFoundException, RemoteException;
	
	public IteratorDsk searchHealthUnitsBySpeciality(int code) throws ObjectNotFoundException, RemoteException;

	public IteratorDsk searchSpecialitiesByHealthUnit(int code) throws ObjectNotFoundException,	RepositoryException, RemoteException;
	
	public void updateComplaint(IComplaintDt complaint) throws ObjectNotFoundException, ObjectNotValidException, RemoteException;
	
	public void updateEmployee(IEmployeeDt e) throws ObjectNotFoundException, ObjectNotValidException, UpdateEntryException, RemoteException;
	
	public void updateHealthUnit(IHealthUnitDt unit) throws ObjectNotFoundException, ObjectNotValidException, RemoteException;
	
	public File getRSSFile() throws FileNotFoundException, RemoteException;
}
