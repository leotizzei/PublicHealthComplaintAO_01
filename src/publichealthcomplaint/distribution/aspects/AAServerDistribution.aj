package publichealthcomplaint.distribution.aspects;

import java.rmi.server.UnicastRemoteObject;

import javax.servlet.http.HttpServletRequest;


import publichealthcomplaint.datatypes.Constants;
import publichealthcomplaint.rmiconnector.HealthWatcherFacade;

public abstract aspect AAServerDistribution {



	public abstract pointcut registering();


	public abstract pointcut callRequest(HttpServletRequest request);


	before(): registering(){ 

		try {
			System.out.println("Creating RMI server...");

			String remoteObjectName = new String("//" + Constants.SERVER_NAME + "/"+ Constants.SYSTEM_NAME);
			boolean objectIsAlreadyBinded = false;

			String[] bindedList = java.rmi.Naming.list("//" + Constants.SERVER_NAME + "/"	+ Constants.SYSTEM_NAME);

			System.out.println("[AAServerDistribution:callRequest()] object "+ remoteObjectName +" is not bind yet");

			HealthWatcherFacade adapter = HealthWatcherFacade.getInstance();
			
			//begin debug
			if(adapter == null)
				System.out.println("[AAServerDistribution:registering()] adapter is null");
			else
				System.out.println("[AAServerDistribution:registering()] adapter = "+adapter);
			//end debug
			
			try
			{
				UnicastRemoteObject.exportObject(adapter);
			}
			catch (java.rmi.RemoteException rmiEx)
			{
				// object already exported 
			}
			
			
			System.out.println("[AAServerDistribution:registering()]  Object "+ adapter.getClass().getName() +" exported");
			java.rmi.Naming.rebind("//" + Constants.SERVER_NAME + "/"
					+ Constants.SYSTEM_NAME, adapter);
			System.out.println("[AAServerDistribution:registering()]  Server created and ready.");



		}   catch (java.rmi.RemoteException rmiEx) {
			System.err.println("[AAServerDistribution:registering()] "+rmiEx.getLocalizedMessage());
			rmiEx.printStackTrace();
			rmiFacadeExceptionHandling(rmiEx);
		} catch (java.net.MalformedURLException rmiEx) {
			System.err.println("[AAServerDistribution:registering()] "+rmiEx.getLocalizedMessage());
			rmiEx.printStackTrace();
			rmiFacadeExceptionHandling(rmiEx);
		}
	}

	/**
	 * Compare the strings which represent the remote object in order to check if the object is already binded
	 * @param remoteObjectCandidate
	 * @param alreadyBindedObject
	 * @return
	 */
	private boolean isAlreadyBinded(String remoteObjectCandidate, String alreadyBindedObject){
		if( remoteObjectCandidate == null || alreadyBindedObject == null )
			return false;

		String canditateName = extractObjectName(remoteObjectCandidate);
		String objBinded = extractObjectName(alreadyBindedObject);

		System.out.println(canditateName+" == "+objBinded);
		if( canditateName.equals(objBinded))
			return true;
		else
			return false;

	}

	//get the name of the object that is the substring after the last '/'
	private String extractObjectName(String fullPath){
		if( ( fullPath != null) && ( fullPath.contains("/") ) ){
			String[] aux = fullPath.split("/");
			if( aux != null ){
				int length = aux.length;
				String nameBinded = aux[ length - 1 ];
				return nameBinded;
			}
		}
		return null;
	}



	private void rmiFacadeExceptionHandling(Throwable exception) {
		System.out.println("**************************************************");
		System.out.println("* Error during server initialization!            *");
		System.out.println("* The exception message is:                      *");
		System.out.println("      " + exception.getMessage()                   );
		System.out.println("* You may have to restart the server or registry.*");
		System.out.println("**************************************************");
		exception.printStackTrace();
	}
}
