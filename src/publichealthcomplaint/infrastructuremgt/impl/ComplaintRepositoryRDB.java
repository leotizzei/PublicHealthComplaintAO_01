package publichealthcomplaint.infrastructuremgt.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.exceptionhandling.impl.ExceptionMessages;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.exceptionhandling.impl.RepositoryException;
import publichealthcomplaint.infrastructuremgt.spec.req.IPersistenceMechanism;


class ComplaintRepositoryRDB  {

	private IPersistenceMechanism mp;

	protected ResultSet resultSet; // Para consultas apenas

	

	public void update(IComplaintDt complaint) throws RepositoryException, ObjectNotFoundException,
			ObjectNotValidException {
		if (complaint != null) {
			String sql = null;
			try {
				
				Statement stmt = (Statement) this.mp.getCommunicationChannel();
				sql = "update scbs_queixa set " + "observacao='"
						+ complaint.getObservacao() + "', " + "situacao= '"
						+ complaint.getSituacao() + "', " + "funcionario= '"
						+ complaint.getAtendente().getLogin() + "'";

				if (complaint.getDataParecer() != null) {
					sql += ", dataparecer= '" + complaint.getDataParecer() + "'";
				}
				sql += " where codigo = '" + complaint.getCodigo() + "'";

				int response = stmt.executeUpdate(sql);
				if (response == 0) {
					throw new ObjectNotFoundException(
							ExceptionMessages.EXC_FALHA_ATUALIZACAO);
				}
				stmt.close();
			} catch (SQLException e) {
				throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
			}
		} else {
			throw new ObjectNotValidException(ExceptionMessages.EXC_NULO);
		}
	}

	

	
}
