package publichealthcomplaint.datatypes;

import java.util.List;

public interface IHealthUnitDt {
	
	public boolean hasSpeciality(int code);
	
	public int getCode();

	public String getDescription();
	

	
	public List getSpecialities();
	

	public void setCode(int cod);
	

	public void setDescription(String descricao);
	

	public String toString();
	

}
