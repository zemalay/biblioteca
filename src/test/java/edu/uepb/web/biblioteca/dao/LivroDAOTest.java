package edu.uepb.web.biblioteca.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.exception.ItemExistException;
import edu.uepb.web.biblioteca.model.Item;
import edu.uepb.web.biblioteca.model.Livro;
import edu.uepb.web.biblioteca.dao.Conexao;
import edu.uepb.web.biblioteca.dao.LivroDAO;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class LivroDAOTest {
	Item livro;
	LivroDAO manager;
	Connection conn;

	@Before
	public void setUp() throws Exception {
		manager = new LivroDAO();
		conn = new Conexao().getConexao();

		livro = new Livro("1234-K", "Harry Potter", "J.K.", "NoS", "2004", "2ª", 928, "Ficcao", "NBA");
	}

	@Test
	public void inserir() throws DAOException {
		int id = manager.inserir(livro);
		if (id < 0) {
			Assert.fail();
		}
	}

	@Test
	public void get() throws DAOException {
		Item livro1 = manager.get(1);
		assertEquals(livro1, livro);
	}

	@Test
	public void getList() throws DAOException {
		List<Item> listaLivro = manager.getLista();
		assertNotEquals(listaLivro.size(), 0);
	}

	@Test
	public void remover() throws DAOException {
		manager.remover(manager.get(1));
		assertEquals(manager.get(1), null);
	}

	@Test
	public void atualizar() throws DAOException {
		Livro livro2 = manager.get(2);
		livro2.setAutor("Shakes");
		manager.atualizar(livro2);
		assertEquals(manager.get(2).getAutor(), "Shakes");
	}

	@Test
	public void isItemExiste() throws ItemExistException, DAOException {
		assertTrue(manager.isItemExiste(livro));
	}

}
