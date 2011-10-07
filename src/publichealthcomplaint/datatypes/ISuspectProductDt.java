package publichealthcomplaint.datatypes;

public interface ISuspectProductDt {
	

	public String getProductName() ;

	public void setProductName(String productName) ;

	public String getLabelStrength() ;

	public void setLabelStrength(String labelStrength) ;

	public String getManufacturer() ;

	public void setManufacturer(String manufacturer) ;

	public String getDose() ;

	public void setDose(String dose);

	public String getFrequency() ;

	public void setFrequency(String frequency) ;

	public String getRoute() ;
	
	public void setRoute(String route) ;

	public IDateDt getStartUseDate() ;

	public void setStartUseDate(IDateDt startUseDate);

	public IDateDt getEndUseDate() ;

	public void setEndUseDate(IDateDt endUseDate) ;

	public String getDiagnosis();

	public void setDiagnosis(String diagnosis);

	public String getEventAbated() ;

	public void setEventAbated(String eventAbated) ;

	public String getLot() ;

	public void setLot(String lot);

	public IDateDt getExpirationDate() ;

	public void setExpirationDate(IDateDt expirationDate) ;

	public String getEventReappeared();

	public void setEventReappeared(String eventReappeared) ;

	public String getId();

	public void setId(String id) ;

}
