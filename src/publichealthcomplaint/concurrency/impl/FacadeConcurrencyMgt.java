package publichealthcomplaint.concurrency.impl;

import publichealthcomplaint.concurrency.spec.prov.IConcurrencyMgt;

class FacadeConcurrencyMgt implements IConcurrencyMgt{

	private ConcurrencyManager concurrencyMgr;
	
	FacadeConcurrencyMgt() {
		concurrencyMgr = new ConcurrencyManager();
	}
	
	public void beginExecution(Object key) {
		
		concurrencyMgr.beginExecution(key);
	}

	
	public void endExecution(Object key) {
		
		concurrencyMgr.endExecution(key);
		
	}

}
