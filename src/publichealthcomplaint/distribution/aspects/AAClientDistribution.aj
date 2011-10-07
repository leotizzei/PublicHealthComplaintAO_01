package publichealthcomplaint.distribution.aspects;

import java.rmi.Remote;

import publichealthcomplaint.distribution.impl.ComponentFactory;
import publichealthcomplaint.distribution.spec.prov.IDistributionMgt;
import publichealthcomplaint.distribution.spec.prov.IIteratorRMITargetAdapter;
import publichealthcomplaint.distribution.spec.prov.IManager;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.distribution.spec.prov.LocalIterator;

public abstract aspect AAClientDistribution {


	/**
	 * Keeps the remote instance
	 */
	protected IRemoteFacade facade = null;

	/**
	 * Redirects the facade methods call to the distributed instance methods using reflection
	 */
	public abstract pointcut distribution();

	Object around(): distribution(){
		Remote objTarget = getRemoteFacade();
		if( objTarget == null)
			System.out.println("[AAClientDistribution:distribution] objTarget is null");
		String methodName = thisJoinPoint.getSignature().getName(); 

		Object[] arguments = thisJoinPoint.getArgs();

		IManager mgr = ComponentFactory.createInstance();
		IDistributionMgt dist = (IDistributionMgt) mgr.getProvidedInterface("IDistributionMgt");

		Object obj = dist.invoke( objTarget, methodName, arguments);


		if ( ( obj != null) && ( obj instanceof LocalIterator ) ) {

			IIteratorRMITargetAdapter iteratorTA = null;

			
			iteratorTA = dist.createIteratorRMITarget((LocalIterator) obj, 3);
			//iteratorTA = new IteratorRMITargetAdapter((LocalIterator) obj, 3);

			IteratorDsk iteratorSrcAdp = dist.createIterator(iteratorTA, (LocalIterator) obj, 3);

			return iteratorSrcAdp;
		}
		return obj;	
	}

	/**
	 * Retrieves the remote instance, creating if needed
	 */
	private synchronized Remote getRemoteFacade() {        
		if (facade == null) {
			try {
				System.out.println("About to lookup:"+"//" + publichealthcomplaint.datatypes.Constants.SERVER_NAME + "/" + publichealthcomplaint.datatypes.Constants.SYSTEM_NAME);
				facade = (IRemoteFacade) java.rmi.Naming.lookup("//" + publichealthcomplaint.datatypes.Constants.SERVER_NAME + "/" + publichealthcomplaint.datatypes.Constants.SYSTEM_NAME);
				System.out.println("Remote DisqueSaude found");
			} catch (java.rmi.RemoteException rmiEx) {
				System.err.println(rmiEx.getLocalizedMessage());
				rmiEx.printStackTrace();
				rmiInitExceptionHandling(rmiEx);
			} catch (java.rmi.NotBoundException rmiEx) {
				System.err.println(rmiEx.getLocalizedMessage());
				rmiEx.printStackTrace();
				rmiInitExceptionHandling(rmiEx);
			} catch (java.net.MalformedURLException rmiEx) {
				System.err.println(rmiEx.getLocalizedMessage());
				rmiEx.printStackTrace();
				rmiInitExceptionHandling(rmiEx);
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		if(facade != null)
			System.out.println("[HWClientDistribution:getRemoteFacade()] returning facade = "+facade.getClass().getName());
		return facade;
	}

	protected void rmiInitExceptionHandling(Throwable exception) {
		String error =  "<p>****************************************************<br>" +
		"Error during servlet initialization!<br>" +
		"The exception message is:<br><dd>" + exception.getMessage() +
		"<p>You may have to restart the servlet container.<br>" +
		"*******************************************************";
		System.out.println(error);
	}

}
