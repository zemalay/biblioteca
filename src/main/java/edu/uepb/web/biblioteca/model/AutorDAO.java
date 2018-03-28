package edu.uepb.web.biblioteca.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * A classe para acessar os dados no banco associando ao {@link Autor}
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class AutorDAO extends DAO<Autor> {
	private Logger logger = Logger.getLogger(AutorDAO.class);

	/**
	 * 
	 * @see edu.uepb.web.biblioteca.model.DAO#get(int)
	 */
	@Override
	public Autor get(int id) {
		logger.info("Executa o metodo 'get' com param id : " + id);
		super.connection = new Conexao().getConexao();
		String sql = "SELECT * FROM autor WHERE autor.idautor = ?";

		Autor autor = null;

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();

			if (resultSet.next()) {
				autor = new Autor();
				autor.setId(resultSet.getInt(1));
				autor.setNome(resultSet.getString(2));
			}
			super.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return autor;
	}

	/**
	 * Este metodo nao esta usado
	 * 
	 * @return null
	 * @see edu.uepb.web.biblioteca.model.DAO#getLista()
	 */
	@Override
	public List<Autor> getLista() {
		return null;
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#inserir(Object)
	 */
	@Override
	public int inserir(Autor obj) {
		logger.info("Executa o metodo 'inserir' com param objeto : " + obj);
		int id = -1;
		super.connection = new Conexao().getConexao();

		String sql = "INSERT INTO autor (nome, anaisCongresso_id) VALUES(?,?)";

		if (obj != null) {
			try {
				super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				super.statement.setString(1, obj.getNome());
				super.statement.setInt(2, obj.getAnaisCongresso().getId());
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
	 * Este metodo nao esta usado
	 * 
	 * @see edu.uepb.web.biblioteca.model.DAO#remover(Object)
	 */
	@Override
	public void remover(Autor obj) {
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#atualizar(Object)
	 */
	@Override
	public void atualizar(Autor obj) {
		logger.info("Executa o metodo 'atualizar' com param objeto : " + obj);
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "UPDATE autor SET nome = ? WHERE idautor = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getNome());
				super.statement.setInt(2, obj.getId());
				super.statement.execute();
				super.connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Pega a lista do/s autor/es de um Anais Congresso
	 * 
	 * @param idAnais
	 *            id do Anais Congresso
	 * @return List lista dos autores
	 */
	public List<Autor> getLista(int idAnais) {
		logger.info("Executa o metodo 'getLista' com param id : " + idAnais);

		super.connection = new Conexao().getConexao();
		String sql = "SELECT * FROM autor WHERE autor.anaisCongresso_id = ?";
		List<Autor> listaAutor = new ArrayList<Autor>();
		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, idAnais);
			super.resultSet = super.statement.executeQuery();

			while (resultSet.next()) {
				Autor autor = new Autor();
				autor.setId(resultSet.getInt(1));
				autor.setNome(resultSet.getString(2));

				listaAutor.add(autor);
			}
			super.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaAutor;
	}

}
