package publichealthcomplaint.persistence.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;

import publichealthcomplaint.datatypes.Constants;
import publichealthcomplaint.exceptionhandling.impl.ExceptionMessages;
import publichealthcomplaint.exceptionhandling.impl.PersistenceMechanismException;
import publichealthcomplaint.exceptionhandling.impl.TransactionException;
import publichealthcomplaint.persistence.spec.prov.IPersistenceMechanism;


class PersistenceMechanism implements IPersistenceMechanism {

	private static PersistenceMechanism singleton;

	private static int numConexoes = 2;

	private Connection conexoesCriadas[];

	private Connection conexoesLivres[];

	private HashMap conexoesAlocadas;

	private String url;

	private String user;

	private String password;

	private boolean indisponivel;

	private PersistenceMechanism(String url, String user, String password, String driverClass)
			throws PersistenceMechanismException {
		conexoesAlocadas = new HashMap();
		this.url = url;
		this.user = user;
		this.password = password;
		indisponivel = false;
		try {
			
		
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			System.err.println("Fail to connect user:"+this.user+" with password:"+this.password+" to:"+this.url+" due to:"+e.getLocalizedMessage());
			throw new PersistenceMechanismException("EXC_CLASSE_NAO_ENCONTRADA");
		}
	}

	public synchronized PreparedStatement getPreparedStatement(String sql)
			throws PersistenceMechanismException {
		try {
			return getCommunicationChannel(false).prepareStatement(sql);
		} catch (SQLException ex) {
			throw new PersistenceMechanismException("SQLException: " + ex.getMessage());
		}
	}

	public synchronized void rollbackTransaction() throws TransactionException {
		try {
			Connection con = (Connection) getCommunicationChannel(true);
			con.rollback();
			con.setAutoCommit(true);
			releaseCommunicationChannel(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TransactionException("EXC_CANCELAR_TRANSACAO");
		} finally {
			try {
				notifyAll();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public synchronized void connect() throws PersistenceMechanismException {

		if (conexoesCriadas == null) {
			try {
				conexoesLivres = new Connection[PersistenceMechanism.numConexoes];
				conexoesCriadas = new Connection[PersistenceMechanism.numConexoes];
				for (int i = 0; i < PersistenceMechanism.numConexoes; i++) {
					conexoesCriadas[i] = DriverManager.getConnection(url, user, password);
					conexoesLivres[i] = conexoesCriadas[i];
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new PersistenceMechanismException(ExceptionMessages.EXC_CONECTAR);
			}
		}
	}

	public synchronized void commitTransaction() throws TransactionException {
		try {
			Connection con = (Connection) getCommunicationChannel(true);
			con.commit();
			con.setAutoCommit(true);
			releaseCommunicationChannel(true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TransactionException("EXC_CONFIRMAR_TRANSACAO");
		} finally {
			notifyAll();
		}
	}

	public synchronized void disconnect() throws PersistenceMechanismException {
		try {
			if (conexoesCriadas != null) {
				int fechadas = 0;
				for (int i = 0; i < PersistenceMechanism.numConexoes; i++) {
					System.out.println("Conexao " + i + " " + conexoesLivres[i]);
				}

				System.out.println("Conexoes alocadas " + conexoesAlocadas.size());
				for (int i = 0; i < PersistenceMechanism.numConexoes; i++) {
					if (conexoesCriadas[i] != null) {
						conexoesCriadas[i].close();
						fechadas++;
					}
				}

				conexoesCriadas = null;
				System.out.println("Foram fechadas " + fechadas + " conexoes");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceMechanismException(ExceptionMessages.EXC_FALHA_DESCONECTAR);
		}
	}

	/**
	 * Retorna um java.sql.Statement
	 */
	public synchronized Object getCommunicationChannel() throws PersistenceMechanismException {
		try {
			return getCommunicationChannel(false).createStatement();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistenceMechanismException(
					ExceptionMessages.EXC_FALHA_GET_CANAL_COMUNICACAO);
		}
	}

	/**
	 * Retorna um java.sql.Connection
	 */
	private synchronized Connection getCommunicationChannel(boolean porTransacao)
			throws PersistenceMechanismException {
		Connection resposta = null;
		try {
			Thread currentThread = Thread.currentThread();
			int threadId = currentThread.hashCode();
			if (conexoesAlocadas.containsKey("" + threadId)) {
				resposta = (Connection) conexoesAlocadas.get("" + threadId);
			} else {
				boolean achou = false;
				do {
					if (achou) {
						break;
					}
					for (int i = 0; !achou && i < PersistenceMechanism.numConexoes; i++) {
						if (conexoesLivres[i] == null) {
							continue;
						}
						achou = true;
						resposta = conexoesLivres[i];
						conexoesLivres[i] = null;
						conexoesAlocadas.put("" + threadId, resposta);
						if (porTransacao) {
							conexoesAlocadas.put("T" + threadId, null);
						}
					}

					if (!achou) {
						indisponivel = true;
						wait();
					}
				} while (true);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistenceMechanismException(
					ExceptionMessages.EXC_FALHA_GET_CANAL_COMUNICACAO);
		}
		return resposta;
	}

	static synchronized PersistenceMechanism getInstance()
			 {
		if (singleton == null)
			try {
				System.out.println("[PersistenceMechanism:getInstance()] singleton is null");
				singleton = new PersistenceMechanism(Constants.DB_URL, Constants.DB_LOGIN,
						Constants.DB_PASS, Constants.DB_DRIVER);
				System.out.println("[PersistenceMechanism:getInstance()] singleton "+singleton);
			} catch (PersistenceMechanismException e) {
				System.err.println("[PersistenceMechanism:getInstance()] It was not possible to create a instance of PersistenceMechanism"+e.getLocalizedMessage());
				e.printStackTrace();
			}
		System.out.println("PersistenceMechanism:getInstance() = "+singleton);
		return singleton;
	}

	public synchronized void beginTransaction() throws TransactionException {
		try {
			while (indisponivel) {
				wait();
			}
			Connection con = (Connection) getCommunicationChannel(true);
			con.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new TransactionException("EXC_INICIAR_TRANSACAO");
		}
	}

	public synchronized void releaseCommunicationChannel() throws PersistenceMechanismException {
		releaseCommunicationChannel(false);
	}

	private synchronized void releaseCommunicationChannel(boolean porTransacao)
			throws PersistenceMechanismException {
		try {
			Thread currentThread = Thread.currentThread();
			int threadId = currentThread.hashCode();
			if (porTransacao || !porTransacao && !conexoesAlocadas.containsKey("T" + threadId)) {
				Object canal = conexoesAlocadas.get("" + threadId);
				boolean achou = false;
				for (int i = 0; !achou && i < PersistenceMechanism.numConexoes; i++) {
					if (conexoesLivres[i] != null) {
						continue;
					}
					achou = true;
					conexoesAlocadas.remove("" + threadId);
					if (conexoesAlocadas.containsKey("T" + threadId)) {
						conexoesAlocadas.remove("T" + threadId);
					}
					conexoesLivres[i] = (java.sql.Connection) canal;
				}

				indisponivel = false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new PersistenceMechanismException(
					ExceptionMessages.EXC_FALHA_LIBERAR_CANAL_COMUNICACAO);
		} finally {
			notifyAll();
		}
	}
}
