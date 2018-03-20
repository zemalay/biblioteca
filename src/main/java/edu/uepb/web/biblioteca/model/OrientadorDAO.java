package edu.uepb.web.biblioteca.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class OrientadorDAO extends DAO<Orientador> {

	@Override
	public Orientador get(int id) {
		super.connection = new Conexao().getConexao();
		String sql = "SELECT orientador.idorientador, orientador.nome";

		Orientador orientador = null;

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();

			if (resultSet.next()) {
				orientador = new Orientador();

				orientador.setId(resultSet.getInt(1));
				orientador.setNome(resultSet.getString(2));
			}

			super.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Orientador> getLista() {
		super.connection = new Conexao().getConexao();

		return null;
	}

	@Override
	public int inserir(Orientador obj) {
		int id = -1;
		String sql = "INSERT INTO orientador (nome, trabalhoConclusao_id) VALUES (?,?)";

		if (!obj.equals(null)) {
			try {
				super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				super.statement.setString(1, obj.getNome());
				super.statement.setInt(2, obj.getTrabalhoConclusao().getId());
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

	@Override
	public void remover(Orientador obj) {
		// TODO Auto-generated method stub

	}


	@Override
	public void atualizar(Orientador obj) {
		// TODO Auto-generated method stub

	}

}
