package publichealthcomplaint.concurrency.spec.prov;

public interface IConcurrencyMgt {
	
	public void beginExecution(Object key);
	
	public void endExecution(Object key);

}
