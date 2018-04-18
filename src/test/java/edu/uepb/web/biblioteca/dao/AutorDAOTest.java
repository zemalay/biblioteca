package edu.uepb.web.biblioteca.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.dao.AnaisCongressoDAO;
import edu.uepb.web.biblioteca.dao.AutorDAO;
import edu.uepb.web.biblioteca.dao.Conexao;
import edu.uepb.web.biblioteca.enums.TipoAnais;
import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.model.AnaisCongresso;
import edu.uepb.web.biblioteca.model.Autor;

/**
 * A classe de teste unitario para AutorDAO, testar os metodos que foram
 * implementados, e ver se esta (salvando atualizando removendo pegando) os
 * dados do BD
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class AutorDAOTest {
	Autor autor;
	AutorDAO manager;
	Connection conn;
	AnaisCongressoDAO dao;
	AnaisCongresso artigo;

	@Before
	public void setUp() throws Exception {
		manager = new AutorDAO();
		conn = new Conexao().getConexao();

		dao = new AnaisCongressoDAO();
		artigo = new AnaisCongresso("Artigo1", TipoAnais.ARTIGO, "ENEC", "2009", "UEPB");

	}

	@Test
	public void inserir() throws DAOException {

		artigo.setId(dao.inserir(artigo));
		autor = new Autor("Lucca", artigo);
		int id = manager.inserir(autor);

		if (id < 0) {
			Assert.fail();
		}
	}

	@Test
	public void get() throws DAOException {
		
		assertNotEquals(manager.get(3), null);
	}
	
	@Test
	public void remover() throws DAOException {
		manager.remover(manager.get(1));
		
		assertEquals(manager.get(1), null);
	}
	
	@Test
	public void atualizar() throws DAOException {
		autor = manager.get(3);
		
		autor.setNome("Zeze");
		manager.atualizar(autor);
		assertEquals(manager.get(3), autor);
	}
	
	@Test
	public void getList() throws DAOException{
		List<Autor> listaAutor =  manager.getLista(1);
		
		assertEquals(listaAutor.size(), 1);
	}

}
