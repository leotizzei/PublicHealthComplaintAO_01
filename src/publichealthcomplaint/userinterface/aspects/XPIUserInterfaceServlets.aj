package publichealthcomplaint.userinterface.aspects;




import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publichealthcomplaint.userinterface.impl.generalcomplaint.impl.ServletInsertAnimalComplaint;
import publichealthcomplaint.userinterface.impl.generalcomplaint.impl.ServletWebServer;
import publichealthcomplaint.userinterface.spec.req.IFacade;

public aspect XPIUserInterfaceServlets {
	
	public pointcut distribution(): call(* IFacade+.*(..)) && ! call(static * *.*(..));
	
	//public pointcut callingServlets():execution(void HttpServlet.doGet(HttpServletRequest, HttpServletResponse)) || execution(void HttpServlet.doPost(HttpServletRequest, HttpServletResponse)); 
	public pointcut callingServlets():execution(void ServletWebServer.doGet(HttpServletRequest, HttpServletResponse)) || execution(void ServletWebServer.doPost(HttpServletRequest, HttpServletResponse));

	
	public pointcut doPostGetExec(HttpServletRequest request):( 
			(execution(public void HttpServlet+.doPost(HttpServletRequest,HttpServletResponse)) || execution(public void HttpServlet+.doGet(HttpServletRequest,HttpServletResponse)) ) 
					&& target(request) );

	public pointcut doPostExec(): 
		(
				call(* IFacade+.*(..)) && ! call(static * *.*(..)) && within(ServletInsertAnimalComplaint)
				);
	
	
	
}
