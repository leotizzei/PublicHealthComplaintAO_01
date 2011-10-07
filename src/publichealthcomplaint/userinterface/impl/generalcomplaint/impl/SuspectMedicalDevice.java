package publichealthcomplaint.userinterface.impl.generalcomplaint.impl;

import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.datatypes.ISuspectMedicalDeviceDt;

class SuspectMedicalDevice implements ISuspectMedicalDeviceDt {

	
	private String brandName;
	
	private String deviceName;
	
	private String manufacturer;
	
	private String city;
	
	private String state;
	
	private String model;
	
	private String catalog;
	
	private String serial;
	
	private String lot;
	
	private IDateDt expirationDate;
	
	private String otherNumber;
	
	private String deviceOperator;
	
	private IDateDt implantedDate;
	
	private IDateDt explantedDate;
  
	private boolean wasReused;
	
	private String extraInfo;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public IDateDt getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(IDateDt expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getOtherNumber() {
		return otherNumber;
	}

	public void setOtherNumber(String otherNumber) {
		this.otherNumber = otherNumber;
	}

	public String getDeviceOperator() {
		return deviceOperator;
	}

	public void setDeviceOperator(String deviceOperator) {
		this.deviceOperator = deviceOperator;
	}

	public IDateDt getImplantedDate() {
		return implantedDate;
	}

	public void setImplantedDate(IDateDt implantedDate) {
		this.implantedDate = implantedDate;
	}

	public IDateDt getExplantedDate() {
		return explantedDate;
	}

	public void setExplantedDate(IDateDt explantedDate) {
		this.explantedDate = explantedDate;
	}

	public boolean isWasReused() {
		return wasReused;
	}

	public void setWasReused(boolean wasReused) {
		this.wasReused = wasReused;
	}

	public String getExtraInfo() {
		return extraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		this.extraInfo = extraInfo;
	}
	
	
	
	
}
