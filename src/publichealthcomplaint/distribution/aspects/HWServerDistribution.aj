package publichealthcomplaint.distribution.aspects;



import publichealthcomplaint.rmiconnector.HealthWatcherFacade;

/**
 * This is the server part for distribution. The following tasks are done:
 * 
 * 1) makes the facade implement the remote interface
 * 2) makes model classes serializable (actually this is also needed in the client)
 * 3) creates a main method in the facade, to start the server
 * 4) publishes the facade as an rmi server when starting
 **/
public aspect HWServerDistribution {

	/*
	 * Forces the facade to implement a remote interface, needed for RMI
	 */
	declare parents: HealthWatcherFacade implements publichealthcomplaint.distribution.aspects.IRemoteFacade;

	
   /**eal
    * Declare the model classes serializable
    */
	declare parents : publichealthcomplaint.datatypes.ISuspectMedicalDeviceDt implements java.io.Serializable;
	declare parents : publichealthcomplaint.datatypes.ISuspectProductDt implements java.io.Serializable;
	declare parents : publichealthcomplaint.datatypes.IDrugDataDt implements java.io.Serializable;
    declare parents : publichealthcomplaint.datatypes.IDrugComplaintDt+ implements java.io.Serializable;
    declare parents : publichealthcomplaint.datatypes.IDiseaseDt+ implements java.io.Serializable;
    declare parents : publichealthcomplaint.datatypes.IComplaintDt+ implements java.io.Serializable;
    declare parents : publichealthcomplaint.datatypes.IAnimalComplaintDt+ implements java.io.Serializable;
    declare parents : publichealthcomplaint.datatypes.IEmployeeDt+ implements java.io.Serializable;
    declare parents : publichealthcomplaint.datatypes.IAddressDt+ implements java.io.Serializable;
    declare parents : publichealthcomplaint.datatypes.IHealthUnitDt+ implements java.io.Serializable;
	declare parents : publichealthcomplaint.datatypes.IMedicalSpecialityDt+ implements java.io.Serializable;
    

}
