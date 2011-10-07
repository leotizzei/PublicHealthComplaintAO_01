package publichealthcomplaint.userinterface.impl.generalcomplaint.impl;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import publichealthcomplaint.datatypes.Constants;
import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.exceptionhandling.impl.InvalidSessionException;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IUpdate;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IUtil;
import publichealthcomplaint.userinterface.impl.infrastr.impl.ServletLogin;




public class ServletUpdateComplaintData extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);

		String obsQueixa = request.getParameter("obsQueixa");
		IComplaintDt q = null;

		response.setContentType("text/html");
		
		IManager mgr = ComponentFactory.createInstance();
		IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
		
		try {
			if (session == null) {
				throw new InvalidSessionException();
			}

			q = (IComplaintDt) session.getValue(ServletUpdateComplaintSearch.QUEIXA);
			
			
			q.setObservacao(obsQueixa);
			q.setSituacao(Constants.CLOSED_COMPLAINT);
			Calendar agora = Calendar.getInstance();
			
			
			IUtil util = (IUtil) mgr.getRequiredInterface("IUtil");
			IDateDt currentDateDt = util.createDate(agora.get(Calendar.SECOND), agora.get(Calendar.MINUTE), agora.get(Calendar.HOUR), agora.get(Calendar.DAY_OF_MONTH),
					agora.get(Calendar.MONTH), agora.get(Calendar.YEAR));
			q.setDataParecer( currentDateDt );
			IEmployeeDt employee = (IEmployeeDt) session.getValue(ServletLogin.EMPLOYEE);
			if( employee == null){
				
				employee = (IEmployeeDt) session.getAttribute(ServletLogin.EMPLOYEE);
				
			}
			
			q.setAtendente(employee);
			
			IUpdate update = (IUpdate) mgr.getRequiredInterface("IUpdate");
			update.updateComplaint(q);

			
			out.println(htmlPageMgt.htmlPageAdministrator("Operation executed", "Complaint updated"+"<P>" + obsQueixa + "</P>"));
		} catch(Exception e){	
			out.println(htmlPageMgt.errorPageAdministrator(e.getMessage()));
			e.printStackTrace(out);
		} finally {
			out.close();
		}   
	}
}
