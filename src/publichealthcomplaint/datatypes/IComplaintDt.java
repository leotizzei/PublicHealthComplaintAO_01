package publichealthcomplaint.datatypes;




public interface IComplaintDt {
	
	public IEmployeeDt getAtendente() ;

	public void setAtendente(IEmployeeDt atendente) ;

	public int getCodigo();

	public void setCodigo(int codigo) ;

	public IDateDt getDataParecer() ;

	public void setDataParecer(IDateDt dataParecer);

	public IDateDt getDataQueixa() ;

	public void setDataQueixa(IDateDt dataQueixa) ;

	public String getDescricao() ;

	public void setDescricao(String descricao);

	public String getEmail();

	public void setEmail(String email);

	public IAddressDt getEnderecoSolicitante() ;

	public void setEnderecoSolicitante(IAddressDt enderecoSolicitante);

	public String getObservacao() ;

	public void setObservacao(String observacao);

	public int getSituacao();

	public void setSituacao(int situacao);

	public String getSolicitante();

	public void setSolicitante(String solicitante);

}
