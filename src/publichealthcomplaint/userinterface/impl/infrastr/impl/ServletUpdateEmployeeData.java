package publichealthcomplaint.userinterface.impl.infrastr.impl;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.exceptionhandling.impl.InvalidSessionException;
import publichealthcomplaint.userinterface.impl.infrastr.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.infrastr.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.infrastr.spec.req.IInfrastructureMgt;



public class ServletUpdateEmployeeData extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out;
        HttpSession session = request.getSession(false);
        
        String name = request.getParameter("name");
        //String password = request.getParameter("password");
        String newPassword = request.getParameter("newPassword");
        IEmployeeDt employee = null;

        response.setContentType("text/html");

        out = response.getWriter();

        IManager mgr = ComponentFactory.createInstance();
        IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
        
        try {
            if (session == null) {
                throw new InvalidSessionException();
            }
            
            employee = (IEmployeeDt) session.getValue(ServletLogin.EMPLOYEE);
            
            employee.setName(name);
            if (!newPassword.equals("")) {
                employee.setPassword(newPassword);
            }
            
            out.println(htmlPageMgt.htmlPageAdministrator("Operation executed", "Employee updated"));
            
            IInfrastructureMgt infra = (IInfrastructureMgt) mgr.getRequiredInterface("IInfrastructureMgt");
            infra.updateEmployee(employee);
            
        } catch(Exception e){	
            out.println(htmlPageMgt.errorPage(e.getMessage()));
            e.printStackTrace(out);
        } finally {
            out.close();
        }
    }
}
