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
import edu.uepb.web.biblioteca.model.Emprestimo;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.model.Item;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class EmprestimoDAOImpl implements DAO<Emprestimo> {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private Funcionario funcionario;
	private Aluno aluno;
	private Item item;
	private FuncionarioDAOImpl funcionarioDAOImpl;
	private AlunoDAOImpl alunoDAOImpl;
	private ItemDAOImpl itemDAOImpl;

	private static Logger logger = Logger.getLogger(EmprestimoDAOImpl.class);

	/**
	 * @throws DAOException
	 * @see {@link DAO#get(int)}
	 */
	@Override
	public Emprestimo get(int id) throws DAOException {
		logger.info("Executa o metodo 'get' do emprestimo : " + id);

		connection = new Conexao().getConexao();
		String sql = "SELECT	 * FROM emprestimo WHERE emprestimo.id = ?";

		Emprestimo emprestimo = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				emprestimo = new Emprestimo();
				funcionarioDAOImpl = new FuncionarioDAOImpl();
				alunoDAOImpl = new AlunoDAOImpl();
				itemDAOImpl = new ItemDAOImpl();

				emprestimo.setId(resultSet.getInt(1));
				emprestimo.setDataCadastrado(resultSet.getString(5));
				emprestimo.setDataDevolucao(resultSet.getString(6));
				emprestimo.setRenovacao(resultSet.getInt(7));
				emprestimo.setEntregou(resultSet.getBoolean(8));

				funcionario = funcionarioDAOImpl.get(resultSet.getInt(2));
				aluno = alunoDAOImpl.get(resultSet.getInt(3));
				item = itemDAOImpl.get(resultSet.getInt(4));

				emprestimo.setFuncionario(funcionario);
				emprestimo.setAluno(aluno);
				emprestimo.setItem(item);
			}
			statement.close();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		logger.info("O emprestimo foi selecionado: " + emprestimo);
		return emprestimo;
	}

	/**
	 * @throws DAOException
	 * @see {@link DAO#getLista()}
	 */
	@Override
	public List<Emprestimo> getLista() throws DAOException {
		logger.info("Executa o metodo 'getLista' do emprestimo");

		connection = new Conexao().getConexao();
		String sql = "SELECT	 * FROM emprestimo";

		List<Emprestimo> listaEmprestimo = new ArrayList<>();

		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Emprestimo emprestimo = new Emprestimo();
				funcionarioDAOImpl = new FuncionarioDAOImpl();
				alunoDAOImpl = new AlunoDAOImpl();
				itemDAOImpl = new ItemDAOImpl();

				emprestimo.setId(resultSet.getInt(1));
				emprestimo.setDataCadastrado(resultSet.getString(5));
				emprestimo.setDataDevolucao(resultSet.getString(6));
				emprestimo.setRenovacao(resultSet.getInt(7));
				emprestimo.setEntregou(resultSet.getBoolean(8));

				funcionario = funcionarioDAOImpl.get(resultSet.getInt(2));
				aluno = alunoDAOImpl.get(resultSet.getInt(3));
				item = itemDAOImpl.get(resultSet.getInt(4));

				emprestimo.setFuncionario(funcionario);
				emprestimo.setAluno(aluno);
				emprestimo.setItem(item);

				listaEmprestimo.add(emprestimo);
			}
			statement.close();

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		logger.info("Pegar os emprestimos: " + listaEmprestimo.toString());
		return listaEmprestimo;
	}

	/**
	 * @throws DAOException
	 * @see {@link DAO#inserir(Object)}
	 */
	@Override
	public int inserir(Emprestimo obj) throws DAOException {
		logger.info("Executa o metodo 'inserir' do emprestimo: " + obj);
		int id = -1;
		if (obj != null) {
			connection = new Conexao().getConexao();
			String sql = "INSERT INTO emprestimo (emprestimo.funcionario_id, emprestimo.aluno_id, emprestimo.item_id, emprestimo.data_cadastrado, emprestimo.data_devolucao) VALUES (?,?,?,?,?)";

			try {
				statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, obj.getFuncionario().getId());
				statement.setInt(2, obj.getAluno().getId());
				statement.setInt(3, obj.getItem().getId());
				statement.setString(4, obj.getDataCadastrado());
				statement.setString(5, obj.getDataDevolucao());
				statement.execute();
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					id = resultSet.getInt(1);
					obj.setId(id);
				}
				statement.close();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
		logger.info("O emprestimo foi inserido: " + obj);
		return id;
	}

	@Override
	public void remover(Emprestimo obj) throws DAOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @throws DAOException
	 * @see {@link DAO#atualizar(Object)}
	 */
	@Override
	public void atualizar(Emprestimo obj) throws DAOException {
		logger.info("Executa o metodo 'atualizar' Emprestimo:" + obj);
		if (obj != null) {
			connection = new Conexao().getConexao();
			String sql = "UPDATE emprestimo SET data_cadastrado = ?, data_devolucao = ? , renovacao = ?, entregou = ? WHERE id = ?";

			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, obj.getDataCadastrado());
				statement.setString(2, obj.getDataDevolucao());
				statement.setInt(3, obj.getRenovacao());
				statement.setBoolean(4, obj.isEntregou());
				statement.setInt(5, obj.getId());

				statement.execute();
				statement.close();
				logger.info("O emprestimo foi atualizado: " + obj);
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
	}

	@Override
	public boolean isExiste(Emprestimo obj) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

}
