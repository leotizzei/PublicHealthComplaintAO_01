package publichealthcomplaint.exceptionhandling.impl;

/**
 * @author scbs
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class PersistenceSoftException extends RuntimeException {

	/**
	 * Constructor for PersistenceSoftException.
	 * @param arg0
	 */
	public PersistenceSoftException(Throwable arg0) {
		super(arg0);
	}

}
