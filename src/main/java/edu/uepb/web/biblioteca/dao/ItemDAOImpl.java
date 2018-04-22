package edu.uepb.web.biblioteca.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.PreparedStatement;

import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.model.Item;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 */
public class ItemDAOImpl implements DAO<Item> {
	private static final int FAKE_ID = -1;
	private Item acervo;
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;

	/**
	 * @throws DAOException
	 * @see {@link DAO#get(int)}
	 */
	@Override
	public Item get(int id) throws DAOException {
		connection = new Conexao().getConexao();
		String sql = "SELECT * FROM item WHERE item.iditem = ?";

		try {
			statement = (PreparedStatement) connection.prepareStatement(sql);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			if (resultSet.next()) {
				acervo = new Item();
				acervo.setId(resultSet.getInt(1));
				acervo.setTipoItem(resultSet.getString(2));
				acervo.setIsbn(resultSet.getString(3));
				acervo.setTitulo(resultSet.getString(4));
				acervo.setTipoAnais(resultSet.getString(5));
				acervo.setTipoMidia(resultSet.getString(6));
				acervo.setTipoTrabalho(resultSet.getString(7));
				acervo.setAutor(resultSet.getString(8));
				acervo.setCongresso(resultSet.getString(9));
				acervo.setAnoPublicacao(resultSet.getString(10));
				acervo.setLocal(resultSet.getString(11));
				acervo.setEditora(resultSet.getString(12));
				acervo.setEdicao(resultSet.getString(13));
				acervo.setNumeroPagina(resultSet.getInt(14));
				acervo.setArea(resultSet.getString(15));
				acervo.setTema(resultSet.getString(16));
				acervo.setDataGravacao(resultSet.getString(17));
				acervo.setOrientador(resultSet.getString(18));
				acervo.setData(resultSet.getString(19));
			}

			statement.close();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return acervo;
	}

	/**
	 * @throws DAOException
	 * @see {@link DAO#getLista()}
	 */
	@Override
	public List<Item> getLista() throws DAOException {
		connection = new Conexao().getConexao();
		List<Item> listaAcervo = new ArrayList<>();
		String sql = "SELECT * FROM item";
		try {
			statement = (PreparedStatement) connection.prepareStatement(sql);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				acervo = new Item();
				acervo.setId(resultSet.getInt(1));
				acervo.setTipoItem(resultSet.getString(2));
				acervo.setIsbn(resultSet.getString(3));
				acervo.setTitulo(resultSet.getString(4));
				acervo.setTipoAnais(resultSet.getString(5));
				acervo.setTipoMidia(resultSet.getString(6));
				acervo.setTipoTrabalho(resultSet.getString(7));
				acervo.setAutor(resultSet.getString(8));
				acervo.setCongresso(resultSet.getString(9));
				acervo.setAnoPublicacao(resultSet.getString(10));
				acervo.setLocal(resultSet.getString(11));
				acervo.setEditora(resultSet.getString(12));
				acervo.setEdicao(resultSet.getString(13));
				acervo.setNumeroPagina(resultSet.getInt(14));
				acervo.setArea(resultSet.getString(15));
				acervo.setTema(resultSet.getString(16));
				acervo.setDataGravacao(resultSet.getString(17));
				acervo.setOrientador(resultSet.getString(18));
				acervo.setData(resultSet.getString(19));
				listaAcervo.add(acervo);
			}
			statement.close();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}
		return listaAcervo;
	}

	/**
	 * @throws DAOException
	 * @see {@link DAO#inserir(Object)}
	 */
	@Override
	public int inserir(Item obj) throws DAOException {
		int id = ItemDAOImpl.FAKE_ID;
		if (obj != null) {
			connection = new Conexao().getConexao();
			String sql = "INSERT INTO item" + "(tipo_item, isbn, titulo, tipo_anais, "
					+ "tipo_midia, tipo_trabalho_conclusao, autor, congresso, ano_publicacao, local, editora, "
					+ "edicao, numero_pagina, area, tema, data_gravacao, orientador, data) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			try {
				statement = (PreparedStatement) connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, obj.getTipoItem().name());
				statement.setString(2, obj.getIsbn());
				statement.setString(3, obj.getTitulo());
				statement.setString(4, obj.getTipoAnais().toString());
				statement.setString(5, obj.getTipoMidia().toString());
				statement.setString(6, obj.getTipoTrabalho().toString());
				statement.setString(7, obj.getAutor());
				statement.setString(8, obj.getCongresso());
				statement.setString(9, obj.getAnoPublicacao());
				statement.setString(10, obj.getLocal());
				statement.setString(11, obj.getEditora());
				statement.setString(12, obj.getEdicao());
				statement.setInt(13, obj.getNumeroPagina());
				statement.setString(14, obj.getArea());
				statement.setString(15, obj.getTema());
				statement.setString(16, obj.getDataGravacao());
				statement.setString(17, obj.getOrientador());
				statement.setString(18, obj.getData());

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
	 * @see {@link DAO#remover(Object)}
	 */
	@Override
	public void remover(Item obj) throws DAOException {
		if (obj != null) {
			connection = new Conexao().getConexao();
			String sql = "DELETE FROM item WHERE item.iditem = ?";

			try {
				statement = (PreparedStatement) connection.prepareStatement(sql);
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
	 * @see {@link DAO#atualizar(Object)}
	 */
	@Override
	public void atualizar(Item obj) throws DAOException {
		if (obj != null) {
			connection = new Conexao().getConexao();
			String sql = "UPDATE item SET tipo_item = ?, isbn = ?, titulo = ?, tipo_anais = ?, "
					+ "tipo_midia = ?, tipo_trabalho_conclusao = ?, autor = ?, congresso = ?, ano_publicacao = ?, local = ?, editora = ?, "
					+ "edicao = ?, numero_pagina = ?, area = ?, tema = ?, data_gravacao = ?, orientador = ? , data = ?"
					+ "WHERE iditem = ?";

			try {
				statement = (PreparedStatement) connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, obj.getTipoItem().name());
				statement.setString(2, obj.getIsbn());
				statement.setString(3, obj.getTitulo());
				statement.setString(4, obj.getTipoAnais().name());
				statement.setString(5, obj.getTipoMidia().name());
				statement.setString(6, obj.getTipoTrabalho().name());
				statement.setString(7, obj.getAutor());
				statement.setString(8, obj.getCongresso());
				statement.setString(9, obj.getAnoPublicacao());
				statement.setString(10, obj.getLocal());
				statement.setString(11, obj.getEditora());
				statement.setString(12, obj.getEdicao());
				statement.setInt(13, obj.getNumeroPagina());
				statement.setString(14, obj.getArea());
				statement.setString(15, obj.getTema());
				statement.setString(16, obj.getDataGravacao());
				statement.setString(17, obj.getOrientador());
				statement.setString(18, obj.getData());

				statement.setInt(19, obj.getId());

				statement.execute();

				connection.close();
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
	}

	/**
	 * @throws DAOException
	 * @see {@link DAO#isExiste(Object)}
	 */
	@Override
	public boolean isExiste(Item obj) throws DAOException {
		if (obj != null) {
			connection = new Conexao().getConexao();
			String sql = "SELECT * FROM item WHERE titulo = ?";

			try {
				statement = (PreparedStatement) connection.prepareStatement(sql);
				statement.setString(1, obj.getTitulo());
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
