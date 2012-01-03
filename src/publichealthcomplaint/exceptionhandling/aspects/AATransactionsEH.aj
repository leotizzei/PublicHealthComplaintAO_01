package publichealthcomplaint.exceptionhandling.aspects;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import publichealthcomplaint.exceptionhandling.impl.TransactionException;
import java.io.IOException;

public abstract aspect AATransactionsEH {

	// Makes soft all IO exceptions raised in this aspect
	declare soft : IOException : within(AATransactionsEH+);
	
	public abstract pointcut transactionsEH(HttpServletResponse response);
	
	
	void around(HttpServletResponse response):transactionsEH(response){ 
	/*: execution(void HWServlet+.doGet(HttpServletRequest, HttpServletResponse)) &&
	args(.., response)*/
		
		try {
			
			proceed(response);
			
        } catch (TransactionException e) {
        	PrintWriter out = response.getWriter();
            out.println("</select></p></center></div>");
            out.println("<P> " + e.getMessage() + " </P>");
		}
	}
	
}
