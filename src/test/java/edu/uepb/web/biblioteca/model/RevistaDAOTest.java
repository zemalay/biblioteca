package edu.uepb.web.biblioteca.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.exception.ItemExistException;

import edu.uepb.web.biblioteca.dao.Conexao;
import edu.uepb.web.biblioteca.dao.RevistaDAO;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class RevistaDAOTest {
	Revista revista;
	RevistaDAO manager;
	Connection conn;

	@Before
	public void setUp() throws Exception {
		conn = new Conexao().getConexao();
		manager = new RevistaDAO();
		revista = new Revista("Titulo Revista", "EditoraRev", "20/05/2016", "4ª", 43);
	}

	@Test
	public void inserir() throws DAOException {
		int id = manager.inserir(revista);
		if (id < 0) {
			Assert.fail();
		}
	}

	@Test
	public void get() throws DAOException {
		Revista revista1 = manager.get(1);
		assertEquals(revista1.getTitulo(), revista.getTitulo());
	}
	
	@Test
	public void getList() throws DAOException {
		List<Item> listaRevista = manager.getLista();
		assertNotEquals(listaRevista.size(), 0);
	}
	
	@Test
	public void remover() throws DAOException {
		manager.remover(manager.get(2));
		assertEquals(manager.get(2), null);
	}
	
	@Test
	public void atualizar() throws DAOException {
		Revista revista2 = manager.get(1);
		revista2.setNumeroPagina(200);
		manager.atualizar(revista2);
		assertEquals(manager.get(1).getNumeroPagina(), 200);
	}
	
	@Test
	public void isItemExiste() throws ItemExistException, DAOException {
		revista.setId(1);
		assertTrue(manager.isItemExiste(revista));
	}
}
