package edu.uepb.web.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.model.Universidade;

/**
 * A classe para acessar os dados no banco associando ao objeto
 * {@link Universidade}
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class UniversidadeDAOImpl implements DAO<Universidade> {
	private static Logger logger = Logger.getLogger(Universidade.class);
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;

	public UniversidadeDAOImpl() {
		this.connection = new Conexao().getConexao();
	}

	/**
	 * @see DAO#getById(int)
	 */
	@Override
	public Universidade getById(int id) {
		logger.info("Executa o metodo 'getById' da universidade : " + id);

		String sql = "SELECT * FROM universidade WHERE id = ?";

		Universidade universidade = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				universidade = new Universidade();
				universidade.setId(resultSet.getInt(1));
				universidade.setNome(resultSet.getString(2));
				universidade.setEndereco(resultSet.getString(3));
				universidade.setPeriodo(resultSet.getString(4));
				universidade.setInicioPeriodo(resultSet.getString(5));
				universidade.setFimPeriodo(resultSet.getString(6));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("A universidade foi selecionada: " + universidade);
		return universidade;
	}

	@Override
	public List<Universidade> getLista() {
		return null;
	}

	/**
	 * @see DAO#inserir(Object)
	 */
	@Override
	public int inserir(Universidade obj) {
		logger.info("Executa o metodo 'inserir' da universidade: " + obj);
		int id = -1;
		if (obj != null) {

			String sql = "INSERT INTO universidade (universidade.nome, universidade.endereco, universidade.periodo, universidade.data_inicioPeriodo, universidade.data_fimPeriodo) VALUES (?,?,?,?,?)";

			try {
				statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, obj.getNome());
				statement.setString(2, obj.getEndereco());
				statement.setString(3, obj.getPeriodo());
				statement.setString(4, obj.getInicioPeriodo());
				statement.setString(5, obj.getFimPeriodo());
				
				statement.execute();
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					id = resultSet.getInt(1);
					obj.setId(id);
				}
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		logger.info("A universidade foi inserida: " + obj);
		return id;
	}

	@Override
	public void remover(Universidade obj) {
		// TODO Auto-generated method stub

	}

	/**
	 * @see DAO#atualizar(Object)
	 */
	@Override
	public void atualizar(Universidade obj) {
		logger.info("Executa o metodo 'atualizar' Universidade:" + obj);
		if (obj != null) {
			String sql = "UPDATE universidade SET nome = ?, endereco = ? , periodo = ?, data_inicioPeriodo = ?, data_fimPeriodo = ? WHERE id = ?";

			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, obj.getNome());
				statement.setString(2, obj.getEndereco());
				statement.setString(3, obj.getPeriodo());
				statement.setString(4, obj.getInicioPeriodo());
				statement.setString(5, obj.getFimPeriodo());
				statement.setInt(6, obj.getId());

				statement.execute();
				statement.close();
				logger.info("A universidade foi atualizada: " + obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean isExiste(Universidade obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Pegar o objeto universidade
	 */
	public Universidade get() {
		logger.info("Executa o metodo 'get' da universidade");

		String sql = "SELECT * FROM universidade";

		Universidade universidade = null;

		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				universidade = new Universidade();
				universidade.setId(resultSet.getInt(1));
				universidade.setNome(resultSet.getString(2));
				universidade.setEndereco(resultSet.getString(3));
				universidade.setPeriodo(resultSet.getString(4));
				universidade.setInicioPeriodo(resultSet.getString(5));
				universidade.setFimPeriodo(resultSet.getString(6));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("A universidade foi selecionada: " + universidade);
		return universidade;
	}

}
