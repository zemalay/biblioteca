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
import edu.uepb.web.biblioteca.model.Aluno;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class AlunoDAO implements DAO<Aluno> {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private static final int ID_FAKE = -1;
	private CursoDAO cursoDAO;
	private static Logger logger = Logger.getLogger(AlunoDAO.class);

	/**
	 * @throws DAOException
	 * @see edu.uepb.web.biblioteca.dao.DAO#get(int)
	 */
	@Override
	public Aluno get(int id) throws DAOException {
		logger.info("Executa o metodo 'get' com param id : " + id);

		connection = new Conexao().getConexao();

		String sql = "SELECT * FROM aluno WHERE aluno.idaluno = ?";
		Aluno aluno = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				aluno = new Aluno();
				cursoDAO = new CursoDAO();
				aluno.setMatricula(resultSet.getString(3));
				aluno.setCurso(cursoDAO.get(resultSet.getInt(2)));
				aluno.setRg(resultSet.getString(4));
				aluno.setCpf(resultSet.getString(5));
				aluno.setNome(resultSet.getString(6));
				aluno.setNomeMae(resultSet.getString(7));
				aluno.setNaturalidade(resultSet.getString(8));
				aluno.setEndereco(resultSet.getString(9));
				aluno.setTelefone(resultSet.getString(10));
				aluno.setAno(resultSet.getString(11));
				aluno.setPeriodoIngresso(resultSet.getString(12));
				aluno.setSenha(resultSet.getString(13));

				statement.close();
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return aluno;
	}

	/**
	 * @throws DAOException
	 * @see edu.uepb.web.biblioteca.dao.DAO#getLista()
	 */
	@Override
	public List<Aluno> getLista() throws DAOException {
		logger.info("Executa o metodo 'getLista'");
		connection = new Conexao().getConexao();
		List<Aluno> listaAluno = new ArrayList<Aluno>();
		Aluno aluno = null;
		String sql = "SELECT * FROM aluno";

		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				aluno = new Aluno();
				cursoDAO = new CursoDAO();
				aluno.setMatricula(resultSet.getString(3));
				aluno.setCurso(cursoDAO.get(resultSet.getInt(2)));
				aluno.setRg(resultSet.getString(4));
				aluno.setCpf(resultSet.getString(5));
				aluno.setNome(resultSet.getString(6));
				aluno.setNomeMae(resultSet.getString(7));
				aluno.setNaturalidade(resultSet.getString(8));
				aluno.setEndereco(resultSet.getString(9));
				aluno.setTelefone(resultSet.getString(10));
				aluno.setAno(resultSet.getString(11));
				aluno.setPeriodoIngresso(resultSet.getString(12));
				aluno.setSenha(resultSet.getString(13));

				listaAluno.add(aluno);
			}
			statement.close();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

		return listaAluno;
	}

	/**
	 * @throws DAOException
	 * @see edu.uepb.web.biblioteca.dao.DAO#inserir(Object)
	 */
	@Override
	public int inserir(Aluno obj) throws DAOException {
		logger.info("Executa o metodo 'inserir' com param objeto : " + obj);
		int id = AlunoDAO.ID_FAKE;

		if (obj != null) {
			connection = new Conexao().getConexao();
			String sql = "INSERT INTO aluno "
					+ "(curso_idcurso, matricula , rg, cpf, nome, mae,  naturalidade, endereco, telefone, ano, periodo, senha) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, obj.getCurso().getId());
				statement.setString(2, obj.getMatricula());
				statement.setString(3, obj.getRg());
				statement.setString(4, obj.getCpf());
				statement.setString(5, obj.getNome());
				statement.setString(6, obj.getNomeMae());
				statement.setString(7, obj.getNaturalidade());
				statement.setString(8, obj.getEndereco());
				statement.setString(9, obj.getTelefone());
				statement.setString(10, obj.getAno());
				statement.setString(11, obj.getPeriodoIngresso());
				statement.setString(12, obj.getSenha());
				statement.execute();
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					id = resultSet.getInt(1);
				}
				statement.close();
			} catch (SQLException e) {
				logger.error("Erro de inserir: " + e.getMessage());
				throw new DAOException(e.getMessage());
			}
		}
		return id;
	}

	/**
	 * @throws DAOException
	 * @see edu.uepb.web.biblioteca.dao.DAO#remover(Object)
	 */
	@Override
	public void remover(Aluno obj) throws DAOException {
		logger.info("Executa o metodo 'remover' com param objeto : " + obj);
		if (obj != null) {
			connection = new Conexao().getConexao();
			String sql = "DELETE FROM aluno WHERE aluno.idaluno = ?";

			try {
				statement = connection.prepareStatement(sql);
				statement.setInt(1, obj.getId());
				statement.execute();

				statement.close();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}

	}

	/**
	 * @throws DAOException
	 * @see edu.uepb.web.biblioteca.dao.DAO#atualizar(Object)
	 */
	@Override
	public void atualizar(Aluno obj) throws DAOException {
		logger.info("Executa o metodo 'atualizar' com param objeto : " + obj);
		if (obj != null) {
			connection = new Conexao().getConexao();
			String sql = "UPDATE aluno SET "
					+ "matricula = ?, curso_idcurso = ?, rg = ?, cpf = ?, nome = ?, mae = ?, naturalidade = ?, "
					+ "endereco = ?, telefone = ?, ano = ?, periodo = ?, senha = ?";

			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, obj.getMatricula());
				statement.setInt(2, obj.getCurso().getId());
				statement.setString(3, obj.getRg());
				statement.setString(4, obj.getCpf());
				statement.setString(5, obj.getNome());
				statement.setString(6, obj.getNomeMae());
				statement.setString(7, obj.getNaturalidade());
				statement.setString(8, obj.getEndereco());
				statement.setString(9, obj.getTelefone());
				statement.setString(10, obj.getAno());
				statement.setString(11, obj.getPeriodoIngresso());
				statement.setString(12, obj.getSenha());
				
				statement.execute();

				statement.close();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}

	}

	public int getUltimoId() throws DAOException {
		logger.info("Executa o metodo 'getUltimoId' para pegar o ultimo id do aluno");

		connection = new Conexao().getConexao();

		String sql = "SELECT max(aluno.idaluno) FROM aluno";

		int ultimoId = AlunoDAO.ID_FAKE;

		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				ultimoId = resultSet.getInt(1);
			}
			statement.close();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return ultimoId;
	}

	@Override
	public boolean isExiste(Aluno obj) throws DAOException {
		if (obj != null) {
			connection = new Conexao().getConexao();
			String sql = "SELECT * FROM aluno WHERE matricula = ?";

			try {
				statement = (PreparedStatement) connection.prepareStatement(sql);
				statement.setString(1, obj.getMatricula());
				resultSet = statement.executeQuery();

				if (resultSet.next()) {
					statement.close();
					return true;
				}
				statement.close();
				return false;
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
		return false;
	}

}
