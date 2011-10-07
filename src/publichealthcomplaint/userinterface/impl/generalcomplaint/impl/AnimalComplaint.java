package publichealthcomplaint.userinterface.impl.generalcomplaint.impl;

import publichealthcomplaint.datatypes.Complaint;
import publichealthcomplaint.datatypes.IAddressDt;
import publichealthcomplaint.datatypes.IAnimalComplaintDt;
import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.datatypes.IEmployeeDt;


class AnimalComplaint extends Complaint implements IAnimalComplaintDt {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private short animalQuantity;

	private IDateDt inconvenienceDate;

	private String animal;

	private IAddressDt occurenceLocalAddress;

	// construtor vazio
	public AnimalComplaint() {
	}

	public AnimalComplaint(String solicitante, String descricao, String observacao, String email,
			IEmployeeDt atendente, int situacao, IDateDt dataParecer, IDateDt dataQueixa,
			IAddressDt enderecoSolicitante, short animalQuantity, IDateDt inconvenienceDate,
			String animal, IAddressDt occurenceLocalAddress) {

		// inicializar tipo da queixa
		super(solicitante, descricao, observacao, email, atendente, situacao, dataParecer,
				dataQueixa, enderecoSolicitante);

		this.animalQuantity = animalQuantity;
		this.inconvenienceDate = inconvenienceDate;
		this.animal = animal;
		this.occurenceLocalAddress = occurenceLocalAddress;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public short getAnimalQuantity() {
		return animalQuantity;
	}

	public void setAnimalQuantity(short animalQuantity) {
		this.animalQuantity = animalQuantity;
	}

	public IDateDt getInconvenienceDate() {
		return inconvenienceDate;
	}

	public void setInconvenienceDate(IDateDt inconvenienceDate) {
		this.inconvenienceDate = inconvenienceDate;
	}

	public IAddressDt getOccurenceLocalAddress() {
		return occurenceLocalAddress;
	}

	public void setOccurenceLocalAddress(IAddressDt occurenceLocalAddress) {
		this.occurenceLocalAddress = occurenceLocalAddress;
	}

}