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

	public int getModel();

	public void setModel(int modelNumber) ;

	public int getCatalog();

	public void setCatalog(int catalog);

	public int getSerial();

	public void setSerial(int serial);

	public int getLot() ;

	public void setLot(int lot);

	public IDateDt getExpirationDate() ;

	public void setExpirationDate(IDateDt expirationDate);

	public int getOtherNumber();

	public void setOtherNumber(int otherNumber);

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
