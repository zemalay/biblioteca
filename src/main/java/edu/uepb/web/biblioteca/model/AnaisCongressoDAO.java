package edu.uepb.web.biblioteca.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class AnaisCongressoDAO extends DAO<AnaisCongresso> {

	private AutorDAO autorDAO;

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#get(int)
	 */
	@Override
	public AnaisCongresso get(int id) {
		super.connection = new Conexao().getConexao();

		String sql = "SELECT anaiscongresso.idanais, anaiscongresso.titulo, anaiscongresso.tipo, anaiscongresso.anoPublicacao, anaiscongresso.local "
				+ "FROM anaiscongresso WHERE anaiscongresso.idanais = ?";

		AnaisCongresso anais = null;

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();

			if (resultSet.next()) {
				anais = new AnaisCongresso();
				anais.setId(resultSet.getInt(1));
				anais.setTitulo(resultSet.getString(2));
				anais.setTipo(resultSet.getString(3));
				anais.setAnoPublicacao(resultSet.getString(4));
				anais.setLocal(resultSet.getString(5));

				anais.setListaAutores(autorDAO.getLista(anais.getId()));
			}

			super.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return anais;
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#getLista()
	 */
	@Override
	public List<AnaisCongresso> getLista() {
		super.connection = new Conexao().getConexao();
		List<AnaisCongresso> listaAnais = new ArrayList<AnaisCongresso>();

		String sql = "SELECT anaiscongresso.idanais, anaiscongresso.titulo, anaiscongresso.tipo, anaiscongresso.anoPublicacao, anaiscongresso.local";

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.resultSet = super.statement.executeQuery();

			while (resultSet.next()) {
				AnaisCongresso anais = new AnaisCongresso();
				anais.setId(resultSet.getInt(1));
				anais.setTitulo(resultSet.getString(2));
				anais.setTipo(resultSet.getString(3));
				anais.setAnoPublicacao(resultSet.getString(4));
				anais.setLocal(resultSet.getString(5));

				listaAnais.add(anais);
			}
			super.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaAnais;
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#inserir(Object)
	 */
	@Override
	public int inserir(AnaisCongresso obj) {
		int id = -1;
		super.connection = new Conexao().getConexao();
		String sql = "INSERT INTO anaiscongresso (titulo, tipo, congresso, anoPublicacao, local) VALUES (?,?,?,?,?)";
		if (!obj.equals(null)) {
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
				super.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#remover(Object)
	 */
	@Override
	public void remover(AnaisCongresso obj) {
		if (!obj.equals(null)) {
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
	 * @see edu.uepb.web.biblioteca.model.DAO#atualizar(Object)
	 */
	@Override
	public void atualizar(AnaisCongresso obj) {
		if (!obj.equals(obj)) {
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

				super.statement.executeQuery();
				super.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#isItemExiste(Object)
	 */
	@Override
	public boolean isItemExiste(AnaisCongresso obj) {
		// TODO Auto-generated method stub
		return false;
	}

}
