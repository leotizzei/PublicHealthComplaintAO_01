package publichealthcomplaint.userinterface.impl.generalcomplaint.impl;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import publichealthcomplaint.datatypes.IAddressDt;
import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.prov.IGeneralComplaintMgt;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IComplaintMgt;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IUtil;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IHTMLPageMgt;





public class ServletInsertSpecialComplaint extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		response.setContentType("text/html");

		IManager mgr = ComponentFactory.createInstance();
		IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
		try {

			//captcha
			HttpSession session = request.getSession();
			String captcha = (String) session.getAttribute("captcha");
			String captchaCode = (String) request.getParameter("captchacode");

			//check captcha code
			if( ! captchaCode.equals(captcha)) {
				out.println("<html><head></head><body><center><h1>Sorry, you have typed the wrong captcha code. Please try again.</h1></center></body></html>");
			}
			else{
				//Queixa Normal
				SpecialComplaint specialComplaint = new SpecialComplaint();
				IGeneralComplaintMgt generalComplaintMgt = (IGeneralComplaintMgt) mgr.getRequiredInterface("IGeneralComplaintMgt");
				generalComplaintMgt.readGeneralComplaintData(request, specialComplaint);

				//Queixa Diversa
				short    idade              = Short.parseShort(request.getParameter("idade"));
				specialComplaint.setIdade(idade);

				String   instrucao          = request.getParameter("instrucao");
				specialComplaint.setInstrucao(instrucao);

				String   ocupacao           = request.getParameter("ocupacao");
				specialComplaint.setOcupacao(ocupacao);

				String   ruaOcorrencia      = request.getParameter("ruaOcorrencia");
				String   compOcorrencia     = request.getParameter("compOcorrencia");
				String   bairroOcorrencia   = request.getParameter("bairroOcorrencia");
				String   cidadeOcorrencia   = request.getParameter("cidadeOcorrencia");
				String   ufOcorrencia       = request.getParameter("ufOcorrencia");
				String   cepOcorrencia      = request.getParameter("cepOcorrencia ");
				String   telefoneOcorrencia = request.getParameter("telefoneOcorrencia");
				
				IUtil util = (IUtil) mgr.getRequiredInterface("IUtil");
				IAddressDt endOcorrencia      = util.createAddress(ruaOcorrencia, compOcorrencia, cepOcorrencia, ufOcorrencia,
						telefoneOcorrencia, cidadeOcorrencia, bairroOcorrencia);
				specialComplaint.setEnderecoOcorrencia(endOcorrencia);

				Calendar agora = Calendar.getInstance();
				IDateDt currentDateDt = util.createDate(agora.get(Calendar.SECOND), agora.get(Calendar.MINUTE), agora.get(Calendar.HOUR), agora.get(Calendar.DAY_OF_MONTH),
						agora.get(Calendar.MONTH), agora.get(Calendar.YEAR));
				specialComplaint.setDataQueixa(currentDateDt);

				IComplaintMgt complaint = (IComplaintMgt) mgr.getRequiredInterface("IComplaintMgt");
				int codigo = complaint.insertComplaint(specialComplaint);

				out.println(htmlPageMgt.htmlPage("Complaint inserted", "<p> <h2> Special Complaint inserted</h2> </p>" +"<p> <h2> Save the complaint number: " + codigo + "</h2> </p>"));
			}
		} catch (ObjectAlreadyInsertedException e) {
			out.println(htmlPageMgt.errorPage("Esta queixa jah existe no BD"));
			e.printStackTrace(out);
		} catch (ObjectNotValidException e) {
			out.println(htmlPageMgt.errorPage("Erro ao inserir esta queixa"));
			e.printStackTrace(out);
		} catch(Exception e){	
			out.println(htmlPageMgt.errorPage("Comunitation error, please try again later."));
			e.printStackTrace(out);
		}finally {
			out.close();
		}

	}
}