package publichealthcomplaint.userinterface.impl.util.impl;

import java.util.Calendar;
import java.util.GregorianCalendar;

import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.datatypes.IScheduleDt;
import publichealthcomplaint.exceptionhandling.impl.InvalidDateException;

class DateDt implements IDateDt{
	public static void main(String args[]) {
		try {
			Calendar agora = Calendar.getInstance();
			IDateDt d = new DateDt(agora.get(Calendar.DAY_OF_MONTH), agora.get(Calendar.MONTH), agora
					.get(Calendar.YEAR));
			System.out.println(d.toString());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

	private int dia;

	private int mes;

	private int ano;

	public IScheduleDt horario;

	/**
	 * Representa o formato dd/mm/aaaa
	 */
	public static final int FORMATO1 = 1;

	/**
	 * Representa o formato dd/mm/aaaa:hh:mi:ss
	 */
	public static final int FORMATO2 = 2;

	/**
	 * Representa o formato ddmmaaaa
	 */
	public static final int FORMATO3 = 3;

	/**
	 * Representa o formato ddmmaaaahhmiss
	 */
	public static final int FORMATO4 = 4;

	/**
	 * Representa o formato dd/mm/aaaa com base no aaaa/mm/dd
	 */
	public static final int FORMATO5 = 5;

	public int diferencaEmDias(IDateDt dtIni, IDateDt dtFim) {
		int diferenca = 0;

		while (dtIni.compara(dtFim) != 0) {
			dtIni = dtIni.proximaData();
			diferenca++;

			// ////$System.out.println(format(dtIni,1)+"--"+diferenca);
		}

		return diferenca;
	}

	public DateDt(int dia, int mes, int ano) throws InvalidDateException {
		this.dia = dia;
		this.mes = mes;
		this.ano = ano;
		this.horario = null;

		validaData(dia, mes, ano);
	}

	public DateDt(int segundo, int minuto, int hora, int dia, int mes, int ano)
	throws InvalidDateException {
		try {
			this.dia = dia;
			this.mes = mes;
			this.ano = ano;
			horario = new Schedule(segundo, minuto, hora);

			validaData(dia, mes, ano);
		} catch (InvalidDateException e) {
			throw new InvalidDateException(dia, mes, ano);
		}

	}

	public DateDt(String diaStr, String mesStr, String anoStr) throws InvalidDateException {
		try {
			this.dia = Integer.parseInt(diaStr);
			this.mes = Integer.parseInt(mesStr);
			this.ano = Integer.parseInt(anoStr);
			this.horario = null;
		} catch (Exception e) {
			throw new InvalidDateException(dia, mes, ano);
		}

		validaData(dia, mes, ano);
	}

	public DateDt(String segundoStr, String minutoStr, String horaStr, String diaStr, String mesStr,
			String anoStr) throws InvalidDateException {
		try {
			this.dia = Integer.parseInt(diaStr);
			this.mes = Integer.parseInt(mesStr);
			this.ano = Integer.parseInt(anoStr);
			this.horario = new Schedule(Integer.parseInt(segundoStr), Integer.parseInt(minutoStr),
					Integer.parseInt(horaStr));
		} catch (Exception e) {
			throw new InvalidDateException(dia, mes, ano);
		}

		validaData(dia, mes, ano);
	}

	public void addDias(IDateDt data, int dias) throws InvalidDateException {
		IDateDt novaData;

		novaData = data;

		while (dias > 0) {
			novaData = novaData.proximaData();
			dias = dias - 1;
		}

		IScheduleDt horario = novaData.getHorario();
		data = new DateDt(horario.getSegundo(), horario.getMinuto(), horario.getHora(), novaData.getDia(), novaData.getMes(), novaData.getAno());

	
	}




	/**
	 * Decrementa a data
	 */
	public IDateDt anteriorData() {
		IDateDt dataRetorno = null;
		int tag;
		int monat;
		int jahre;

		if (dia > 1) {
			tag = dia - 1;
			monat = mes;
			jahre = ano;
		} else if (dia == 1 && (mes != 1)) {
			tag = numeroDeDiasDoMes(mes - 1);
			monat = mes - 1;
			jahre = ano;
		} else {
			tag = 31;
			monat = 12;
			jahre = ano - 1;
		}

		try {
			dataRetorno = new DateDt(tag, monat, jahre);
		} catch (InvalidDateException die) {
		}

		return dataRetorno;
	}

	public int compara(IDateDt data) {
		int retorno = 0;

		if (ano > data.getAno()) {
			retorno = 1;
		} else if (ano < data.getAno()) {
			retorno = -1;
		} else {
			if (mes > data.getMes()) {
				retorno = 1;
			} else if (mes < data.getMes()) {
				retorno = -1;
			} else {
				if (dia > data.getDia()) {
					retorno = 1;
				} else if (dia < data.getDia()) {
					retorno = -1;
				}
			}
		}

		return retorno;
	}



	/**
	 * Defini��o do M�todo
	 * 
	 * 
	 * @param data
	 * 
	 * @return
	 */
	public long diferencaEmSegundos(IDateDt data) {
		int segundo2, minuto2, hora2, dia2, mes2, ano2;
		int segundo1, minuto1, hora1;
		int diferSegundo, diferMinuto, diferHora, diferDia, diferMes, diferAno;
		IScheduleDt horario2;
		long diferenca = 0;

		horario2 = data.getHorario();
		dia2 = data.getDia();
		mes2 = data.getMes();
		ano2 = data.getAno();

		if (horario2 != null) {
			segundo2 = horario2.getSegundo();
			minuto2 = horario2.getMinuto();
			hora2 = horario2.getHora();
		} else {
			segundo2 = 0;
			minuto2 = 0;
			hora2 = 0;
		}

		if (horario != null) {
			segundo1 = horario.getSegundo();
			minuto1 = horario.getMinuto();
			hora1 = horario.getHora();
		} else {
			segundo1 = 0;
			minuto1 = 0;
			hora1 = 0;
		}

		diferSegundo = segundo2 - segundo1;
		diferMinuto = minuto2 - minuto1;
		diferHora = hora2 - hora1;
		diferDia = dia2 - dia;
		diferMes = mes2 - mes;
		diferAno = ano2 - ano;
		diferenca = (diferSegundo + 60 * (diferMinuto + 60 * (diferHora + 24 * (diferDia + 30 * (diferMes + 12 * diferAno)))));

		return diferenca;
	}



	/**
	 * Defini��o do M�todo
	 * 
	 * 
	 * @return
	 */
	public boolean ehFinalDeSemana() {
		IDateDt data = null;

		try {
			data = new DateDt(dia, mes, ano);
		} catch (Exception e) {
		}

		return ehFinalDeSemana(data);
	}

	/**
	 * Verifica se a Date passada � umfinal de semana.
	 */
	public boolean ehFinalDeSemana(IDateDt data) {
		GregorianCalendar calendar = new GregorianCalendar();
		int diaDaSemana = 0;
		boolean retorno = false;

		calendar.set(data.getAno() - 1900, data.getMes(), data.getDia());

		// calendar.setTime(new Date(data.getAno()-1900,data.getMes(),data.getDia()));
		// calendar.setTime(new Date());
		diaDaSemana = calendar.get(Calendar.DAY_OF_WEEK);

		// ////$System.out.println(diaDaSemana);
		if (diaDaSemana == Calendar.SUNDAY || diaDaSemana == Calendar.SATURDAY) {
			retorno = true;
		}

		return retorno;
	}

	/**
	 * Retorna a representa��o string da data,
	 * recebe como par�metro um constante inteira que representa
	 * o formato data
	 */
	public String format(IDateDt data, int formato) {
		String diaStr = "", mesStr = "", anoStr = "";
		String texto = "";
		IScheduleDt horario;

		try {
			horario = data.getHorario();
			diaStr = String.valueOf(data.getDia());
			mesStr = String.valueOf(data.getMes());
			anoStr = String.valueOf(data.getAno());

			if (diaStr.length() < 2) {
				diaStr = "0" + diaStr;
			}

			if (mesStr.length() < 2) {
				mesStr = "0" + mesStr;
			}

			for (int i = anoStr.length(); i < 4; i++) {
				anoStr = "0" + anoStr;
			}

			switch (formato) {

			case (FORMATO1):
				texto = diaStr + "/" + mesStr + "/" + anoStr;

			break;

			case (FORMATO2):
				texto = diaStr + "/" + mesStr + "/" + anoStr;
			texto += ":" + horario.format(Schedule.FORMATO1);

			break;

			case (FORMATO3):
				texto = diaStr + mesStr + anoStr;

			break;

			case (FORMATO4):
				texto = diaStr + mesStr + anoStr;
			texto += horario.format(Schedule.FORMATO2);

			break;

			case (FORMATO5):
				texto = diaStr + "/" + mesStr + "/" + anoStr;

			break;

			default:
				texto = null;

				break;
			}
		} catch (NumberFormatException nb) {
		}

		return texto;
	}

	/**
	 * Defini��o do M�todo
	 * 
	 * 
	 * @param formato
	 * 
	 * @return
	 */
	public String format(int formato) {
		return format(this, formato);
	}

	/**
	 * Defini��o do M�todo
	 * 
	 * 
	 * @return
	 */
	public int getAno() {
		return ano;
	}

	/**
	 * Defini��o do M�todo
	 * 
	 * 
	 * @return
	 */
	public int getDia() {
		return dia;
	}

	/**
	 * Defini��o do M�todo
	 * 
	 * 
	 * @return
	 */
	public IScheduleDt getHorario() {
		return horario;
	}

	/**
	 * Defini��o do M�todo
	 * 
	 * 
	 * @return
	 */
	public int getMes() {
		return mes;
	}

	/**
	 * Retorna o n�mero de dias do m�s passado como par�metro.
	 */
	private int numeroDeDiasDoMes(int mes) {
		int retorno = -1;
		GregorianCalendar calendar = new GregorianCalendar();

		switch (mes) {

		case 1:
			retorno = 31;

			break;

		case 2:
			if (calendar.isLeapYear(ano)) {
				retorno = 29;
			} else {
				retorno = 28;
			}

			break;

		case 3:
			retorno = 31;

			break;

		case 4:
			retorno = 30;

			break;

		case 5:
			retorno = 31;

			break;

		case 6:
			retorno = 30;

			break;

		case 7:
			retorno = 31;

			break;

		case 8:
			retorno = 31;

			break;

		case 9:
			retorno = 30;

			break;

		case 10:
			retorno = 31;

			break;

		case 11:
			retorno = 30;

			break;

		case 12:
			retorno = 31;
		}

		return retorno;
	}

	/**
	 * Incrementa a Date
	 */
	public IDateDt proximaData() {
		IDateDt dataRetorno = null;
		int tag;
		int monat;
		int jahre;

		if (dia < this.numeroDeDiasDoMes(mes)) {
			tag = dia + 1;
			monat = mes;
			jahre = ano;
		} else if (dia == this.numeroDeDiasDoMes(mes) && (mes != 12)) {
			tag = 1;
			monat = mes + 1;
			jahre = ano;
		} else {
			tag = 1;
			monat = 1;
			jahre = ano + 1;
		}

		try {
			dataRetorno = new DateDt(tag, monat, jahre);
		} catch (InvalidDateException die) {
		}

		return dataRetorno;
	}

	/**
	 * Transforma string em data.
	 * recebe como par�metro o String e assume
	 * / como o separador utilizado.
	 */
	public IDateDt stringToData(String dataStr, int formato) throws InvalidDateException {
		String diaStr, mesStr, anoStr;
		String minutoStr, segundoStr, horaStr;
		DateDt data = null;

		try {
			switch (formato) {

			case (FORMATO1):
				diaStr = dataStr.substring(0, 2);
			mesStr = dataStr.substring(3, 5);
			anoStr = dataStr.substring(6, 10);
			data = new DateDt(diaStr, mesStr, anoStr);

			break;

			case (FORMATO2):
				diaStr = dataStr.substring(0, 2);
			mesStr = dataStr.substring(3, 5);
			anoStr = dataStr.substring(6, 10);
			horaStr = dataStr.substring(11, 13);
			minutoStr = dataStr.substring(14, 16);
			segundoStr = dataStr.substring(17, 19);
			data = new DateDt(segundoStr, minutoStr, horaStr, diaStr, mesStr, anoStr);

			break;

			case (FORMATO3):
				diaStr = dataStr.substring(0, 2);
			mesStr = dataStr.substring(2, 4);
			anoStr = dataStr.substring(4, 8);

			break;

			case (FORMATO4):
				diaStr = dataStr.substring(0, 2);
			mesStr = dataStr.substring(2, 4);
			anoStr = dataStr.substring(4, 8);
			horaStr = dataStr.substring(8, 10);
			minutoStr = dataStr.substring(10, 12);
			segundoStr = dataStr.substring(12, 14);
			data = new DateDt(segundoStr, minutoStr, horaStr, diaStr, mesStr, anoStr);

			break;

			case (FORMATO5):
				anoStr = dataStr.substring(0, 4);
			mesStr = dataStr.substring(5, 7);
			diaStr = dataStr.substring(8, 10);
			data = new DateDt(diaStr, mesStr, anoStr);

			break;

			default:
				data = null;

				break;
			}
		} catch (Exception nb) {
			throw new InvalidDateException(dataStr);
		}

		return data;
	}

	public String toString() {
		return dia + "/" + mes + "/" + ano;
	}

	/**
	 * Valida uma data (dia, mes e ano), caso algum dos valores seja
	 * inv�lido, lan�a a exce��o InvalidDateException
	 */
	private void validaData(int dia, int mes, int ano) throws InvalidDateException {
		if (1 == 2) {
			throw new InvalidDateException(dia, mes, ano);
		}
	}

}
