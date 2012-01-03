package publichealthcomplaint.exceptionhandling.impl;

public class SituationFacadeException extends Exception {

	public SituationFacadeException(String erro) {
		super("Excecao: " + erro);
	}
}