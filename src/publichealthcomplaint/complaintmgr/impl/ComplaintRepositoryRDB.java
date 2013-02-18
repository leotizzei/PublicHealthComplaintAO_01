package publichealthcomplaint.complaintmgr.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import publichealthcomplaint.complaintmgr.spec.prov.IManager;
import publichealthcomplaint.complaintmgr.spec.req.IDistributionMgt;
import publichealthcomplaint.complaintmgr.spec.req.IPersistenceMechanism;
import publichealthcomplaint.datatypes.Complaint;
import publichealthcomplaint.datatypes.Constants;
import publichealthcomplaint.datatypes.IAddressDt;
import publichealthcomplaint.datatypes.IAnimalComplaintDt;
import publichealthcomplaint.datatypes.IComplaintDt;
import publichealthcomplaint.datatypes.IDateDt;
import publichealthcomplaint.datatypes.IDrugComplaintDt;
import publichealthcomplaint.datatypes.IDrugDataDt;
import publichealthcomplaint.datatypes.IEmployeeDt;
import publichealthcomplaint.datatypes.IFoodComplaintDt;
import publichealthcomplaint.datatypes.ISpecialComplaintDt;
import publichealthcomplaint.datatypes.ISuspectMedicalDeviceDt;
import publichealthcomplaint.datatypes.ISuspectProductDt;
import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ExceptionMessages;
import publichealthcomplaint.exceptionhandling.impl.InvalidDateException;
import publichealthcomplaint.exceptionhandling.impl.ObjectAlreadyInsertedException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotValidException;
import publichealthcomplaint.exceptionhandling.impl.PersistenceMechanismException;
import publichealthcomplaint.exceptionhandling.impl.PersistenceSoftException;
import publichealthcomplaint.exceptionhandling.impl.RepositoryException;





class ComplaintRepositoryRDB {

	private IPersistenceMechanism mp;

	private IDistributionMgt distributionMgt;

	protected ResultSet resultSet; // Para consultas apenas

	private AddressRepositoryRDB addressRep;

	private EmployeeRepositoryRDB employeeRep;



	private IManager manager;

	ComplaintRepositoryRDB(IManager mgr) {
		manager = mgr;
		addressRep = new AddressRepositoryRDB();
		employeeRep = new EmployeeRepositoryRDB();


	}

