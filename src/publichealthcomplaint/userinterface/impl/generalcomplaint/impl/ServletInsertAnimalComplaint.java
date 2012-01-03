
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
import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.exceptionhandling.impl.InvalidDateException;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IComplaintMgt;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IUtil;




public class ServletInsertAnimalComplaint extends HttpServlet {


	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("[ServletInsertAnimalComplaint:doPost()]");

		PrintWriter out;
		IComplaintDt queixa;

		response.setContentType("text/html");

		out = response.getWriter();
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
				
				AnimalComplaint animalComplaint = new AnimalComplaint();
				
				FacadeGeneralComplaint generalComplaintMgt = new FacadeGeneralComplaint();
				generalComplaintMgt.readGeneralComplaintData(request, animalComplaint);
				
				//these data are specific to animal complaints
				String nomeAnimal = request.getParameter("nomeAnimal");
				short qtdeAnimal = Short.parseShort(request.getParameter("qtdeAnimal"));
				int diaIncomodo = Integer.parseInt(request.getParameter("diaIncomodo"));
				int mesIncomodo = Integer.parseInt(request.getParameter("mesIncomodo"));
				int anoIncomodo = Integer.parseInt(request.getParameter("anoIncomodo"));


				String ruaOcorrencia = request.getParameter("ruaOcorrencia");
				String compOcorrencia = request.getParameter("compOcorrencia");
				String bairroOcorrencia = request.getParameter("bairroOcorrencia");
				String cidadeOcorrencia = request.getParameter("cidadeOcorrencia");
				String ufOcorrencia = request.getParameter("ufOcorrencia");
				String cepOcorrencia = request.getParameter("cepOcorrencia ");
				String telefoneOcorrencia = request.getParameter("telefoneOcorrencia");

				IUtil util = (IUtil) mgr.getRequiredInterface("IUtil");
				IAddressDt endOcorrencia = util.createAddress(ruaOcorrencia, compOcorrencia, cepOcorrencia,	ufOcorrencia, telefoneOcorrencia, cidadeOcorrencia,	bairroOcorrencia);

				Calendar agora = Calendar.getInstance();
				
				IDateDt currentDateDt = util.createDate(agora.get(Calendar.SECOND), agora.get(Calendar.MINUTE), agora.get(Calendar.HOUR), agora.get(Calendar.DAY_OF_MONTH),
						agora.get(Calendar.MONTH), agora.get(Calendar.YEAR));
				
				IDateDt eventDate = util.createDate(0, 0, 0, diaIncomodo, mesIncomodo, anoIncomodo);
				
				animalComplaint.setAnimalQuantity(qtdeAnimal);
				animalComplaint.setDataQueixa(currentDateDt);
				animalComplaint.setAnimal(nomeAnimal);
				animalComplaint.setOccurenceLocalAddress(endOcorrencia);
				animalComplaint.setInconvenienceDate(eventDate);
				
				System.out.println("[ServletInsertAnimalComplaint:doPost()] antes de chamar o facade.insertComplaint");

				IComplaintMgt complaintMgt = (IComplaintMgt) mgr.getRequiredInterface("IComplaintMgt");
				int codigo = complaintMgt.insertComplaint( animalComplaint );


				out.println(htmlPageMgt.htmlPage("Complaint inserted", 
						"<p> <h2> Animal Complaint inserted</h2> </p>"  +
						"<p> <h2> Save the complaint number: " + codigo + "</h2> </p>")); 
			}
		} catch (ObjectAlreadyInsertedException e) {
			out.println(htmlPageMgt.errorPage("Complaint already inserted"));
		} catch (ObjectNotValidException e) {
			out.println(htmlPageMgt.errorPage("Unespected error. Try to contact the support team.")); 
		}catch(InvalidDateException e){
			out.println(htmlPageMgt.errorPage("Invalid date."));
		}finally {
			out.println(htmlPageMgt.close());
			out.close();
		}
	}
}