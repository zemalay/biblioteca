package edu.uepb.web.biblioteca.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Curso;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class AlunoDAO extends DAO<Aluno> {
	
	private CursoDAO cursoDAO;
	private Curso curso;
	private static Logger logger = Logger.getLogger(AlunoDAO.class);

	@Override
	public Aluno get(int id) {
		logger.info("Executa o metodo 'get' com param id : " + id);

		super.connection = new Conexao().getConexao();

		String sql = "SELECT * FROM aluno WHERE aluno.idaluno = ?";
		Aluno aluno;
		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();

			aluno = new Aluno();
			aluno.setMatricula(resultSet.getString(3));
			aluno.setCurso(cursoDAO.get(resultSet.getInt(2)));
			aluno.setRg(resultSet.getString(4));
			aluno.setCpf(resultSet.getString(5));
			aluno.setNome(resultSet.getString(6));
			aluno.setNomeMae(resultSet.getString(7));
			aluno.setNaturalidade(resultSet.getString(8));
			aluno.setEndereco(resultSet.getString(9));
			aluno.setTelefone(resultSet.getString(10));
			aluno.setAno(resultSet.getString(11));
			aluno.setPeriodoIngresso(resultSet.getString(12));
			aluno.setSenha(resultSet.getString(13));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Aluno> getLista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int inserir(Aluno obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void remover(Aluno obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void atualizar(Aluno obj) {
		// TODO Auto-generated method stub

	}

}
