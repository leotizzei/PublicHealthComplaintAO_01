package publichealthcomplaint.userinterface.impl.generalcomplaint.impl;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publichealthcomplaint.datatypes.Constants;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IUtil;

public class ServletWebServer extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] keywords = { "##SYSTEM_ROOT##", "##SYSTEM_ACTION##",
			"##SERVLET_SERVER_PATH##", "##CLOSE##" };

	private String[] newWords; 

	private IHTMLPageMgt htmlPageMgt;
	
	private IUtil util;
	
    public ServletWebServer() {
    	System.out.println("Instantiating ServletWebServer...");
		IManager mgr = ComponentFactory.createInstance();
		util = (IUtil) mgr.getRequiredInterface("IUtil");
		
		htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
		newWords = new String[4];
		newWords[0] = Constants.SYSTEM_ROOT;
		newWords[1] = Constants.SYSTEM_ACTION;
		newWords[2] = Constants.SERVLET_SERVER_PATH;
		newWords[3] = htmlPageMgt.closeAdministrator();
	}
	
    public void init(ServletConfig config){
    	try {
			super.init();
		} catch (ServletException e) {
			e.printStackTrace();
		}
    }
    
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   	
        PrintWriter out;

        response.setContentType("text/html");
        
        String file = request.getParameter("file");
        
        if (file == null)
           	file = "index.html";
       
        
        out = response.getWriter();
        System.out.println("[ServletWebServer:doGet] requested file="+file);
        
        out.println(util.getFileListReplace(keywords, newWords, Constants.FORM_PATH+file));
        
        out.close();
        System.out.println("[ServletWebServer:doGet] End of doGet");
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("[ServletWebServer:doPost] begin...");
    	
        PrintWriter out;

        response.setContentType("text/html");

        String file = request.getParameter("file");
        System.out.println("[ServletWebServer:doPost] file="+file);
        out = response.getWriter();        
        
        out.println(util.getFileListReplace(keywords, newWords, Constants.FORM_PATH+file));
        out.close();
    }
}