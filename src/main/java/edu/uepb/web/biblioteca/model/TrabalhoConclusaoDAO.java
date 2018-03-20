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
	public TrabalhoConclusao get(int id) {
		super.connection = new Conexao().getConexao();

		String sql = "SELECT trabalhoconclusao.idtrabalho, trabalhoconclusao.titulo, trabalhoconclusao.tipo, "
				+ "trabalhoconclusao.anoDefesa, trabalhoconclusao.local, autor.idautor, autor.nome FROM trabalhoconclusao "
				+ "INNER JOIN autor ON autor.idautor = trabalhoconclusao.autor_idautor "
				+ "WHERE trabalhoconclusao.idtrabalho = ?";

		TrabalhoConclusao trabalhoConclusao = null;

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();

			if (resultSet.next()) {
				trabalhoConclusao = new TrabalhoConclusao();
				Autor autor = new Autor();

				trabalhoConclusao.setId(resultSet.getInt(1));
				trabalhoConclusao.setTitulo(resultSet.getString(2));
				trabalhoConclusao.setTipo(resultSet.getString(3));
				trabalhoConclusao.setAnoDefesa(resultSet.getString(4));
				trabalhoConclusao.setLocal(resultSet.getString(5));
				
//				trabalhoConclusao.setListaorientadores(listaorientadores);

				autor.setId(resultSet.getInt(6));
				autor.setNome(resultSet.getString(7));
			}
			super.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trabalhoConclusao;
	}

	public List<TrabalhoConclusao> getLista() {
		super.connection = new Conexao().getConexao();
		List<TrabalhoConclusao> listaTrabalhos = new ArrayList<TrabalhoConclusao>();

		String sql = "SELECT trabalhoconclusao.idtrabalho, trabalhoconclusao.titulo, trabalhoconclusao.tipo, "
				+ "trabalhoconclusao.anoDefesa, trabalhoconclusao.local, autor.idautor, autor.nome FROM trabalhoconclusao "
				+ "INNER JOIN autor ON autor.idautor = trabalhoconclusao.autor_idautor";

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.resultSet = super.statement.executeQuery();

			while (resultSet.next()) {
				Autor autor = new Autor();
				autor.setId(resultSet.getInt(6));
				autor.setNome(resultSet.getString(7));

				TrabalhoConclusao trabalhoConclusao = new TrabalhoConclusao();
				trabalhoConclusao.setId(resultSet.getInt(1));
				trabalhoConclusao.setTitulo(resultSet.getString(2));
				trabalhoConclusao.setTipo(resultSet.getString(3));
				trabalhoConclusao.setAnoDefesa(resultSet.getString(4));
				trabalhoConclusao.setLocal(resultSet.getString(5));
				trabalhoConclusao.setAutor(autor);

				listaTrabalhos.add(trabalhoConclusao);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaTrabalhos;
	}

	public int inserir(TrabalhoConclusao obj) {
		int id = -1;
		super.connection = new Conexao().getConexao();

		String sql = "INSERT INTO trabalhoconclusao (titulo, tipo, anoDefesa, local, autor_idautor) VALUES (?,?,?,?,?)";

		if (!obj.equals(null)) {
			try {
				super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				super.statement.setString(1, obj.getTitulo());
				super.statement.setString(2, obj.getTipo().name());
				super.statement.setString(3, obj.getAnoDefesa());
				super.statement.setString(4, obj.getLocal());
				super.statement.setInt(5, obj.getAutor().getId());

				if (resultSet.next()) {
					id = super.resultSet.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void atualizar(TrabalhoConclusao obj) {
		if (!obj.equals(null)) {
			super.connection = new Conexao().getConexao();
			String sql = "UPDATE trabalhoconclusao SET titulo = ?, tipo = ?, anoDefesa = ?, local = ?, autor_idautor = ?"
					+ " WHERE idtrabalho = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getTitulo());
				super.statement.setString(2, obj.getTipo().name());
				super.statement.setString(3, obj.getAnoDefesa());
				super.statement.setString(4, obj.getLocal());
				super.statement.setInt(5, obj.getAutor().getId());

				super.statement.execute();
				super.closeConnections();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
