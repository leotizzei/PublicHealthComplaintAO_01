package publichealthcomplaint.infrastructuremgt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import publichealthcomplaint.datatypes.IHealthUnitDt;
import publichealthcomplaint.datatypes.IMedicalSpecialityDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ExceptionMessages;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.exceptionhandling.impl.RepositoryException;
import publichealthcomplaint.infrastructuremgt.spec.prov.IManager;
import publichealthcomplaint.infrastructuremgt.spec.req.IDistributionMgt;
import publichealthcomplaint.infrastructuremgt.spec.req.IPersistenceMechanism;


class HealthUnitRepositoryRDB {

	private IPersistenceMechanism mp;

	private IDistributionMgt distributionMgt;

	private ResultSet resultSet;

	private SpecialityRepositoryRDB specialityRep;

	HealthUnitRepositoryRDB(IManager mgr) {
		specialityRep = new SpecialityRepositoryRDB(); 
	}

	void update(IHealthUnitDt us) throws RepositoryException, ObjectNotFoundException,
	ObjectNotValidException {
		if (us != null) {
			String sql = null;
			try {
				Statement stmt = (Statement) this.mp.getCommunicationChannel();
				sql = "update scbs_unidadesaude set " + "descricao='" + us.getDescription() + "'"
				+ " where codigo = '" + us.getCode() + "'";
				stmt.executeUpdate(sql);
				stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sql);
				throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
			} finally {
				mp.releaseCommunicationChannel();
			}
		} else {
			throw new ObjectNotValidException(ExceptionMessages.EXC_NULO);
		}
	}

	IteratorDsk getHealthUnitList() throws RepositoryException, ObjectNotFoundException {
		List listaUs = new ArrayList();

		// Query para selecionar os codigos de todas unidades de saude
		// existentes no sistema
		String sql = "SELECT codigo FROM scbs_unidadesaude";
		ResultSet rs = null;

		try {
			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			rs = stmt.executeQuery(sql);

			// O resultado da query e testado para saber
			// da existencia de unidades de saude cadastradas.
			// Caso nao existam uma excecao e lancada.
			if (rs.next()) {
				IHealthUnitDt us = search((new Integer(rs.getString("codigo"))).intValue());
				listaUs.add(us);
			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}

			// O resultado da query e navegado, e cada
			// codigo e informado a um metodo (procura) que
			// monta uma unidade de saude a partir do codigo.
			while (rs.next()) {
				IHealthUnitDt us = new HealthUnit();
				us = search((new Integer(rs.getString("codigo"))).intValue());
				listaUs.add(us);
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

		return distributionMgt.createIterator(listaUs);
	}

	IHealthUnitDt search(int code) throws RepositoryException, ObjectNotFoundException {

		IHealthUnitDt us = null;
		String sql = null;
		try {

			// Query montada para recuperar os relacionamentos de
			// unidades de saude com especialidades
			// filtrando pelo identificador da unidade.
			sql = "select * from scbs_unidadeespecialidade where " + "codigounidadesaude = '"
			+ code + "'";
			 
			Statement stmt = (Statement) this.mp.getCommunicationChannel();

			resultSet = stmt.executeQuery(sql);
			List specialities = new ArrayList();

			// Iterar nos resultados da query para recuperar as
			// especialidades e inserir em um conjunto
			// (RepositorioEspecialidadeArray)
			while (resultSet.next()) {
				try {
					IMedicalSpecialityDt esp = specialityRep.search((new Integer(resultSet
							.getString("codigoespecialidade"))).intValue());
					System.out.println("medicalspeciality: " + esp.getCodigo() + " "
							+ esp.getDescricao());
					specialities.add(esp);
				} catch (ObjectNotFoundException ex) {
				}
			}
			resultSet.close();
			stmt.close();

			// Query montada para recuperar a unidade de saude
			// usando o identificador da unidade informado como
			// parametro do metodo
			sql = "select * from scbs_unidadesaude where " + "codigo = '" + code + "'";
			System.out.println("[HealthUnitRepositoryRDB:search()] sql="+sql);
			stmt = (Statement) this.mp.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);
			
			 
		
			if ( resultSet.next() ) {
				String descr = resultSet.getString("descricao");
			
				us = new HealthUnit( descr, specialities);

				//us.setId(resultSet.getLong("ID"));
				us.setCode((new Integer(resultSet.getString("codigo"))).intValue());

				//preparar para buscar em outra tabela as especialidades desta unidade de saude
				//depois vai chamar deepAccess() de RepositorioEspecialidadeBDR
			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}
			resultSet.close();
			stmt.close();

		} catch (SQLException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		} finally {
			mp.releaseCommunicationChannel();
		}

		return us;
	}

	IHealthUnitDt partialSearch(int codigo) throws RepositoryException, ObjectNotFoundException {
		IHealthUnitDt hu = null;
		String sql = null;
		try {
			// Query montada para recuperar a unidade de saude
			// usando o identificador da unidade informado como
			// parametro do metodo
			sql = "select * from scbs_unidadesaude where " + "codigo = '" + codigo + "'";

			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);

			if (resultSet.next()) {
				hu = new HealthUnit();
				hu.setCode((new Integer(resultSet.getString("codigo"))).intValue());
				hu.setDescription(resultSet.getString("descricao"));
			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}
			resultSet.close();
			stmt.close();

		} catch (SQLException e) {
			System.out.println(sql);
			throw new RepositoryException("SQLException: " + e.getMessage());
		} finally {
			mp.releaseCommunicationChannel();
		}
		return hu;
	}


}
