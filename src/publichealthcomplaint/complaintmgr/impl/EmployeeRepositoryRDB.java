package publichealthcomplaint.complaintmgr.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import publichealthcomplaint.complaintmgr.spec.req.IPersistenceMechanism;
import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.exceptionhandling.impl.ExceptionMessages;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.exceptionhandling.impl.PersistenceSoftException;




class EmployeeRepositoryRDB {

	private IPersistenceMechanism pm;

	protected ResultSet resultSet; //Para consultas apenas

	EmployeeRepositoryRDB() {
		
	}

	void insert(IEmployeeDt employee) {
		String sql = null;
		try {
			//Inserir na tabela agora
			sql = "insert into scbs_funcionario (login,nome,senha) values ('";

			sql += employee.getLogin() + "',";
			sql += "'" + employee.getName() + "',";
			sql += "'" + employee.getPassword() + "')";

			Statement stmt = (Statement) this.pm.getCommunicationChannel();
			stmt.executeUpdate(sql);
			stmt.close();

		} catch (SQLException e) {
			System.out.println(sql);
			throw new PersistenceSoftException(e);
		} finally {
			pm.releaseCommunicationChannel();			
		}
	}

	IEmployeeDt search(String login) throws ObjectNotFoundException {
		System.out.println("[EmployeeRepositoryRDB:search()] Looking for "+login+"'s password");
		IEmployeeDt employee = null;
		String sql = null;
		try {

			sql = "select * from scbs_funcionario where login='" + login + "'";

			Statement stmt = (Statement) this.pm.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);
			if (resultSet.next()) {
				employee = new Employee(resultSet.getString("login"), resultSet.getString("senha"),
						resultSet.getString("nome"));
			} else {
				System.out.println("not found " + login);
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(sql);
			throw new PersistenceSoftException(e);
		} finally {
			pm.releaseCommunicationChannel();			
		}
		return employee;
	}

	boolean exists(String login) {
		boolean response = false;
		String sql = null;
		try {
			sql = "select * from scbs_funcionario where login='" + login + "'";

			Statement stmt = (Statement) this.pm.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);
			response = resultSet.next();
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(sql);
			throw new PersistenceSoftException(e);
		} finally {
			pm.releaseCommunicationChannel();			
		}
		return response;
	}

	void update(IEmployeeDt employee) throws ObjectNotFoundException, ObjectNotValidException {
		String sql = null;
		try {
			//Inserir na tabela agora
			sql = "UPDATE scbs_funcionario SET senha='" + employee.getPassword() + "', nome='"
					+ employee.getName() + "' where login='" + employee.getLogin() + "'";
			Statement stmt = (Statement) this.pm.getCommunicationChannel();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			System.out.println(sql);
			throw new PersistenceSoftException(e);
		} finally {
			pm.releaseCommunicationChannel();			
		}
	}

	
}
