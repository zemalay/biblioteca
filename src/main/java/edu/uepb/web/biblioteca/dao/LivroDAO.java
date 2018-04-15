package edu.uepb.web.biblioteca.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.model.Item;
import edu.uepb.web.biblioteca.model.Livro;

/**
 * A classe para acessar os dados no banco associando ao objeto {@link Livro}
 * 
 * @autor Larrissa Dantas
 * 
 */
public class LivroDAO extends ItemDAO<Livro> {
	private static Logger logger = Logger.getLogger(LivroDAO.class);

	/**
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#get(int)
	 */
	@Override
	public Livro get(int id) {
		logger.info("Executa o metodo 'get' com param id : " + id);

		super.connection = new Conexao().getConexao();
		String sql = "SELECT * FROM livro WHERE livro.id = ?";

		Livro livro = null;

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();

			if (resultSet.next()) {

				livro = new Livro();

				livro.setId(resultSet.getInt(1));
				livro.setIsbn(resultSet.getString(2));
				livro.setTitulo(resultSet.getString(3));
				livro.setAutor(resultSet.getString(4));
				livro.setEditora(resultSet.getString(5));
				livro.setAnoPublicacao(resultSet.getString(6));
				livro.setEdicao(resultSet.getString(7));
				livro.setNumeroPagina(resultSet.getInt(8));
				livro.setArea(resultSet.getString(9));
				livro.setTema(resultSet.getString(10));

			}
			super.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return livro;
	}

	/**
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#getLista()
	 */
	@Override
	public List<Item> getLista() {
		logger.info("Executa o metodo 'getLista' ");

		super.connection = new Conexao().getConexao();
		List<Item> listaLivros = new ArrayList<Item>();

		String sql = "SELECT * FROM livro";

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.resultSet = super.statement.executeQuery();

			while (resultSet.next()) {

				Livro livro = new Livro();

				livro.setId(resultSet.getInt(1));
				livro.setIsbn(resultSet.getString(2));
				livro.setTitulo(resultSet.getString(3));
				livro.setAutor(resultSet.getString(4));
				livro.setEditora(resultSet.getString(5));
				livro.setAnoPublicacao(resultSet.getString(6));
				livro.setEdicao(resultSet.getString(7));
				livro.setNumeroPagina(resultSet.getInt(8));
				livro.setArea(resultSet.getString(9));
				livro.setTema(resultSet.getString(10));

				listaLivros.add(livro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaLivros;
	}

	/**
	 * @see edu.uepb.web.biblioteca.dao.ItemDAO#inserir(Item)
	 */

	@Override
	public int inserir(Item item) {
		logger.info("Executa o metodo 'inserir' com param objeto : " + item);

		Livro obj = (Livro) item;
		int id = -1;
		super.connection = new Conexao().getConexao();

		String sql = "INSERT INTO livro (isbn, titulo, autor, editora, ano_publicacao, edicao, numero_pagina, area, tema) VALUES (?,?,?,?,?,?,?,?,?)";
		if (obj != null) {
			try {
				super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				super.statement.setString(1, obj.getIsbn());
				super.statement.setString(2, obj.getTitulo());
				super.statement.setString(3, obj.getAutor());
				super.statement.setString(4, obj.getEditora());
				super.statement.setString(5, obj.getAnoPublicacao());
				super.statement.setString(6, obj.getEdicao());
				super.statement.setInt(7, obj.getNumeroPagina());
				super.statement.setString(8, obj.getArea());
				super.statement.setString(9, obj.getTema());
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
		Livro obj = (Livro) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "DELETE FROM livro WHERE livro.id = ?";

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

		Livro obj = (Livro) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "UPDATE livro SET isbn = ?, titulo= ?, autor = ?, editora = ?, ano_publicacao = ?, "
					+ "edicao = ?, numero_pagina = ?, area = ?, tema = ?" + "WHERE livro.id = ?";
			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getIsbn());
				super.statement.setString(2, obj.getTitulo());
				super.statement.setString(3, obj.getAutor());
				super.statement.setString(4, obj.getEditora());
				super.statement.setString(5, obj.getAnoPublicacao());
				super.statement.setString(6, obj.getEdicao());
				super.statement.setInt(7, obj.getNumeroPagina());
				super.statement.setString(8, obj.getArea());
				super.statement.setString(9, obj.getTema());

				super.statement.setInt(10, obj.getId());

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

		Livro obj = (Livro) item;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "SELECT livro.isbn FROM livro WHERE livro.isbn = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getIsbn());
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
