package publichealthcomplaint.datatypes;

public interface IFoodComplaintDt extends IComplaintDt {

	
	public IAddressDt getEnderecoDoente() ;

	public String getLocalAtendimento() ;

	public int getQtdeComensais() ;

	public int getQtdeDoentes() ;

	public int getQtdeInternacoes();

	public int getQtdeObitos() ;

	public String getRefeicaoSuspeita();

	public void setEnderecoDoente(IAddressDt enderecoDoente);

	public void setLocalAtendimento(String localAtendimento) ;

	public void setQtdeComensais(int qtdeComensais);

	public void setQtdeDoentes(int qtdeDoentes);

	public void setQtdeInternacoes(int qtdeInternacoes) ;

	public void setQtdeObitos(int qtdeObitos) ;

	public void setRefeicaoSuspeita(String refeicaoSuspeita) ;
}
