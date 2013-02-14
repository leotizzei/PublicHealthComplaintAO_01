package publichealthcomplaint.complaintmgr.impl;

import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.datatypes.ISuspectProductDt;

public class SuspectProductDt implements ISuspectProductDt {

	private String productName;
	private String  labelStrength;
	private String manufacturer;
	private String  dose;
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
		
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;

	}

	public String getLabelStrength() {
		return this.labelStrength;
	}

	
	public void setLabelStrength(String labelStrength) {
		this.labelStrength = labelStrength;

	}

	
	public String getManufacturer() {
		return this.getManufacturer();
		
	}

	
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;

	}

	
	public String getDose() {
		return this.dose;
	}

	
	public void setDose(String dose) {
		this.dose = dose;

	}

	
	public String getFrequency() {
		return this.frequency;
	}

	
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	
	public String getRoute() {
		return this.route;
	}

	
	public void setRoute(String route) {
		this.route = route;

	}

	
	public IDateDt getStartUseDate() {
		return this.startUseDate;
	}

	
	public void setStartUseDate(IDateDt startUseDate) {
		this.startUseDate = startUseDate;

	}

	
	public IDateDt getEndUseDate() {
		return this.endUseDate;
	}

	
	public void setEndUseDate(IDateDt endUseDate) {
		this.endUseDate = endUseDate;

	}

	
	public String getDiagnosis() {
		return this.diagnosis;
	}

	
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;

	}

	
	public String getEventAbated() {
		return this.eventAbated;
	}

	
	public void setEventAbated(String eventAbated) {
		this.eventAbated = eventAbated;
	}

	
	public String getLot() {
		
		return this.lot;
	}

	
	public void setLot(String lot) {
		this.lot = lot;

	}

	
	public IDateDt getExpirationDate() {
		
		return this.expirationDate;
	}

	
	public void setExpirationDate(IDateDt expirationDate) {
		this.expirationDate = expirationDate;

	}

	
	public String getEventReappeared() {
		return this.eventReappeared;
	}

	
	public void setEventReappeared(String eventReappeared) {
		this.eventReappeared = eventReappeared;

	}

	
	public String getId() {
		return this.id;
	}

	
	public void setId(String id) {
		this.id = id;
	
	}

}
