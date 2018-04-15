package edu.uepb.web.biblioteca.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.exception.ItemExistException;

import edu.uepb.web.biblioteca.dao.Conexao;
import edu.uepb.web.biblioteca.dao.JornalDAO;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class JornalDAOTest {
	Item jornal;
	JornalDAO manager;
	Connection conn;

	@Before
	public void setUp() throws Exception {
		conn = new Conexao().getConexao();
		jornal = new Jornal("Post FREE", "02/09/2013", "1º");
		manager = new JornalDAO();
	}

	@Test
	public void inserir() throws DAOException {
		int id = manager.inserir(jornal);

		if (id < 0) {
			Assert.fail();
		}
		((Jornal) jornal).setId(id);
		assertEquals(manager.get(id), jornal);
	}

	@Test
	public void get() throws DAOException {
		assertNotEquals(manager.get(1), null);
	}

	@Test
	public void remover() throws DAOException {
		((Jornal) jornal).setId(3);
		manager.remover(jornal);
		assertEquals(manager.get(3), null);
	}

	@Test
	public void atualizar() throws DAOException {
		Jornal jornalUpdate = manager.get(1);
		jornalUpdate.setEdicao("2ª");
		manager.atualizar(jornalUpdate);
		assertEquals(manager.get(1).getEdicao(), "2ª");
	}

	@Test
	public void isItemExiste() throws ItemExistException, DAOException {
		assertFalse(manager.isItemExiste(jornal));
	}

}
