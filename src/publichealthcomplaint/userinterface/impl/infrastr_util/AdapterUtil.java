package publichealthcomplaint.userinterface.impl.infrastr_util;

import java.io.FileNotFoundException;

import publichealthcomplaint.datatypes.IAddressDt;
import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.exceptionhandling.impl.InvalidDateException;
import publichealthcomplaint.userinterface.impl.infrastr.spec.req.IUtil;

public class AdapterUtil implements IUtil {

	
	public IAddressDt createAddress(String street, String complement,
			String zip, String state, String phone, String city,
			String neighbourhood) {
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IUtil util = (publichealthcomplaint.userinterface.impl.util.spec.prov.IUtil) mgr.getRequiredInterface("IUtil");
		return util.createAddress(street, complement, zip, state, phone, city, neighbourhood);
	}

	
	public IDateDt createDate(int segundo, int minuto, int hora, int dia,
			int mes, int ano) throws InvalidDateException {
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IUtil util = (publichealthcomplaint.userinterface.impl.util.spec.prov.IUtil) mgr.getRequiredInterface("IUtil");
		return util.createDate(segundo, minuto, hora, dia, mes, ano);
	}

	
	public String getFileListReplace(String[] keywords, String[] newWords,
			String file) throws FileNotFoundException {
		IManager mgr = ComponentFactory.createInstance();
		publichealthcomplaint.userinterface.impl.util.spec.prov.IUtil util = (publichealthcomplaint.userinterface.impl.util.spec.prov.IUtil) mgr.getRequiredInterface("IUtil");
		return util.getFileListReplace(keywords, newWords, file);
	}

}
