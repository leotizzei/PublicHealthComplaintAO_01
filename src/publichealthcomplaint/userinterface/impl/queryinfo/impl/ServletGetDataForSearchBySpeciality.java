
package publichealthcomplaint.userinterface.impl.queryinfo.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publichealthcomplaint.datatypes.Constants;
import publichealthcomplaint.datatypes.IMedicalSpecialityDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;

import publichealthcomplaint.userinterface.impl.queryinfo.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IQueryInfoMgt;




public class ServletGetDataForSearchBySpeciality extends HttpServlet {


    public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out;

        response.setContentType("text/html");

        out = response.getWriter();

        IManager mgr = ComponentFactory.createInstance();
		IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
        out.println(htmlPageMgt.open("Queries - Health Units"));
        out.println("<body><h1>Queries:<br>Search Health units by Medical specialty</h1>");
        out.println("<p>Choose a specialty: </p>");
        out.println("<form method=\"POST\"action=\"http://"+Constants.SERVLET_SERVER_PATH+"ServletSearchHealthUnitsBySpecialty\">");

        try {
            out.println("<div align=\"center\"><center><p><select name=\"codEspecialidade\" size=\"1\">");

            IQueryInfoMgt query = (IQueryInfoMgt) mgr.getRequiredInterface("IQueryInfoMgt");
            IteratorDsk repEsp = query.getSpecialityList();

            if (!repEsp.hasNext()) {
                out.println("</select></p></center></div>");
                out.println("<P>There isn't registered specialties.</P>");
            } else {
            	IMedicalSpecialityDt esp;

                do {
                    esp = (IMedicalSpecialityDt) repEsp.next();
                    out.println("<option value=\"" + esp.getCodigo() + "\"> "+ esp.getDescricao() + " </OPTION>");
                } while (repEsp.hasNext());

                repEsp.close();
                out.println("</select></p></center></div>");
                out.println(" <div align=\"center\"><center><p><input type=\"submit\" value=\"Consultar\" name=\"B1\"></p></center></div></form>");
            }
            out.println(htmlPageMgt.closeQueries());
        } catch (ObjectNotFoundException e) {
            out.println("</select></p></center></div>");
            out.println("<P> " + e.getMessage() + " </P>");
            out.println("<P> Nenhuma especialidade foi cadastrada</P>");
        } catch(Exception e) {
        	out.println("Error!");
            out.println(htmlPageMgt.errorPage("Comunication error, please try again later."));
            e.printStackTrace(out);
        } finally {        	
            out.close();
        }
    }
}
