
package publichealthcomplaint.userinterface.impl.queryinfo.impl;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publichealthcomplaint.datatypes.Constants;
import publichealthcomplaint.datatypes.IDiseaseDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IQueryInfoMgt;






public class ServletGetDataForSearchByDiseaseType extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("[ServletGetDataForSearchByDiseaseType:doGet]");
        PrintWriter out;

        response.setContentType("text/html");

        out = response.getWriter();
        
        IManager mgr = ComponentFactory.createInstance();
        IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
        
        out.println(htmlPageMgt.open("Queries - Disease information"));
        out.println("<body><h1>Queries:<br>Querie about diseases</h1>");
        out.println("<p>Choose a disease: </p>");
        out.println("<form method=\"POST\" action=\"http://"+Constants.SERVLET_SERVER_PATH+"ServletSearchDiseaseData\">");

        try {
            out.println("<div align=\"center\"><center><p><select name=\"codTipoDoenca\" size=\"1\">");
        
            IQueryInfoMgt query = (IQueryInfoMgt) mgr.getRequiredInterface("IQueryInfoMgt");
            IteratorDsk repTP = query.getDiseaseTypeList();
            
            if (repTP==null||!repTP.hasNext()) {
                out.println("</select></p></center></div>");
                out.println("<p><font color=\"red\"><b> There isn't diseases registered.</b></font></p>");
            } else {
                IDiseaseDt tp;
                do {
                    tp = (IDiseaseDt) repTP.next();

                    out.println("<option value=\""+ tp.getCode() + "\"> " + tp.getName()+ " </OPTION>");
                } while (repTP.hasNext());
                repTP.close();
                
                out.println("</select></p></center></div>");
                out.println("<div align=\"center\"><center><p><input type=\"submit\" value=\"Consultar\" name=\"B1\"></p></center></div></form>");
            }
            out.println(htmlPageMgt.closeQueries());
        } catch (ObjectNotFoundException e) {
        	out.println(htmlPageMgt.errorPageQueries("There isn't registered diseases"));
        }finally {
            out.close();
        }
    }
}