package publichealthcomplaint.userinterface.impl.util.impl;

import java.io.FileNotFoundException;

import publichealthcomplaint.datatypes.IAddressDt;
import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.exceptionhandling.impl.InvalidDateException;
import publichealthcomplaint.userinterface.impl.util.spec.prov.IUtil;

class FacadeUtil implements IUtil {

	
	public String getFileListReplace(String[] keywords, String[] newWords,
			String file) throws FileNotFoundException {
		return Library.getFileListReplace(keywords, newWords, file);
	}

	public IAddressDt createAddress(String street, String complement,
			String zip, String state, String phone, String city,
			String neighbourhood) {
		
		return new Address(street, complement, zip, state, phone, city, neighbourhood);
	}

	
	public IDateDt createDate(int segundo, int minuto, int hora, int dia,
			int mes, int ano) throws InvalidDateException {
		if( segundo < 0 || segundo > 59 || minuto < 0 || minuto > 59 || hora < 0 || hora > 23 || dia < 0 || dia > 31 || mes < 0 || mes > 12 || ano < 0  )
			throw new InvalidDateException("Wrong parameter");
			
			
		return new DateDt(segundo, minuto, hora, dia, mes, ano);
	}

}
