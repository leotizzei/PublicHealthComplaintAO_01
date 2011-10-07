package publichealthcomplaint.supportservices.impl.queryinfomgr.impl;

import publichealthcomplaint.datatypes.Complaint;
import publichealthcomplaint.datatypes.IAddressDt;
import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.datatypes.IFoodComplaintDt;



class FoodComplaint extends Complaint implements IFoodComplaintDt{
	private int qtdeComensais;

	private int qtdeDoentes;

	private int qtdeInternacoes;

	private int qtdeObitos;

	private String localAtendimento;

	private String refeicaoSuspeita;

	private IAddressDt enderecoDoente;

	//construtor vazio
	public FoodComplaint() {
	}

	public FoodComplaint(String solicitante, String descricao, String observacao, String email,
			IEmployeeDt atendente, int situacao, IDateDt dataParecer, IDateDt dataQueixa,
			IAddressDt enderecoSolicitante, int qtdeComensais, int qtdeDoentes, int qtdeInternacoes,
			int qtdeObitos, String localAtendimento, String refeicaoSuspeita, IAddressDt enderecoDoente) {

		//inicializar tipo da queixa
		super(solicitante, descricao, observacao, email, atendente, situacao, dataParecer,
				dataQueixa, enderecoSolicitante);

		this.qtdeComensais = qtdeComensais;
		this.qtdeDoentes = qtdeDoentes;
		this.qtdeInternacoes = qtdeInternacoes;
		this.qtdeObitos = qtdeObitos;
		this.localAtendimento = localAtendimento;
		this.refeicaoSuspeita = refeicaoSuspeita;
		this.enderecoDoente = enderecoDoente;
	}

	public IAddressDt getEnderecoDoente() {
		return enderecoDoente;
	}

	public void setEnderecoDoente(IAddressDt enderecoDoente) {
		this.enderecoDoente = enderecoDoente;
	}

	public String getLocalAtendimento() {
		return localAtendimento;
	}

	public void setLocalAtendimento(String localAtendimento) {
		this.localAtendimento = localAtendimento;
	}

	public int getQtdeComensais() {
		return qtdeComensais;
	}

	public void setQtdeComensais(int qtdeComensais) {
		this.qtdeComensais = qtdeComensais;
	}

	public int getQtdeDoentes() {
		return qtdeDoentes;
	}

	public void setQtdeDoentes(int qtdeDoentes) {
		this.qtdeDoentes = qtdeDoentes;
	}

	public int getQtdeInternacoes() {
		return qtdeInternacoes;
	}

	public void setQtdeInternacoes(int qtdeInternacoes) {
		this.qtdeInternacoes = qtdeInternacoes;
	}

	public int getQtdeObitos() {
		return qtdeObitos;
	}

	public void setQtdeObitos(int qtdeObitos) {
		this.qtdeObitos = qtdeObitos;
	}

	public String getRefeicaoSuspeita() {
		return refeicaoSuspeita;
	}

	public void setRefeicaoSuspeita(String refeicaoSuspeita) {
		this.refeicaoSuspeita = refeicaoSuspeita;
	}

	
	public void setAtendente(IEmployeeDt atendente) {
		
		
	}

	
	
}
