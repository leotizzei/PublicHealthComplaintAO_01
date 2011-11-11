package publichealthcomplaint.supportservices.impl.queryinfomgr.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import publichealthcomplaint.datatypes.IHealthUnitDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ExceptionMessages;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.RepositoryException;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.prov.IManager;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req.IDistributionMgt;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req.IHealthUnitMgt;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req.IPersistenceMechanism;


class HealthUnitRepositoryRDB {

	private IPersistenceMechanism mp;

	private IDistributionMgt distributionMgt;

	private ResultSet resultSet;



	private IHealthUnitMgt healthUnitMgt;

	HealthUnitRepositoryRDB(IManager mgr) {


	}

	IteratorDsk getPartialHealthUnitList() throws RepositoryException,	ObjectNotFoundException {
		System.out.println("[HealthUnitRepositoryRDB:getPartialHealthUnitList()]");
		List listaUs = new ArrayList();

		// Query para selecionar os codigos de todas unidades de saude
		//existentes no sistema
		String sql = "SELECT codigo FROM scbs_unidadesaude";
		ResultSet rs = null;

		try {
			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			rs = stmt.executeQuery(sql);

			// O resultado da query e testado para saber
			//da existencia de unidades de saude cadastradas.
			// Caso nao existam uma excecao e lancada.
			if (rs.next()) {
				//convert the code from string to int
				String codeStr = rs.getString("codigo");
				Integer codeInteger = new Integer(codeStr);
				int code = codeInteger.intValue(); 
				
				//get required interface
				if( healthUnitMgt == null){
					IManager mgr = ComponentFactory.createInstance();
					healthUnitMgt = (IHealthUnitMgt) mgr.getRequiredInterface("IHealthUnitMgt");

				}
				IHealthUnitDt us = this.healthUnitMgt.partialSearch(code);
				listaUs.add(us);

			} else {
				System.err.println("[HealthUnitRepositoryRDB:getPartialHealthUnitList()] rs is empty");
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}

			// 		O resultado da query e navegado, e cada
			// codigo e informado a um metodo (procura) que
			// monta uma unidade de saude a partir do codigo.
			while (rs.next()) {
				IHealthUnitDt us = this.healthUnitMgt.searchHealthUnit((new Integer(rs.getString("codigo"))).intValue());
				listaUs.add(us);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_PROCURA);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			mp.releaseCommunicationChannel();			
		}

		// 	O retorno desse metodo e uma estrutura que permite a
		// iteracao nos elementos
		System.out.println("[HealthUnitRepositoryRDB:getPartialHealthUnitList()] before return");		
		return distributionMgt.createIterator(listaUs);

	}

	IteratorDsk getHealthUnitListBySpeciality(int code) throws RepositoryException,
	ObjectNotFoundException {
		List listaUS = new ArrayList();

		// Query para selecionar os codigos das unidades associadas
		// a especialidade informada como parametro.
		String sql = "select U.codigo from "
			+ "scbs_unidadeespecialidade R, scbs_especialidade E, scbs_unidadesaude U where "
			+ "E.codigo=R.codigoespecialidade AND U.codigo=R.codigounidadesaude AND "
			+ "E.codigo = '" + code + "'";

		ResultSet rs = null;

		try {
			//get required interface
			if( healthUnitMgt == null){
				IManager mgr = ComponentFactory.createInstance();
				healthUnitMgt = (IHealthUnitMgt) mgr.getRequiredInterface("IHealthUnitMgt");

			}
			
			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			rs = stmt.executeQuery(sql);

			// O resultado da query e testado para saber
			// da existencia de unidades de saude relacionadas.
			// Caso nao existam uma excecao e lancada.
			if (rs.next()) {
				//get required interface
				
				IHealthUnitDt us = this.healthUnitMgt.partialSearch((new Integer(rs.getString("codigo"))).intValue());
				listaUS.add(us);
			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}

			// O resultado da query e navegado, e cada
			// codigo e informado a um metodo (procura) que
			// monta uma unidade de saude a partir do codigo.
			while (rs.next()) {
				IHealthUnitDt us = this.healthUnitMgt.searchHealthUnit((new Integer(rs.getString("codigo"))).intValue());
				listaUS.add(us);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_PROCURA);
		} finally {
			mp.releaseCommunicationChannel();
		}
		// O retorno desse metodo e uma estrutura que permite a
		// iteracao nos elementos


		return distributionMgt.createIterator(listaUS);
	}





}