	private IFoodComplaintDt accessFood(int code) throws RepositoryException, ObjectNotFoundException, InvalidDateException {

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
				System.err.println("The result of the query ["+sql+"] is either empty or null");
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}
			resultSet.close();
			stmt.close();
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		}
		finally
		{
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
	 * @throws InvalidDateException 
	 */
	private IAnimalComplaintDt accessAnimal(int codigo) throws RepositoryException,
	ObjectNotFoundException, InvalidDateException {

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
		}
		finally
		{
			mp.releaseCommunicationChannel();
		}

		return complaint;
	}


	private IDrugComplaintDt accessDrug(int code) throws ObjectNotFoundException, RepositoryException, InvalidDateException{


		IDrugComplaintDt complaint = null;
		String sql = null;
		try {

			complaint = new DrugComplaint();
			IDrugDataDt drugData = new DrugData();

			sql = "select * from scbs_queixa q,scbs_queixamedica qa where q.codigo=qa.code and q."
					+ "codigo = '" + code + "'";
			System.out.println("[accessDrug] sql="+sql);
			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);

			if (resultSet.next()) {
				accessComplaint(resultSet, complaint);
				//TODO criar uma tabela pra drogas no BD

			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		}
		finally
		{
			mp.releaseCommunicationChannel();
		}

		return complaint;

	}

	private void accessComplaint(ResultSet resultSet, IComplaintDt complaint) throws SQLException,
	ObjectNotFoundException, RepositoryException, InvalidDateException {
		try {
			complaint.setCodigo((new Integer(resultSet.getString("codigo"))).intValue());
			complaint.setSolicitante(resultSet.getString("solicitante"));
			complaint.setDescricao(resultSet.getString("descricao"));
			complaint.setObservacao(resultSet.getString("observacao"));
			complaint.setEmail(resultSet.getString("email"));

			String funcionario = resultSet.getString("funcionario");
			IEmployeeDt employee = null;
			if (funcionario != null && !funcionario.equalsIgnoreCase("null")) {
				employee = employeeRep.search(funcionario);
			}
			if( employee != null)
				complaint.setAtendente(employee);
			else
				System.out.println("Employee "+funcionario+" was not found");

			complaint.setSituacao((new Integer(resultSet.getString("situacao"))).intValue());
			System.out.println("[ComplaintRepositoryRDB:accessComplaint()] situacao="+complaint.getSituacao());

			java.util.Date d = null;
			String dateFeedback = resultSet.getString("dataParecer");
			System.out.println("[ComplaintRepositoryRDB:accessComplaint()] date="+dateFeedback);
			if ( ( dateFeedback != null ) && ( !dateFeedback.equals("NULL") ) ) {
				StringTokenizer token = new StringTokenizer(dateFeedback, "/");
				int day = (new Integer(token.nextToken())).intValue();
				int month = (new Integer(token.nextToken())).intValue();
				int year = (new Integer(token.nextToken())).intValue();
				d = new java.util.Date(year, month, day);

				if (d != null) {
					try {
						complaint.setDataParecer(new Date(d.getDate(), d.getMonth() + 1, d
								.getYear() + 1900));
						System.out.println("[ComplaintRepositoryRDB:accessComplaint()] ano data parecer ="+complaint.getDataParecer().getAno());
					} catch (InvalidDateException ex) {
						throw new InvalidDateException("Invalid date");
					}
				} else {
					complaint.setDataParecer(null);
				}
			}
			String dateComplaint = resultSet.getString("dataQueixa");
			System.out.println("[ComplaintRepositoryRDB:accessComplaint()] date=["+dateComplaint+"]");
			if ( ( dateComplaint != null) && ( !dateComplaint.equals("NULL") ) && ( !dateComplaint.equals("") ) ) {

				StringTokenizer token = new StringTokenizer(dateComplaint, "/");
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
					throw new InvalidDateException("Invalid Date");
				}
			} else {
				complaint.setDataQueixa(null);
			}


			int codEndereco = resultSet.getInt("enderecosolicitante");
			System.out.println("[ComplaintRepositoryRDB:accessComplaint()] codEndereco="+codEndereco);

			if( ( addressRep  != null) && ( codEndereco > 0) ){
				System.out.println("[ComplaintRepositoryRDB:accessComplaint()] looking for address, given codEndereco="+codEndereco);
				IAddressDt endSol = addressRep.search(codEndereco);
				complaint.setEnderecoSolicitante(endSol);
			}
			else
				System.out.println("[ComplaintRepositoryRDB:accessComplaint()] No address available");
		} catch (RepositoryException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		} catch (ObjectNotFoundException e) {
			System.out.println("[ComplaintRepositoryRDB:accessComplaint()]"+e.getLocalizedMessage());
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
	 * @throws InvalidDateException 
	 */
	private SpecialComplaint accessSpecial(int code) throws RepositoryException,
	ObjectNotFoundException, InvalidDateException {
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
		}
		finally
		{
			mp.releaseCommunicationChannel();
		}

		return complaint;
	}


	void update(IComplaintDt complaint) throws RepositoryException, ObjectNotFoundException,
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

				System.out.println("[ComplaintRepositoryRDB:update()] sql="+sql);
				int response = stmt.executeUpdate(sql);
				if (response == 0) {
					System.err.println("Response is zero because a database access error occurred");
					throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_ATUALIZACAO);
				}
				System.out.println("[ComplaintRepositoryRDB:update()] Closing connection...");
				stmt.close();
			} catch (SQLException e) {
				System.out.println(e.getLocalizedMessage()+" SQL="+sql);
				throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
			}
			finally
			{
				mp.releaseCommunicationChannel();
			}
		} else {
			throw new ObjectNotValidException(ExceptionMessages.EXC_NULO);
		}
	}


	private void deepInsertFood(IFoodComplaintDt complaint) throws PersistenceMechanismException,
	RepositoryException, ObjectAlreadyInsertedException {
		if (complaint.getEnderecoDoente() != null) {
			try {
				addressRep.insert(complaint.getEnderecoDoente());
			} catch (ObjectNotValidException e) {
			}
		}
		insertFood(complaint);
	}

	private void deepInsertAnimal(IAnimalComplaintDt complaint) throws PersistenceMechanismException,
	RepositoryException, ObjectAlreadyInsertedException {
		if (complaint.getOccurenceLocalAddress() != null) {
			try {
				addressRep.insert(complaint.getOccurenceLocalAddress());
			} catch (ObjectNotValidException e) {
			}
		}
		insertAnimal(complaint);
	}

	private int getComplaintType(IComplaintDt complaint){
		Class interfaces[] = complaint.getClass().getInterfaces();


		int i;
		for( i = 0; i < interfaces.length; i++){
			if( interfaces[i].equals(IAnimalComplaintDt.class) )
				return Complaint.QUEIXA_ANIMAL;
			if( interfaces[i].equals(IFoodComplaintDt.class) )
				return Complaint.QUEIXA_ALIMENTAR;
			if( interfaces[i].equals(ISpecialComplaintDt.class) )
				return Complaint.QUEIXA_DIVERSA;
			if( interfaces[i].equals(IDrugComplaintDt.class) )
				return Complaint.QUEIXA_DROGA;
		}
		return -1;
	}

	private void deepInsertCommon(IComplaintDt complaint) throws ObjectAlreadyInsertedException,
	PersistenceMechanismException, RepositoryException {
		String sql = null;
		try {
			if (complaint.getEnderecoSolicitante() != null) {
				try {
					addressRep.insert(complaint.getEnderecoSolicitante());
				} catch (ObjectNotValidException e) {
				}
			}

			int complaintType = this.getComplaintType(complaint);

			sql = "INSERT INTO scbs_queixa (codigo,tipoqueixa,solicitante,descricao,observacao,email,funcionario,situacao,dataparecer,dataqueixa,enderecosolicitante) VALUES(";
			sql += "'" + complaint.getCodigo() + "'" + ",";
			sql += "'" + complaintType + "'" + ",";
			sql += "'" + complaint.getSolicitante() + "',";
			sql += "'" + complaint.getDescricao() + "',";
			sql += "'" + complaint.getObservacao() + "',";
			sql += "'" + complaint.getEmail() + "',";

			if (complaint.getAtendente() != null) {
				sql += "'" + complaint.getAtendente().getLogin() + "','";
			} else {
				sql += "'NULL',";
			}

			sql += "'" + complaint.getSituacao() + "'" + ",";
			IDateDt date = complaint.getDataParecer();
			if (complaint.getDataParecer() != null) {
				sql += "'" +  date.format(1) + "'";
			} else {
				sql += "'NULL',";
			}

			date = complaint.getDataQueixa();
			if (complaint.getDataQueixa() != null) {
				sql += "'" + date.format(1) + "',";
				
			} else {
				sql += "'',";
				
			}

			if (complaint.getEnderecoSolicitante() != null) {
				sql += "'" + complaint.getEnderecoSolicitante().getCode() + "'";
			} else {
				sql += "NULL";
			}
			sql += ");";

			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			
			//debug
			System.out.println("[ComplaintRepositoryRDB:deepInsertCommon()] final sql="+sql);
			
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			System.err.println(e.getLocalizedMessage());
			System.err.println("[ComplaintRepositoryRDB:deepInserCommon()] SQL="+sql);
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		}
		finally
		{
			mp.releaseCommunicationChannel();
		}
	}

	private void deepInsertSpecial(ISpecialComplaintDt complaint)
			throws PersistenceMechanismException, RepositoryException,
			ObjectAlreadyInsertedException {
		if (complaint.getEnderecoOcorrencia() != null) {
			try {
				addressRep.insert(complaint.getEnderecoOcorrencia());
			} catch (ObjectNotValidException e) {
			}
		}
		insertSpecial(complaint);
	}

	private void deepInsertDrug(IDrugComplaintDt complaint) throws PersistenceMechanismException, RepositoryException, ObjectAlreadyInsertedException {

		insertDrug(complaint);
	}

	boolean exists(int code) {
		boolean response = false;
		String consulta = null;
		try {
			consulta = "select codigo from scbs_queixa where codigo='" + code + "'";

			Statement stmt = (Statement) mp.getCommunicationChannel();
			resultSet = stmt.executeQuery(consulta);

			response = resultSet.next();

			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			System.out.println(consulta);
			throw new PersistenceSoftException(e);
		}
		finally
		{
			mp.releaseCommunicationChannel();
		}
		return response;
	}

	int insert(IComplaintDt complaint) throws ObjectAlreadyInsertedException,
	RepositoryException, ObjectNotValidException {
		System.out.println("[ComplaintRepository:insert()]");
		String consulta = null;
		try {
			if (complaint != null) {
				consulta = "select * FROM scbs_queixa";

				Statement stmt = (Statement) mp.getCommunicationChannel();
				resultSet = stmt.executeQuery(consulta);

				int max = 0;
				while (resultSet.next()) {
					String str = resultSet.getString("CODIGO");
					int tmp = Integer.parseInt(str);
					if( tmp > max )
						max = tmp;
				}
				complaint.setCodigo(max + 1);

				deepInsertCommon(complaint);

				int complaintType = getComplaintType(complaint);
				switch( complaintType ){
				//food complaint
				case Complaint.QUEIXA_ALIMENTAR:
					IFoodComplaintDt food = (IFoodComplaintDt) complaint;
					deepInsertFood(food);break;
					//animal complaint
				case Complaint.QUEIXA_ANIMAL:
					IAnimalComplaintDt animal = (IAnimalComplaintDt) complaint;
					deepInsertAnimal(animal);break;
					//special complaint
				case Complaint.QUEIXA_DIVERSA:
					ISpecialComplaintDt special = (ISpecialComplaintDt) complaint;
					deepInsertSpecial(special); break;
					//drug complaint
				case Complaint.QUEIXA_DROGA:
					IDrugComplaintDt drugComplaint = (IDrugComplaintDt) complaint;
					deepInsertDrug(drugComplaint); break;
				default:
					System.err.println("The type of complaint is undefined");

				}


			}
		} catch (PersistenceMechanismException e) {
			e.printStackTrace();
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_INCLUSAO);
		} catch (SQLException e) {
			System.out.println(consulta);
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("[ComplaintRepository:insert()] Exception:"+e.getLocalizedMessage());
			e.printStackTrace();
		}
		finally
		{
			mp.releaseCommunicationChannel();
		}

		return complaint.getCodigo();
	}

	private void insertFood(IFoodComplaintDt complaint) throws RepositoryException {
		String sql = null;
		try {
			sql = "insert into scbs_queixaalimentar (codigo,qtdecomensais,qtdedoentes,qtdeinternacoes,qtdeobitos,localatendimento,refeicaosuspeita,enderecodoente) values (";
			sql += "'" + complaint.getCodigo() + "','";
			sql += complaint.getQtdeComensais() + "','";
			sql += complaint.getQtdeDoentes() + "','";
			sql += complaint.getQtdeInternacoes() + "',";
			sql += complaint.getQtdeObitos() + ",";
			sql += "'" + complaint.getLocalAtendimento() + "',";
			sql += "'" + complaint.getRefeicaoSuspeita() + "','";

			if (complaint.getEnderecoDoente() != null) {
				sql += complaint.getEnderecoDoente().getCode() + "')";
			} else {
				sql += "NULL')";
			}

			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			System.out.println(sql);
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		}
		finally
		{
			mp.releaseCommunicationChannel();
		}
	}

	
	
	private void insertDrug(IDrugComplaintDt complaint) throws RepositoryException {
		System.out.println("[ComplaintRepositoryRDB:insertDrug] ");
		String sqlSuspectProduct = null; 
		String sqlQueixaMedica = null;
		String sqlMedicalDevice = null;

		IDrugDataDt drugData = complaint.getDrugData();
		// need to create the query to insert drug data

		Statement stmt = (Statement) this.mp.getCommunicationChannel();
		try {

			// defining common fields and values
			sqlQueixaMedica = "insert into "+Constants.DB_NAME+".scbs_queixamedica (code,drugdata,suspectmedicaldevice,suspectproduct) values " +
					"("+complaint.getCodigo()+","+complaint.getCodigo()+","+complaint.getCodigo()+","+complaint.getCodigo()+");";

			//debug
			System.out.println("[insertDrug] sqlQueixaMedica=["+sqlQueixaMedica+"]");

			//insert values into scbs_queixamedica table 
			stmt.executeUpdate(sqlQueixaMedica);
			
			//if suspect medical device not null, insert data
			ISuspectMedicalDeviceDt suspectMedDev = complaint.getSuspectMedicalDevice();
			if (suspectMedDev != null){

				sqlMedicalDevice = "insert into "+Constants.DB_NAME+".scbs_medicaldevice (brandname,commonname,manufacturer,city,province," +
						"modelnumber,catalognumber,serial,lotnumber,expirationdate,operatordevice,implanteddate,explanteddate,reuseddevice,reuseddescription,code) values ('"+ 
						suspectMedDev.getBrandName()+"','"+suspectMedDev.getDeviceName()+"','"+suspectMedDev.getManufacturer()+"','"+suspectMedDev.getManufacturerCity()+"','"
						+suspectMedDev.getManufacturerState()+"',"+ suspectMedDev.getModel()+","+suspectMedDev.getCatalog()+","+suspectMedDev.getSerial()+","+
						suspectMedDev.getLot()+","+suspectMedDev.getExpirationDate()+",'"+suspectMedDev.getDeviceOperator()+"',"+suspectMedDev.getImplantedDate()+","+
						suspectMedDev.getExplantedDate()+",'"+suspectMedDev.isWasReused()+"','"+suspectMedDev.getExtraInfo()+"',"+complaint.getCodigo()+");";

				System.out.println("[insertDrug] sqlMedicalDevice=["+sqlMedicalDevice+"]");
				
				//insert values into scbs_medicaldevice table 
				stmt.executeUpdate(sqlMedicalDevice);
			}
			else{
				//if suspect product not null, insert data
				ISuspectProductDt suspectProduct = complaint.getSuspectProduct();
				if(suspectProduct != null){
					sqlSuspectProduct =  "insert into "+Constants.DB_NAME+".scbs_medicalproduct (productname,labelstrenght,manufacturer,dose,frequency,route,startdate,enddate,eventabated,expirationdate," +
							"eventreappeared,medicineid,code,diagnosis) values ('"+suspectProduct.getProductName()+"','"+suspectProduct.getLabelStrength()+"','"+suspectProduct.getManufacturer()+"','"+suspectProduct.getDose()+"','"
							+suspectProduct.getFrequency()+"','"+suspectProduct.getRoute()+"',"+suspectProduct.getStartUseDate()+","+suspectProduct.getEndUseDate()+",'"+suspectProduct.getEventAbated()+"',"
							+suspectProduct.getExpirationDate()+",'"+suspectProduct.getEventReappeared()+"','"+suspectProduct.getId()+"',"+complaint.getCodigo()+",'"+suspectProduct.getDiagnosis()+"')";
					
					System.out.println("[insertDrug] sqlSuspectProduct=["+sqlSuspectProduct+"]");
					//insert values into scbs_medicalproduct table 
					stmt.executeUpdate(sqlSuspectProduct);
				}
				else{
					System.out.println("[ComplaintRepositoryRDB.insertDrug(..)] Both suspect medical device data and suspect product data are null");
				}
			}


		
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

	
	
	
	
	private void insertAnimal(IAnimalComplaintDt complaint) throws RepositoryException {
		String sql = null;
		try {
			// Inserir na tabela agora
			sql = "insert into scbs_queixaanimal (codigo,qtdeanimais,dataincomodo,animal,enderecolocalocorrencia) values (";
			sql += "'" + complaint.getCodigo() + "','";
			sql += complaint.getAnimalQuantity() + "',";
			sql += "'" + complaint.getInconvenienceDate() + "',";
			sql += "'" + complaint.getAnimal() + "','";

			if (complaint.getOccurenceLocalAddress() != null) {
				sql += complaint.getOccurenceLocalAddress().getCode() + "')";
			} else {
				sql += "NULL')";
			}

			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			System.out.println(sql);
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		}
		finally
		{
			mp.releaseCommunicationChannel();
		}
	}

	private void insertSpecial(ISpecialComplaintDt complaint) throws RepositoryException {
		String sql = null;
		try {
			// Inserir na tabela agora
			sql = "insert into scbs_queixadiversa (codigo,idade,ocupacao,instrucao,enderecoocorrencia) values (";
			sql += "'" + complaint.getCodigo() + "','";
			sql += complaint.getIdade() + "',";
			sql += "'" + complaint.getOcupacao() + "',";
			sql += "'" + complaint.getInstrucao() + "','";

			if (complaint.getEnderecoOcorrencia() != null) {
				sql += complaint.getEnderecoOcorrencia().getCode() + "')";
			} else {
				sql += "NULL')";
			}
			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (SQLException e) {
			System.out.println(sql);
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		}
		finally
		{
			mp.releaseCommunicationChannel();
		}
	}


	void remove(int codigo) throws RepositoryException, ObjectNotFoundException {
	}

	IComplaintDt search(int code) throws RepositoryException, ObjectNotFoundException, InvalidDateException {
		// Query montada para obtencao do tipo de queixa referente ao
		// codigo informado
		String sql = "SELECT tipoqueixa FROM scbs_queixa WHERE codigo = '" + code + "'";
		System.out.println("[ComplaintRepositoryRDB:search()] sql="+sql);
		int tipoQueixa;
		IComplaintDt q = null;

		try {
			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				System.out.println("[ComplaintRepositoryRDB:search] achou!");
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
		}
		finally
		{
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
		case Complaint.QUEIXA_DROGA:
			q = accessDrug(code);
			break;	
		default:
			throw new IllegalArgumentException();
		}
		return q;
	}

	IteratorDsk getComplaintList() throws ObjectNotFoundException, RepositoryException, InvalidDateException {
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
				String queixaId = rs.getString("tipoqueixa");

				System.out.println("[ComplaintRepositoryRDB:getComplaintList()] queixaId="+queixaId);

				int tipoQueixa = (new Integer(queixaId)).intValue();

				System.out.println("[ComplaintRepositoryRDB:getComplaintList()] tipoQueixa="+tipoQueixa);

				int code = (new Integer(rs.getString("codigo"))).intValue();

				System.out.println("[ComplaintRepositoryRDB:getComplaintList()] code="+code);

				switch (tipoQueixa) {
				case Complaint.QUEIXA_DIVERSA:
					complaint = accessSpecial(code);
					break;

				case Complaint.QUEIXA_ALIMENTAR:
					complaint = accessFood(code);
					break;

				case Complaint.QUEIXA_ANIMAL:
					complaint = accessAnimal(code);
					break;
				case Complaint.QUEIXA_DROGA:
					complaint = accessDrug(code);
					break;	
				default:
					System.err.println("The type of complaint is undefined");
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
		}
		finally
		{
			mp.releaseCommunicationChannel();
		}



		return this.distributionMgt.createIterator(cList);
	}	


}
