package edu.uepb.web.biblioteca.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.model.Item;
import edu.uepb.web.biblioteca.model.Jornal;

/**
 * A classe para acessar os dados no banco associando ao objeto {@link Jornal}
 * 
 * @author Larrissa Dantas
 *
 */

public class JornalDAO extends ItemDAO<Jornal> {
	private static Logger logger = Logger.getLogger(JornalDAO.class);

	/**
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#get(int)
	 */
	@Override
	public Jornal get(int id) {
		logger.info("Executa o metodo 'get' com param id : " + id);
		super.connection = new Conexao().getConexao();

		String sql = "SELECT * FROM jornal WHERE jornal.id = ?";
		Jornal jornal = null;
		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();

			if (resultSet.next()) {
				jornal = new Jornal();

				jornal.setId(resultSet.getInt(1));
				jornal.setTitulo(resultSet.getString(2));
				jornal.setData(resultSet.getString(3));
				jornal.setEdicao(resultSet.getString(4));
			}
			super.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return jornal;
	}

	/**
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#getLista()
	 */
	@Override
	public List<Item> getLista() {
		logger.info("Executa o metodo 'getLista'");
		super.connection = new Conexao().getConexao();
		List<Item> listajornais = new ArrayList<Item>();
		String sql = "SELECT * FROM jornal";

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.resultSet = super.statement.executeQuery();

			while (resultSet.next()) {
				Jornal jornal = new Jornal();
				jornal.setId(resultSet.getInt(1));
				jornal.setTitulo(resultSet.getString(2));
				jornal.setData(resultSet.getString(3));
				jornal.setEdicao(resultSet.getString(4));

				listajornais.add(jornal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listajornais;
	}

	/**
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#inserir(Item)
	 */
	@Override
	public int inserir(Item item) {
		logger.info("Executa o metodo 'inserir' com param objeto : " + item);

		Jornal obj = (Jornal) item;
		int id = -1;
		super.connection = new Conexao().getConexao();
		String sql = "INSERT INTO jornal(titulo, data, edicao) VALUES (?,?,?)";
		if (obj != null) {
			try {
				super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				super.statement.setString(1, obj.getTitulo());
				super.statement.setString(2, obj.getData());
				super.statement.setString(3, obj.getEdicao());
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

		Jornal obj = (Jornal) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "DELETE FROM jornal WHERE jornal.id = ?";

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
		logger.info("Executa o metodo 'atualizar' com param objeto : " + item);

		Jornal obj = (Jornal) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "UPDATE jornal SET titulo = ?, data = ?, edicao = ? WHERE jornal.id = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getTitulo());
				super.statement.setString(2, obj.getData());
				super.statement.setString(3, obj.getEdicao());
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

		Jornal obj = (Jornal) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "SELECT jornal.titulo FROM jornal WHERE jornal.titulo = ?";

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
