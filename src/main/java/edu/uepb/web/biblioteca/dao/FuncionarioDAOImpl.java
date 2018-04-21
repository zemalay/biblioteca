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
import edu.uepb.web.biblioteca.model.Funcionario;

/**
 * A classe para acessar os dados no banco associando ao objeto
 * {@link Funcionario}
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class FuncionarioDAOImpl implements DAO<Funcionario> {
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private static final int ID_FAKE = -1;
	private static Logger logger = Logger.getLogger(FuncionarioDAOImpl.class);

	/**
	 * @throws DAOException
	 * @see edu.uepb.web.biblioteca.model.DAO#get(int)
	 */
	@Override
	public Funcionario get(int id) throws DAOException {
		logger.info("Executa o metodo 'get' com param id : " + id);
		connection = new Conexao().getConexao();

		String sql = "SELECT * FROM funcionario WHERE funcionario.id = ?";

		Funcionario funcionario = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				funcionario = new Funcionario();
				funcionario.setId(resultSet.getInt(1));
				funcionario.setNome(resultSet.getString(2));
				funcionario.setTipoFunc(resultSet.getString(3));
				funcionario.setCpf(resultSet.getString(4));
				funcionario.setRg(resultSet.getString(5));
				funcionario.setNaturalidade(resultSet.getString(6));
				funcionario.setEndereco(resultSet.getString(7));
				funcionario.setTelefone(resultSet.getString(8));
				funcionario.setEmail(resultSet.getString(9));
				funcionario.setUsuario(resultSet.getString(10));
				funcionario.setSenha(resultSet.getString(11));

			}
			statement.close();
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
		connection = new Conexao().getConexao();

		String sql = "SELECT * FROM funcionario";

		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setId(resultSet.getInt(1));
				funcionario.setNome(resultSet.getString(2));
				funcionario.setTipoFunc(resultSet.getString(3));
				funcionario.setCpf(resultSet.getString(4));
				funcionario.setRg(resultSet.getString(5));
				funcionario.setNaturalidade(resultSet.getString(6));
				funcionario.setEndereco(resultSet.getString(7));
				funcionario.setTelefone(resultSet.getString(8));
				funcionario.setEmail(resultSet.getString(9));
				funcionario.setUsuario(resultSet.getString(10));
				funcionario.setSenha(resultSet.getString(11));
				listaFuncionario.add(funcionario);
			}

			statement.close();
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
		int id = FuncionarioDAOImpl.ID_FAKE;
		if (obj != null) {
			connection = new Conexao().getConexao();
			String sql = "INSERT INTO funcionario (nome, tipo_funcionario, cpf, rg, naturalidade, endereco, telefone, email, usuario, senha) VALUES (?,?,?,?,?,?,?,?,?,?)";

			try {
				statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, obj.getNome());
				statement.setString(2, obj.getTipoFunc().name());
				statement.setString(3, obj.getCpf());
				statement.setString(4, obj.getRg());
				statement.setString(5, obj.getNaturalidade());
				statement.setString(6, obj.getEndereco());
				statement.setString(7, obj.getTelefone());
				statement.setString(8, obj.getEmail());
				statement.setString(9, obj.getUsuario());
				statement.setString(10, obj.getSenha());

				statement.execute();
				resultSet = statement.getGeneratedKeys();
				if (resultSet.next()) {
					id = resultSet.getInt(1);
				}
				statement.close();
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
			connection = new Conexao().getConexao();
			String sql = "DELETE FROM funcionario WHERE funcionario.id = ?";

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
	 * @see edu.uepb.web.biblioteca.model.DAO#atualizar(Object)
	 */
	@Override
	public void atualizar(Funcionario obj) throws DAOException {
		logger.info("Executa o metodo 'atualizar' com param objeto : " + obj);
		if (obj != null) {
			connection = new Conexao().getConexao();
			String sql = "UPDATE funcionario SET "
					+ "nome = ?, tipo_funcionario = ? , cpf = ?, rg = ?, naturalidade = ?, endereco = ?, telefone = ?, email = ?, usuario = ?, senha = ?"
					+ "WHERE funcionario.id = ?";

			try {
				statement = connection.prepareStatement(sql);
				statement.setString(1, obj.getNome());
				statement.setString(2, obj.getTipoFunc().name());
				statement.setString(3, obj.getCpf());
				statement.setString(4, obj.getRg());
				statement.setString(5, obj.getNaturalidade());
				statement.setString(6, obj.getEndereco());
				statement.setString(7, obj.getTelefone());
				statement.setString(8, obj.getEmail());
				statement.setString(9, obj.getUsuario());
				statement.setString(10, obj.getSenha());

				statement.setInt(11, obj.getId());
				statement.execute();
				statement.close();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
	}

	@Override
	public boolean isExiste(Funcionario obj) throws DAOException {
		if (obj != null) {
			connection = new Conexao().getConexao();
			String sql = "SELECT * FROM funcionario WHERE cpf = ?";

			try {
				statement = (PreparedStatement) connection.prepareStatement(sql);
				statement.setString(1, obj.getCpf());
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
