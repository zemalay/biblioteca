package edu.uepb.web.biblioteca.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.dao.Conexao;
import edu.uepb.web.biblioteca.dao.CursoDAO;
import edu.uepb.web.biblioteca.enums.TipoNivel;
import edu.uepb.web.biblioteca.exception.DAOException;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class CursoDAOTest {
	Curso curso;
	CursoDAO manager;
	Connection conn;
	@Before
	public void setUp() throws Exception {
		manager = new CursoDAO();
		conn = new Conexao().getConexao();
		curso = new Curso("Odontologia", TipoNivel.GRADUACAO, "Saude");
	}

	@Test
	public void inserir() throws DAOException {
		int id = manager.inserir(curso);
		if(id<0) {
			Assert.fail();
		}
	}
	
	@Test
	public void get() throws DAOException {
		assertEquals(manager.get(1).getNome(), curso.getNome());
	}
	
	@Test
	public void getLista() throws DAOException {
		List<Curso> listaCurso = manager.getLista();
		
		assertNotEquals(listaCurso.size(), 0);
		
		for (Curso curso : listaCurso) {
			System.out.println(curso.toString());
		}
	}
	
	@Test
	public void remover() throws DAOException {
		manager.remover(manager.get(1));
		
		assertEquals(manager.get(1), null);
	}
	
	@Test
	public void atualizar() throws DAOException {
		Curso curso1 = manager.get(3);
		curso1.setNome("Direito");
		manager.atualizar(curso1);
		System.out.println(manager.get(3));
		assertNotEquals(curso1.getNome(), manager.get(3));
	}

}

