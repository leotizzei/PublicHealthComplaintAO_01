package publichealthcomplaint.complaintmgr.impl;

import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.datatypes.ISuspectMedicalDeviceDt;

class SuspectMedicalDeviceDt implements ISuspectMedicalDeviceDt {
	
	
	private static final long serialVersionUID = 1L;
	private String brandName;
	private String catalog;
	private String manufacturerCity;
	private String deviceName;
	private String deviceOperator;
	private IDateDt expirationDate;
	private IDateDt explantedDate;
	private IDateDt implantedDate;
	private boolean isReused;
	private String lot;
	private String manufacturer;
	private String model;
	private String otherNumber;
	private String serial;
	private String manufacturerState;
	private String extraInfo;
	
	
	public String getBrandName() {
		return this.brandName;
	}

	
	
	public String getCatalog() {
		return this.catalog;
	}

	
	public String getManufacturerCity() {
		return this.manufacturerCity;
	}

	
	public String getDeviceName() {
		return this.deviceName;
	}

	
	public String getDeviceOperator() {
		return this.deviceOperator;
	}

	
	public IDateDt getExpirationDate() {
		return this.expirationDate;
	}

	
	public IDateDt getExplantedDate() {
		return this.explantedDate;
	}

	
	public String getExtraInfo() {
		return this.extraInfo;
	}

	
	
	public IDateDt getImplantedDate() {
		return this.implantedDate;
	}

	
	
	public String getLot() {
		return this.lot;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	
	public String getModel() {
		return this.model;
	}

	public String getOtherNumber() {
		return this.otherNumber;
	}

	
	public String getSerial() {
		return this.serial;
	}

	public String getManufacturerState() {
		return this.manufacturerState;
	}

	
	public boolean isWasReused() {
		return this.isReused;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;

	}

	public void setCatalog(String catalog) {
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

	
	public void setLot(String lot) {
		this.lot = lot;

	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	
	public void setModel(String model) {
		this.model = model;

	}

	
	public void setOtherNumber(String otherNumber) {
		this.otherNumber = otherNumber;

	}

	
	public void setSerial(String serial) {
		this.serial = serial;

	}

	public void setManufacturerState(String manufacturerState) {
		this.manufacturerState = manufacturerState;

	}

	
	public void setWasReused(boolean wasReused) {
		this.isReused = wasReused;

	}

}
