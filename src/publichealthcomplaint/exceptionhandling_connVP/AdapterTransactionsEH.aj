package publichealthcomplaint.exceptionhandling_connVP;

import javax.servlet.http.HttpServletResponse;
import publichealthcomplaint.userinterface.aspects.XPIHWServlet;
import publichealthcomplaint.exceptionhandling.aspects.AATransactionsEH;;

public aspect AdapterTransactionsEH extends AATransactionsEH {

	public pointcut transactionsEH(HttpServletResponse response):XPIHWServlet.doGet(response);

}
