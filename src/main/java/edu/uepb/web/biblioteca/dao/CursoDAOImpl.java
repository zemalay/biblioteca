package edu.uepb.web.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.model.Curso;

/**
 * A classe para acessar os dados no banco associando ao objeto {@link Curso}
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 */
public class CursoDAOImpl implements DAO<Curso> {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private static Logger logger = Logger.getLogger(CursoDAOImpl.class);

	/**
	 * @throws DAOException
	 * @see {@link DAO#get(int)}
	 */
	@Override
	public Curso get(int id) throws DAOException {
		logger.info("Executa o metodo 'get' com param id : " + id);

		connection = new Conexao().getConexao();

		String sql = "SELECT * FROM curso WHERE curso.idcurso = ?";

		Curso curso = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				curso = new Curso();
				curso.setId(resultSet.getInt(1));
				curso.setNome(resultSet.getString(2));
				curso.setNivel(resultSet.getString(3));
				curso.setArea(resultSet.getString(4));
			}
			statement.close();
		} catch (SQLException e) {
			logger.error("Erro selecao o dado no base de dados", e);
			throw new DAOException(e.getMessage());
		}
		return curso;
	}

	/**
	 * @throws DAOException
	 * @see {@link DAO#getLista()}
	 */
	@Override
	public List<Curso> getLista() throws DAOException {
		logger.info("Executa o metodo 'getLista' ");
		connection = new Conexao().getConexao();

		String sql = "SELECT * FROM curso";
		List<Curso> listaCurso = new ArrayList<Curso>();

		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Curso curso = new Curso();
				curso.setId(resultSet.getInt(1));
				curso.setNome(resultSet.getString(2));
				curso.setNivel(resultSet.getString(3));
				curso.setArea(resultSet.getString(4));

				listaCurso.add(curso);
			}
			statement.close();
		} catch (SQLException e) {
			logger.error("Erro selecao o dado no base de dados", e);
			throw new DAOException(e.getMessage());
		}
		return listaCurso;
	}

	/**
	 * @throws DAOException
	 * @see {@link DAO#inserir(Object)}
	 */
	@Override
	public int inserir(Curso obj) throws DAOException {
		logger.info("Executa o metodo 'inserir' com param objeto : " + obj);
		int id = -1;
		connection = new Conexao().getConexao();
		String sql = "INSERT INTO curso (nome, tipoNivel, area) VALUES (?,?,?)";
		if (obj != null) {
			try {
				statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, obj.getNome());
				statement.setString(2, obj.getNivel().name());
				statement.setString(3, obj.getArea());
				statement.execute();
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					id = resultSet.getInt(1);
				}
				statement.close();
			} catch (SQLException e) {
				logger.error("Erro insercao o dado no base de dados", e);
				throw new DAOException(e.getMessage());
			}
		}
		return id;
	}

	/**
	 * @throws DAOException
	 * @see {@link DAO#remover(Object)}
	 */
	@Override
	public void remover(Curso obj) throws DAOException {
		logger.info("Executa o metodo 'remover' com param objeto : " + obj);

		if (obj != null) {
			connection = new Conexao().getConexao();
			String sql = "DELETE FROM curso WHERE idcurso = ?";

			try {
				statement = connection.prepareStatement(sql);
				statement.setInt(1, obj.getId());
				statement.execute();

				statement.close();
			} catch (SQLException e) {
				logger.error("Erro remocao o dado no base de dados", e);
				throw new DAOException(e.getMessage());
			}
		}

	}

	/**
	 * @throws DAOException
	 * @see {@link DAO#atualizar(Object)}
	 */
	@Override
	public void atualizar(Curso obj) throws DAOException {
		logger.info("Executa o metodo 'atualizar' com param objeto : " + obj);
		if (obj != null) {
			connection = new Conexao().getConexao();
			String sql = "UPDATE curso SET nome = ?, tipoNivel = ? , area = ? WHERE curso.idcurso = ?";

			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, obj.getNome());
				statement.setString(2, obj.getNivel().name());
				statement.setString(3, obj.getArea());
				statement.setInt(4, obj.getId());

				statement.execute();

				statement.close();
			} catch (SQLException e) {
				logger.error("Erro atualizacao o dado no base de dados", e);
				throw new DAOException(e.getMessage());
			}
		}
	}

	/**
	 * @throws DAOException
	 * @see {@link DAO#isExiste(Object)}
	 */
	@Override
	public boolean isExiste(Curso obj) throws DAOException {
		if (obj != null) {
			connection = new Conexao().getConexao();
			String sql = "SELECT * FROM curso WHERE nome = ?";

			try {
				statement = (PreparedStatement) connection.prepareStatement(sql);
				statement.setString(1, obj.getNome());
				resultSet = statement.executeQuery();

				if (resultSet.next()) {
					statement.close();
					return true;
				}
				statement.close();
				return false;
			} catch (SQLException e) {
				logger.error("Erro selecao o dado no base de dados", e);
				throw new DAOException(e.getMessage());
			}
		}
		return false;
	}

}