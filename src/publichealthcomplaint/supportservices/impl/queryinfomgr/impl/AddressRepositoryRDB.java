package publichealthcomplaint.supportservices.impl.queryinfomgr.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import publichealthcomplaint.datatypes.IAddressDt;
import publichealthcomplaint.exceptionhandling.impl.ExceptionMessages;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.exceptionhandling.impl.PersistenceMechanismException;
import publichealthcomplaint.exceptionhandling.impl.RepositoryException;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req.IPersistenceMechanism;




/**
 * Repositorio responsavel por realizar a persitencia
 * de enderecos. Esse persistencia e realizada em banco
 * de dados relacional utilizando JDBC
 */
public class AddressRepositoryRDB{

	private IPersistenceMechanism mp;

	private ResultSet resultSet;

	AddressRepositoryRDB() {
		
	}

	/**
	 * Metodo para atualizacao de um endereco.
	 * Na versao atual do sistema disque saude
	 * essa funcionalidade nao esta implementada.
	 */
	private void update(IAddressDt end) throws RepositoryException, ObjectNotFoundException {
	}

	/**
	 * Metodo para verificacao da existencia de um
	 * endereco com codigo especificado como parametro.
	 * Na versao atual do sistema disque saude essa
	 * funcionalidade nao esta implementada.
	 */
	private boolean exists(int codigo) throws RepositoryException {
		return false;
	}

	/**
	 * Metodo para insercao de endereco no repositrorio persistente.
	 * Essa implementacao faz a persistencia atraves de JDBC
	 * conectando com um banco de dados relacional.
	 */
	int insert(IAddressDt end) throws ObjectAlreadyInsertedException, ObjectNotValidException,
		ObjectNotValidException, RepositoryException {
		
		// teste da validade do objeto a ser inserido
		if (end == null) {
			throw new ObjectNotValidException(ExceptionMessages.EXC_NULO);
		}

		Statement stmt;

		try {

			// Bloco para a recuperacao de um codigo sequencial do
			// banco de dados para ser utilizado como identificador
			// e codigo da tabela de enderecos.
			String consulta = null;
			try {

				//pega id e codigo e seta no objeto primeiro
				consulta = "select * from scbs_endereco";

				stmt = (Statement) mp.getCommunicationChannel();
				resultSet = stmt.executeQuery(consulta);

				int count = 0;
				while (resultSet.next()) {
					count++;
				}
				end.setCode(count + 1);

				count++;

				resultSet.close();
				stmt.close();

			} catch (SQLException e) {
				System.out.println(consulta);
				throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
			} finally {
				mp.releaseCommunicationChannel();			
			}

			// Consulta na tabela ao codigo gerado pelo trecho anterior.
			// Caso o codigo ja esteja em uso uma excecao e levantada.
			try {
				stmt = (Statement) mp.getCommunicationChannel();
				String sql = "SELECT * FROM scbs_endereco WHERE codigo = '" + end.getCode() + "'";
				resultSet = stmt.executeQuery(sql);

				if (resultSet.next()) {
					throw new ObjectAlreadyInsertedException(ExceptionMessages.EXC_JA_EXISTE);
				}
				resultSet.close();
				stmt.close();
			} catch (SQLException e) {
				throw new PersistenceMechanismException(ExceptionMessages.EXC_FALHA_BD);
			} finally {
				mp.releaseCommunicationChannel();			
			}

			// Essa ultima etapa da insercao insere de fato os valores
			// recebidos no objeto do parametro com codigo e
			// identificador devidamente alterados
			try {
				String sql = "INSERT INTO scbs_endereco VALUES(";

				//sql += end.getId() + ",";
				sql += "'" + end.getCode() + "',";
				sql += "'" + end.getStreet() + "',";
				sql += "'" + end.getComplement() + "',";
				sql += "'" + end.getZip() + "',";
				sql += "'" + end.getState() + "',";
				sql += "'" + end.getPhone() + "',";
				sql += "'" + end.getCity() + "',";
				sql += "'" + end.getNeighbourhood() + "')";

				stmt = (Statement) this.mp.getCommunicationChannel();
				stmt.executeUpdate(sql);
				stmt.close();

			} catch (SQLException e) {
				throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
			} finally {
				mp.releaseCommunicationChannel();			
			}
		} catch (PersistenceMechanismException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_INCLUSAO);
		}
		
		// TODO THIS IS WRONG!!!
		return -1;
	}

	/**
	 * Metodo para recuperacao de endereco a partir do codigo.
	 *
	 * @param codigo Codigo identificador do endereco a 
	 *				 ser recuperado.
	 * @return endereco montado com os dados recuperados do bd
	 * @exception RepositorioException caso ocorra algum problema
	 *			  relacionado ao acesso aos dados persistentes.
	 * @exception ObjetoInexistenteException caso o endereco
	 *			  nao seja encontrada nos dados persistentes
	 */
	IAddressDt search(int code) throws RepositoryException, ObjectNotFoundException {
		IAddressDt end = null;
		String sql = null;
		try {
			sql = "select * from scbs_endereco where " + " codigo = '" + code + "'";

			Statement stmt = (Statement) this.mp.getCommunicationChannel();

			//System.out.println(sql);
			resultSet = stmt.executeQuery(sql);

			if (resultSet.next()) {
				//long id = resultSet.getLong("ID");
				code = (new Integer(resultSet.getString("codigo"))).intValue();

				String rua = resultSet.getString("rua");
				String complemento = resultSet.getString("complemento");
				String cep = resultSet.getString("cep");
				String uf = resultSet.getString("uf");
				String fone = resultSet.getString("fone");
				String cidade = resultSet.getString("cidade");
				String bairro = resultSet.getString("bairro");
				end = new Address(rua, complemento, cep, uf, fone, cidade, bairro);
				end.setCode(code);

			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}
			resultSet.close();
			stmt.close();

		} catch (SQLException e) {
			System.out.println(sql);
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		} finally {
			mp.releaseCommunicationChannel();			
		}

		return end;
	}

	private void remove(int code) throws ObjectNotFoundException, RepositoryException {
	}
}
