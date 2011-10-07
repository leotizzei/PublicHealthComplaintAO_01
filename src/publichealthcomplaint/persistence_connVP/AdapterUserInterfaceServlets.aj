package publichealthcomplaint.persistence_connVP;

import publichealthcomplaint.persistence.aspects.AAConnectionDB;
import publichealthcomplaint.userinterface.aspects.XPIUserInterfaceServlets;

public aspect AdapterUserInterfaceServlets extends AAConnectionDB {

	public pointcut persistenceInit():XPIUserInterfaceServlets.distribution();

}
