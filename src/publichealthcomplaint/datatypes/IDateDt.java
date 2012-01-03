package publichealthcomplaint.datatypes;

import publichealthcomplaint.exceptionhandling.impl.InvalidDateException;


public interface IDateDt {


	public void addDias(IDateDt data, int dias) throws InvalidDateException ;

	/**
	 * Decrementa a data
	 */
	public IDateDt anteriorData() ;


	public int compara(IDateDt data);

	public int diferencaEmDias(IDateDt dtIni, IDateDt dtFim);

	/**
	 * Defini��o do M�todo
	 * 
	 * 
	 * @param data
	 * 
	 * @return
	 */
	public long diferencaEmSegundos(IDateDt data) ;

	/**
	 * Defini��o do M�todo
	 * 
	 * 
	 * @return
	 */
	public boolean ehFinalDeSemana();

	/**
	 * Verifica se a Date passada � umfinal de semana.
	 */
	public boolean ehFinalDeSemana(IDateDt data) ;

	/**
	 * Retorna a representa��o string da data,
	 * recebe como par�metro um constante inteira que representa
	 * o formato data
	 */
	public String format(IDateDt data, int formato) ;

	/**
	 * Defini��o do M�todo
	 * 
	 * 
	 * @param formato
	 * 
	 * @return
	 */
	public String format(int formato) ;

	/**
	 * Defini��o do M�todo
	 * 
	 * 
	 * @return
	 */
	public int getAno() ;

	/**
	 * Defini��o do M�todo
	 * 
	 * 
	 * @return
	 */
	public int getDia() ;

	/**
	 * Defini��o do M�todo
	 * 
	 * 
	 * @return
	 */
	public IScheduleDt getHorario() ;

	/**
	 * Retorna o n�mero de dias do m�s passado como par�metro.
	 */


	/**
	 * Defini��o do M�todo
	 * 
	 * 
	 * @return
	 */
	public int getMes() ;

	/**
	 * Incrementa a Date
	 */
	public IDateDt proximaData() ;

	/**
	 * Transforma string em data.
	 * recebe como par�metro o String e assume
	 * / como o separador utilizado.
	 */
	public  IDateDt stringToData(String dataStr, int formato) throws InvalidDateException ;

	/**
	 * Valida uma data (dia, mes e ano), caso algum dos valores seja
	 * inv�lido, lan�a a exce��o InvalidDateException
	 */


	public String toString();


}
