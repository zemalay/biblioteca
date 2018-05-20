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

	public FuncionarioDAOImpl() {
		this.connection = new Conexao().getConexao();
	}

	/**
	 * @see {@link DAO#getById(int)}
	 */
	@Override
	public Funcionario getById(int id) {
		logger.info("Executa o metodo 'get' do funcionario : " + id);
		

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
			e.printStackTrace();
		}
		logger.info("O funcionario foi selecionado: " + funcionario);
		return funcionario;
	}

	/**
	 * @see {@link DAO#getLista()}
	 */
	@Override
	public List<Funcionario> getLista() {
		logger.info("Executa o metodo 'getLista' do funcionario");
		

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
			e.printStackTrace();
		}
		logger.info("Pegar os funcionarios: " + listaFuncionario.toString());
		return listaFuncionario;
	}

	/**
	 * @see {@link DAO#inserir(Object)}
	 */
	@Override
	public int inserir(Funcionario obj) {
		logger.info("Executa o metodo 'inserir' do funcionario : " + obj);
		int id = FuncionarioDAOImpl.ID_FAKE;
		if (obj != null) {
			
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
					obj.setId(id);
				}
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		logger.info("O funcionario foi inserido: " + obj);
		return id;
	}

	/**
	 * @see {@link DAO#remover(Object)}
	 */
	@Override
	public void remover(Funcionario obj) {
		logger.info("Executa o metodo 'remover' funcionario : " + obj);
		if (obj != null) {
			
			String sql = "DELETE FROM funcionario WHERE funcionario.id = ?";

			try {
				statement = connection.prepareStatement(sql);
				statement.setInt(1, obj.getId());
				statement.execute();

				statement.close();
				logger.info("O funcionario foi removido" + obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see {@link DAO#atualizar(Object)}
	 */
	@Override
	public void atualizar(Funcionario obj) {
		logger.info("Executa o metodo 'atualizar' do funcionario : " + obj);
		if (obj != null) {
			
			String sql = "UPDATE funcionario SET nome = ?, tipo_funcionario = ? , cpf = ?, rg = ?, naturalidade = ?, endereco = ?, telefone = ?, email = ?, usuario = ?, senha = ? WHERE funcionario.id = ?";

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
				logger.info("O funcionario foi atualizado" + obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see {@link DAO#isExiste(Object)}
	 */
	@Override
	public boolean isExiste(Funcionario obj) {
		logger.info("Executar metodo 'isExiste' do funcionario: " + obj);
		if (obj != null) {
			
			String sql = "SELECT * FROM funcionario WHERE cpf = ?";

			try {
				statement = (PreparedStatement) connection.prepareStatement(sql);
				statement.setString(1, obj.getCpf());
				resultSet = statement.executeQuery();

				if (resultSet.next()) {
					statement.close();
					logger.info("Esse funcionario ja existe no banco: " + obj);
					return true;
				}
				statement.close();
				logger.info("Esse funcionario nao existe no banco: " + obj);
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Autenticar o funcionario de acordo com parametros dados
	 * 
	 * @param matricula
	 * @param senha
	 * @return Aluno
	 * @throws AutenticacaoException
	 * @
	 */
	public Funcionario login(String usuario, String senha) throws AutenticacaoException {
		logger.info("Executar metodo 'login' do funcionario: " + usuario + " : " + senha);

		
		Funcionario funcionario = null;
		String sql = "SELECT id, usuario, senha, tipo_funcionario FROM funcionario WHERE usuario = ?";

		try {
			statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setString(1, usuario);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getString(3).equals(senha)) {
					funcionario = new Funcionario();
					funcionario.setId(resultSet.getInt(1));
					funcionario.setUsuario(resultSet.getString(2));
					funcionario.setSenha(resultSet.getString(3));
					funcionario.setTipoFunc(resultSet.getString(4));
				} else {
					throw new AutenticacaoException("Usuario ou Senha Invalida");
				}
			} else {
				throw new AutenticacaoException("Usuario ou Senha Invalida");
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("O funcionario foi autenticado: " + funcionario);
		return funcionario;
	}
}
