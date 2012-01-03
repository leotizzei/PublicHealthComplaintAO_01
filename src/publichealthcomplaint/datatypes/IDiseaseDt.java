package publichealthcomplaint.datatypes;

import java.util.List;

public interface IDiseaseDt {
	
	public void delete();

	public int getCode() ;

	public void setCode(int codigo);

	public String getDescription() ;

	public void setDescription(String descricao) ;

	public String getDuration() ;

	public void setDuration(String duracao) ;

	public String getManifestation();

	public void setManifestation(String manifestacao);

	public String getName() ;

	public void setName(String nome) ;

	public List getSymptoms() ;

	public void setSymptoms(List sintomas);

}
