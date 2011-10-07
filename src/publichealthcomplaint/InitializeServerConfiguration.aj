package publichealthcomplaint;

import publichealthcomplaint.complaintmgr.spec.prov.IComplaintMgt;
import publichealthcomplaint.infrastructuremgt.spec.prov.IEmployeeMgt;
import publichealthcomplaint.infrastructuremgt.spec.prov.IHealthUnitMgt;
import publichealthcomplaint.infrastructuremgt.spec.prov.ISpecialityRepository;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.prov.IDiseaseRepository;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.prov.IHealthUnitRepository;
import publichealthcomplaint.supportservices.impl.rssfeedsmgr.spec.prov.IRSSFeedsMgt;
import publichealthcomplaint.userinterface.impl.generalcomplaint.impl.ServletWebServer;


public aspect InitializeServerConfiguration {
	
	/**
	 * After initializing the client side, initiliaze the server side. 
	 */
	
	
	/*public pointcut doPostExec():execution(public void HttpServlet+.doPost(HttpServletRequest,HttpServletResponse)) ;

	public pointcut doGetExec():execution(public void HttpServlet+.doGet(HttpServletRequest,HttpServletResponse)) ; */
	
	public pointcut firstThing():execution(public ServletWebServer.new());
		
	before():firstThing(){
		configure();
	}
	
	
	void configure(){
		System.out.println("[InitializeServerConfiguration:configure() ] Defining architectural configuration...");
		//components
		publichealthcomplaint.supportservices.impl.queryinfomgr.spec.prov.IManager ssmMgr = publichealthcomplaint.supportservices.impl.queryinfomgr.impl.ComponentFactory.createInstance();
		publichealthcomplaint.distribution.spec.prov.IManager distMgr = publichealthcomplaint.distribution.impl.ComponentFactory.createInstance();
		publichealthcomplaint.rmiconnector.IManager rmiConnMgr = publichealthcomplaint.rmiconnector.ComponentFactory.createInstance();
		publichealthcomplaint.persistence.spec.prov.IManager persistenceMgr = publichealthcomplaint.persistence.impl.ComponentFactory.createInstance();
		publichealthcomplaint.complaintmgr.spec.prov.IManager complaintMgr = publichealthcomplaint.complaintmgr.impl.ComponentFactory.createInstance();
		publichealthcomplaint.infrastructuremgt.spec.prov.IManager infrastructureMgt = publichealthcomplaint.infrastructuremgt.impl.ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.spec.prov.IManager userIntMgr = publichealthcomplaint.userinterface.impl.ComponentFactory.createInstance();
		publichealthcomplaint.supportservices.impl.rssfeedsmgr.spec.prov.IManager rssMgr = publichealthcomplaint.supportservices.impl.rssfeedsmgr.impl.ComponentFactory.createInstance();
		
		
		//userinterface subcomponents and their connectors
		publichealthcomplaint.userinterface.impl.feedsmgr.spec.prov.IManager feedsMgr = publichealthcomplaint.userinterface.impl.feedsmgr.impl.ComponentFactory.createInstance(); 
		publichealthcomplaint.userinterface.impl.generalcomplaint.spec.prov.IManager generalCompMgr = publichealthcomplaint.userinterface.impl.generalcomplaint.impl.ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.infrastr.spec.prov.IManager infraMgr = publichealthcomplaint.userinterface.impl.infrastr.impl.ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.queryinfo.spec.prov.IManager queryMgr = publichealthcomplaint.userinterface.impl.queryinfo.impl.ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IManager utilMgr = publichealthcomplaint.userinterface.impl.util.impl.ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.userinterf_connvp.IManager user_conn = publichealthcomplaint.userinterface.impl.userinterf_connvp.ComponentFactory.createInstance(); 
		publichealthcomplaint.userinterface.impl.generalcomplaint_util.IManager gen_utilMgr = publichealthcomplaint.userinterface.impl.generalcomplaint_util.ComponentFactory.createInstance(); 
		publichealthcomplaint.userinterface.impl.infrastr_util.IManager infrastr_utilMgr = publichealthcomplaint.userinterface.impl.infrastr_util.ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.queryinfo_util.IManager queryinfor_utilMgr = publichealthcomplaint.userinterface.impl.queryinfo_util.ComponentFactory.createInstance(); 
		
		//regular (i.e. non-aspect-oriented) connectors
		publichealthcomplaint.infrastructure_supportservicesmgr.IManager infra_ssm = publichealthcomplaint.infrastructure_supportservicesmgr.ComponentFactory.createInstance();
		
		
		IHealthUnitRepository healthUnitRepository = (IHealthUnitRepository) ssmMgr.getProvidedInterface("IHealthUnitRepository");
		IDiseaseRepository diseaseRep = (IDiseaseRepository) ssmMgr.getProvidedInterface("IDiseaseRepository");
		ISpecialityRepository specialityRep = (ISpecialityRepository) infrastructureMgt.getProvidedInterface("ISpecialityRepository");
		IEmployeeMgt employeeMgt = (IEmployeeMgt) infrastructureMgt.getProvidedInterface("IEmployeeMgt");
		IComplaintMgt complaintMgt = (IComplaintMgt) complaintMgr.getProvidedInterface("IComplaintMgt");
		IHealthUnitMgt healthUnitMgt = (IHealthUnitMgt) infrastructureMgt.getProvidedInterface("IHealthUnitMgt");
		IRSSFeedsMgt rss = (IRSSFeedsMgt) rssMgr.getProvidedInterface("IRSSFeedsMgt");
		
		//setting connector required interfaces
		rmiConnMgr.setRequiredInterface("IHealthUnitRepository", healthUnitRepository);
		rmiConnMgr.setRequiredInterface("IDiseaseRepository", diseaseRep );
		rmiConnMgr.setRequiredInterface("ISpecialityRepository", specialityRep );
		rmiConnMgr.setRequiredInterface("IEmployeeMgt", employeeMgt);
		rmiConnMgr.setRequiredInterface("IComplaintMgt", complaintMgt);
		rmiConnMgr.setRequiredInterface("IHealthUnitMgt", healthUnitMgt);
		rmiConnMgr.setRequiredInterface("IHealthUnitMgt", healthUnitMgt);
		rmiConnMgr.setRequiredInterface("IRSSFeedsMgt", rss);
		
		infra_ssm.setRequiredInterface("IEmployeeMgt", employeeMgt);
		infra_ssm.setRequiredInterface("ISpecialityRepository", specialityRep);
		infra_ssm.setRequiredInterface("IHealthUnitMgt", healthUnitMgt);
		
		
		ssmMgr.setRequiredInterface("IEmployeeMgt", infra_ssm.getProvidedInterface("IEmployeeMgt"));
		ssmMgr.setRequiredInterface("ISpecialityRepository", infra_ssm.getProvidedInterface("ISpecialityRepository"));
		ssmMgr.setRequiredInterface("IHealthUnitMgt", infra_ssm.getProvidedInterface("IHealthUnitMgt"));
		
		// setting required interfaces for all userinterface's subcomponents and their connectors (alphabetically ordered)
		gen_utilMgr.setRequiredInterface("IHTMLPageMgt", utilMgr.getProvidedInterface("IHTMLPageMgt"));
		gen_utilMgr.setRequiredInterface("IUtil", utilMgr.getProvidedInterface("IUtil"));
			
		generalCompMgr.setRequiredInterface("IUtil", gen_utilMgr.getProvidedInterface("IUtil"));
		generalCompMgr.setRequiredInterface("IHTMLPageMgt", gen_utilMgr.getProvidedInterface("IHTMLPageMgt"));
		generalCompMgr.setRequiredInterface("IUpdate", userIntMgr.getProvidedInterface("IUpdate"));
		generalCompMgr.setRequiredInterface("IComplaintMgt", user_conn.getProvidedInterface("IComplaintMgt"));
		
		user_conn.setRequiredInterface("IFacade", rmiConnMgr.getProvidedInterface("IFacade"));
		
		infraMgr.setRequiredInterface("IHTMLPageMgt", infrastr_utilMgr.getProvidedInterface("IHTMLPageMgt"));
		infraMgr.setRequiredInterface("IInfrastructureMgt", user_conn.getProvidedInterface("IInfrastructureMgt"));
		infraMgr.setRequiredInterface("IUtil", infrastr_utilMgr.getProvidedInterface("IUtil"));
		
		infrastr_utilMgr.setRequiredInterface("IHTMLPageMgt", utilMgr.getProvidedInterface("IHTMLPageMgt"));
		infrastr_utilMgr.setRequiredInterface("IUtil", utilMgr.getProvidedInterface("IUtil"));
		
		queryMgr.setRequiredInterface("IHTMLPageMgt", queryinfor_utilMgr.getProvidedInterface("IHTMLPageMgt"));
		queryMgr.setRequiredInterface("IUtil", queryinfor_utilMgr.getProvidedInterface("IUtil"));
		queryMgr.setRequiredInterface("IQueryInfoMgt", user_conn.getProvidedInterface("IQueryInfoMgt"));
	}

}
