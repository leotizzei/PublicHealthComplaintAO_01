package publichealthcomplaint.userinterface.impl.queryinfo.impl;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publichealthcomplaint.datatypes.IHealthUnitDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;

import publichealthcomplaint.userinterface.impl.queryinfo.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IQueryInfoMgt;




public class ServletSearchHealthUnitsBySpecialty extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        int codigoEsp =  Integer.parseInt(request.getParameter("codEspecialidade"));

        IManager mgr = ComponentFactory.createInstance();
		IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
        try {
        	
        	IQueryInfoMgt query = (IQueryInfoMgt) mgr.getRequiredInterface("IQueryInfoMgt");
        	IteratorDsk repUS = query.searchHealthUnitsBySpeciality(codigoEsp);

        	out.println(htmlPageMgt.open("Queries - Health Unit"));
            out.println("<body><h1>Querie result<br>Health units</h1>");
            
            out.println("<P><h3>Medical specialty: " + codigoEsp + "</h3></P>");
            out.println("<h3>Health units:</h3>");

            if ( repUS != null) {
	            while (repUS.hasNext()) {
	                IHealthUnitDt us = (IHealthUnitDt) repUS.next();
	                out.println("<dd><dd>" + us.getDescription());             
	            }
            }
            
            out.println(htmlPageMgt.closeQueries());
        }catch (ObjectNotFoundException e) {
            out.println("<P> " + e.getMessage() + " </P>");  
        } catch(Exception e){
            out.println(htmlPageMgt.errorPage("Comunitation error, please try again later."));
            e.printStackTrace(out);
        }finally {
            out.close();
        }
    }
}