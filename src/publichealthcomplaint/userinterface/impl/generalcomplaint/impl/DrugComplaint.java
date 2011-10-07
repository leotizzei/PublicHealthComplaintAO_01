package publichealthcomplaint.userinterface.impl.generalcomplaint.impl;

import publichealthcomplaint.datatypes.Complaint;
import publichealthcomplaint.datatypes.IDrugComplaintDt;
import publichealthcomplaint.datatypes.IDrugDataDt;
import publichealthcomplaint.datatypes.ISuspectMedicalDeviceDt;
import publichealthcomplaint.datatypes.ISuspectProductDt;

class DrugComplaint extends Complaint implements IDrugComplaintDt {

	private int complainerWeight;
	private int gender;
	private IDrugDataDt drugData;
	private ISuspectMedicalDeviceDt suspectMedicalDevice;
	private ISuspectProductDt suspectProduct;
	
	public int getComplainerWeight() {
	
		return complainerWeight;
	}

	
	public int getGender() {
	
		return gender;
	}


	public void setComplainerWeight(int weight) {
		complainerWeight = weight;

	}


	public void setGender(int genderCode) {
		gender = genderCode;

	}

	
	
	public IDrugDataDt getDrugData() {
		
		return drugData;
	}



	public ISuspectMedicalDeviceDt getSuspectMedicalDevice() {
		
		return suspectMedicalDevice;
	}


	
	public ISuspectProductDt getSuspectProduct() {
		
		return suspectProduct;
	}


	
	public void setDrugData(IDrugDataDt drugData) {
		
		this.drugData = drugData;
	}


	
	public void setSuspectMedicalDevice(
			ISuspectMedicalDeviceDt suspectMedicalDevice) {
	
		this.suspectMedicalDevice = suspectMedicalDevice;
		
	}


	
	public void setSuspectProduct(ISuspectProductDt suspectProduct) {
		
		this.suspectProduct = suspectProduct;
		
	}


}
