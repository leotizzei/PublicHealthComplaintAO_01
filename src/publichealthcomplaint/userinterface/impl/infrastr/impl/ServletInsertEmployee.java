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
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.userinterface.impl.infrastr.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.infrastr.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.infrastr.spec.req.IInfrastructureMgt;



public class ServletInsertEmployee extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out;
        IEmployeeDt employee;
        HttpSession session = request.getSession(false);
       
        response.setContentType("text/html");

        out = response.getWriter();
        
        IManager mgr = ComponentFactory.createInstance();
        IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
        
        try {
            if (session == null) {
                throw new InvalidSessionException();
            }            

            //Complaint Normal
            String name = request.getParameter("name");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            
            employee = new Employee(login, password, name);
            System.out.println("[ServletInsertEmployee:doPost()] employee="+employee.getLogin());
            
            IInfrastructureMgt infra = (IInfrastructureMgt) mgr.getRequiredInterface("IInfrastructureMgt");
            infra.insert(employee);
            
            out.println(htmlPageMgt.htmlPageAdministrator("Operation executed", "Employee inserted"));
        } catch (ObjectAlreadyInsertedException e) {
        	out.println(htmlPageMgt.errorPageAdministrator(e.getMessage()));
            e.printStackTrace(out);
        } catch (ObjectNotValidException e) {
        	out.println(htmlPageMgt.errorPageAdministrator(e.getMessage()));
            e.printStackTrace(out);            
        } catch (InvalidSessionException e) {
        	out.println(htmlPageMgt.errorPageAdministrator(e.getMessage()));
            e.printStackTrace(out);
        } finally {
            out.close();
        }
    }
}