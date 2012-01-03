
package publichealthcomplaint.userinterface.impl.queryinfo.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import publichealthcomplaint.datatypes.Constants;
import publichealthcomplaint.datatypes.IAddressDt;
import publichealthcomplaint.datatypes.IAnimalComplaintDt;
import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.datatypes.IFoodComplaintDt;
import publichealthcomplaint.datatypes.ISpecialComplaintDt;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;


import publichealthcomplaint.userinterface.impl.queryinfo.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IHTMLPageMgt;
import publichealthcomplaint.userinterface.impl.queryinfo.spec.req.IQueryInfoMgt;






public class ServletSearchComplaintData extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        int codQueixa = Integer.parseInt(request.getParameter("codQueixa"));
      
        IManager mgr = ComponentFactory.createInstance();
		IHTMLPageMgt htmlPageMgt = (IHTMLPageMgt) mgr.getRequiredInterface("IHTMLPageMgt");
		
        try {
        	IQueryInfoMgt query = (IQueryInfoMgt) mgr.getRequiredInterface("IQueryInfoMgt");
        	IComplaintDt q = query.searchComplaint(codQueixa);
        	
        	out.println(htmlPageMgt.open("Queries - Complaints"));
            
            out.println("<body><h1>Search response<br>Complaint: " + codQueixa + "</h1>");

            out.println("<P>Complaint code: " + q.getCodigo() + "</P>");

            String t = null;

            if (q instanceof ISpecialComplaintDt) {
				t = "Special";
            } else if (q instanceof IFoodComplaintDt) {
            	t = "Food";
            } else if (q instanceof IAnimalComplaintDt) {
            	t = "Animal";
            }

            out.println("<P>Complaint kind: " + t + "</P>");
            out.println("<P>Complainer: " + q.getSolicitante() + "</P>");
            out.println("<P>E-mail: " + q.getEmail() + "</P>");
            out.println("<P>Complaint's description: " + q.getDescricao() + "</P>");
            out.println("<P>Observation: " + q.getObservacao() + "</P>");
            
            IDateDt date = q.getDataQueixa(); 
            if (q.getDataQueixa() != null) {
                out.println("<P>Date: "+ date.format(1) + "</P>");
            }
            
            String sit;
            if (q.getSituacao() == Constants.OPEN_COMPLAINT) {
                sit = "Open";
            } else if (q.getSituacao() == Constants.CLOSED_COMPLAINT) {
                sit = "Closed";
            } else {
                sit = "Suspended";
            }

            out.println("<P>Status: " + sit + "</P>");
            
            date = q.getDataParecer();
            if (q.getSituacao() == Constants.CLOSED_COMPLAINT) {
                if (q.getDataParecer() != null) {
                    out.println("<P>Observation Date: "+ date.format(1)+ "</P>");
                }
            }

            IAddressDt end = q.getEnderecoSolicitante();

            if (end != null) {
                out.println("<P>Complainer's address: " + end.getStreet()+ "," + end.getComplement() + " Province: "+ end.getNeighbourhood() + " </P>");
                out.println("<P>ZIP code: " + end.getZip() + " City: "+ end.getCity() + " State: " + end.getState()+ "</P>");
                out.println("<P> Phone number: " + end.getPhone() + "</P>");
            }


            if (q instanceof IFoodComplaintDt) {
            	out.println("<P>Amount of people that ate the meal: " + ((IFoodComplaintDt) q).getQtdeComensais() + "</P>");
                out.println("<P>Amount of sick people: " + ((IFoodComplaintDt) q).getQtdeDoentes() + "</P>");
                out.println("<P>Amount of people checked into a hospital: " + ((IFoodComplaintDt) q).getQtdeInternacoes() + "</P>");
                out.println("<P>Amount of deaths: " + ((IFoodComplaintDt) q).getQtdeObitos() + "</P>");
                out.println("<P>Place of medical care: " + ((IFoodComplaintDt) q).getLocalAtendimento() + "</P>");
                out.println("<P>Suspicious meal: " + ((IFoodComplaintDt) q).getRefeicaoSuspeita() + "</P>");

                end = ((IFoodComplaintDt) q).getEnderecoDoente();

                if (end != null) {
                    out.println("<P>Person sick's address: " + end.getStreet()+ "," + end.getComplement() + " Province: "+ end.getNeighbourhood() + " </P>");
                    out.println("<P>ZIP code: " + end.getZip() + " City: "+ end.getCity() + " State: " + end.getState()+ "</P>");
                    out.println("<P> Phone number: " + end.getPhone() + "</P>");
                }
            }

            if (q instanceof IAnimalComplaintDt) {
            	out.println("<P>Animal: " + ((IAnimalComplaintDt) q).getAnimal() + "</P>");
                out.println("<P>Amount of animals: " + ((IAnimalComplaintDt) q).getAnimalQuantity() + "</P>");

                if (((IAnimalComplaintDt) q).getInconvenienceDate() != null) {
                	date = ((IAnimalComplaintDt) q).getInconvenienceDate();
                    out.println("<P>Date: " +  date.format(1) + "</P>");
                }

                end = ((IAnimalComplaintDt) q).getOccurenceLocalAddress();

                if (end != null) {
                    out.println("<P>Person sick's address: " + end.getStreet() + "," + end.getComplement() + " Province: " + end.getNeighbourhood() + " </P>");
                    out.println("<P>ZIP code: " + end.getZip() + " City: "+ end.getCity() + " State: " + end.getState()+ "</P>");
                    out.println("<P> Phone number: " + end.getPhone() + "</P>");
                }
            }

            if (q instanceof ISpecialComplaintDt) {
            	out.println("<P>Years old: " + ((ISpecialComplaintDt) q).getIdade()+"</P>");
                out.println("<P>School level: "+ ((ISpecialComplaintDt) q).getInstrucao() + "</P>");
                out.println("<P>Ocuppation: "+ ((ISpecialComplaintDt) q).getOcupacao() + "</P>");

                end = ((ISpecialComplaintDt) q).getEnderecoOcorrencia();

                if (end != null) {
                    out.println("<P>Person sick's address: " + end.getStreet()+ "," + end.getComplement() + " Province: " + end.getNeighbourhood() + " </P>");
                    out.println("<P>ZIP code: " + end.getZip() + " City: "+ end.getCity() + " State: " + end.getState()+ "</P>");
                    out.println("<P> Phone number: " + end.getPhone() + "</P>");
                }
            }
            out.println(htmlPageMgt.closeQueries());
        } catch (ObjectNotFoundException e) {
            out.println(htmlPageMgt.errorPageQueries("Complaint " + codQueixa + " not found"));
            //e.printStackTrace(out);
        } catch(Exception e){
        	out.println(htmlPageMgt.errorPageQueries(e.getMessage()));
            e.printStackTrace(out);
        }finally {
            out.close();
        }
    }
}