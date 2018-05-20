package edu.uepb.web.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.model.Aluno;

/**
 * A classe para acessar os dados no banco associando ao objeto {@link Aluno}
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class AlunoDAOImpl implements DAO<Aluno> {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private static final int ID_FAKE = -1;
	private CursoDAOImpl cursoDAO;
	private static Logger logger = Logger.getLogger(AlunoDAOImpl.class);

	public AlunoDAOImpl() {
		this.connection = new Conexao().getConexao();
	}

	/**
	 * @ @see {@link DAO#getById(int)}
	 */
	@Override
	public Aluno getById(int id) {
		logger.info("Executa o metodo 'get' do aluno: " + id);

		String sql = "SELECT * FROM aluno WHERE aluno.id = ?";
		Aluno aluno = null;
		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				aluno = new Aluno();
				cursoDAO = new CursoDAOImpl();
				aluno.setId(resultSet.getInt(1));
				aluno.setMatricula(resultSet.getString(3));
				aluno.setCurso(cursoDAO.getById(resultSet.getInt(2)));
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
				aluno.setEmail(resultSet.getString(14));

				statement.close();
			}
		} catch (SQLException e) {
			logger.error("Erro selecao o dado no base de dados", e);
			e.printStackTrace();
		}
		logger.info("O aluno foi selecionado: " + aluno);
		return aluno;
	}

	/**
	 * @ @see {@link DAO#getLista()}
	 */
	@Override
	public List<Aluno> getLista() {
		logger.info("Executa o metodo 'getLista' do aluno");

		List<Aluno> listaAluno = new ArrayList<Aluno>();
		Aluno aluno = null;
		String sql = "SELECT * FROM aluno";

		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				aluno = new Aluno();
				cursoDAO = new CursoDAOImpl();
				aluno.setId(resultSet.getInt(1));
				aluno.setMatricula(resultSet.getString(3));
				aluno.setCurso(cursoDAO.getById(resultSet.getInt(2)));
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
				aluno.setEmail(resultSet.getString(14));

				listaAluno.add(aluno);
			}
			statement.close();
		} catch (SQLException e) {
			logger.error("Erro selecao no banco", e);
			e.printStackTrace();
		}
		logger.info("Pegar os aluno: " + listaAluno.toString());
		return listaAluno;
	}

	/**
	 * @ @see {@link DAO#inserir(Object)}
	 */
	@Override
	public int inserir(Aluno obj) {
		logger.info("Executa o metodo 'inserir' do aluno: " + obj);
		int id = AlunoDAOImpl.ID_FAKE;

		if (obj != null) {

			String sql = "INSERT INTO aluno (curso_id, matricula , rg, cpf, nome, mae,  naturalidade, endereco, telefone, ano, periodo, senha, email) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
				statement.setString(13, obj.getEmail());
				statement.execute();
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					id = resultSet.getInt(1);
					obj.setId(id);
				}
				statement.close();
			} catch (SQLException e) {
				logger.error("Erro insercao no banco", e);
				e.printStackTrace();
			}
		}
		logger.info("O aluno foi inserido: " + obj);
		return id;
	}

	/**
	 * @ @see {@link DAO#remover(Object)}
	 */
	@Override
	public void remover(Aluno obj) {
		logger.info("Executa o metodo 'remover' do aluno : " + obj);
		if (obj != null) {

			String sql = "DELETE FROM aluno WHERE aluno.id = ?";

			try {
				statement = connection.prepareStatement(sql);
				statement.setInt(1, obj.getId());
				statement.execute();

				statement.close();
				logger.info("O aluno foi removido" + obj);
			} catch (SQLException e) {
				logger.error("Erro remocao o dado no base de dados", e);
				e.printStackTrace();
			}
		}

	}

	/**
	 * @ @see {@link DAO#atualizar(Object)}
	 */
	@Override
	public void atualizar(Aluno obj) {
		logger.info("Executa o metodo 'atualizar' do aluno: " + obj);
		if (obj != null) {

			String sql = "UPDATE aluno SET matricula = ?, curso_id = ?, rg = ?, cpf = ?, nome = ?, mae = ?, naturalidade = ?, endereco = ?, telefone = ?, ano = ?, periodo = ?, senha = ?, email = ? WHERE id = ?";

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
				statement.setString(13, obj.getEmail());

				statement.setInt(14, obj.getId());

				statement.execute();

				statement.close();
				logger.info("O aluno foi atualizado: " + obj);
			} catch (SQLException e) {
				logger.error("Erro atualizacao no banco", e);
				e.printStackTrace();
			}
		}

	}

	/**
	 * Pegar o ultimo id do aluno cadastrado no base de dados Esse metodo eh
	 * especifico para criacao da matricula do aluno
	 * 
	 * @return int ultimo id cadastrado @
	 */
	public int getUltimoId() {
		logger.info("Executa o metodo 'getUltimoId' do aluno");

		String sql = "SELECT max(aluno.id) FROM aluno";

		int ultimoId = AlunoDAOImpl.ID_FAKE;

		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				ultimoId = resultSet.getInt(1);
			}
			statement.close();
		} catch (SQLException e) {
			logger.error("Erro selecao o dado no banco", e);
			e.printStackTrace();
		}
		logger.info("O ultmo id do aluno foi selecionado: " + ultimoId);
		return ultimoId;
	}

	/**
	 * @ @see {@link DAO#isExiste(Object)}
	 */
	@Override
	public boolean isExiste(Aluno obj) {
		logger.info("Executar metodo 'isExiste' do aluno: " + obj);
		if (obj != null) {

			String sql = "SELECT * FROM aluno WHERE matricula = ?";

			try {
				statement = (PreparedStatement) connection.prepareStatement(sql);
				statement.setString(1, obj.getMatricula());
				resultSet = statement.executeQuery();

				if (resultSet.next()) {
					statement.close();
					logger.info("Esse aluno ja existe no banco: " + obj);
					return true;
				}
				statement.close();
				logger.info("Esse aluno nao existe no banco: " + obj);
				return false;
			} catch (SQLException e) {
				logger.error("Erro selecao no banco", e);
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Autenticar o aluno de acordo com parametros dados
	 * 
	 * @param matricula
	 * @param senha
	 * @return Aluno
	 * @throws AutenticacaoException
	 * @
	 */
	public Aluno login(String matricula, String senha) throws AutenticacaoException {
		logger.info("Executar metodo 'login' do aluno: " + matricula + " : " + senha);

		Aluno aluno = null;
		String sql = "SELECT id, matricula, senha, nome FROM aluno WHERE matricula = ?";

		try {
			statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setString(1, matricula);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getString(3).equals(senha)) {
					aluno = new Aluno();
					aluno.setId(resultSet.getInt(1));
					aluno.setMatricula(resultSet.getString(2));
					aluno.setSenha(resultSet.getString(3));
					aluno.setNome(resultSet.getString(4));
				} else {
					throw new AutenticacaoException("Senha Invalida");
				}
			} else {
				throw new AutenticacaoException("Matricula Invalida");
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aluno;
	}

}
