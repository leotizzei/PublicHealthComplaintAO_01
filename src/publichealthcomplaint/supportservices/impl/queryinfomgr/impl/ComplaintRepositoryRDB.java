package publichealthcomplaint.supportservices.impl.queryinfomgr.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import publichealthcomplaint.datatypes.Complaint;
import publichealthcomplaint.datatypes.IAddressDt;
import publichealthcomplaint.datatypes.IAnimalComplaintDt;
import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.datatypes.IFoodComplaintDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ExceptionMessages;
import publichealthcomplaint.exceptionhandling.impl.InvalidDateException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.RepositoryException;

import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.prov.IManager;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req.IDistributionMgt;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req.IEmployeeMgt;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req.IPersistenceMechanism;


class ComplaintRepositoryRDB {

	private IPersistenceMechanism mp;
	
	private IDistributionMgt distributionMgt;

	protected ResultSet resultSet; // Para consultas apenas

	private AddressRepositoryRDB addressRep;

	private IEmployeeMgt employeeRep;

	private final int FOOD_COMPLAINT = 1;

	private final int ANIMAL_COMPLAINT = 2;

	private final int SPECIAL_COMPLAINT = 3;

	private IManager manager;
	
	ComplaintRepositoryRDB(IManager mgr) {
		manager = mgr;
		addressRep = new AddressRepositoryRDB();
		employeeRep = (IEmployeeMgt) manager.getRequiredInterface("IEmployeeMgt");
		distributionMgt = (IDistributionMgt) manager.getRequiredInterface("IDistributionMgt"); 
	}

