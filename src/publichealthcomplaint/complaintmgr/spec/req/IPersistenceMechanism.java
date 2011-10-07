package publichealthcomplaint.complaintmgr.spec.req;



public interface IPersistenceMechanism {

	public void connect() ;

	public void disconnect() ;

	public Object getCommunicationChannel() ;

	public void releaseCommunicationChannel();

	public void beginTransaction() ;

	public void commitTransaction() ;

	public void rollbackTransaction() ;

	
}
