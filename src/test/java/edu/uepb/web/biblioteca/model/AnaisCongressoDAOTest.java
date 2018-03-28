package edu.uepb.web.biblioteca.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.enums.TipoAnais;

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

//	@Test
//	public void inserir() {
//		int id = manager.inserir(poster);
//		if(id<0) {
//			Assert.fail();
//		}
//	}
	
//	@Test 
//	public void get() {
//		assertNotEquals(poster, manager.get(1));
//	}
	
//	@Test
//	public void remover() {
//		manager.remover(manager.get(3));
//		
//		assertEquals(manager.get(3), null);
//		
//	}
	
//	@Test
//	public void getLista() {
//		List<Item> listaAnais = manager.getLista();
//		
//		assertNotEquals(listaAnais.size(), 0);
//		
//		for (Item anaisCongresso : listaAnais) {
//			System.out.println(anaisCongresso.toString());
//		}
//	}
	
//	@Test
//	public void atualizar() {
//		AnaisCongresso anais =  manager.get(2);
//		anais.setLocal("UFRN");
//		manager.atualizar(anais);
//		System.out.println(manager.get(2));
//		assertNotEquals(manager.get(2).getLocal(), "UEPB");
//	}
	
//	@Test
//	public void isItemExiste() {
//		assertTrue(manager.isItemExiste(poster));
//	}
}

