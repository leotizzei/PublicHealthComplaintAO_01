
package publichealthcomplaint.userinterface.impl.queryinfo.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publichealthcomplaint.datatypes.IDiseaseDt;
import publichealthcomplaint.datatypes.ISymptomDt;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;


import publichealthcomplaint.userinterface.impl.queryinfo.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IQueryInfoMgt;





public class ServletSearchDiseaseData extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        int codigoTipoDoenca = Integer.parseInt(request.getParameter("codTipoDoenca"));

        IManager mgr = ComponentFactory.createInstance();
		IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
        try {
        	
        	IQueryInfoMgt query = (IQueryInfoMgt) mgr.getRequiredInterface("IQueryInfoMgt");
            IDiseaseDt tp = query.searchDiseaseType(codigoTipoDoenca);

            out.println(htmlPageMgt.open("Queries - Diseases"));
            out.println("<body><h1>Querie result<br>Disease</h1>");          

            out.println("<P><h3>Name: " + tp.getName() + "</h3></P>");
            out.println("<P>Description: " + tp.getDescription() + "</P>");
            out.println("<P>How manifests: " + tp.getManifestation()
                        + " </P>");
            out.println("<P>Duration: " + tp.getDuration() + " </P>");
            out.println("<P>Symptoms: </P>");
      
            Iterator i = tp.getSymptoms().iterator();

            if (! i.hasNext()) {
            	out.println("<P>There isn't registered symptoms.</P>");
            } else {
            	while(i.hasNext()) {
            		ISymptomDt s = (ISymptomDt) i.next();
                    out.println("<li> " + s.getDescription() + " </li>");
                }
            }
            out.println(htmlPageMgt.closeQueries());
        } catch (ObjectNotFoundException e) {
            out.println("<P> " + e.getMessage() + " </P>");
        }catch(Exception e){
            out.println(htmlPageMgt.errorPage("Comunitation error, please try again later."));
            e.printStackTrace(out);
        } finally {
            out.close();
        }
    }
}