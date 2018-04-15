package edu.uepb.web.biblioteca.dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.model.Curso;

/**
 * A classe para acessar os dados no banco associando ao objeto {@link Curso}
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 */
public class CursoDAO extends DAO<Curso> {
	private static Logger logger = Logger.getLogger(CursoDAO.class);

	/**
	 * @see edu.uepb.web.biblioteca.dao.DAO#get(int)
	 */
	@Override
	public Curso get(int id) {
		logger.info("Executa o metodo 'get' com param id : " + id);

		super.connection = new Conexao().getConexao();

		String sql = "SELECT * FROM curso WHERE curso.idcurso = ?";

		Curso curso = null;

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();

			if (resultSet.next()) {
				curso = new Curso();
				curso.setId(resultSet.getInt(1));
				curso.setNome(resultSet.getString(2));
				curso.setNivel(resultSet.getString(3));
				curso.setArea(resultSet.getString(4));
			}
			super.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return curso;
	}

	/**
	 * @see edu.uepb.web.biblioteca.dao.DAO#getLista()
	 */
	@Override
	public List<Curso> getLista() {
		logger.info("Executa o metodo 'getLista' ");
		super.connection = new Conexao().getConexao();

		String sql = "SELECT * FROM curso";
		List<Curso> listaCurso = new ArrayList<Curso>();

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.resultSet = super.statement.executeQuery();

			while (resultSet.next()) {
				Curso curso = new Curso();
				curso.setId(resultSet.getInt(1));
				curso.setNome(resultSet.getString(2));
				curso.setNivel(resultSet.getString(3));
				curso.setArea(resultSet.getString(4));

				listaCurso.add(curso);
			}
			super.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaCurso;
	}

	/**
	 * @see edu.uepb.web.biblioteca.dao.DAO#inserir(Object)
	 */
	@Override
	public int inserir(Curso obj) {
		logger.info("Executa o metodo 'inserir' com param objeto : " + obj);
		int id = -1;
		super.connection = new Conexao().getConexao();
		String sql = "INSERT INTO curso (nome, tipoNivel, area) VALUES (?,?,?)";
		if (obj != null) {
			try {
				super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				super.statement.setString(1, obj.getNome());
				super.statement.setString(2, obj.getNivel().name());
				super.statement.setString(3, obj.getArea());
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
	 * @see edu.uepb.web.biblioteca.dao.DAO#remover(Object)
	 */
	@Override
	public void remover(Curso obj) {
		logger.info("Executa o metodo 'remover' com param objeto : " + obj);

		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "DELETE FROM curso WHERE idcurso = ?";

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
	 * @see edu.uepb.web.biblioteca.dao.DAO#atualizar(Object)
	 */
	@Override
	public void atualizar(Curso obj) {
		logger.info("Executa o metodo 'atualizar' com param objeto : " + obj);
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "UPDATE curso SET nome = ?, tipoNivel = ? , area = ? WHERE curso.idcurso = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getNome());
				super.statement.setString(2, obj.getNivel().name());
				super.statement.setString(3, obj.getArea());
				super.statement.setInt(4, obj.getId());

				super.statement.execute();

				super.closeConnections();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
