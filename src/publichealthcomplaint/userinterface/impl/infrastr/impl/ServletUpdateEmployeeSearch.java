package publichealthcomplaint.userinterface.impl.infrastr.impl;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import publichealthcomplaint.datatypes.Constants;
import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.exceptionhandling.impl.InvalidSessionException;
import publichealthcomplaint.userinterface.impl.infrastr.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.infrastr.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.infrastr.spec.req.IUtil;




public class ServletUpdateEmployeeSearch extends HttpServlet {  

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out;
        HttpSession session = request.getSession(false);
        
        response.setContentType("text/html");

        out = response.getWriter();
        IManager mgr = ComponentFactory.createInstance();
        IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
        try {
            if (session == null) {
                throw new InvalidSessionException();
            }            

            IEmployeeDt employee = (IEmployeeDt)session.getValue(ServletLogin.EMPLOYEE);
            
            String[] keywords = {"##LOGIN##",
                                 "##NAME##",
                                 "##SERVLET_SERVER_PATH##",
                                 "##CLOSE##"};
                                  
            String[] newWords = {employee.getLogin(),
                                 employee.getName(),
                                 Constants.SERVLET_SERVER_PATH,
                                 htmlPageMgt.closeAdministrator()};
            IUtil util = (IUtil) mgr.getRequiredInterface("IUtil");                               
            out.println(util.getFileListReplace(keywords, newWords, Constants.FORM_PATH+"UpdateEmployee.html"));
                         
        } catch (InvalidSessionException e) {
            out.println(htmlPageMgt.errorPageAdministrator("<p>Ivalid Session! <br>You must <a href=\""+Constants.SYSTEM_LOGIN+"\">login</a> again!"));
        } catch (FileNotFoundException e) {
            out.println(htmlPageMgt.errorPageAdministrator(e.getMessage()));
        } finally {
            out.close();
        }
    }
}
