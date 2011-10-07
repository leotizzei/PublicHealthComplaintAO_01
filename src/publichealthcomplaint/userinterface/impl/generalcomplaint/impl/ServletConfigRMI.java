package publichealthcomplaint.userinterface.impl.generalcomplaint.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publichealthcomplaint.userinterface.impl.util.spec.prov.IHTMLPageMgt;




public class ServletConfigRMI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IHTMLPageMgt htmlPageMgt;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out;

		response.setContentType("text/html");

		out = response.getWriter();

		try {

			out.println(htmlPageMgt.htmlPage("PublicHealthComplaint SPL - Health-Watcher 2011", "Server name stored"));
		} catch (Exception e) {
			e.printStackTrace();
			out.println(htmlPageMgt.errorPageAdministrator(e.getMessage()));
		} finally {
			out.close();
		}
	}
}