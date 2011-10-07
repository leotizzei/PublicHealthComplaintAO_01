package publichealthcomplaint.datatypes;



public interface IDrugComplaintDt extends IComplaintDt {
	
	public void setComplainerWeight(int weight);

	public int getComplainerWeight();
	
	public void setGender(int genderCode);
	
	public int getGender();
	
	public IDrugDataDt getDrugData();
	
	public void setDrugData(IDrugDataDt drugData);
	
	public ISuspectProductDt getSuspectProduct();
	
	public void setSuspectProduct(ISuspectProductDt suspectProduct);
	
	public ISuspectMedicalDeviceDt getSuspectMedicalDevice();
	
	public void setSuspectMedicalDevice(ISuspectMedicalDeviceDt suspectMedicalDevice);
	
	
}
