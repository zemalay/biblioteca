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
public class TrabalhoConclusaoDAO extends DAO<TrabalhoConclusao> {

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#get(int)
	 */
	@Override
	public TrabalhoConclusao get(int id) {
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
			e.printStackTrace();
		}
		return trabalhoConclusao;
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#getLista()
	 */
	@Override
	public List<TrabalhoConclusao> getLista() {
		super.connection = new Conexao().getConexao();
		List<TrabalhoConclusao> listaTrabalhos = new ArrayList<TrabalhoConclusao>();

		String sql = "SELECT trabalhoconclusao.idtrabalho, trabalhoconclusao.titulo, trabalhoconclusao.tipo, "
				+ "trabalhoconclusao.anoDefesa, trabalhoconclusao.local, trabalhoconclusao.autor, trabalhoconclusao.orientador";

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
			e.printStackTrace();
		}
		return listaTrabalhos;
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#inserir(Object)
	 */
	@Override
	public int inserir(TrabalhoConclusao obj) {
		int id = -1;
		super.connection = new Conexao().getConexao();

		String sql = "INSERT INTO trabalhoconclusao (titulo, tipo, anoDefesa, local, autor, orientador) VALUES (?,?,?,?,?,?)";

		if (!obj.equals(null)) {
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
				e.printStackTrace();
			}
		}
		return id;
	}

	/**
	 * @return
	 * @see edu.uepb.web.biblioteca.model.DAO#remover(Object)
	 */
	@Override
	public void remover(TrabalhoConclusao obj) {
		if (!obj.equals(null)) {
			super.connection = new Conexao().getConexao();
			String sql = "DELETE FROM trabalhoconclusao WHERE idtrabalho = ?";

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
	public void atualizar(TrabalhoConclusao obj) {
		if (!obj.equals(null)) {
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#isItemExiste(Object)
	 */
	@Override
	public boolean isItemExiste(TrabalhoConclusao obj) {
		if (!obj.equals(null)) {
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
				return false;

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return false;
	}

}
