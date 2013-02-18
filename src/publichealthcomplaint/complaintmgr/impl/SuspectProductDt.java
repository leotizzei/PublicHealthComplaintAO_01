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
		if( this.productName != null && this.productName.equals(""))
			return null;
		else
			return this.productName;
	}

	public void setProductName(String productName) {

		this.productName = productName;

	}

	public String getLabelStrength() {
		if(this.labelStrength != null && this.labelStrength.equals(""))
			return null;
		else
			return this.labelStrength;
	}


	public void setLabelStrength(String labelStrength) {

		this.labelStrength = labelStrength;

	}


	public String getManufacturer() {
		if( this.manufacturer != null && this.manufacturer.equals(""))
			return null;
		else
			return this.getManufacturer();

	}


	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;

	}


	public String getDose() {

		if(this.dose != null && this.dose.equals(""))
			return null;
		else
			return this.dose;
	}


	public void setDose(String dose) {
		this.dose = dose;

	}


	public String getFrequency() {

		if(this.frequency != null && this.frequency.equals("") )
			return null;
		else

			return this.frequency;
	}


	public void setFrequency(String frequency) {


		this.frequency = frequency;
	}


	public String getRoute() {

		if(this.route != null && this.route.equals(""))
			return null;
		else
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

		if( this.diagnosis != null && this.diagnosis.equals(""))
			return null;
		else

			return this.diagnosis;
	}


	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;

	}


	public String getEventAbated() {

		if(this.eventAbated != null && this.eventAbated.equals(""))
			return null;
		else
			return this.eventAbated;
	}


	public void setEventAbated(String eventAbated) {
		this.eventAbated = eventAbated;
	}


	public String getLot() {
		if( this.lot != null && this.lot.equals(""))
			return null;
		else
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
		if(this.eventReappeared != null && this.eventReappeared.equals(""))
			return null;
		else
			return this.eventReappeared;
	}


	public void setEventReappeared(String eventReappeared) {
		this.eventReappeared = eventReappeared;

	}


	public String getId() {
		if(this.id != null && this.id.equals(""))
			return null;
		else
			return this.id;
	}


	public void setId(String id) {
		this.id = id;

	}

}