	private IFoodComplaintDt accessFood(int code) throws RepositoryException, ObjectNotFoundException {

		IFoodComplaintDt complaint;
		String sql = null;
		try {
			complaint = new FoodComplaint();

			// fazer join para acessar as duas tabelas
			sql = "select * from scbs_queixa q,scbs_queixaalimentar qa where q.codigo=qa.codigo and q."
				+ "codigo = '" + code + "';";

			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);

			if (resultSet.next()) {
				try {
					accessComplaint(resultSet, complaint);
				} catch (ObjectNotFoundException e) {
					e.printStackTrace();
				}
				complaint.setQtdeComensais(resultSet.getShort("qtdeComensais"));
				complaint.setQtdeDoentes(resultSet.getShort("qtdeDoentes"));
				complaint.setQtdeInternacoes(resultSet.getShort("qtdeInternacoes"));
				complaint.setQtdeObitos(resultSet.getShort("qtdeObitos"));
				complaint.setLocalAtendimento(resultSet.getString("localAtendimento"));
				complaint.setRefeicaoSuspeita(resultSet.getString("refeicaoSuspeita"));

				String endDoente = resultSet.getString("enderecodoente");
				// System.out.println("endereco doente = "+endDoente);
				IAddressDt endDo = addressRep.search((new Integer(endDoente)).intValue());
				complaint.setEnderecoDoente(endDo);
			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}
			resultSet.close();
			stmt.close();
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		} finally {
			mp.releaseCommunicationChannel();			
		}
		return complaint;
	}

	/**
	 * Metodos para recuperar uma queixa animal a partir do seu codigo
	 * 
	 * @param codigo
	 *            codigo da queixa animal
	 * @return queixa animal encontrada
	 * @exception RepositoryException
	 *                erro no acesso ao repositorio de dados
	 * @exception ObjectNotFoundException
	 *                caso nao seja encontrada uma queixa com o codigo
	 *                especificado.
	 */
	private IAnimalComplaintDt accessAnimal(int codigo) throws RepositoryException,
	ObjectNotFoundException {

		IAnimalComplaintDt complaint;
		String sql = null;
		try {

			complaint = new AnimalComplaint();

			// Query sql que recupera todos os dados de uma complaint animal
			// para isso realiza um join para acessar as duas tabelas
			// A primeira tabela contem os dados genericos a todas as
			// queixas, a segunda tabela contem os dados especificos
			// a complaint animal
			sql = "select * from scbs_queixa q,scbs_queixaanimal qa where q.codigo=qa.codigo and q."
				+ "codigo = '" + codigo + "'";

			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);

			if (resultSet.next()) {
				accessComplaint(resultSet, complaint);

				complaint.setAnimalQuantity(resultSet.getShort("qtdeAnimais"));
				String date = resultSet.getString("dataIncomodo");
				java.util.Date d = null;
				if (!date.equals("NULL")) {
					StringTokenizer token = new StringTokenizer(date, "/");
					int day = (new Integer(token.nextToken())).intValue();
					int month = (new Integer(token.nextToken())).intValue();
					int year = (new Integer(token.nextToken())).intValue();
					d = new java.util.Date(year, month, day);
				}
				if (d != null) {
					try {
						complaint.setInconvenienceDate(new Date(d.getDate(), d.getMonth() + 1, d
								.getYear() + 1900));
					} catch (InvalidDateException ex) {
					}
				} else {
					complaint.setInconvenienceDate(null);
				}

				complaint.setAnimal(resultSet.getString("animal"));

				String endAnimal = resultSet.getString("enderecolocalocorrencia");
				// System.out.println("endereco animal = "+ endAnimal);
				IAddressDt endLO = addressRep.search((new Integer(endAnimal)).intValue());
				complaint.setOccurenceLocalAddress(endLO);

			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		} finally {
			mp.releaseCommunicationChannel();			
		}
		return complaint;
	}

	private void accessComplaint(ResultSet resultSet, IComplaintDt complaint) throws SQLException,
	ObjectNotFoundException, RepositoryException {
		try {
			complaint.setCodigo((new Integer(resultSet.getString("codigo"))).intValue());
			complaint.setSolicitante(resultSet.getString("solicitante"));
			complaint.setDescricao(resultSet.getString("descricao"));
			complaint.setObservacao(resultSet.getString("observacao"));
			complaint.setEmail(resultSet.getString("email"));

			String funcionario = resultSet.getString("funcionario");
			IEmployeeDt employee = null;
			if (funcionario != null && !funcionario.equalsIgnoreCase("null")) {
				employee = employeeRep.searchEmployee(funcionario);
			}
			complaint.setAtendente(employee);

			complaint.setSituacao((new Integer(resultSet.getString("situacao"))).intValue());

			try {
				java.util.Date d = null;
				String date = resultSet.getString("dataParecer");
				if (!date.equals("NULL")) {
					StringTokenizer token = new StringTokenizer(date, "/");
					int day = (new Integer(token.nextToken())).intValue();
					int month = (new Integer(token.nextToken())).intValue();
					int year = (new Integer(token.nextToken())).intValue();
					d = new java.util.Date(year, month, day);
				}
				if (d != null) {
					try {
						complaint.setDataParecer(new Date(d.getDate(), d.getMonth() + 1, d
								.getYear() + 1900));
					} catch (InvalidDateException ex) {
					}
				} else {
					complaint.setDataParecer(null);
				}
				date = resultSet.getString("dataQueixa");
				if (!date.equals("NULL")) {

					StringTokenizer token = new StringTokenizer(date, "/");
					int day = (new Integer(token.nextToken())).intValue();
					int month = (new Integer(token.nextToken())).intValue();
					int year = (new Integer(token.nextToken())).intValue();
					d = new java.util.Date(year, month, day);
				} else
					d = null;
				if (d != null) {
					try {
						complaint.setDataQueixa(new Date(d.getDate(), d.getMonth() + 1,
								d.getYear() + 1900));
					} catch (InvalidDateException ex) {
					}
				} else {
					complaint.setDataQueixa(null);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			int codEndereco = resultSet.getInt("enderecosolicitante");
			// System.out.println("endereco solicitante" + codEndereco);
			IAddressDt endSol = addressRep.search(codEndereco);
			complaint.setEnderecoSolicitante(endSol);

		} catch (RepositoryException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		} catch (ObjectNotFoundException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		} catch (SQLException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		}

	}

	/**
	 * Metodos para recuperar uma queixa diversa a partir do seu codigo
	 * 
	 * @param codigo
	 *            codigo da queixa diversa
	 * @return queixa diversa encontrada
	 * @exception RepositoryException
	 *                erro no acesso ao repositorio de dados
	 * @exception ObjectNotFoundException
	 *                caso nao seja encontrada uma queixa com o codigo
	 *                especificado.
	 */
	private SpecialComplaint accessSpecial(int code) throws RepositoryException,
	ObjectNotFoundException {
		SpecialComplaint complaint;
		String sql = null;
		try {

			complaint = new SpecialComplaint();

			// fazer join para acessar as duas tabelas
			sql = "select * from scbs_queixa q, scbs_queixadiversa qd where q.codigo=qd.codigo and q."
				+ "codigo = '" + code + "'";

			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);

			if (resultSet.next()) {

				accessComplaint(resultSet, complaint);

				complaint.setIdade(resultSet.getShort("idade"));
				complaint.setInstrucao(resultSet.getString("instrucao"));
				complaint.setOcupacao(resultSet.getString("ocupacao"));

				IAddressDt endO = addressRep.search((new Integer(resultSet
						.getString("enderecoocorrencia")).intValue()));
				complaint.setEnderecoOcorrencia(endO);
			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(sql);
			e.printStackTrace();
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		} finally {
			mp.releaseCommunicationChannel();			
		}
		return complaint;
	}


	IComplaintDt search(int code) throws RepositoryException, ObjectNotFoundException {
		// Query montada para obtencao do tipo de queixa referente ao
		// codigo informado
		String sql = "SELECT tipoqueixa FROM scbs_queixa WHERE codigo = '" + code + "'";
		int tipoQueixa;
		IComplaintDt q = null;

		try {
			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				tipoQueixa = (new Integer(rs.getString("tipoqueixa"))).intValue();
			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA + " code: "
						+ code);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(sql);
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_PROCURA);
		} finally {
			mp.releaseCommunicationChannel();			
		}

		// Dependendo do tipo da queixa o acesso aos dados e feito
		// por um metodo especifico
		switch (tipoQueixa) {

		case Complaint.QUEIXA_ALIMENTAR:
			q = accessFood(code);
			break;

		case Complaint.QUEIXA_ANIMAL:
			q = accessAnimal(code);
			break;

		case Complaint.QUEIXA_DIVERSA:
			q = accessSpecial(code);
			break;

		default:
			throw new IllegalArgumentException();
		}
		return q;
	}

	IteratorDsk getComplaintList() throws ObjectNotFoundException, RepositoryException {
		List cList = new ArrayList();
		String sql = "SELECT * FROM scbs_queixa";
		ResultSet rs = null;
		IComplaintDt complaint = null;
		try {
			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			rs = stmt.executeQuery(sql);

			if (!rs.next()) {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}
			do {
				int tipoQueixa = (new Integer(rs.getString("tipoqueixa"))).intValue();
				int code = (new Integer(rs.getString("codigo"))).intValue();
				switch (tipoQueixa) {
				case SPECIAL_COMPLAINT:
					complaint = accessSpecial(code);
					break;

				case FOOD_COMPLAINT:
					complaint = accessFood(code);
					break;

				case ANIMAL_COMPLAINT:
					complaint = accessAnimal(code);
					break;

				default:
					throw new IllegalArgumentException();
				}
				cList.add(complaint);
			} while (rs.next());

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException("SQLException: " + e.getMessage());
		} catch (RepositoryException e) {
			e.printStackTrace();
			throw new RepositoryException("SQLException: " + e.getMessage());
		} finally {
			mp.releaseCommunicationChannel();			
		}

		return this.distributionMgt.createIterator(cList);
	}	


}
