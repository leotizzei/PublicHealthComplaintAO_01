package publichealthcomplaint.datatypes;


public interface IDrugDataDt {
	
	public String[] getTypeOfProblems() ;
	public void setTypeOfProblems(String[] typeOfProblems) ;
	public String[] getOutcomes();
	public void setOutcomes(String[] outcomes);
	public IDateDt getDateOfEvent();
	public void setDateOfEvent(IDateDt dateOfEvent) ;
	public String getEventDescription() ;
	public void setEventDescription(String eventDescription) ;
	public String getTests() ;
	public void setTests(String tests) ;
	public String getHistory();
	public void setHistory(String history);
	public String getAvailable() ;
	public void setAvailable(String available);
	public String[] getAlsoReported() ;
	public void setAlsoReported(String[] alsoReported);

}
