package publichealthcomplaint.complaintmgr.impl;

import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.datatypes.IDrugDataDt;

public class DrugData implements IDrugDataDt {

	private String[] typeOfProblems;
	private String[] outcomes;
	private IDateDt dateOfEvent;
	private String eventDescription;
	private String tests;
	private String history;
	private String available;
	private String[] alsoReported;
	
	public String[] getTypeOfProblems() {
		return typeOfProblems;
	}
	public void setTypeOfProblems(String[] typeOfProblems) {
		this.typeOfProblems = typeOfProblems;
	}
	public String[] getOutcomes() {
		return outcomes;
	}
	public void setOutcomes(String[] outcomes) {
		this.outcomes = outcomes;
	}
	public IDateDt getDateOfEvent() {
		return dateOfEvent;
	}
	public void setDateOfEvent(IDateDt dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getTests() {
		return tests;
	}
	public void setTests(String tests) {
		this.tests = tests;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String[] getAlsoReported() {
		return alsoReported;
	}
	public void setAlsoReported(String[] alsoReported) {
		this.alsoReported = alsoReported;
	} 
	
}
