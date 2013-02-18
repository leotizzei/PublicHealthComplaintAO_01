package publichealthcomplaint.userinterface.impl.generalcomplaint.impl;

import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.datatypes.ISuspectMedicalDeviceDt;

class SuspectMedicalDevice implements ISuspectMedicalDeviceDt {
	
	
	private static final long serialVersionUID = 1L;
	private String brandName;
	private int catalog;
	private String manufacturerCity;
	private String deviceName;
	private String deviceOperator;
	private IDateDt expirationDate;
	private IDateDt explantedDate;
	private IDateDt implantedDate;
	private boolean isReused;
	private int lot;
	private String manufacturer;
	private int model;
	private int otherNumber;
	private int serial;
	private String manufacturerState;
	private String extraInfo;
	
	
	public String getBrandName() {
		if(this.brandName != null &&  this.brandName.equals(""))
			return null;
		else
			return this.brandName;
	}

	
	
	public int getCatalog() {
		return this.catalog;
	}

	
	public String getManufacturerCity() {
		if(this.manufacturerCity != null && this.manufacturerCity.equals(""))
			return null;
		else
			return this.manufacturerCity;
	}

	
	public String getDeviceName() {
		if(this.deviceName != null && this.deviceName.equals(""))
			return null;
		else
		return this.deviceName;
	}

	
	public String getDeviceOperator() {
		if(this.deviceOperator != null && this.deviceOperator.equals(""))
			return null;
		else
		return this.deviceOperator;
	}

	
	public IDateDt getExpirationDate() {
		return this.expirationDate;
	}

	
	public IDateDt getExplantedDate() {
		return this.explantedDate;
	}

	
	public String getExtraInfo() {
		if(this.extraInfo != null && this.extraInfo.equals(""))
			return null;
		else
		return this.extraInfo;
	}

	
	
	public IDateDt getImplantedDate() {
		return this.implantedDate;
	}

	
	
	public int getLot() {
		return this.lot;
	}

	public String getManufacturer() {
		if( ( this.manufacturer != null ) && ( this.manufacturer.equals("") ) )
			return null;
		else
			return this.manufacturer;
	}

	
	public int getModel() {
		return this.model;
	}

	public int getOtherNumber() {
		return this.otherNumber;
	}

	
	public int getSerial() {
		return this.serial;
	}

	public String getManufacturerState() {
		if( this.manufacturerState != null && this.manufacturerState.equals(""))
			return null;
		else
			return this.manufacturerState;
	}

	
	public boolean isWasReused() {
		return this.isReused;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;

	}

	public void setCatalog(int catalog) {
		this.catalog = catalog;
	
	}

	public void setManufacturerCity(String manufacturerCity) {
		this.manufacturerCity = manufacturerCity;

	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	
	public void setDeviceOperator(String deviceOperator) {
		this.deviceOperator = deviceOperator;

	}

	
	public void setExpirationDate(IDateDt expirationDate) {
		this.expirationDate = explantedDate;
	}

	
	public void setExplantedDate(IDateDt explantedDate) {
		this.explantedDate = explantedDate;
	}

	
	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}

	
	public void setImplantedDate(IDateDt implantedDate) {
		this.implantedDate = implantedDate;

	}

	
	public void setLot(int lot) {
		this.lot = lot;

	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	
	public void setModel(int model) {
		this.model = model;

	}

	
	public void setOtherNumber(int otherNumber) {
		this.otherNumber = otherNumber;

	}

	
	public void setSerial(int serial) {
		this.serial = serial;

	}

	public void setManufacturerState(String manufacturerState) {
		this.manufacturerState = manufacturerState;

	}

	
	public void setWasReused(boolean wasReused) {
		this.isReused = wasReused;

	}





}
