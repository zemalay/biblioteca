package edu.uepb.web.biblioteca.model;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.dao.Conexao;
import edu.uepb.web.biblioteca.dao.MidiaDAO;
import edu.uepb.web.biblioteca.enums.TipoMidia;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class MidiaDAOTest {
	Midia cd;
	MidiaDAO manager;
	Connection conn;

	@Before
	public void setUp() throws Exception {
		conn = new Conexao().getConexao();
		manager = new MidiaDAO();

		cd = new Midia("Titulo CD", TipoMidia.CD, "02/12/2018");
	}

	@Test
	public void inserir() {
		int id = manager.inserir(cd);

		if (id < 0) {
			Assert.fail();
		}
	}

	@Test
	public void get() {
		Midia cd1 = manager.get(2);
		cd.setId(2);
		assertEquals(cd1, cd);
	}
	
	@Test
	public void getLista() {
		List<Item> listaMidia = manager.getLista();
		assertNotEquals(listaMidia.size(), 0);
	}
	
	@Test
	public void remover() {
		manager.remover(manager.get(2));
		assertEquals(manager.get(2), null);

	}
	
	@Test
	public void atualizar() {
		Midia dvd = manager.get(3);
		dvd.setTipo(TipoMidia.DVD);
		manager.atualizar(dvd);
		assertEquals(manager.get(3).getTipo(), TipoMidia.DVD);
 }

	@Test
	public void isItemExiste() {
		assertTrue(manager.isItemExiste(cd));
	}

}