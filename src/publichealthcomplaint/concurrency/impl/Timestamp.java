package publichealthcomplaint.concurrency.impl;

import publichealthcomplaint.concurrency.aspects.ITimestamp;

public class Timestamp implements ITimestamp {
	
    private long timestamp; // para tratamento de concorrencia (scbs)
	
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public void incTimestamp() {
		this.timestamp = timestamp + 1;
	}

}
