package edu.uepb.web.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Divida;
import edu.uepb.web.biblioteca.model.Emprestimo;

/**
 * A classe para acessar os dados no banco associando ao objeto {@link Divida}
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 */
public class DividaDAOImpl implements DAO<Divida> {
	private static Logger logger = Logger.getLogger(DividaDAOImpl.class);
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private static final int ID_FAKE = -1;
	private Aluno aluno;
	private Emprestimo emprestimo;
	private AlunoDAOImpl alunoDAO;
	private EmprestimoDAOImpl emprestimoDAO;

	public DividaDAOImpl() {
		this.connection = new Conexao().getConexao();
	}

	/**
	 * @see DAO#getById(int)
	 */
	@Override
	public Divida getById(int id) {
		logger.info("Executa o metodo 'get' da divida : " + id);

		String sql = "SELECT * FROM divida WHERE id = ?";
		Divida divida = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				divida = new Divida();
				alunoDAO = new AlunoDAOImpl();
				emprestimoDAO = new EmprestimoDAOImpl();

				divida.setId(resultSet.getInt(1));
				aluno = alunoDAO.getById(resultSet.getInt(2));
				emprestimo = emprestimoDAO.getById(resultSet.getInt(3));

				divida.setAluno(aluno);
				divida.setEmprestimo(emprestimo);
				divida.setSaldo(resultSet.getFloat(4));
				divida.setPago(resultSet.getBoolean(5));

			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("A divida foi selecionada: " + divida);
		return divida;
	}

	/**
	 * @see DAO#getLista()
	 */
	@Override
	public List<Divida> getLista() {
		logger.info("Executa o metodo 'getLista' da divida");

		String sql = "SELECT * FROM divida";
		List<Divida> listaDivida = new ArrayList<>();
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Divida divida = new Divida();
				divida = new Divida();
				alunoDAO = new AlunoDAOImpl();
				emprestimoDAO = new EmprestimoDAOImpl();

				divida.setId(resultSet.getInt(1));
				aluno = alunoDAO.getById(resultSet.getInt(2));
				emprestimo = emprestimoDAO.getById(resultSet.getInt(3));

				divida.setAluno(aluno);
				divida.setEmprestimo(emprestimo);
				divida.setSaldo(resultSet.getFloat(4));
				divida.setPago(resultSet.getBoolean(5));

				listaDivida.add(divida);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("Pegar as dividas: " + listaDivida.toString());
		return listaDivida;
	}

	/**
	 * @see DAO#inserir(Object)
	 */
	@Override
	public int inserir(Divida obj) {
		logger.info("Executa o metodo 'inserir' da divida" + obj);
		int id = ID_FAKE;
		if (obj != null) {
			String sql = "INSERT INTO divida (aluno_id, emprestimo_id, saldo, pago) VALUES (?,?,?,?)";
			try {
				statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				statement.setInt(1, obj.getAluno().getId());
				statement.setInt(2, obj.getEmprestimo().getId());
				statement.setFloat(3, obj.getSaldo());
				statement.setBoolean(4, obj.isPago());
				statement.execute();
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					id = resultSet.getInt(1);
					obj.setId(id);
					logger.info("A divida foi inserida: " + obj);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	/**
	 * Remover a divida de acordo com id do aluno Nao com id da divida
	 * 
	 * @see DAO#remover(Object)
	 */
	@Override
	public void remover(Divida obj) {
		logger.info("Executa o metodo 'remover' divida : " + obj);
		if (obj != null) {
			String sql = "DELETE FROM divida WHERE aluno_id = ?";

			try {
				statement = connection.prepareStatement(sql);
				statement.setInt(1, obj.getAluno().getId());
				statement.execute();
				logger.info("A divida foi removida" + obj);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * @see DAO#atualizar(Object)
	 */
	@Override
	public void atualizar(Divida obj) {
		logger.info("Executa o metodo 'atualizar' da divida : " + obj);
		if (obj != null) {
			String sql = "UPDATE divida SET aluno_id = ?, emprestimo_id, saldo = ?, pago = ? WHERE divida.id = ?";
			try {
				statement = connection.prepareStatement(sql);
				statement.setInt(1, obj.getAluno().getId());
				statement.setInt(2, obj.getEmprestimo().getId());
				statement.setFloat(3, obj.getSaldo());
				statement.setBoolean(4, obj.isPago());
				statement.setInt(5, obj.getId());
				statement.execute();
				statement.close();
				logger.info("A divida foi atualizada" + obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public boolean dividaByAlunoId(int idAluno) {
		logger.info("Executar metodo 'dividaByAlunoId' da divida: " + idAluno);
		if (idAluno > 0) {
			String sql = "SELECT * FROM divida WHERE aluno_id = ?";
			try {
				statement = (PreparedStatement) connection.prepareStatement(sql);
				statement.setInt(1, idAluno);
				resultSet = statement.executeQuery();

				while (resultSet.next()) {
					if (resultSet.getBoolean(5)) {
						statement.close();
						logger.info("O aluno tem divida registrado");
						return true;
					}
				}
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean isExiste(Divida obj) {
		// TODO Auto-generated method stub
		return false;
	}

}