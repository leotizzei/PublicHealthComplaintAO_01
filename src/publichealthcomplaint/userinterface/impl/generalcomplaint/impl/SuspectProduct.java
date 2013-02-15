package publichealthcomplaint.userinterface.impl.generalcomplaint.impl;

import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.datatypes.ISuspectProductDt;

class SuspectProduct implements ISuspectProductDt{
	
	private String productName;
	
	private String labelStrength;
	
	private String manufacturer;
	
	private String dose;
	
	private String frequency;
	
	private String route;
	
	private IDateDt startUseDate;
	
	private IDateDt endUseDate;
	
	private String diagnosis;
	
	private String eventAbated;
	
	private String lot;
	
	private IDateDt expirationDate;
	
	private String eventReappeared;
	
	private String id;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getLabelStrength() {
		return labelStrength;
	}

	public void setLabelStrength(String labelStrength) {
		this.labelStrength = labelStrength;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDose() {
		return dose;
	}

	public void setDose(String dose) {
		this.dose = dose;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public IDateDt getStartUseDate() {
		return startUseDate;
	}

	public void setStartUseDate(IDateDt startUseDate) {
		this.startUseDate = startUseDate;
	}

	public IDateDt getEndUseDate() {
		return endUseDate;
	}

	public void setEndUseDate(IDateDt endUseDate) {
		this.endUseDate = endUseDate;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getEventAbated() {
		return eventAbated;
	}

	public void setEventAbated(String eventAbated) {
		this.eventAbated = eventAbated;
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

	public String getEventReappeared() {
		return eventReappeared;
	}

	public void setEventReappeared(String eventReappeared) {
		this.eventReappeared = eventReappeared;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
}
