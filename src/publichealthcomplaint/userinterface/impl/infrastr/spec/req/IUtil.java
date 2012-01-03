package publichealthcomplaint.userinterface.impl.infrastr.spec.req;

import java.io.FileNotFoundException;

import publichealthcomplaint.datatypes.IAddressDt;
import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.exceptionhandling.impl.InvalidDateException;

public interface IUtil {

	public String getFileListReplace(String[] keywords, String[] newWords, String file)	throws FileNotFoundException ;
	
	public IAddressDt createAddress(String street, String complement, String zip, String state, String phone, String city, String neighbourhood);
	
	public IDateDt createDate(int segundo, int minuto, int hora, int dia, int mes, int ano) throws InvalidDateException;
}
