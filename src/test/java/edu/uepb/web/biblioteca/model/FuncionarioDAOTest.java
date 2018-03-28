package edu.uepb.web.biblioteca.model;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.enums.TipoFuncionario;
import edu.uepb.web.biblioteca.enums.TipoMidia;
import edu.uepb.web.biblioteca.enums.TipoNivel;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class FuncionarioDAOTest {
	Funcionario funcionario;
	Midia midia;
	Jornal jornal;
	Curso curso;
	FuncionarioDAO manager;
	Connection conn;

	@Before
	public void setUp() throws Exception {
		conn = new Conexao().getConexao();
		manager = new FuncionarioDAO();
		funcionario = new Funcionario("Naruto", TipoFuncionario.ADMINISTRADOR);
		curso = new Curso("Computacao", TipoNivel.GRADUACAO, "Tecnologia");

		midia = new Midia("ABD", TipoMidia.DVD, "21/03/1998");
		jornal = new Jornal("Hoje", "10/04/2000", "4º");
	}

//	@Test
//	public void inserirFuncionario() {
//		int id_fun = manager.inserir(funcionario);
//		if (id_fun < 0) {
//			Assert.fail();
//		}
//	}

//	@Test
//	public void cadastrarItem() {
//		int id_midia = manager.cadastraItem(funcionario, midia);
//		int id_jornal = manager.cadastraItem(funcionario, jornal);
//
//		if (id_midia < 0) {
//			Assert.fail();
//		}
//
//		if (id_jornal < 0) {
//			Assert.fail();
//		}
//
//	}

//	@Test
//	public void atualizarItem() {
//		midia.setId(4);// midia com id = 4 ja existe no banco
//		assertEquals(manager.atualizarItem(funcionario, midia), true);
//	}

//	@Test
//	public void removerItem() {
//		midia.setId(4);
//		assertEquals(manager.removerItem(funcionario, midia), true);
//	}
	
//	@Test
//	public void cadastraCurso() {
//		int id_curso = manager.cadastraCurso(funcionario, curso);
//
//		if (id_curso < 0) {
//			Assert.fail();
//		}
//	}
//
//	@Test
//	public void removerCurso() {
//		curso.setId(4);
//		assertEquals(manager.removerCurso(funcionario, curso), true);
//	}

}
