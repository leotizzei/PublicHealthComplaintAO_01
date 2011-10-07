package publichealthcomplaint.datatypes;


//classe queixa eh uma classe basica que tem subclasses
public abstract class Complaint implements IComplaintDt{

	public static final int QUEIXA_ALIMENTAR = 1;

	public static final int QUEIXA_ANIMAL = 2;

	public static final int QUEIXA_DIVERSA = 3;

	public static final int QUEIXA_DROGA = 4;
	
	private int codigo;

	private String solicitante;

	private String descricao;

	private String observacao;

	private String email;

	private IEmployeeDt atendente;

	private int situacao;

	private IDateDt dataParecer;

	private IDateDt dataQueixa;

	private IAddressDt enderecoSolicitante;

	public Complaint() {
	}

	public Complaint(String solicitante, String descricao, String observacao, String email,
			IEmployeeDt atendente, int situacao, IDateDt dataParecer, IDateDt dataQueixa,
			IAddressDt enderecoSolicitante) {

		//Numero fica vazio por enquanto - no Repositorio ele eh inicializado
		this.codigo = 0;
		this.solicitante = solicitante;
		this.descricao = descricao;
		this.observacao = observacao;
		this.email = email;
		this.atendente = atendente;
		this.situacao = situacao;
		this.dataParecer = dataParecer;
		this.dataQueixa = dataQueixa;
		this.enderecoSolicitante = enderecoSolicitante;
	}

	public IEmployeeDt getAtendente() {
		System.out.println("[Complaint:getAtendente()]");
		return atendente;
	}

	public void setAtendente(IEmployeeDt atendente) {
		System.out.println("[Complaint:setAtendente("+atendente.getLogin()+")]");
		this.atendente = atendente;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public IDateDt getDataParecer() {
		return dataParecer;
	}

	public void setDataParecer(IDateDt dataParecer) {
		this.dataParecer = dataParecer;
	}

	public IDateDt getDataQueixa() {
		return dataQueixa;
	}

	public void setDataQueixa(IDateDt dataQueixa) {
		this.dataQueixa = dataQueixa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public IAddressDt getEnderecoSolicitante() {
		return enderecoSolicitante;
	}

	public void setEnderecoSolicitante(IAddressDt enderecoSolicitante) {
		this.enderecoSolicitante = enderecoSolicitante;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public int getSituacao() {
		return situacao;
	}

	public void setSituacao(int situacao) {
		this.situacao = situacao;
	}

	public String getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}
}