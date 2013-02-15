package publichealthcomplaint.datatypes;

public interface ISuspectMedicalDeviceDt {
	
	public String getBrandName();

	public void setBrandName(String brandName) ;

	public String getDeviceName();

	public void setDeviceName(String deviceName) ;

	public String getManufacturer() ;

	public void setManufacturer(String manufacturer);

	public String getManufacturerCity();

	public void setManufacturerCity(String city);

	public String getManufacturerState();

	public void setManufacturerState(String state) ;

	public String getModel();

	public void setModel(String model) ;

	public String getCatalog();

	public void setCatalog(String catalog);

	public String getSerial();

	public void setSerial(String serial);

	public String getLot() ;

	public void setLot(String lot);

	public IDateDt getExpirationDate() ;

	public void setExpirationDate(IDateDt expirationDate);

	public String getOtherNumber();

	public void setOtherNumber(String otherNumber);

	public String getDeviceOperator() ;

	public void setDeviceOperator(String deviceOperator);

	public IDateDt getImplantedDate();

	public void setImplantedDate(IDateDt implantedDate);

	public IDateDt getExplantedDate() ;

	public void setExplantedDate(IDateDt explantedDate);

	public boolean isWasReused();

	public void setWasReused(boolean wasReused);

	public String getExtraInfo();

	public void setExtraInfo(String extraInfo);

}
