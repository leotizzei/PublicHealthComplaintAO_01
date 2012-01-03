package publichealthcomplaint.exceptionhandling.impl;

public class InvalidDateException extends Exception {

	public InvalidDateException(int segundos, int minutos, int hora) {
		super("ExcecaoHora: ");
	}

	public InvalidDateException(String erro) {
		super("ExcecaoHora:" + erro);
	}
}