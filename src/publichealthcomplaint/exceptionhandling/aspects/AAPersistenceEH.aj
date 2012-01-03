package publichealthcomplaint.exceptionhandling.aspects;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import publichealthcomplaint.exceptionhandling.impl.RepositoryException;


public abstract aspect AAPersistenceEH {
	
	// Makes soft all IO exceptions raised in this aspect
	declare soft : IOException : within(AAPersistenceEH+);
	

	
	private static final long serialVersionUID = 1L;

	//protected IFacade facade = null;

	public abstract pointcut doGet(HttpServletResponse response);
	
	void around(HttpServletResponse response) : doGet(response){
				
		try {
			
			proceed(response);
			
        } catch (RepositoryException e) {
        	PrintWriter out = response.getWriter();
            out.println("</select></p></center></div>");
            out.println("<P> " + e.getMessage() + " </P>");
		}
	}
	
	/*private void init(ServletConfig config) throws ServletException {
		System.out.println("[HWServlet:init()]");
		if(facade == null){
			System.out.println("[HWServlet:init()] facade is null");
			//initialize client architecture
			Main main = Main.getInstance();
			if( main.initialized == false){
				System.out.println("[HWServlet:init()] facade is null and main.initialized is false");
				main.initClientArchitectureConfiguration();
			}
			//get userinterface required interface
			IManager mgr = ComponentFactory.createInstance();
			this.facade =  (IFacade) mgr.getRequiredInterface("IFacade");
		}

	}*/

}
