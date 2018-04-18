package edu.uepb.web.biblioteca.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.dao.AnaisCongressoDAO;
import edu.uepb.web.biblioteca.dao.Conexao;
import edu.uepb.web.biblioteca.enums.TipoAnais;
import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.exception.ItemExistException;
import edu.uepb.web.biblioteca.model.AnaisCongresso;
import edu.uepb.web.biblioteca.model.Item;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class AnaisCongressoDAOTest {
	Item poster;
	AnaisCongressoDAO manager;
	Connection conn;
	@Before
	public void setUp() throws Exception {
		manager = new AnaisCongressoDAO();
		conn = new Conexao().getConexao();
		poster = new AnaisCongresso("Poster1",TipoAnais.POSTER,"ENEC","2003","UEPB");
		
	}

	@Test
	public void inserir() throws DAOException {
		int id = manager.inserir(poster);
		if(id<0) {
			Assert.fail();
		}
	}
	
	@Test 
	public void get() throws DAOException {
		assertNotEquals(poster, manager.get(1));
	}
	
	@Test
	public void remover() throws DAOException {
		manager.remover(manager.get(3));
		
		assertEquals(manager.get(3), null);
		
	}
	
	@Test
	public void getLista() throws DAOException {
		List<Item> listaAnais = manager.getLista();
		
		assertNotEquals(listaAnais.size(), 0);
		
		for (Item anaisCongresso : listaAnais) {
			System.out.println(anaisCongresso.toString());
		}
	}
	
	@Test
	public void atualizar() throws DAOException {
		AnaisCongresso anais =  manager.get(2);
		anais.setLocal("UFRN");
		manager.atualizar(anais);
		System.out.println(manager.get(2));
		assertNotEquals(manager.get(2).getLocal(), "UEPB");
	}
	
	@Test
	public void isItemExiste() throws DAOException, ItemExistException {
		assertTrue(manager.isItemExiste(poster));
	}
}

