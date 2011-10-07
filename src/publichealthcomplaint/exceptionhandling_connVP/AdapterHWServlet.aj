package publichealthcomplaint.exceptionhandling_connVP;

import javax.servlet.http.HttpServletResponse;

import publichealthcomplaint.exceptionhandling.aspects.AAPersistenceEH;
import publichealthcomplaint.userinterface.aspects.XPIHWServlet;

public aspect AdapterHWServlet extends AAPersistenceEH {

	public pointcut doGet(HttpServletResponse response):XPIHWServlet.doGet(response);

}
