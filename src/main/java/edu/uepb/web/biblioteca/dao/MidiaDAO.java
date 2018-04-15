package edu.uepb.web.biblioteca.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.model.Item;
import edu.uepb.web.biblioteca.model.Midia;

/**
 * A classe para acessar os dados no banco associando ao objeto {@link Midia}
 * 
 * @autor Larrissa Dantas
 * 
 */
public class MidiaDAO extends ItemDAO<Midia> {
	private static Logger logger = Logger.getLogger(MidiaDAO.class);

	/**
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#get(int)
	 */
	@Override
	public Midia get(int id) {
		logger.info("Executa o metodo 'get' com param id : " + id);

		super.connection = new Conexao().getConexao();
		String sql = "SELECT * FROM midia WHERE midia.id = ?";
		Midia midia = null;

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();

			if (resultSet.next()) {
				midia = new Midia();

				midia.setId(resultSet.getInt(1));
				midia.setTitulo(resultSet.getString(2));
				midia.setDataGravacao(resultSet.getString(3));
				midia.setTipo(resultSet.getString(4));

			}
			super.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return midia;
	}

	/**
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#getLista()
	 */
	@Override
	public List<Item> getLista() {
		logger.info("Executa o metodo 'getLista'");

		super.connection = new Conexao().getConexao();
		List<Item> listaMidias = new ArrayList<Item>();
		String sql = "SELECT * FROM midia";

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.resultSet = super.statement.executeQuery();

			while (resultSet.next()) {
				Midia midia = new Midia();
				midia.setId(resultSet.getInt(1));
				midia.setTitulo(resultSet.getString(2));
				midia.setDataGravacao(resultSet.getString(3));
				midia.setTipo(resultSet.getString(4));

				listaMidias.add(midia);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaMidias;

	}

	/**
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#inserir(Item)
	 */
	@Override
	public int inserir(Item item) {
		logger.info("Executa o metodo 'inserir' com param objeto : " + item);

		Midia obj = (Midia) item;

		int id = -1;
		super.connection = new Conexao().getConexao();
		String sql = "INSERT INTO midia( titulo, data_gravacao, tipo) VALUES (?,?,?)";
		if (obj != null) {
			try {
				super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				super.statement.setString(1, obj.getTitulo());
				super.statement.setString(2, obj.getDataGravacao());
				super.statement.setString(3, obj.getTipo().name());

				super.statement.execute();
				super.resultSet = super.statement.getGeneratedKeys();

				if (resultSet.next()) {
					id = super.resultSet.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	/**
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#remover(Item)
	 */
	@Override
	public void remover(Item item) {
		logger.info("Executa o metodo 'remover' com param objeto : " + item);

		Midia obj = (Midia) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "DELETE FROM midia WHERE midia.id = ?";

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
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#atualizar(Item)
	 */
	@Override
	public void atualizar(Item item) {
		logger.info("Executa o metodo 'autalizar' com param objeto : " + item);

		Midia obj = (Midia) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "UPDATE midia SET titulo = ?, data_gravacao = ?, tipo = ?" + " WHERE midia.id = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getTitulo());
				super.statement.setString(2, obj.getDataGravacao());
				super.statement.setString(3, obj.getTipo().name());
				super.statement.setInt(4, obj.getId());

				super.statement.execute();
				super.closeConnections();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#isItemExiste(Item)
	 */
	@Override
	public boolean isItemExiste(Item item) {
		logger.info("Executa o metodo 'isItemExiste' com param objeto : " + item);

		Midia obj = (Midia) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "SELECT midia.titulo FROM midia WHERE midia.titulo = ?";
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
