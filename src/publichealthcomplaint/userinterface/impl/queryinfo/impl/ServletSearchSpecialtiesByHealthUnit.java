
package publichealthcomplaint.userinterface.impl.queryinfo.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publichealthcomplaint.datatypes.IMedicalSpecialityDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;

import publichealthcomplaint.userinterface.impl.queryinfo.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IQueryInfoMgt;



public class ServletSearchSpecialtiesByHealthUnit extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("[ServletSearchSpecialtiesByHealthUnit:doPost()] beginning of method");
		
		IManager mgr = ComponentFactory.createInstance();
		
		IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
				
		PrintWriter out = response.getWriter();
		
		response.setContentType("text/html");

		int code = Integer.parseInt(request.getParameter("codUnidadeSaude"));

		try {
		
			IQueryInfoMgt query = (IQueryInfoMgt) mgr.getRequiredInterface("IQueryInfoMgt");
			IteratorDsk repEsp = query.searchSpecialitiesByHealthUnit(code);

			out.println(htmlPageMgt.open("Queries - Especialties"));
			out.println("<body><h1>Querie result</h1>");

			out.println("<P><h3>Health unit: " + code+ " </h3></P>");
			out.println("<h3>Especialties :</h3>");

			while (repEsp.hasNext()) {
				IMedicalSpecialityDt esp = (IMedicalSpecialityDt) repEsp.next();
				out.println("<dd><dd>" + esp.getDescricao());

			}
			out.println(htmlPageMgt.closeQueries());             

		} catch(Exception e){
			out.println(htmlPageMgt.errorPage("Communication error, please try again later."));
			e.printStackTrace(out);
		} finally {
			out.close();
		}

	}
}