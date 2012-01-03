package publichealthcomplaint.datatypes;

public interface ISpecialComplaintDt extends IComplaintDt{
	
	public IAddressDt getEnderecoOcorrencia();

	public short getIdade() ;
	
	public String getInstrucao() ;

	public String getOcupacao();

	public void setEnderecoOcorrencia(IAddressDt enderecoOcorrencia);

	public void setIdade(short idade);

	public void setInstrucao(String instrucao) ;

	public void setOcupacao(String ocupacao) ;

}
