package publichealthcomplaint.userinterface.impl.infrastr.impl;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import publichealthcomplaint.datatypes.IHealthUnitDt;
import publichealthcomplaint.exceptionhandling.impl.InvalidSessionException;
import publichealthcomplaint.userinterface.impl.infrastr.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.infrastr.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.infrastr.spec.req.IInfrastructureMgt;




public class ServletUpdateHealthUnitData extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
        HttpSession session = request.getSession(true);
        
        response.setContentType("text/html");

        IHealthUnitDt unit;
        IManager mgr = ComponentFactory.createInstance();
        IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
        
        try {
        	if (session == null) {
                throw new InvalidSessionException();
            }
        	
        	unit = (IHealthUnitDt) session.getValue(ServletUpdateHealthUnitSearch.HEALTH_UNIT);                        
            
            String descricao = request.getParameter("descricao");            
            
            unit.setDescription(descricao);
        	
            out.println(htmlPageMgt.htmlPageAdministrator("Operation executed", "Health Unit updated"));
            
            IInfrastructureMgt infra = (IInfrastructureMgt) mgr.getRequiredInterface("IInfrastructureMgt");
            infra.updateHealthUnit(unit);

        } catch(Exception e){
            out.println(htmlPageMgt.errorPage("Comunitation error, please try again later."));
        } finally {
            out.close();
        }
    }
}
