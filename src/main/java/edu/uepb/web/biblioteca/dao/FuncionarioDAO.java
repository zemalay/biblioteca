package edu.uepb.web.biblioteca.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.model.Funcionario;

/**
 * A classe para acessar os dados no banco associando ao objeto
 * {@link Funcionario}
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class FuncionarioDAO extends DAO<Funcionario> {
	private static final int ID_FAKE = -1;
	private static Logger logger = Logger.getLogger(FuncionarioDAO.class);
	/**
	 * @throws DAOException
	 * @see edu.uepb.web.biblioteca.model.DAO#get(int)
	 */
	@Override
	public Funcionario get(int id) throws DAOException {
		logger.info("Executa o metodo 'get' com param id : " + id);
		super.connection = new Conexao().getConexao();

		String sql = "SELECT * FROM funcionario WHERE funcionario.id";

		Funcionario funcionario = null;

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();

			if (resultSet.next()) {
				funcionario = new Funcionario();
				funcionario.setId(resultSet.getInt(1));
				funcionario.setNome(resultSet.getString(2));
				funcionario.setTipoFunc(resultSet.getString(3));
			}
			super.closeConnections();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return funcionario;
	}

	/**
	 * @throws DAOException
	 * @see edu.uepb.web.biblioteca.model.DAO#getLista()
	 */
	@Override
	public List<Funcionario> getLista() throws DAOException {
		logger.info("Executa o metodo 'getLista'");
		super.connection = new Conexao().getConexao();

		String sql = "SELECT * FROM funcionario";

		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		try {
			super.statement = super.connection.prepareStatement(sql);
			super.resultSet = super.statement.executeQuery();

			while (resultSet.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setId(resultSet.getInt(1));
				funcionario.setNome(resultSet.getString(2));
				funcionario.setTipoFunc(resultSet.getString(3));

				listaFuncionario.add(funcionario);
			}

			super.closeConnections();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return listaFuncionario;
	}

	/**
	 * @throws DAOException
	 * @see edu.uepb.web.biblioteca.model.DAO#inserir(Object)
	 */
	@Override
	public int inserir(Funcionario obj) throws DAOException {
		logger.info("Executa o metodo 'inserir' com param objeto : " + obj);
		int id = FuncionarioDAO.ID_FAKE;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "INSERT INTO funcionario (nome, tipoFuncionario) VALUES (?,?)";

			try {
				super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				super.statement.setString(1, obj.getNome());
				super.statement.setString(2, obj.getTipoFunc().name());
				super.statement.execute();
				super.resultSet = super.statement.getGeneratedKeys();
				if (resultSet.next()) {
					id = super.resultSet.getInt(1);
				}
				super.closeConnections();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
		return id;
	}

	/**
	 * @throws DAOException
	 * @see edu.uepb.web.biblioteca.model.DAO#remover(Object)
	 */
	@Override
	public void remover(Funcionario obj) throws DAOException {
		logger.info("Executa o metodo 'remover' com param objeto : " + obj);
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "DELETE FROM funcionario WHERE funcionario.id = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setInt(1, obj.getId());
				super.statement.execute();

				super.closeConnections();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
	}

	/**
	 * @throws DAOException
	 * @see edu.uepb.web.biblioteca.model.DAO#atualizar(Object)
	 */
	@Override
	public void atualizar(Funcionario obj) throws DAOException {
		logger.info("Executa o metodo 'atualizar' com param objeto : " + obj);
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "UPDATE funcionario SET nome = ?, tipoFuncionario = ? WHERE fucnionario.id = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getNome());
				super.statement.setString(2, obj.getTipoFunc().name());
				super.statement.setInt(3, obj.getId());
				super.statement.execute();

				super.closeConnections();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
	}
}
