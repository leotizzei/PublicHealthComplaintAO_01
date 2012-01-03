package publichealthcomplaint.exceptionhandling.impl;

public class InvalidSessionException extends Exception {

	public InvalidSessionException(String erro) {
		super(erro);
	}

	public InvalidSessionException() {
		this("Invalid session, go to the first page!");
	}
}