package publichealthcomplaint.userinterface.impl.infrastr.impl;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import publichealthcomplaint.datatypes.Constants;
import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.exceptionhandling.impl.InvalidSessionException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.userinterface.impl.infrastr.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.infrastr.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.infrastr.spec.req.IInfrastructureMgt;
import publichealthcomplaint.userinterface.impl.infrastr.spec.req.IUtil;





public class ServletLogin extends HttpServlet {

	private String[] keywords = {"##SYSTEM_ROOT##",	"##SERVLET_SERVER_PATH##", "##CLOSE##"};



	private String[] newWords;

	

	public static final String EMPLOYEE = "employee";

	public ServletLogin() {
		
		
		IManager mgr = ComponentFactory.createInstance(); 
	
		newWords = new String[4];
		newWords[0] = Constants.SYSTEM_ROOT;
		
		newWords[1] = Constants.SYSTEM_ACTION;
		
		newWords[2] = Constants.SERVLET_SERVER_PATH;
		IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
		newWords[3] = htmlPageMgt.closeAdministrator();
	}



	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[ServletLogin:doGet()] beginning...");
		PrintWriter out;
		HttpSession session = request.getSession(false);
		
		IManager mgr = ComponentFactory.createInstance(); 
		
		response.setContentType("text/html");

		out = response.getWriter();

		try {
			if (session == null) {
				throw new InvalidSessionException("Invalid Session! <br><a href=\""+Constants.SYSTEM_LOGIN+"\">Try again</a>");
			}            
			IUtil util = (IUtil) mgr.getRequiredInterface("IUtil");
			out.println(util.getFileListReplace(keywords, newWords, Constants.FORM_PATH+"MenuEmployee.html"));
		} catch (Exception e) {
			IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
			out.println(htmlPageMgt.errorPageAdministrator(e.getMessage()));
		} finally {           
			out.close();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {        
		System.out.println("[ServletLogin:doPost()] beginning...");
		PrintWriter out;
		HttpSession session = request.getSession(true);
		IManager mgr = ComponentFactory.createInstance(); 
		response.setContentType("text/html");

		out = response.getWriter();

		String login = request.getParameter("login");
		String password = request.getParameter("password");        

		IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
		
		try {
			
			IInfrastructureMgt infra = (IInfrastructureMgt) mgr.getRequiredInterface("IInfrastructureMgt");
			IEmployeeDt employee = infra.searchEmployee(login);

			if (employee.validatePassword(password)) {
				session.setAttribute(ServletLogin.EMPLOYEE, employee);
				session.putValue(ServletLogin.EMPLOYEE, employee);
				IUtil util = (IUtil) mgr.getRequiredInterface("IUtil");
				out.println(util.getFileListReplace(keywords, newWords, Constants.FORM_PATH+"MenuEmployee.html"));                
			} else {                                 
				out.println(htmlPageMgt.errorPage("Invalid password! <br><a href=\""+Constants.SYSTEM_LOGIN+"\">Try again</a>"));
			}
		} catch (ObjectNotFoundException e) {
			out.println(htmlPageMgt.errorPage("Invalid login! <br><a href=\""+Constants.SYSTEM_LOGIN+"\">Try again</a>"));
		} catch (FileNotFoundException e) {
			out.println(htmlPageMgt.errorPage(e.getMessage()));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(htmlPageMgt.errorPage(e.getMessage()));
		} finally {           
			out.close();
		}
	}
}