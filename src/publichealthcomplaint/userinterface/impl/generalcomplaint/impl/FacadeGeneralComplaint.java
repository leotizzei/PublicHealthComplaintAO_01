package publichealthcomplaint.userinterface.impl.generalcomplaint.impl;

import javax.servlet.http.HttpServletRequest;

import publichealthcomplaint.datatypes.IAddressDt;
import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.prov.IGeneralComplaintMgt;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.prov.IManager;
import publichealthcomplaint.userinterface.impl.generalcomplaint.spec.req.IUtil;

class FacadeGeneralComplaint implements IGeneralComplaintMgt {

	

	
	
	public void readGeneralComplaintData(HttpServletRequest request, IComplaintDt complaint) {

		if( request != null && complaint != null){
			complaint.setDescricao( request.getParameter("descricaoQueixa") );
			complaint.setObservacao( request.getParameter("observacaoQueixa") );
			complaint.setSolicitante( request.getParameter("nomeSolicitante") );

			String ruaSolicitante = request.getParameter("ruaSolicitante");
			String compSolicitante = request.getParameter("compSolicitante");
			String bairroSolicitante = request.getParameter("bairroSolicitante");
			String cidadeSolicitante = request.getParameter("cidadeSolicitante");
			String ufSolicitante = request.getParameter("ufSolicitante");
			String cepSolicitante = request.getParameter("cepSolicitante ");
			String telefoneSolicitante = request.getParameter("telefoneSolicitante");
			
			IManager manager = ComponentFactory.createInstance();
			IUtil util = (IUtil) manager.getRequiredInterface("IUtil"); 
			IAddressDt endSolicitante = util.createAddress(ruaSolicitante, compSolicitante, cepSolicitante, ufSolicitante, telefoneSolicitante, cidadeSolicitante, bairroSolicitante);
			complaint.setEnderecoSolicitante(endSolicitante);
			complaint.setEmail( request.getParameter("emailSolicitante") );



		}
	}

}
