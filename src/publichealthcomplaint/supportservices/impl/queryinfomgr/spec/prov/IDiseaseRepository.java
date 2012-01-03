package publichealthcomplaint.supportservices.impl.queryinfomgr.spec.prov;

import publichealthcomplaint.datatypes.IDiseaseDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;

public interface IDiseaseRepository {


	/**
	 *  Repository exception is declared soft by publichealthcomplaint.persistence.aspects.HWDataCollection
	 * @param code
	 * @return
	 * @throws ObjectNotFoundException
	 */
	public IDiseaseDt search(int code) throws ObjectNotFoundException;

	/**
	 *  Repository exception is declared soft by publichealthcomplaint.persistence.aspects.HWDataCollection
	 * @return
	 * @throws ObjectNotFoundException
	 */
	public IteratorDsk getDiseaseTypeList() throws ObjectNotFoundException;
}


