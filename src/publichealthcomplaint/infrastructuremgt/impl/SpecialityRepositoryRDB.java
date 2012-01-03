package publichealthcomplaint.infrastructuremgt.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import publichealthcomplaint.datatypes.IMedicalSpecialityDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ExceptionMessages;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.RepositoryException;
import publichealthcomplaint.infrastructuremgt.spec.req.IDistributionMgt;
import publichealthcomplaint.infrastructuremgt.spec.req.IPersistenceMechanism;




class SpecialityRepositoryRDB {

	private IPersistenceMechanism mp;
	
	private IDistributionMgt distributionMgt;

	protected ResultSet resultSet;

	SpecialityRepositoryRDB() {
	
	}

	
	IteratorDsk getSpecialityList() throws RepositoryException, ObjectNotFoundException {

		List listaEsp = new ArrayList();
		String sql = "SELECT * FROM scbs_especialidade";
		ResultSet rs = null;

		try {
			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			rs = stmt.executeQuery(sql);

			if (!rs.next()) {
				throw new ObjectNotFoundException("");
			}
			do {
				IMedicalSpecialityDt esp = null;
				try {
					esp = search((new Integer(rs.getString("codigo"))).intValue());
				} catch (NumberFormatException e) {
				
					e.printStackTrace();
				} catch (RepositoryException e) {
				
					e.printStackTrace();
				}
				listaEsp.add(esp);
			} while (rs.next());

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			
				throw new RepositoryException(ExceptionMessages.EXC_FALHA_PROCURA);
			

		} catch (ObjectNotFoundException e) {
			
			throw new ObjectNotFoundException("Object not found");
		} finally {
			mp.releaseCommunicationChannel();
		}

		return this.distributionMgt.createIterator(listaEsp);
	}

	
	IMedicalSpecialityDt search(int code) throws ObjectNotFoundException, RepositoryException {

		IMedicalSpecialityDt esp = null;
		String sql = null;
		try {
			sql = "select * from scbs_especialidade where " + "codigo = '" + code + "'";

			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);

			if (resultSet.next()) {
				esp = new MedicalSpeciality(resultSet.getString("descricao"));
				esp.setCodigo((new Integer(resultSet.getString("codigo"))).intValue());
			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}
			resultSet.close();
			stmt.close();
		} catch (java.sql.SQLException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		} finally {
			mp.releaseCommunicationChannel();
		}

		return esp;
	}

	
}
