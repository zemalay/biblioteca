package edu.uepb.web.biblioteca.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.exception.ItemExistException;
import edu.uepb.web.biblioteca.model.Item;
import edu.uepb.web.biblioteca.model.TrabalhoConclusao;

/**
 * A classe para acessar os dados no banco associando ao objeto
 * {@link TrabalhoConclusao}
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 */
public class TrabalhoConclusaoDAO extends ItemDAO<TrabalhoConclusao> {
	private static Logger logger = Logger.getLogger(TrabalhoConclusaoDAO.class);

	/**
	 * @throws DAOException 
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#get(int)
	 */
	@Override
	public TrabalhoConclusao get(int id) throws DAOException {
		logger.info("Executa o metodo 'get' com param id : " + id);

		super.connection = new Conexao().getConexao();

		String sql = "SELECT * FROM trabalhoconclusao WHERE trabalhoconclusao.idtrabalho = ?";

		TrabalhoConclusao trabalhoConclusao = null;

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();

			if (resultSet.next()) {
				trabalhoConclusao = new TrabalhoConclusao();

				trabalhoConclusao.setId(resultSet.getInt(1));
				trabalhoConclusao.setTitulo(resultSet.getString(2));
				trabalhoConclusao.setTipo(resultSet.getString(3));
				trabalhoConclusao.setAnoDefesa(resultSet.getString(4));
				trabalhoConclusao.setLocal(resultSet.getString(5));
				trabalhoConclusao.setAutor(resultSet.getString(6));
				trabalhoConclusao.setOrientador(resultSet.getString(7));

			}
			super.closeConnections();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return trabalhoConclusao;
	}

	/**
	 * @throws DAOException 
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#getLista()
	 */
	@Override
	public List<Item> getLista() throws DAOException {
		logger.info("Executa o metodo 'getLista' ");
		super.connection = new Conexao().getConexao();
		List<Item> listaTrabalhos = new ArrayList<Item>();

		String sql = "SELECT trabalhoconclusao.idtrabalho, trabalhoconclusao.titulo, trabalhoconclusao.tipo, "
				+ "trabalhoconclusao.anoDefesa, trabalhoconclusao.local, trabalhoconclusao.autor, trabalhoconclusao.orientador"
				+ " FROM trabalhoconclusao";

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.resultSet = super.statement.executeQuery();

			while (resultSet.next()) {

				TrabalhoConclusao trabalhoConclusao = new TrabalhoConclusao();
				trabalhoConclusao.setId(resultSet.getInt(1));
				trabalhoConclusao.setTitulo(resultSet.getString(2));
				trabalhoConclusao.setTipo(resultSet.getString(3));
				trabalhoConclusao.setAnoDefesa(resultSet.getString(4));
				trabalhoConclusao.setLocal(resultSet.getString(5));
				trabalhoConclusao.setAutor(resultSet.getString(6));
				trabalhoConclusao.setOrientador(resultSet.getString(7));

				listaTrabalhos.add(trabalhoConclusao);

			}
			super.connection.close();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return listaTrabalhos;
	}

	/**
	 * @throws DAOException 
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#inserir(Item)
	 */
	@Override
	public int inserir(Item item) throws DAOException {
		logger.info("Executa o metodo 'inserir' com param objeto : " + item);

		TrabalhoConclusao obj = (TrabalhoConclusao) item;
		int id = -1;
		super.connection = new Conexao().getConexao();

		String sql = "INSERT INTO trabalhoconclusao (titulo, tipo, anoDefesa, local, autor, orientador) VALUES (?,?,?,?,?,?)";

		if (obj != null) {
			try {
				super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				super.statement.setString(1, obj.getTitulo());
				super.statement.setString(2, obj.getTipo().name());
				super.statement.setString(3, obj.getAnoDefesa());
				super.statement.setString(4, obj.getLocal());
				super.statement.setString(5, obj.getAutor());
				super.statement.setString(6, obj.getOrientador());
				super.statement.execute();
				super.resultSet = super.statement.getGeneratedKeys();

				if (resultSet.next()) {
					id = super.resultSet.getInt(1);
				}
				super.connection.close();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
		return id;
	}

	/**
	 * @throws DAOException 
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#remover(Item)
	 */
	@Override
	public void remover(Item item) throws DAOException {
		logger.info("Executa o metodo 'remover' com param objeto : " + item);

		TrabalhoConclusao obj = (TrabalhoConclusao) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "DELETE FROM trabalhoconclusao WHERE idtrabalho = ?";

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
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#atualizar(Item)
	 */
	@Override
	public void atualizar(Item item) throws DAOException {
		logger.info("Executa o metodo 'autalizar' com param objeto : " + item);

		TrabalhoConclusao obj = (TrabalhoConclusao) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "UPDATE trabalhoconclusao SET titulo = ?, tipo = ?, anoDefesa = ?, local = ?, autor = ?, orientador = ?"
					+ " WHERE idtrabalho = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getTitulo());
				super.statement.setString(2, obj.getTipo().name());
				super.statement.setString(3, obj.getAnoDefesa());
				super.statement.setString(4, obj.getLocal());
				super.statement.setString(5, obj.getAutor());
				super.statement.setString(6, obj.getOrientador());
				super.statement.setInt(7, obj.getId());

				super.statement.execute();
				super.closeConnections();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
	}

	/**
	 * @throws ItemExistException
	 * @throws DAOException 
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#isItemExiste(Item)
	 */
	@Override
	public boolean isItemExiste(Item item) throws ItemExistException, DAOException {
		logger.info("Executa o metodo 'isItemExiste' com param objeto : " + item);

		TrabalhoConclusao obj = (TrabalhoConclusao) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "SELECT trabalhoconclusao.titulo FROM trabalhoconclusao WHERE trabalhoconclusao.titulo = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getTitulo());
				super.resultSet = super.statement.executeQuery();

				if (resultSet.next()) {
					super.closeConnections();
					return true;
				}
				super.closeConnections();
				throw new ItemExistException("Este item ja existe no banco de dados");

			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}

		}
		return false;
	}

}
