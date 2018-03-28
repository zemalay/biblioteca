package edu.uepb.web.biblioteca.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * A classe para acessar os dados no banco associando ao objeto
 * {@link AnaisCongresso}
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 */
public class AnaisCongressoDAO extends ItemDAO<Item> {

	private AutorDAO autorDAO;
	private static Logger logger = Logger.getLogger(AnaisCongressoDAO.class);

	/**
	 * @see edu.uepb.web.biblioteca.model.ItemDAO#get(int)
	 */
	@Override
	public AnaisCongresso get(int id) {
		logger.info("Executa o metodo 'get' com param id : " + id);

		super.connection = new Conexao().getConexao();

		String sql = "SELECT anaiscongresso.idanais, anaiscongresso.titulo,"
				+ " anaiscongresso.tipo,anaiscongresso.congresso,"
				+ " anaiscongresso.anoPublicacao, anaiscongresso.local "
				+ "FROM anaiscongresso WHERE anaiscongresso.idanais = ?";

		AnaisCongresso anais = null;
		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();

			if (resultSet.next()) {
				autorDAO = new AutorDAO();
				anais = new AnaisCongresso();
				anais.setId(resultSet.getInt(1));
				anais.setTitulo(resultSet.getString(2));
				anais.setTipo(resultSet.getString(3));
				anais.setCongresso(resultSet.getString(4));
				anais.setAnoPublicacao(resultSet.getString(5));
				anais.setLocal(resultSet.getString(6));

				anais.setListaAutores(autorDAO.getLista(anais.getId()));
			}

			super.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return anais;
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.ItemDAO#getLista()
	 */
	@Override
	public List<Item> getLista() {
		logger.info("Executa o metodo 'getLista'");

		super.connection = new Conexao().getConexao();
		List<Item> listaAnais = new ArrayList<Item>();

		String sql = "SELECT anaiscongresso.idanais, anaiscongresso.titulo, anaiscongresso.tipo, anaiscongresso.anoPublicacao, anaiscongresso.local"
				+ " FROM anaiscongresso";

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.resultSet = super.statement.executeQuery();

			while (resultSet.next()) {
				autorDAO = new AutorDAO();
				AnaisCongresso anais = new AnaisCongresso();
				anais.setId(resultSet.getInt(1));
				anais.setTitulo(resultSet.getString(2));
				anais.setTipo(resultSet.getString(3));
				anais.setAnoPublicacao(resultSet.getString(4));
				anais.setLocal(resultSet.getString(5));

				anais.setListaAutores(autorDAO.getLista(anais.getId()));
				listaAnais.add(anais);
			}
			super.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaAnais;
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.ItemDAO#inserir(Object)
	 */
	@Override
	public int inserir(Item item) {
		logger.info("Executa o metodo 'inserir' com param objeto : " + item);

		AnaisCongresso obj = (AnaisCongresso) item;
		int id = -1;
		super.connection = new Conexao().getConexao();
		String sql = "INSERT INTO anaiscongresso (titulo, tipo, congresso, anoPublicacao, local) VALUES (?,?,?,?,?)";
		if (obj != null) {
			try {
				super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				super.statement.setString(1, obj.getTitulo());
				super.statement.setString(2, obj.getTipo().name());
				super.statement.setString(3, obj.getCongresso());
				super.statement.setString(4, obj.getAnoPublicacao());
				super.statement.setString(5, obj.getLocal());
				super.statement.execute();
				super.resultSet = super.statement.getGeneratedKeys();
				if (resultSet.next()) {
					id = super.resultSet.getInt(1);
				}
				super.closeConnections();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.ItemDAO#remover(Object)
	 */
	@Override
	public void remover(Item item) {
		logger.info("Executa o metodo 'remover' com param objeto : " + item);

		AnaisCongresso obj = (AnaisCongresso) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "DELETE FROM anaiscongresso WHERE idanais = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setInt(1, obj.getId());
				super.statement.execute();

				super.closeConnections();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @see edu.uepb.web.biblioteca.model.ItemDAO#atualizar(Object)
	 */
	@Override
	public void atualizar(Item item) {
		logger.info("Executa o metodo 'atualizar' com param objeto : " + item);
		AnaisCongresso obj = (AnaisCongresso) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "UPDATE anaiscongresso SET titulo = ?, tipo = ?, congresso = ?, anoPublicacao = ?, local = ? "
					+ "WHERE idanais = ?";
			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getTitulo());
				super.statement.setString(2, obj.getTipo().name());
				super.statement.setString(3, obj.getCongresso());
				super.statement.setString(4, obj.getAnoPublicacao());
				super.statement.setString(5, obj.getLocal());
				super.statement.setInt(6, obj.getId());

				super.statement.execute();
				super.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.ItemDAO#isItemExiste(Object)
	 */
	@Override
	public boolean isItemExiste(Item item) {
		logger.info("Executa o metodo 'isItemExiste' com param objeto : " + item);

		AnaisCongresso obj = (AnaisCongresso) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "SELECT anaiscongresso.titulo FROM anaiscongresso WHERE anaiscongresso.titulo = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getTitulo());
				super.resultSet = super.statement.executeQuery();

				if (resultSet.next()) {
					super.closeConnections();
					return true;
				}
				super.closeConnections();
				return false;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
