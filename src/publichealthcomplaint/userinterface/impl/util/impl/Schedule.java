package publichealthcomplaint.userinterface.impl.util.impl;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import publichealthcomplaint.datatypes.IScheduleDt;
import publichealthcomplaint.exceptionhandling.impl.InvalidDateException;


class Schedule implements IScheduleDt, Serializable {

	private int segundo;

	private int hora;

	private int minuto;

	/**
	 * Representa o formato hh:mi:ss
	 */
	public static final int FORMATO1 = 1;

	/**
	 * Representa o formato hhmiss
	 */
	public static final int FORMATO2 = 2;

	public Schedule() {

		GregorianCalendar calendar = new GregorianCalendar();

		hora = calendar.get(Calendar.HOUR_OF_DAY);
		minuto = calendar.get(Calendar.MINUTE);
		segundo = calendar.get(Calendar.SECOND);
	}

	public Schedule(int segundo, int minuto, int hora) throws InvalidDateException {

		this.segundo = segundo;
		this.minuto = minuto;
		this.hora = hora;

		validaHorario(segundo, minuto, hora);
	}

	public Schedule(String segundoStr, String minutoStr, String horaStr)
			throws InvalidDateException {

		try {
			this.segundo = Integer.parseInt(segundoStr);
			this.minuto = Integer.parseInt(minutoStr);
			this.hora = Integer.parseInt(horaStr);

			validaHorario(segundo, minuto, hora);
		} catch (NumberFormatException ne) {
			throw new InvalidDateException(segundo, minuto, hora);
		}
	}

	public int compara(IScheduleDt horario) {

		int retorno = 0;

		if (hora > horario.getHora()) {
			retorno = 1;
		} else if (hora < horario.getHora()) {
			retorno = -1;
		} else {
			if (minuto > horario.getMinuto()) {
				retorno = 1;
			} else if (minuto < horario.getMinuto()) {
				retorno = -1;
			} else {
				if (segundo > horario.getSegundo()) {
					retorno = 1;
				} else if (segundo < horario.getSegundo()) {
					retorno = -1;
				}
			}
		}

		return retorno;
	}

	/**
	 * Retorna a representacao string da hora que foi recebida
	 * como parametro no formato representado pela constante formato.
	 */
	public String format(int formato) {
		return format(this, formato);
	}

	/**
	 * Retorna a representacao string da hora que foi recebida
	 * como parametro no formato representado pela constante formato.
	 */
	public String format(IScheduleDt horario, int formato) {

		String segundoStr = "", minutoStr = "", horaStr = "";
		String texto = null;

		try {
			segundoStr = String.valueOf(horario.getSegundo());
			minutoStr = String.valueOf(horario.getMinuto());
			horaStr = String.valueOf(horario.getHora());

			if (segundoStr.length() < 2) {
				segundoStr = "0" + segundoStr;
			}

			if (minutoStr.length() < 2) {
				minutoStr = "0" + minutoStr;
			}

			if (horaStr.length() < 2) {
				horaStr = "0" + horaStr;
			}
		} catch (NumberFormatException nb) {
		}

		switch (formato) {

		case (FORMATO1):
			texto = horaStr + ":" + minutoStr + ":" + segundoStr;
			break;

		case (FORMATO2):
			texto = horaStr + minutoStr + segundoStr;
			break;

		default:
			texto = null;
			break;
		}

		return texto;
	}

	/**
	 * Definicao do Metodo
	 *
	 *
	 * @return
	 *
	 * @see
	 */
	public int getHora() {
		return hora;
	}

	/**
	 * Definicao do Metodo
	 *
	 *
	 * @return
	 *
	 * @see
	 */
	public int getMinuto() {
		return minuto;
	}

	/**
	 * Definicao do Metodo
	 *
	 *
	 * @return
	 *
	 * @see
	 */
	public int getSegundo() {
		return segundo;
	}

	/**
	 * Transforma string em data.
	 * recebe como parametro o String e o separador utilizado.
	 */
	public IScheduleDt stringToHorario(String horarioStr, int formato)
			throws InvalidDateException {

		String segundoStr = null, minutoStr = null, horaStr = null;
		IScheduleDt horario = null;

		try {
			switch (formato) {

			case (Schedule.FORMATO1):
				horaStr = horarioStr.substring(0, 2);
				minutoStr = horarioStr.substring(3, 5);
				segundoStr = horarioStr.substring(6, 8);
				break;

			case (Schedule.FORMATO2):
				segundoStr = horarioStr.substring(0, 2);
				minutoStr = horarioStr.substring(2, 4);
				horaStr = horarioStr.substring(4, 6);
				break;

			default:
				horario = null;
				break;
			}

			horario = new Schedule(segundoStr, minutoStr, horaStr);
		} catch (Exception nb) {
			throw new InvalidDateException(horarioStr);
		}

		return horario;
	}

	/**
	 * Valida uma data (dia, mes e ano), caso algum dos valores seja
	 * invalido lanca a excecao DataInvalidaException
	 */
	private void validaHorario(int segundo, int minuto, int hora) throws InvalidDateException {

		if (!((segundo >= 0) && (segundo <= 59) && (minuto >= 0) && (minuto <= 59) && (hora >= 0) && (hora <= 23))) {
			throw new InvalidDateException(segundo, minuto, hora);
		}
	}
}