package publichealthcomplaint.userinterface.spec.req;

import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.datatypes.IDiseaseDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;



public interface IFacade {
	
	public IteratorDsk getComplaintList() /*throws ObjectNotFoundException*/;

	public IteratorDsk getDiseaseTypeList() /*throws ObjectNotFoundException*/;
	
	//public IteratorDsk getHealthUnitList() throws ObjectNotFoundException;
	
	public IteratorDsk getPartialHealthUnitList() /*throws ObjectNotFoundException*/;
	
	//public File getRSSFile() throws FileNotFoundException;
	
	public IteratorDsk getSpecialityList() /*throws ObjectNotFoundException*/;

	//public void insert(IEmployeeDt e) throws ObjectAlreadyInsertedException, ObjectNotValidException;
	
	public int insertComplaint(IComplaintDt complaint) /*throws ObjectAlreadyInsertedException,	ObjectNotValidException*/;

	public IComplaintDt searchComplaint(int code) /*throws ObjectNotFoundException*/;
	
	public IDiseaseDt searchDiseaseType(int code) /*throws ObjectNotFoundException*/;

	//public IEmployeeDt searchEmployee(String login) throws ObjectNotFoundException;
	
	//public IHealthUnitDt searchHealthUnit(int healthUnitCode) throws ObjectNotFoundException;
	
	public IteratorDsk searchHealthUnitsBySpeciality(int code) /*throws ObjectNotFoundException*/;
	
	public IteratorDsk searchSpecialitiesByHealthUnit(int code) /*throws ObjectNotFoundException*/;
	
	public void updateComplaint(IComplaintDt complaint) /*throws ObjectNotFoundException, ObjectNotValidException*/;
	
	//public void updateEmployee(IEmployeeDt e) throws ObjectNotFoundException, ObjectNotValidException, UpdateEntryException;
	
	//public void updateHealthUnit(IHealthUnitDt unit) throws ObjectNotFoundException, ObjectNotValidException;

}
