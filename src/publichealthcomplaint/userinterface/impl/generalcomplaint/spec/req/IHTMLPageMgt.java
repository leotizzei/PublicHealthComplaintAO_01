package publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req;


public interface IHTMLPageMgt {
	

	

	public String close();

	public String closeAdministrator() ;

	public String errorPage(String message) ;
	
	public String errorPageAdministrator(String message) ;
	
	public String errorPageQueries(String message);

	public String htmlPage(String title, String text);

	public String htmlPageAdministrator(String title, String text) ;

	public String open(String title) ;

	

}
