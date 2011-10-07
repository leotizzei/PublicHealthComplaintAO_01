package publichealthcomplaint.concurrency.aspects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import publichealthcomplaint.concurrency.spec.req.IPersistenceMechanism;
import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.exceptionhandling.impl.ExceptionMessages;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.PersistenceMechanismException;
import publichealthcomplaint.exceptionhandling.impl.RepositoryException;

public privileged abstract aspect AATimestamp {
	

	
	
	public abstract pointcut insertion(IComplaintDt complaint);

	// After inserting, update the DB
	after(IComplaintDt complaint) : insertion(complaint){
		updateTimestamp(complaint.getTimestamp() + "", "scbs_queixa", complaint.getCodigo() + "");
	}
	
	
	public abstract pointcut search();
	
	// After searching, load from the DB
	after() returning(IComplaintDt complaint) : search(){
		long timestamp = searchTimestamp("scbs_queixa", complaint.getCodigo() + "");
		complaint.setTimestamp(timestamp);
	}
	
	
	public abstract pointcut update(IComplaintDt complaint);
	
	// When updating, check the TS before and update DB after
	void around (IComplaintDt complaint) : update(complaint){
		 
		
		synchronized (this) {
			String sql = null;
			try {
				long timestamp;
				IPersistenceMechanism persitenceMech = null;
				Statement stmt = (Statement) persitenceMech.getCommunicationChannel();
				// vendo se a vers�o do objeto � a mesma no BD
				sql = "select ts from scbs_queixa " + " where codigo='"
						+ complaint.getCodigo() + "'";
				ResultSet resultSet = stmt.executeQuery(sql);
				if (resultSet.next()) {
					timestamp = (new Long(resultSet.getString("ts"))).longValue();
					if (timestamp != complaint.getTimestamp()) {
							new RepositoryException(
								ExceptionMessages.EXC_FALHA_ATUALIZACAO_COPIA);
					} else {
						complaint.incTimestamp();
					}
				} else {
					new ObjectNotFoundException(
						ExceptionMessages.EXC_FALHA_ATUALIZACAO);
				}
				resultSet.close();
				stmt.close();
			} catch (SQLException e) {
				System.out.println(sql);
					new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
			} catch (PersistenceMechanismException e) {
					new RepositoryException(ExceptionMessages.EXC_FALHA_ATUALIZACAO);
			}
		
			proceed(complaint);
		
			updateTimestamp(complaint.getTimestamp() + "", "scbs_queixa", complaint.getCodigo()
					+ "");
		}
	}
	
	// Auxiliary Methods
	private void updateTimestamp(String value, String tableName, String id) {
		String sql = null;
		Statement stmt = null;
		int result = 0;
		try {
			sql = "update " + tableName + " set ts='" + value + "' where codigo='" + id
					+ "'";
			IPersistenceMechanism persistenceMech = null;
			stmt = (Statement) persistenceMech.getCommunicationChannel();
			result = stmt.executeUpdate(sql);
			if (result == 0) {
				throw new RuntimeException("ERRO no aspecto TimestampAspectHealthWatcher ##2");
			}
		} catch (Exception ex) {
			System.err.println("[AATimestamp:updateTimestamp] The following SQL query may have caused the exception ="+sql);
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception ex) {
				System.err.println("[AATimestamp:updateTimestamp] While closing the connection with DB, the following SQL query may have caused the exception ="+sql);
				throw new RuntimeException(ex);
			}
		}
	}

	private long searchTimestamp(String tableName, String id) {
		Statement stmt = null;
		ResultSet resultSet = null;
		long answer = 0;
		try {
			String sql = "SELECT ts FROM " + tableName + " WHERE codigo='" + id + "'";

			IPersistenceMechanism persistenceMech = null;
			stmt = (Statement) persistenceMech.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);
			if (resultSet.next()) {
				answer = resultSet.getLong(1);
			} else {
				throw new RuntimeException("ERRO no aspecto TimestampAspectHealthWatcher ##2");
			}
			return answer;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (stmt != null)
					stmt.close();
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}

}
