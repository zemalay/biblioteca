package edu.uepb.web.biblioteca.dao;

import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.enums.TipoNivel;
import edu.uepb.web.biblioteca.model.Emprestimo;
import edu.uepb.web.biblioteca.utils.BibliotecaDateTime;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class EmprestimoDAOTest {
	Connection conn;
	EmprestimoDAOImpl manager;
	FuncionarioDAOImpl funcDAO;
	AlunoDAOImpl alunoDAO;
	ItemDAOImpl itemDAO;
	Emprestimo emprestimo;

	@Before
	public void setUp() throws Exception {
		manager = new EmprestimoDAOImpl();
		funcDAO = new FuncionarioDAOImpl();
		alunoDAO = new AlunoDAOImpl();
		itemDAO = new ItemDAOImpl();
	}

//	@Test
//	public void inserir() throws DAOException {
//		emprestimo = new Emprestimo();
//
//		TipoNivel tipo = alunoDAO.get(3).getCurso().getNivel();
//		emprestimo.setFuncionario(funcDAO.get(3));
//		emprestimo.setAluno(alunoDAO.get(3));
//		emprestimo.setItem(itemDAO.get(3));
//		emprestimo.setDataCadastrado(BibliotecaDateTime.getDataCadastrado());
//		emprestimo.setDataDevolucao(BibliotecaDateTime.getDataDevolucao(tipo));
//
//		manager.inserir(emprestimo);
//
//	}

	@Test
	public void get() {
		emprestimo = new Emprestimo();

		TipoNivel tipo = alunoDAO.getById(4).getCurso().getNivel();
		emprestimo.setFuncionario(funcDAO.getById(3));
		emprestimo.setAluno(alunoDAO.getById(4));
		emprestimo.setItem(itemDAO.getById(4));
		emprestimo.setDataCadastrado(BibliotecaDateTime.getDataCadastrado());
		emprestimo.setDataDevolucao(BibliotecaDateTime.getDataDevolucao(tipo));

		assertNotEquals(null, manager.getById(manager.inserir(emprestimo)));
	}

//	@Test
//	public void getLista() {
//		emprestimo = new Emprestimo();
//
//		TipoNivel tipo = alunoDAO.get(6).getCurso().getNivel();
//		emprestimo.setFuncionario(funcDAO.get(3));
//		emprestimo.setAluno(alunoDAO.get(6));
//		emprestimo.setItem(itemDAO.get(6));
//		emprestimo.setDataCadastrado(BibliotecaDateTime.getDataCadastrado());
//		emprestimo.setDataDevolucao(BibliotecaDateTime.getDataDevolucao(tipo));
//
//		assertNotEquals(0, manager.getLista().size());
//	}
//
//	@Test
//	public void atualizar() {
//		emprestimo = new Emprestimo();
//
//		TipoNivel tipo = alunoDAO.get(5).getCurso().getNivel();
//		emprestimo.setFuncionario(funcDAO.get(2));
//		emprestimo.setAluno(alunoDAO.get(5));
//		emprestimo.setItem(itemDAO.get(5));
//		emprestimo.setDataCadastrado(BibliotecaDateTime.getDataCadastrado());
//		emprestimo.setDataDevolucao(BibliotecaDateTime.getDataDevolucao(tipo));
//
//		
//
//	}

}
