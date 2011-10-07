package publichealthcomplaint.datatypes;

/**
 * This constants define system specific configurations.
 */
public class Constants {

	// Database Configuration
	
	//public static final String DB_URL = "jdbc:odbc:test";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/test";
	
	//public static final String DB_LOGIN = "orbi2";
	public static final String DB_LOGIN = "root";
	
	//public static final String DB_PASS = "orbi2";
	public static final String DB_PASS = "EgdmcT";
	
	//public static final String DB_DRIVER = "sun.jdbc.odbc.JdbcOdbcDriver";
	public static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	
	// RMI Configuration
	public static final String SERVER_NAME = "localhost";
	public static final String SYSTEM_NAME = "PublicHealthComplaint";

	// SERVLETS Configuration
	
	// You should point this path to the base of the forms in your system
	
	public static final String FORM_PATH = "/home/leonardo/workspace3/PublicHealthComplaintAO_01/web/publichealthcomplaint/forms/first/";
	

	
	//public static final String SERVLET_SERVER_PATH = "localhost:8080/PHC-v01/publichealthcomplaint.userinterface.impl.";
	public static final String SERVLET_SERVER_PATH = "localhost:8080/PHC-v01/";
	

	public static final String SYSTEM_ROOT = "http://" + SERVLET_SERVER_PATH + "ServletWebServer?file=";


	public static final String SYSTEM_ACTION = "http://" + SERVLET_SERVER_PATH + "HWServlet";

	public static final String SYSTEM_INDEX = "http://"+SERVLET_SERVER_PATH + "ServletWebServer?file=index.html";
	

	public static final String SYSTEM_INDEX_ADMINISTRATOR = "http://"+SERVLET_SERVER_PATH+"ServletLogin";

	public static final String SYSTEM_LOGIN = SYSTEM_ROOT + "Login.html";
	public static final String SYSTEM_QUERIES = SYSTEM_ROOT + "QueriesMenu.html";
	
	/**
	 * Complaint status
	 */
	public static final int OPEN_COMPLAINT = 1;
	public static final int SUSPENDED_COMPLAINT = 2;
	public static final int CLOSED_COMPLAINT = 3;
	
	/*RSS feature*/
	public static final String RSSLink = "http://localhost:8080/PHC-v01/rss";
	public static final String RSSAbsolutePath = "/home/leonardo/workspace3/PublicHealthComplaintAO_01/conf/rss/phcfeeds.xml";
	public static final String RSSRelativePath = "rss/phcfeeds.xml";
	/**
	 * Defines if the system should be persistent or not (use DB or not)
	 * 
	 * @return true, if the system should use DB, false otherwise
	 */
	public static boolean isPersistent() {
		return true;
	}
}
