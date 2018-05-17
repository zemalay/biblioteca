package edu.uepb.web.biblioteca.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.enums.TipoAnais;
import edu.uepb.web.biblioteca.enums.TipoItem;
import edu.uepb.web.biblioteca.enums.TipoMidia;
import edu.uepb.web.biblioteca.exception.ExistException;
import edu.uepb.web.biblioteca.model.Item;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class ItemDAOTest {
	Item midia;
	Item jornal;
	Item livro;
	Item revista;
	Item anais;
	ItemDAOImpl manager;
	List<Item> listaItem;

	@Before
	public void setUp() throws Exception {
		manager = new ItemDAOImpl();
		
		listaItem = new ArrayList<Item>();

		midia = new Item();
		midia.setTipoItem(TipoItem.MIDIA);
		midia.setTitulo("Titulo Midia123");
		midia.setTipoMidia(TipoMidia.CD);
		midia.setDataGravacao("12/02/2009");
		midia.setQuantidade(10);

		jornal = new Item();
		jornal.setTipoItem(TipoItem.JORNAL);
		jornal.setTitulo("Titulo Jornal");
		jornal.setData("02/04/2003");
		jornal.setQuantidade(2);

		livro = new Item();
		livro.setTipoItem(TipoItem.LIVRO);
		livro.setIsbn("234-3232");
		livro.setTitulo("Titulo Livro");
		livro.setAutor("AA");
		livro.setEdicao("2");
		livro.setEditora("NNN");
		livro.setNumeroPagina(300);
		livro.setArea("Saude");
		livro.setTema("HHH");
		livro.setQuantidade(4);

		revista = new Item();
		revista.setTipoItem(TipoItem.REVISTA);
		revista.setTitulo("titulo revista");
		revista.setEditora("III");
		revista.setEdicao("4");
		revista.setData("03/02/2013");
		revista.setNumeroPagina(23);
		revista.setQuantidade(6);
		
		anais = new Item();
		anais.setTipoItem(TipoItem.ANAIS);
		anais.setTitulo("Titulo Anais");
		anais.setTipoAnais(TipoAnais.RESUMO);
		anais.setCongresso("ASE");
		anais.setAnoPublicacao("2009");
		anais.setLocal("LOSK");
		anais.setAutor("SASA");
		anais.setQuantidade(4);

	}

	@Test
	public void inserir()  {
		assertNotEquals(manager.inserir(midia), -1);
	}

	@Test
	public void get()  {
		Item jornal1 = new Item();
		jornal1 = new Item();
		jornal1.setTipoItem(TipoItem.JORNAL);
		jornal1.setTitulo("Titulo Jornal1");
		jornal1.setData("01/02/2013");
		jornal.setQuantidade(2);
		
		int id = manager.inserir(jornal1);
		
		assertNotEquals(null, manager.getById(id));
	}

	@Test
	public void getList()  {
		listaItem = manager.getLista();
		 if (listaItem.size() <= 0) {
			Assert.fail();
         }
	}

	@Test
	public void atualizar()  {
		revista.setId(manager.inserir(revista));
		revista.setNumeroPagina(27);

		manager.atualizar(revista);
		assertEquals(27, manager.getById(revista.getId()).getNumeroPagina());
	}
	
	@Test
	public void remover()  {
		int id = manager.inserir(anais);
		anais.setId(id);
		manager.remover(anais);
		assertEquals(null, manager.getById(id));
	}
	
	@Test
	public void isExiste() throws ExistException {
		Item item = new Item();
		item.setTipoItem(TipoItem.JORNAL);
		item.setTitulo("Titulo Item");
		item.setData("29/02/2020");
		
		assertEquals(false, manager.isExiste(item));
	}

}
