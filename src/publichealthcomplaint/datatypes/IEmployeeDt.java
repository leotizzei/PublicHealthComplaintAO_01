package publichealthcomplaint.datatypes;

public interface IEmployeeDt {
	
	public String getName() ;

	public void setName(String name);

	public String getLogin() ;

	public void setLogin(String login) ;

	public void setPassword(String password);

	public String getPassword();

	public boolean validatePassword(String password);

}
