package publichealthcomplaint.exceptionhandling.impl;

public class PersistenceMechanismException extends Exception {

	public PersistenceMechanismException(String erro) {
		super("ExcecaoDados: " + erro);
	}
}
