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
import publichealthcomplaint.datatypes.IFoodComplaintDt;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IComplaintMgt;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IUtil;





public class ServletInsertFoodComplaint extends HttpServlet {

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
				IFoodComplaintDt foodComplaint = new FoodComplaint();
				FacadeGeneralComplaint generalComplaintMgt = new FacadeGeneralComplaint();
				generalComplaintMgt.readGeneralComplaintData(request, foodComplaint);

				//Queixa Alimentar
				String ruaVitima = request.getParameter("ruaVitima");
				String compVitima = request.getParameter("compVitima");
				String bairroVitima = request.getParameter("bairroVitima");
				String cidadeVitima = request.getParameter("cidadeVitima");
				String ufVitima = request.getParameter("ufVitima");
				String cepVitima = request.getParameter("cepVitima ");
				String telefoneVitima = request.getParameter("telefoneVitima");
				
				IUtil util = (IUtil) mgr.getRequiredInterface("IUtil");
				IAddressDt endVitima = util.createAddress(ruaVitima, compVitima,cepVitima, ufVitima,telefoneVitima,
						cidadeVitima, bairroVitima);
				foodComplaint.setEnderecoDoente(endVitima);
				
				short qtdeComensais = Short.parseShort(request.getParameter("qtdeComensais"));
				foodComplaint.setQtdeComensais(qtdeComensais);
				
				short qtdeDoentes = Short.parseShort(request.getParameter("qtdeDoentes"));
				foodComplaint.setQtdeDoentes(qtdeDoentes);
				
				short qtdeInternacoes = Short.parseShort(request.getParameter("qtdeInternacoes"));
				foodComplaint.setQtdeInternacoes(qtdeInternacoes);				
				
				short qtdeObitos = Short.parseShort(request.getParameter("qtdeObitos"));
				foodComplaint.setQtdeObitos(qtdeObitos);
				
				String localAtendimento = request.getParameter("localAtendimento");
				foodComplaint.setLocalAtendimento(localAtendimento);
				
				String refeicaoSuspeita = request.getParameter("refeicaoSuspeita");
				foodComplaint.setRefeicaoSuspeita(refeicaoSuspeita);
				
				Calendar agora = Calendar.getInstance();
				IDateDt currentDateDt = util.createDate(agora.get(Calendar.SECOND), agora.get(Calendar.MINUTE), agora.get(Calendar.HOUR), agora.get(Calendar.DAY_OF_MONTH),
						agora.get(Calendar.MONTH), agora.get(Calendar.YEAR));
				foodComplaint.setDataQueixa( currentDateDt );
				
				IComplaintMgt complaint = (IComplaintMgt) mgr.getRequiredInterface("IComplaintMgt");
				int codigo = complaint.insertComplaint( foodComplaint );

				out.println(htmlPageMgt.htmlPage("Complaint inserted","<p> <h2> Food Complaint inserted</h2> </p>" +"<p> <h2> Save the complaint number: " + codigo + "</h2> </p>"));
			}
		} catch (ObjectAlreadyInsertedException e) {
			out.println(htmlPageMgt.errorPage("Esta queixa jah existe no BD"));
			e.printStackTrace(out);
		} catch (ObjectNotValidException e) {
			out.println(htmlPageMgt.errorPage("Erro ao inserir esta queixa"));
			e.printStackTrace(out);
		}catch(Exception e){
			e.printStackTrace();
			out.println(htmlPageMgt.errorPage("Comunitation error, please try again later."));
			e.printStackTrace(out);
		} finally {
			out.println(htmlPageMgt.close());
			out.close();
		}
	}
}