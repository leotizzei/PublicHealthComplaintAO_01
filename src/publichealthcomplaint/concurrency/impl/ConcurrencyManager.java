package publichealthcomplaint.concurrency.impl;

import java.util.HashMap;

class ConcurrencyManager {

	private final static String ERROR_MESSAGE = "Invallid Execution --- possible programming error at aspects.concurrencyControl.util.ConcurrencyManager";

	private HashMap keys;

	public ConcurrencyManager() {
		keys = new HashMap();
	}

	public synchronized void beginExecution(Object key) {

		while (keys.containsKey(key)) {
			//System.out.println("You have to wait -> ConcurrencyManager");
			try {
				wait();
			} catch (InterruptedException ex) {
				throw new RuntimeException(ERROR_MESSAGE + ex.getMessage());
			}
		}
		//System.out.println("You can execute -> ConcurrencyManager");
		keys.put(key, null);

	}

	public synchronized void endExecution(Object key) {
		try {
			if (keys.containsKey(key)) {
				keys.remove(key);
			} else {
				throw new RuntimeException(ERROR_MESSAGE);
			}
		} catch (Exception ex) {
			System.out.println(ERROR_MESSAGE + ex.getMessage());
		} finally {
			notifyAll();

		}
	}
}
