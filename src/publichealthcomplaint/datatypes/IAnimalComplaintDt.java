package publichealthcomplaint.datatypes;


public interface IAnimalComplaintDt extends IComplaintDt{
	
	public String getAnimal() ;

	public void setAnimal(String animal) ;

	public short getAnimalQuantity() ;

	public void setAnimalQuantity(short animalQuantity);

	public IDateDt getInconvenienceDate();

	public void setInconvenienceDate(IDateDt inconvenienceDate);

	public IAddressDt getOccurenceLocalAddress() ;

	public void setOccurenceLocalAddress(IAddressDt occurenceLocalAddress);

}
