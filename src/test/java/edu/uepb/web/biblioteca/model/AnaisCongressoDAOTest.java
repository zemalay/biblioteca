package edu.uepb.web.biblioteca.model;

import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.enums.TipoAnais;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class AnaisCongressoDAOTest {
	AnaisCongresso poster;
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
	
	@Test 
	public void get() {
		System.out.println(manager.get(1).toString());
//		assertNotEquals(poster, manager.get(1));
	}

}

