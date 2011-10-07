package publichealthcomplaint.supportservices.impl.queryinfomgr.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import publichealthcomplaint.distribution.spec.prov.IteratorDsk;
import publichealthcomplaint.exceptionhandling.impl.ExceptionMessages;
import publichealthcomplaint.exceptionhandling.impl.ObjectNotFoundException;
import publichealthcomplaint.exceptionhandling.impl.PersistenceSoftException;
import publichealthcomplaint.exceptionhandling.impl.RepositoryException;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req.IDistributionMgt;
import publichealthcomplaint.supportservices.impl.queryinfomgr.spec.req.IPersistenceMechanism;





class DiseaseTypeRepositoryRDB  {

	private IPersistenceMechanism mp;
	
	private IDistributionMgt dist;
	
	private ResultSet resultSet;

	DiseaseTypeRepositoryRDB() {
		
	}

	
	IteratorDsk getDiseaseTypeList() throws RepositoryException, ObjectNotFoundException {
		List listatd = new ArrayList();
		String sql = "SELECT * FROM scbs_tipodoenca";
		ResultSet rs = null;

		try {
			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			rs = stmt.executeQuery(sql);
			
			if (!rs.next()) {
				System.out.println("There is no disease type registered.");
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}

			do {
				DiseaseType td = partialSearch((new Integer(rs.getString("codigo"))).intValue());
				System.out.println("[DiseaseTypeRepositoryRDB:getDiseaseTypeList()]"+td.getName());
				listatd.add(td);
			} while (rs.next());

			rs.close();
			stmt.close();
		} catch (SQLException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		}
		
		return this.dist.createIterator(listatd);
	}

	

	/**
	 * Metodo para recuperar um tipo de doenca do banco de dados.
	 *
	 * @param codigo codigo do tipo de doenca a ser procurado
	 * @return um objeto tipo doenca montado a partir dos dados
	 *           do banco de dados
	 */
	private DiseaseType partialSearch(int codigo) throws ObjectNotFoundException {

		//$System.out.println("RepositorioTipoDoenca::procuraParcial()->begin");

		DiseaseType td = null;
		String nome, descricao, manifestacao, duracao;
		String sql = null;
		// Tentativa de recuperar os dados do bd usando o codigo 
		// informado
		try {
			sql = "select * from scbs_tipodoenca where " + "codigo = '" + codigo + "'";

			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);

			if (resultSet.next()) {
				codigo = (new Integer(resultSet.getString("codigo"))).intValue();
				nome = resultSet.getString("nome");
				descricao = resultSet.getString("descricao");
				manifestacao = resultSet.getString("manifestacao");
				duracao = resultSet.getString("duracao");

				//preparar para buscar em outra tabela os sintomas desta doenca
				//depois vai chamar deepAccess() de SymptomRepositoryArray
			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}
			resultSet.close();
			stmt.close();

			td = new DiseaseType();
			td.setName(nome);
			td.setDescription(descricao);
			td.setManifestation(manifestacao);
			td.setDuration(duracao);
			td.setCode(codigo);

		} catch (SQLException e) {
			System.out.println(sql);
			throw new PersistenceSoftException(e);
		} finally {
			mp.releaseCommunicationChannel();
		}

		return td;
	}

	/**
	 * Metodo para recuperar um tipo de doenca do banco de dados.
	 *
	 * @param codigo codigo do tipo de doenca a ser procurado
	 * @return um objeto tipo doenca montado a partir dos dados
	 *		   do banco de dados
	 */
	DiseaseType search(int code) throws RepositoryException, ObjectNotFoundException {

		DiseaseType td = null;
		String nome, descricao, manifestacao, duracao;
		List sintomas;
		String sql = null;
		// Tentativa de recuperar os dados do bd usando o codigo 
		// informado
		try {
			sql = "select * from scbs_tipodoenca where " + "codigo = '" + code + "'";

			Statement stmt = (Statement) this.mp.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);

			if (resultSet.next()) {
				code = (new Integer(resultSet.getString("codigo"))).intValue();
				nome = resultSet.getString("nome");
				descricao = resultSet.getString("descricao");
				manifestacao = resultSet.getString("manifestacao");
				duracao = resultSet.getString("duracao");

				//preparar para buscar em outra tabela os sintomas desta doenca
				//depois vai chamar deepAccess() de RepositorioSintomaArray
			} else {
				throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
			}
			resultSet.close();
			stmt.close();

			// Query para recuperar os sintomas relacionados com o tipo
			// de doenca encontrado a partir do codigo
			sql = "select * from scbs_tipodoencasintoma where codigotipodoenca = '" + code + "'";

			stmt = (Statement) this.mp.getCommunicationChannel();
			resultSet = stmt.executeQuery(sql);

			sintomas = new ArrayList();
			while (resultSet.next()) {
				int codeSymptom = (new Integer(resultSet.getString("codigosintoma"))).intValue();

				// Query para encontrar os dados de um sintoma usando o
				// codigo encontrado na tabela de relacionamentos.
				sql = "select * from scbs_sintoma where " + "codigo = '" + codeSymptom + "'";

				Statement stmt2 = (Statement) this.mp.getCommunicationChannel();
				ResultSet resultSet2 = stmt2.executeQuery(sql);
				Symptom sintoma;

				if (resultSet2.next()) {
					sintoma = new Symptom(resultSet2.getString("descricao"));
					sintoma.setCode((new Integer(resultSet2.getString("codigo"))).intValue());
				} else {
					// Caso esse trecho de codigo seja executado,
					// a tabela de relacinoamentos nao esta consistente
					// com a tabela de sintomas
					throw new ObjectNotFoundException(ExceptionMessages.EXC_FALHA_PROCURA);
				}
				resultSet2.close();
				stmt2.close();

				sintomas.add(sintoma);

			}
			resultSet.close();
			stmt.close();

			td = new DiseaseType(nome, descricao, manifestacao, duracao, sintomas);
			td.setCode(code);

		} catch (SQLException e) {
			throw new RepositoryException(ExceptionMessages.EXC_FALHA_BD);
		}
		
		return td;
	}
}