package publichealthcomplaint.userinterface.impl.generalcomplaint.impl;

import publichealthcomplaint.datatypes.Complaint;
import publichealthcomplaint.datatypes.IAddressDt;
import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.datatypes.ISpecialComplaintDt;



class SpecialComplaint extends Complaint implements ISpecialComplaintDt {

	private short idade;

	private String instrucao;

	private String ocupacao;

	private IAddressDt enderecoOcorrencia;

	//construtor vazio
	public SpecialComplaint() {
	}

	public SpecialComplaint(String solicitante, String descricao, String observacao, String email,
			IEmployeeDt atendente, int situacao, IDateDt dataParecer, IDateDt dataQueixa,
			IAddressDt enderecoSolicitante, short idade, String instrucao, String ocupacao,
			IAddressDt enderecoOcorrencia) {

		//inicializar tambem o tipo da queixa
		super(solicitante, descricao, observacao, email, atendente, situacao, dataParecer,
				dataQueixa, enderecoSolicitante);

		this.idade = idade;
		this.instrucao = instrucao;
		this.ocupacao = ocupacao;
		this.enderecoOcorrencia = enderecoOcorrencia;
	}

	public IAddressDt getEnderecoOcorrencia() {
		return enderecoOcorrencia;
	}

	public void setEnderecoOcorrencia(IAddressDt enderecoOcorrencia) {
		this.enderecoOcorrencia = enderecoOcorrencia;
	}

	public short getIdade() {
		return idade;
	}

	public void setIdade(short idade) {
		this.idade = idade;
	}

	public String getInstrucao() {
		return instrucao;
	}

	public void setInstrucao(String instrucao) {
		this.instrucao = instrucao;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}

	
	
}