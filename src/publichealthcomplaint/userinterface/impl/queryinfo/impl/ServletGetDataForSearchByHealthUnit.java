package publichealthcomplaint.userinterface.impl.queryinfo.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publichealthcomplaint.datatypes.Constants;
import publichealthcomplaint.datatypes.IHealthUnitDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;

import publichealthcomplaint.userinterface.impl.queryinfo.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IQueryInfoMgt;






public class ServletGetDataForSearchByHealthUnit extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out;

		response.setContentType("text/html");

		out = response.getWriter();
	
		IManager mgr = ComponentFactory.createInstance();
		IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
		out.println( htmlPageMgt.open("Queries - Specialties") );
		out.println("<body><h1>Queries:<br>Search Specialties of a Health unit</h1>");
		out.println("<p>Choose a health unit: </p>");
		System.out.println("canonical name="+ServletSearchSpecialtiesByHealthUnit.class.getCanonicalName());
		out.println("<form method=\"POST\" action=\"http://"+Constants.SERVLET_SERVER_PATH+"ServletSearchSpecialtiesByHealthUnit\">");
		System.out.println("<form method=\"POST\" action=\"http://"+Constants.SERVLET_SERVER_PATH+"ServletSearchSpecialtiesByHealthUnit\">");
		try {
			out.println("<div align=\"center\"><center><p><select name=\"codUnidadeSaude\" size=\"1\">");
			IQueryInfoMgt query = (IQueryInfoMgt) mgr.getRequiredInterface("IQueryInfoMgt");
			IteratorDsk repUS = query.getPartialHealthUnitList();
	
			if (!repUS.hasNext()) {
			
				out.println("</select></p></center></div>");
				out.println("<P> There isn't registered health units.</P>");
			} else {
				IHealthUnitDt us;
				do {
					us = (IHealthUnitDt) repUS.next();
					out.println("<option value=\""+ us.getCode() + "\"> " + us.getDescription() + " </OPTION>");
				} while (repUS.hasNext());
				
				repUS.close();
				out.println("</select></p></center></div>");
				out.println("<div align=\"center\"><center><p><input type=\"submit\" value=\"Consultar\" name=\"B1\"></p></center></div></form>");
			}
			System.out.println("[ServletGetDataForSearchByHealthUnit:doGet()] end of method");
			out.println(htmlPageMgt.closeQueries());
		} catch (ObjectNotFoundException e) {
			out.println("</select></p></center></div>");
			out.println("<P> " + e.getMessage() + " </P>");
			out.println("<P> Nenhuma unidade de saude foi cadastrada</P>");
		} catch(Exception e){
			System.err.println("[ServletGetDataForSearchByHealthUnit:doGet()] Exception:"+e.getLocalizedMessage());
			out.println(htmlPageMgt.errorPage("Comunication error, please try again later."));
			e.printStackTrace(out);
		} finally {
			out.close();
		}
	}
}