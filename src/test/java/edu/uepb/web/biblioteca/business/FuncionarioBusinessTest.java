package edu.uepb.web.biblioteca.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.dao.AlunoDAOImpl;
import edu.uepb.web.biblioteca.dao.Conexao;
import edu.uepb.web.biblioteca.dao.CursoDAOImpl;
import edu.uepb.web.biblioteca.dao.EmprestimoDAOImpl;
import edu.uepb.web.biblioteca.dao.ItemDAOImpl;
import edu.uepb.web.biblioteca.enums.TipoAnais;
import edu.uepb.web.biblioteca.enums.TipoFuncionario;
import edu.uepb.web.biblioteca.enums.TipoItem;
import edu.uepb.web.biblioteca.enums.TipoNivel;
import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.exception.ExistException;
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Curso;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.model.Item;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class FuncionarioBusinessTest {
	FuncionarioBusiness service;
	CursoDAOImpl cursoDAO;
	AlunoDAOImpl alunoDAO;
	ItemDAOImpl itemDAO;

	Funcionario admin;
	Funcionario operador;
	Aluno aluno1;
	Aluno aluno2;
	Aluno aluno3;

	Curso historia;
	Curso geografia;
	Curso contabeis;

	Item livro;
	Item anais;
	Item jornal;

	Connection connection;

	@Before
	public void setUp() throws Exception {
		service = new FuncionarioBusiness();
		alunoDAO = new AlunoDAOImpl();
		cursoDAO = new CursoDAOImpl();
		itemDAO = new ItemDAOImpl();
		
		admin = new Funcionario();
		operador = new Funcionario();
		
		admin.setNome("Zeze");
		admin.setTipoFunc(TipoFuncionario.ADMINISTRADOR);
		operador.setTipoFunc(TipoFuncionario.OPERADOR);
		connection = new Conexao().getConexao();
		aluno1 = new Aluno("", "356", "29384", "Geo", "Dina", "Brasil", "Sao Paulo", "9893434", null, "2006", "1",
				"dsenr");

		aluno2 = new Aluno("", "903", "234", "Zina", "Bas", "Australia", "Sao Paulo", "9893434", null, "2009", "1",
				"dsenr");

		aluno3 = new Aluno("", "00", "384748", "Dom", "Sani", "Brasil", "Redencao", "9893434", null, "2006", "1",
				"dsenr");

		historia = new Curso("C23", TipoNivel.DOUTORADO, "Sociais");
		geografia = new Curso("Curso83", TipoNivel.ESPECIALIZACAO, "Sociais");
		contabeis = new Curso("Ciencia435", TipoNivel.MESTRADO, "Exatas");

		livro = new Item();
		livro.setTipoItem(TipoItem.LIVRO);
		livro.setIsbn("13-1383");
		livro.setTitulo("Livro 654");
		livro.setAutor("bnas");
		livro.setEdicao("2");
		livro.setEditora("NNN");
		livro.setNumeroPagina(300);
		livro.setArea("Sei la");
		livro.setTema("BAS");
		livro.setQuantidade(20);

		anais = new Item();
		anais.setTipoItem(TipoItem.ANAIS);
		anais.setTitulo("Titulo Anais 2001");
		anais.setTipoAnais(TipoAnais.RESUMO);
		anais.setCongresso("ASH");
		anais.setAnoPublicacao("1999");
		anais.setLocal("KAJS");
		anais.setAutor("basmj");
		anais.setQuantidade(2);

		jornal = new Item();
		jornal.setTipoItem(TipoItem.JORNAL);
		jornal.setTitulo("Titulo Jornal 2300");
		jornal.setData("22/03/2014");
		jornal.setQuantidade(3);
	}

	@Test
	public void inserir() throws AutenticacaoException, DAOException, ExistException {
		int idCurso = service.cadastraCurso(admin, historia);
		aluno1.setCurso(cursoDAO.get(idCurso));
		int idAluno = service.cadastrarAluno(operador, aluno1);

		int idItem = service.cadastraItem(admin, livro);

		if (idCurso < 0 && idAluno < 0 && idItem < 0) {
			Assert.fail();
		}
		
		EmprestimoDAOImpl empreDAO = new EmprestimoDAOImpl();
		service.cadastrarEmprestimo(1, 1, 2);
		
	}

//	@Test
//	public void remover() throws AutenticacaoException, DAOException, ExistException {
//		int idCurso = service.cadastraCurso(admin, geografia);
//		aluno2.setCurso(cursoDAO.get(idCurso));
//		int idAluno = service.cadastrarAluno(admin, aluno2);
//		int idItem = service.cadastraItem(admin, anais);
//
//		service.removerCurso(admin, geografia);
//		service.removerAluno(admin, aluno2);
//		service.removerItem(admin, anais);
//
//		assertNotEquals(null, cursoDAO.get(idCurso));
//		assertNotEquals(null, alunoDAO.get(idAluno));
//		assertNotEquals(null, itemDAO.get(idItem));
//	}
//
//	@Test
//	public void atualizar() throws AutenticacaoException, DAOException, ExistException {
//		int idCurso = service.cadastraCurso(admin, contabeis);
//		aluno3.setCurso(cursoDAO.get(idCurso));
//		int idAluno = service.cadastrarAluno(admin, aluno3);
//		int idItem = service.cadastraItem(admin, jornal);
//
//		aluno3.setId(idAluno);
//		aluno3.setNome("Nome Atualizado");
//
//		jornal.setId(idItem);
//		jornal.setTitulo("Titulo Atualizado");
//
//		service.atualizarAluno(admin, aluno3);
//		service.atualizarItem(admin, jornal);
//
//		assertEquals("Nome Atualizado", alunoDAO.get(idAluno).getNome());
//		assertEquals("Titulo Atualizado", itemDAO.get(idItem).getTitulo());
//	}

}
