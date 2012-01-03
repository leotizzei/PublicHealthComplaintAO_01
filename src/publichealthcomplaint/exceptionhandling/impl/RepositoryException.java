package publichealthcomplaint.exceptionhandling.impl;

public class RepositoryException extends Exception {

	public RepositoryException(String erro) {
		super("ExcecaoDados: " + erro);
	}
}
