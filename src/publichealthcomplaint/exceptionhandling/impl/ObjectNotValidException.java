package publichealthcomplaint.exceptionhandling.impl;

public class ObjectNotValidException extends Exception {

	public ObjectNotValidException(String erro) {
		super("ExcecaoDados: " + erro);
	}
}
