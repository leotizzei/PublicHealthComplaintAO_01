package publichealthcomplaint.exceptionhandling.impl;

public class ObjectAlreadyInsertedException extends Exception {

	public ObjectAlreadyInsertedException(String erro) {
		super("ExcecaoDados: " + erro);
	}
}
