package edu.uepb.web.biblioteca.dao;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.model.Aluno;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class AlunoDAOTest {
	Aluno aluno;
	CursoDAO cursoDAO;
	AlunoDAO manager;
	FuncionarioDAO funcDAO;
	Connection conn;

	@Before
	public void setUp() throws Exception {
		conn = new Conexao().getConexao();
		manager = new AlunoDAO();
		funcDAO = new FuncionarioDAO();
		cursoDAO = new CursoDAO();
		aluno = new Aluno("3254", "123erd", "783.234.321-33", "Zeze", "Erlina", "Timor", "Centro", "9893434", null,
				"2012", "2", "lospalos");

	}

	@Test
	public void inserir() throws DAOException {
		aluno.setCurso(cursoDAO.get(6));
		int id = manager.inserir(aluno);
		if (id < 0) {
			Assert.fail();
		}

	}

}
