package publichealthcomplaint.exceptionhandling.impl;

public class ObjectNotFoundException extends Exception {

	public ObjectNotFoundException(String erro) {
		super("ExcecaoDados: " + erro);
	}
}
