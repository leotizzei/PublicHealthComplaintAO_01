package publichealthcomplaint.userinterface.impl.generalcomplaint.impl;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import publichealthcomplaint.datatypes.Constants;
import publichealthcomplaint.datatypes.IAnimalComplaintDt;
import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.datatypes.IFoodComplaintDt;
import publichealthcomplaint.datatypes.ISpecialComplaintDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.InvalidSessionException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.SituationFacadeException;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IUpdate;





public class ServletUpdateComplaintSearch extends HttpServlet {

	public static final String QUEIXA = "queixa";

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);
		response.setContentType("text/html");

		IManager mgr = ComponentFactory.createInstance();
		IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
		try {



			if (session == null) {
				throw new InvalidSessionException();
			}
			out.println(htmlPageMgt.open("Queries - Complaint information"));
			out.println("<body><h1>Queries:<br>Querie about complaint</h1>");
			out.println("<p>Choose a complaint: </p>");
			out.println("<form method=\"POST\" action=\"http://"+Constants.SERVLET_SERVER_PATH+"ServletUpdateComplaintSearch\">");



			IUpdate update = (IUpdate) mgr.getRequiredInterface("IUpdate");

			IteratorDsk repTP = update.getComplaintList();


			if (repTP==null||!repTP.hasNext()) {                
				out.println("<p><center><font color=\"red\"><b> There isn't any complaints.</b></font></center></p>");


			} else {
				out.println("<div align=\"center\"><center><p><select name=\"numQueixa\" size=\"1\">");            	
				IComplaintDt tp;
				do {
					tp = (IComplaintDt) repTP.next();
					out.println("<option value=\""      +
							tp.getCodigo() + "\"> " + 
							tp.getDescricao()      +
							" </OPTION>");                    
				} while (repTP.hasNext());
				repTP.close();

				out.println("</select></p></center></div>");
				out.println("  <div align=\"center\"><center><p><input type=\"submit\" value=\"Search\" name=\"B1\"></p></center></div></form>");

			}
			out.println(htmlPageMgt.closeAdministrator());            
		} catch (ObjectNotFoundException e) {



			out.println(htmlPageMgt.errorPageQueries("There isn't any complaints"));
		}catch(InvalidSessionException e){

			out.println(htmlPageMgt.errorPageAdministrator("<p>Invalid Session! <br>You must <a href=\""+Constants.SYSTEM_LOGIN+"\">login</a> again!"));
		}catch(Exception e){
			out.println(htmlPageMgt.errorPageQueries("There isn't any complaints"));
			e.printStackTrace(out);
		} finally {
			out.close();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession(false);

		response.setContentType("text/html");
		IManager mgr = ComponentFactory.createInstance();
		IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");

		try {
			if (session == null) {
				throw new InvalidSessionException();
			}  

			int numQueixa = (new Integer(request.getParameter("numQueixa"))).intValue();

			IUpdate update = (IUpdate) mgr.getRequiredInterface("IUpdate");
			IComplaintDt q = update.searchComplaint(numQueixa);


			session.putValue(ServletUpdateComplaintSearch.QUEIXA, q);

			if (q.getSituacao() != Constants.OPEN_COMPLAINT) {

				throw new SituationFacadeException("Complaint not open");
			}

			out.println(htmlPageMgt.open("Update complaint"));
			out.println("<script language=\"javascript\">");
			out.println("function submeterDados(modulo)");
			out.println("{");

			String a1 = "\"";
			String a2 = "\"";

			out.println("   var f = document.formAlterarQueixa2;");
			out.println("   if(f.obsQueixa.value ==" + a1 + a2 + ")");
			out.println("   {");
			out.println("           alert(\"Please, write your opinion about the complaint.\");");
			out.println("           f.obsQueixa.select();");
			out.println("           return;");
			out.println("   }");
			out.println("   f.submit();");
			out.println("}");
			out.println("//--></script>");
			out.println("<body><h1>Update Complaint:</h1>");

			out.println("<form method=\"POST\" name=\"formAlterarQueixa2\" action=\"http://"+Constants.SERVLET_SERVER_PATH+"ServletUpdateComplaintData\">");
			out.println("<div align=\"center\"><center><h4>Complaint : " + numQueixa+ "</h4></center></div>");

			String t = getComplaintType(q);
			System.out.println("[ServletUpdateComplaintSearch:doPost()] t="+t);

			out.println("<div align=\"center\"><center><p><strong>Complaint kind: "+t+"</strong></p></center></div>");
			out.println("<div align=\"center\"><center><p><strong>Description: "+ q.getDescricao() + "</strong></p></center></div>");
			out.println("<div align=\"center\"><center><p><strong>Observation (complaint's solution):</strong><br><textarea rows=\"5\" name=\"obsQueixa\" cols=\"22\"></textarea></p></center></div>");
			out.println("<div align=\"center\"><center><h4><input type=\"button\" value=\"Update\" name=\"bt1\" onClick=\"javascript:submeterDados();\"><input type=\"reset\" value=\"Clear\" name=\"bt2\"></h4></center></div></form>");
			out.println(htmlPageMgt.closeAdministrator());
		} catch (InvalidSessionException e) {
			out.println(htmlPageMgt.errorPageAdministrator("<p>Ivalid Session! <br>You must <a href=\""+Constants.SYSTEM_LOGIN+"\">login</a> again!"));
		} catch (ObjectNotFoundException e) {
			out.println(htmlPageMgt.errorPageAdministrator("Complaint does not exist!"));
		} catch (SituationFacadeException e) {
			out.println(htmlPageMgt.errorPageAdministrator("This complaint's status is closed!"));
		}
		finally {
			out.close();
		} 
	}

	private String getComplaintType(IComplaintDt complaint){
		Class interfaces[] = complaint.getClass().getInterfaces();
		int i;
		for( i = 0; i < interfaces.length; i++){
			if( interfaces[i].equals(IAnimalComplaintDt.class) )
				return "Animal complaint";
			if( interfaces[i].equals(IFoodComplaintDt.class) )
				return "Food complaint";
			if( interfaces[i].equals(ISpecialComplaintDt.class) )
				return "Special complaint";
		}
		return null;
	}
}