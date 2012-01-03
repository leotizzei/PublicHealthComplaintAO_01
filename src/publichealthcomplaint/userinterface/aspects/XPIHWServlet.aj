package publichealthcomplaint.userinterface.aspects;



import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public aspect XPIHWServlet {
	
	public pointcut doGet(HttpServletResponse response): execution(* HttpServlet+.doGet(HttpServletRequest, HttpServletResponse)) &&
	args(.., response);
	
}
