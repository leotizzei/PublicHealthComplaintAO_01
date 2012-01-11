package publichealthcomplaint.rmiconnector;

import java.io.File;

import publichealthcomplaint.complaintmgr.spec.prov.IComplaintMgt;
import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.datatypes.IDiseaseDt;
import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.datatypes.IHealthUnitDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.infrastructuremgt.spec.prov.IEmployeeMgt;
import publichealthcomplaint.infrastructuremgt.spec.prov.IHealthUnitMgt;
import publichealthcomplaint.infrastructuremgt.spec.prov.ISpecialityRepository;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.prov.IDiseaseRepository;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.prov.IHealthUnitRepository;
import publichealthcomplaint.supportservices.impl.rssfeedsmgr.spec.prov.IRSSFeedsMgt;
import publichealthcomplaint.userinterface.spec.req.IFacade;


public class HealthWatcherFacade implements IFacade {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static HealthWatcherFacade singleton; // singleton pattern


	public synchronized static HealthWatcherFacade getInstance() {
	
		if (singleton == null) {
			singleton = new HealthWatcherFacade();			
		}
		return singleton;
	}

	private HealthWatcherFacade() {

	}

	

	public IteratorDsk getComplaintList()  {
		IManager mgr = ComponentFactory.createInstance();
		IComplaintMgt complaintMgt = (IComplaintMgt) mgr.getRequiredInterface("IComplaintMgt");
		return complaintMgt.getComplaintList();
		
	}




	public IteratorDsk getDiseaseTypeList() {
		System.out.println("[HealthWatcherFacade:getDiseaseTypeList()]");
		IManager mgr = ComponentFactory.createInstance();                             
		IDiseaseRepository diseaseRepository = (IDiseaseRepository) mgr.getRequiredInterface("IDiseaseRepository"); 
		return diseaseRepository.getDiseaseTypeList();
		

	}


	public IteratorDsk getHealthUnitList() {
		IManager mgr = ComponentFactory.createInstance();
		IHealthUnitMgt healthUnitMgt = (IHealthUnitMgt) mgr.getRequiredInterface("IHealthUnitMgt");
		
		return healthUnitMgt.getHealthUnitList();
	
	}


	public IteratorDsk getPartialHealthUnitList()  {
		System.out.println("[HealthWatcherFacade:getPartialHealthUnitList]");
		IManager mgr = ComponentFactory.createInstance();                             
		IHealthUnitRepository healthUnitRepository = (IHealthUnitRepository) mgr.getRequiredInterface("IHealthUnitRepository"); 

		return healthUnitRepository.getPartialHealthUnitList();
		

	}


	public IteratorDsk getSpecialityList() {
		System.out.println("[HealthWatcherFacade:getSpecialityList()]");
		IManager mgr = ComponentFactory.createInstance();
		ISpecialityRepository specRep = (ISpecialityRepository) mgr.getRequiredInterface("ISpecialityRepository");
		
		//try {
			return specRep.getSpecialityList();
		/*} catch (ObjectNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;*/
		
		
	}


	public void insert(IEmployeeDt e)  {
		System.out.println("[HealthWatcherFacade:insert()]");
		IManager mgr = ComponentFactory.createInstance();
		IEmployeeMgt employeeMgr = (IEmployeeMgt) mgr.getRequiredInterface("IEmployeeMgt");
		employeeMgr.insert(e);
		

	}


	public int insertComplaint(IComplaintDt complaint) {
		System.out.println("[HealthWatcherFacade:insertComplaint()]");
		IManager mgr = ComponentFactory.createInstance();
		IComplaintMgt complaintMgt = (IComplaintMgt) mgr.getRequiredInterface("IComplaintMgt");
		
		return complaintMgt.insertComplaint(complaint);
		
	}


	public IComplaintDt searchComplaint(int code) {
		System.out.println("[HealthWatcherFacade:searchComplaint("+code+")]");
		IManager mgr = ComponentFactory.createInstance();        
		IComplaintMgt complaintMgt = (IComplaintMgt) mgr.getRequiredInterface("IComplaintMgt");
		return complaintMgt.search(code);
		
	}


	public IDiseaseDt searchDiseaseType(int code)
	{
		System.out.println("[HealthWatcherFacade:searchDiseaseType()]");
		IManager mgr = ComponentFactory.createInstance();                             
		IDiseaseRepository diseaseRepository = (IDiseaseRepository) mgr.getRequiredInterface("IDiseaseRepository"); 
		
		return diseaseRepository.search(code);
		
	}


	public IEmployeeDt searchEmployee(String login) {
		System.out.println("[HealthWatcherFacade:searchEmployee()] login="+login);
		IManager mgr = ComponentFactory.createInstance();   
		IEmployeeMgt employee = (IEmployeeMgt) mgr.getRequiredInterface("IEmployeeMgt");
		
		return employee.searchEmployee(login);
		

	}


	public IHealthUnitDt searchHealthUnit(int healthUnitCode)
	{
		IManager mgr = ComponentFactory.createInstance();
		IHealthUnitMgt healthUnitMgt = (IHealthUnitMgt) mgr.getRequiredInterface("IHealthUnitMgt");
		
		return healthUnitMgt.searchHealthUnit(healthUnitCode);
		
	}


	public IteratorDsk searchHealthUnitsBySpeciality(int code) {
		System.out.println("[HealthWatcherFacade:searchHealthUnitsBySpeciality()]");
		IManager mgr = ComponentFactory.createInstance();                             
		IHealthUnitRepository healthUnitRepository = (IHealthUnitRepository) mgr.getRequiredInterface("IHealthUnitRepository"); 
		
		return healthUnitRepository.getHealthUnitListBySpeciality(code);
		
	}


	public IteratorDsk searchSpecialitiesByHealthUnit(int code) {
		System.out.println("[HealthWatcherFacade:searchSpecialitiesByHealthUnit()]");
		IManager mgr = ComponentFactory.createInstance();                             
		IHealthUnitRepository healthUnitRepository = (IHealthUnitRepository) mgr.getRequiredInterface("IHealthUnitRepository"); 
		
		return healthUnitRepository.searchSpecialitiesByHealthUnit(code);
		

	}


	public void updateComplaint(IComplaintDt complaint){
		IManager mgr = ComponentFactory.createInstance();
		IComplaintMgt complaintMgt = (IComplaintMgt) mgr.getRequiredInterface("IComplaintMgt");
		
		complaintMgt.updateComplaint(complaint);
		
		System.out.println("[HealthwatcherFacade:updateComplaint() returning...]");
	}


	public void updateEmployee(IEmployeeDt e) {
		System.out.println("[HealthWatcherFacade:insert()]");
		IManager mgr = ComponentFactory.createInstance();
		IEmployeeMgt infrastructureMgt = (IEmployeeMgt) mgr.getRequiredInterface("IEmployeeMgt");
		
		infrastructureMgt.updateEmployee(e);
		

	}


	public void updateHealthUnit(IHealthUnitDt unit) {
		IManager mgr = ComponentFactory.createInstance();
		IHealthUnitMgt healthUnitMgt = (IHealthUnitMgt) mgr.getRequiredInterface("IHealthUnitMgt");
		healthUnitMgt.updateHealthUnit(unit);
		

	}

	
	public File getRSSFile() {
		IManager mgr = ComponentFactory.createInstance();
		IRSSFeedsMgt feedsMgr = (IRSSFeedsMgt) mgr.getRequiredInterface("IRSSFeedsMgt");
		
		return feedsMgr.getRSSFile();
		
	}





}