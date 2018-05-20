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
import edu.uepb.web.biblioteca.model.Item;
import edu.uepb.web.biblioteca.model.Reserva;

/**
 * A classe para acessar os dados no banco associando ao objeto {@link Reserva}
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class ReservaDAOImpl implements DAO<Reserva> {
	private static Logger logger = Logger.getLogger(Reserva.class);
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private AlunoDAOImpl alunoDAO;
	private ItemDAOImpl itemDAO;
	private Aluno aluno;
	private Item item;

	public ReservaDAOImpl() {
		this.connection = new Conexao().getConexao();
	}

	/**
	 * @see {@link DAO#getById(int)}
	 */
	@Override
	public Reserva getById(int id) {
		logger.info("Executa o metodo 'get' do reserva : " + id);

		String sql = "SELECT * FROM reserva WHERE reserva.id = ?";

		Reserva reserva = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				reserva = new Reserva();
				alunoDAO = new AlunoDAOImpl();
				itemDAO = new ItemDAOImpl();

				reserva.setId(resultSet.getInt(1));
				aluno = alunoDAO.getById(resultSet.getInt(2));
				item = itemDAO.getById(resultSet.getInt(3));

				reserva.setAluno(aluno);
				reserva.setItem(item);
				reserva.setDataReservado(resultSet.getString(4));
				reserva.setDataPegar(resultSet.getString(5));
				reserva.setEmail(resultSet.getBoolean(6));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("O reserva foi selecionado: " + reserva);
		return reserva;
	}

	/**
	 * @see {@link DAO#getLista()}
	 */
	@Override
	public List<Reserva> getLista() {
		logger.info("Executa o metodo 'getLista' do reserva");
		String sql = "SELECT	 * FROM reserva";

		List<Reserva> listaReserva = new ArrayList<>();

		try {
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Reserva reserva = new Reserva();
				alunoDAO = new AlunoDAOImpl();
				itemDAO = new ItemDAOImpl();
				reserva.setId(resultSet.getInt(1));
				aluno = alunoDAO.getById(resultSet.getInt(2));
				item = itemDAO.getById(resultSet.getInt(3));

				reserva.setAluno(aluno);
				reserva.setItem(item);
				reserva.setDataReservado(resultSet.getString(4));
				reserva.setDataPegar(resultSet.getString(5));
				reserva.setEmail(resultSet.getBoolean(6));

				listaReserva.add(reserva);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("Pegar as reservas: " + listaReserva.toString());
		return listaReserva;
	}

	/**
	 * @see {@link DAO#inserir(Object)}
	 */
	@Override
	public int inserir(Reserva obj) {
		logger.info("Executa o metodo 'inserir' do emprestimo: " + obj);
		int id = -1;
		if (obj != null) {

			String sql = "INSERT INTO reserva (reserva.aluno_idaluno, reserva.item_iditem, reserva.data_reservado, reserva.data_pegar, reserva.email) VALUES (?,?,?,?,?)";

			try {
				statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

				statement.setInt(1, obj.getAluno().getId());
				statement.setInt(2, obj.getItem().getId());
				statement.setString(3, obj.getDataReservado());
				statement.setString(4, obj.getDataPegar());
				statement.setBoolean(5, obj.isEmail());
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
		logger.info("A reserva foi inserido: " + obj);
		return id;
	}

	/**
	 * @see {@link DAO#remover(Object)}
	 */
	@Override
	public void remover(Reserva obj) {
		logger.info("Executa o metodo 'remover' reserva : " + obj);
		if (obj != null) {
			String sql = "DELETE FROM reserva WHERE reserva.id = ?";

			try {
				statement = connection.prepareStatement(sql);
				statement.setInt(1, obj.getId());
				statement.execute();

				statement.close();
				logger.info("A reserva foi removida" + obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Remover todos os registros da reserva de um Aluno
	 * 
	 * @param idAluno
	 */
	public void removerReservasAluno(int idAluno) {
		logger.info("Executa o metodo 'removerReservasAluno' reservaDAO : " + idAluno);
		String sql = "DELETE FROM reserva WHERE reserva.aluno_idaluno = ?";

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idAluno);
			statement.execute();
			statement.close();
			logger.info("A reserva foi removida" + idAluno);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void atualizar(Reserva obj) {
		// TODO Auto-generated method stub
	}

	/**
	 * Verificar se o item ainda nao reservado por alguem (aluno)
	 * 
	 * @see {@link DAO#isExiste(Object)}
	 */
	@Override
	public boolean isExiste(Reserva obj) {
		logger.info("Executar metodo 'isExiste' da reserva: " + obj);
		if (obj != null) {
			String sql = "SELECT * FROM reserva WHERE item_iditem = ?";

			try {
				statement = (PreparedStatement) connection.prepareStatement(sql);
				statement.setInt(1, obj.getItem().getId());
				resultSet = statement.executeQuery();

				if (resultSet.next()) {
					statement.close();
					logger.info("A reserva para esse item ja existe no banco: " + obj);
					return true;
				}
				statement.close();
				logger.info("Esta reserva nao existe no banco: " + obj);
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * Pegar objeto Reserva com parametro ID do Aluno e ID do Item
	 * 
	 * @param idAluno
	 * @param idItem
	 * @return Reserva
	 */
	public Reserva getByAlunoItemId(int idAluno, int idItem) {
		logger.info("Executa o metodo 'get' do reserva : " + idAluno + " " + idItem);

		String sql = "SELECT * FROM reserva WHERE aluno_idaluno = ? and item_iditem = ?";

		Reserva reserva = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idAluno);
			statement.setInt(2, idItem);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				reserva = new Reserva();
				alunoDAO = new AlunoDAOImpl();
				itemDAO = new ItemDAOImpl();

				reserva.setId(resultSet.getInt(1));
				aluno = alunoDAO.getById(resultSet.getInt(2));
				item = itemDAO.getById(resultSet.getInt(3));

				reserva.setAluno(aluno);
				reserva.setItem(item);
				reserva.setDataReservado(resultSet.getString(4));
				reserva.setDataPegar(resultSet.getString(5));
				reserva.setEmail(resultSet.getBoolean(6));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("O reserva foi selecionado: " + reserva);
		return reserva;
	}

	/**
	 * Pegar objeto Reserva com parametro ID do Item
	 * 
	 * @param idItem
	 * @return Reserva
	 */
	public Reserva getByItemId(int idItem) {
		logger.info("Executa o metodo 'get' do reserva : " + idItem);

		String sql = "SELECT * FROM reserva WHERE item_iditem = ?";

		Reserva reserva = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idItem);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				reserva = new Reserva();
				alunoDAO = new AlunoDAOImpl();
				itemDAO = new ItemDAOImpl();

				reserva.setId(resultSet.getInt(1));
				aluno = alunoDAO.getById(resultSet.getInt(2));
				item = itemDAO.getById(resultSet.getInt(3));

				reserva.setAluno(aluno);
				reserva.setItem(item);
				reserva.setDataReservado(resultSet.getString(4));
				reserva.setDataPegar(resultSet.getString(5));
				reserva.setEmail(resultSet.getBoolean(6));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("O reserva foi selecionado: " + reserva);
		return reserva;

	}

	/**
	 * Pegar objeto Reserva com parametro ID do Aluno
	 * 
	 * @param idAluno
	 * @return Reserva
	 */
	public Reserva getByAlunoId(int idAluno) {
		logger.info("Executa o metodo 'get' do reserva : " + idAluno);

		String sql = "SELECT * FROM reserva WHERE aluno_idaluno = ?";

		Reserva reserva = null;

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idAluno);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				reserva = new Reserva();
				alunoDAO = new AlunoDAOImpl();
				itemDAO = new ItemDAOImpl();

				reserva.setId(resultSet.getInt(1));
				aluno = alunoDAO.getById(resultSet.getInt(2));
				item = itemDAO.getById(resultSet.getInt(3));

				reserva.setAluno(aluno);
				reserva.setItem(item);
				reserva.setDataReservado(resultSet.getString(4));
				reserva.setDataPegar(resultSet.getString(5));
				reserva.setEmail(resultSet.getBoolean(6));
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("O reserva foi selecionado: " + reserva);
		return reserva;

	}

	/**
	 * Pegar todas as Reservas cadastrados do Aluno List<Reserva>
	 * 
	 * @return
	 */
	public List<Reserva> getReservasByAlunoId(int idAluno) {
		logger.info("Executa o metodo 'getReservasByAlunoId' do reserva " + idAluno);
		String sql = "SELECT	 * FROM reserva WHERE aluno_idaluno = ?";

		List<Reserva> listaReserva = new ArrayList<>();
		alunoDAO = new AlunoDAOImpl();
		itemDAO = new ItemDAOImpl();

		try {
			statement = connection.prepareStatement(sql);
			statement.setInt(1, idAluno);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				Reserva reserva = new Reserva();
				reserva.setId(resultSet.getInt(1));
				aluno = alunoDAO.getById(resultSet.getInt(2));
				item = itemDAO.getById(resultSet.getInt(3));

				reserva.setAluno(aluno);
				reserva.setItem(item);
				reserva.setDataReservado(resultSet.getString(4));
				reserva.setDataPegar(resultSet.getString(5));
				reserva.setEmail(resultSet.getBoolean(6));

				listaReserva.add(reserva);
			}
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		logger.info("Pegar as reservas: " + listaReserva.toString());
		return listaReserva;
	}

}
