package edu.uepb.web.biblioteca.dao;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.business.FuncionarioBusiness;
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
	FuncionarioBusiness funService;
	Connection conn;

	@Before
	public void setUp() throws Exception {
		conn = new Conexao().getConexao();
		funService = new FuncionarioBusiness();
		manager = new AlunoDAO();
		cursoDAO = new CursoDAO();
		aluno = new Aluno("", "980", "032.634.501-31", "Lula", "Dina", "Brasil", "Sao Paulo", "9893434", null,
				"2016", "2", "Campinas");

	}

	@Test
	public void inserir() throws DAOException {
		aluno.setCurso(cursoDAO.get(8));
		aluno.setMatricula(funService.gerarMatricula(aluno));
		int id = manager.inserir(aluno);
		if (id < 0) {
			Assert.fail();
		}

	}

}
