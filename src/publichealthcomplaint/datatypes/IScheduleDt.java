package publichealthcomplaint.datatypes;

import publichealthcomplaint.exceptionhandling.impl.InvalidDateException;

public interface IScheduleDt {
	
	public int compara(IScheduleDt horario) ;

	/**
	 * Retorna a representacao string da hora que foi recebida
	 * como parametro no formato representado pela constante formato.
	 */
	public String format(int formato) ;
	/**
	 * Retorna a representacao string da hora que foi recebida
	 * como parametro no formato representado pela constante formato.
	 */
	public String format(IScheduleDt horario, int formato) ;
	/**
	 * Definicao do Metodo
	 *
	 *
	 * @return
	 *
	 * @see
	 */
	public int getHora() ;

	/**
	 * Definicao do Metodo
	 *
	 *
	 * @return
	 *
	 * @see
	 */
	public int getMinuto();
	/**
	 * Definicao do Metodo
	 *
	 *
	 * @return
	 *
	 * @see
	 */
	public int getSegundo() ;
	/**
	 * Transforma string em data.
	 * recebe como parametro o String e o separador utilizado.
	 */
	public IScheduleDt stringToHorario(String horarioStr, int formato) throws InvalidDateException ;

	

}
