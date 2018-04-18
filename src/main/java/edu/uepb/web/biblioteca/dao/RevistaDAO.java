package edu.uepb.web.biblioteca.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.exception.ItemExistException;
import edu.uepb.web.biblioteca.model.Item;
import edu.uepb.web.biblioteca.model.Revista;

/**
 * A classe para acessar os dados no banco associando ao objeto {@link Revista}
 * 
 * @autor Larrissa Dantas
 *
 */
public class RevistaDAO extends ItemDAO<Revista> {
	private static Logger logger = Logger.getLogger(RevistaDAO.class);

	/**
	 * @throws DAOException 
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#get(int)
	 */
	@Override
	public Revista get(int id) throws DAOException {
		logger.info("Executa o metodo 'get' com param id : " + id);

		super.connection = new Conexao().getConexao();

		String sql = "SELECT * FROM revista WHERE revista.id = ?";
		Revista revista = null;

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();

			if (resultSet.next()) {

				revista = new Revista();

				revista.setId(resultSet.getInt(1));
				revista.setTitulo(resultSet.getString(2));
				revista.setEditora(resultSet.getString(3));
				revista.setDataPublicacao(resultSet.getString(4));
				revista.setEdicao(resultSet.getString(5));
				revista.setNumeroPagina(resultSet.getInt(6));

			}
			super.closeConnections();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}

		return revista;
	}

	/**
	 * @throws DAOException 
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#getLista()
	 */
	@Override
	public List<Item> getLista() throws DAOException {
		logger.info("Executa o metodo 'getLista' ");

		super.connection = new Conexao().getConexao();
		List<Item> listaRevistas = new ArrayList<Item>();

		String sql = "SELECT * FROM revista";

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.resultSet = super.statement.executeQuery();

			while (resultSet.next()) {

				Revista revista = new Revista();
				revista.setId(resultSet.getInt(1));
				revista.setTitulo(resultSet.getString(2));
				revista.setEditora(resultSet.getString(3));
				revista.setDataPublicacao(resultSet.getString(4));
				revista.setEdicao(resultSet.getString(5));
				revista.setTitulo(resultSet.getString(6));

				listaRevistas.add(revista);

			}

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return listaRevistas;
	}

	/**
	 * @throws DAOException 
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#inserir(Item)
	 */
	@Override
	public int inserir(Item item) throws DAOException {
		logger.info("Executa o metodo 'inserir' com param objeto : " + item);

		Revista obj = (Revista) item;
		int id = -1;
		super.connection = new Conexao().getConexao();

		String sql = "INSERT INTO revista(titulo, editora, data_publicacao, edicao, numero_pagina) VALUES (?,?,?,?,?)";

		if (obj != null) {
			try {
				super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				super.statement.setString(1, obj.getTitulo());
				super.statement.setString(2, obj.getEditora());
				super.statement.setString(3, obj.getDataPublicacao());
				super.statement.setString(4, obj.getEdicao());
				super.statement.setInt(5, obj.getNumeroPagina());

				super.statement.execute();
				super.resultSet = super.statement.getGeneratedKeys();

				if (resultSet.next()) {
					id = super.resultSet.getInt(1);
				}

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

		Revista obj = (Revista) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "DELETE FROM revista WHERE revista.id = ?";

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

		Revista obj = (Revista) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "UPDATE revista SET titulo = ?, editora = ?, data_publicacao = ?, edicao = ?, numero_pagina = ? "
					+ "WHERE revista.id = ?";
			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getTitulo());
				super.statement.setString(2, obj.getEditora());
				super.statement.setString(3, obj.getDataPublicacao());
				super.statement.setString(4, obj.getEdicao());
				super.statement.setInt(5, obj.getNumeroPagina());
				super.statement.setInt(6, obj.getId());

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

		Revista obj = (Revista) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "SELECT revista.titulo FROM revista WHERE revista.titulo = ?";

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
