package publichealthcomplaint.userinterface.impl.queryinfo.spec.req;

import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.datatypes.IDiseaseDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;

public interface IQueryInfoMgt {

	public IteratorDsk getDiseaseTypeList() throws ObjectNotFoundException;
	
	public IteratorDsk getPartialHealthUnitList() throws ObjectNotFoundException;
	
	public IteratorDsk getSpecialityList() throws ObjectNotFoundException;
	
	public IComplaintDt searchComplaint(int code) throws ObjectNotFoundException;
	
	public IDiseaseDt searchDiseaseType(int code) throws ObjectNotFoundException;
	
	public IteratorDsk searchHealthUnitsBySpeciality(int code) throws ObjectNotFoundException;
	
	public IteratorDsk searchSpecialitiesByHealthUnit(int code) throws ObjectNotFoundException;
	
}
