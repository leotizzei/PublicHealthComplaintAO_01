package publichealthcomplaint.userinterface.impl.infrastr.spec.req;


public interface IHTMLPageMgt {
	

	public String close();

	public String closeAdministrator() ;

	public String closeQueries();

	public String errorPage(String message) ;

	public String errorPageAdministrator(String message) ;

	public String errorPageQueries(String message);
	
	public String htmlPage(String title, String text);

	public String htmlPage(String title, String text, int pageType);

	public String htmlPageAdministrator(String title, String text) ;

	public String htmlPageQueries(String title, String text) ;

	public String link(String url, String description);
	
	public String open() ;

	public String open(String title) ;

	public String open(String title, String bgColor) ;

}
