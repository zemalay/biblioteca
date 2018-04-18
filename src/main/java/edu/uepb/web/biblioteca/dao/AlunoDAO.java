package edu.uepb.web.biblioteca.dao;

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
public class AlunoDAO extends DAO<Aluno> {

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

		super.connection = new Conexao().getConexao();

		String sql = "SELECT * FROM aluno WHERE aluno.idaluno = ?";
		Aluno aluno;
		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();

			aluno = new Aluno();
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

			super.closeConnections();
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
		super.connection = new Conexao().getConexao();
		List<Aluno> listaAluno = new ArrayList<Aluno>();
		Aluno aluno = null;
		String sql = "SELECT * FROM aluno";

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.resultSet = super.statement.executeQuery();

			while (resultSet.next()) {
				aluno = new Aluno();
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
			super.closeConnections();
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
			super.connection = new Conexao().getConexao();
			String sql = "INSERT INTO aluno "
					+ "(curso_idcurso, matricula , rg, cpf, nome, mae,  naturalidade, endereco, telefone, ano, periodo, senha) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
			try {
				super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				super.statement.setInt(1, obj.getCurso().getId());
				super.statement.setString(2, obj.getMatricula());
				super.statement.setString(3, obj.getRg());
				super.statement.setString(4, obj.getCpf());
				super.statement.setString(5, obj.getNome());
				super.statement.setString(6, obj.getNomeMae());
				super.statement.setString(7, obj.getNaturalidade());
				super.statement.setString(8, obj.getEndereco());
				super.statement.setString(9, obj.getTelefone());
				super.statement.setString(10, obj.getAno());
				super.statement.setString(11, obj.getPeriodoIngresso());
				super.statement.setString(12, obj.getSenha());
				super.statement.execute();
				super.resultSet = super.statement.getGeneratedKeys();
				if (resultSet.next()) {
					id = super.resultSet.getInt(1);
				}
				super.connection.close();
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
			super.connection = new Conexao().getConexao();
			String sql = "DELETE FROM aluno WHERE aluno.idaluno = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setInt(1, obj.getId());
				super.statement.execute();

				super.connection.close();
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
			super.connection = new Conexao().getConexao();
			String sql = "UPDATE aluno SET "
					+ "matricula = ?, curso_idcurso = ?, rg = ?, cpf = ?, nome = ?, mae = ?, naturalidae = ?, "
					+ "endereco = ?, telefone = ?, ano = ?, periodo = ?, senha = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getMatricula());
				super.statement.setInt(2, obj.getCurso().getId());
				super.statement.setString(3, obj.getRg());
				super.statement.setString(4, obj.getCpf());
				super.statement.setString(5, obj.getNome());
				super.statement.setString(6, obj.getNomeMae());
				super.statement.setString(7, obj.getNaturalidade());
				super.statement.setString(8, obj.getEndereco());
				super.statement.setString(9, obj.getTelefone());
				super.statement.setString(10, obj.getAno());
				super.statement.setString(11, obj.getPeriodoIngresso());
				super.statement.setString(12, obj.getSenha());

				super.connection.close();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}

	}

	public int getUltimoId() throws DAOException {
		logger.info("Executa o metodo 'getUltimoId' para pegar o ultimo id do aluno");

		super.connection = new Conexao().getConexao();

		String sql = "SELECT max(aluno.idaluno) FROM aluno";

		int ultimoId = AlunoDAO.ID_FAKE;

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.resultSet = super.statement.executeQuery();

			if (resultSet.next()) {
				ultimoId = resultSet.getInt(1);
			}
			super.closeConnections();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return ultimoId;
	}

}
