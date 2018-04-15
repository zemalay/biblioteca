package edu.uepb.web.biblioteca.model;

import static org.junit.Assert.*;
import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.enums.TipoTrabalhoConclusao;
import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.exception.ItemExistException;

/**
 * A classe de teste unitario para TrabalhoConclusaoDAO, testar os metodos que
 * foram implementados, e ver se esta (salvando atualizando removendo pegando)
 * os dados do BD
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class TrabalhoConclusaoDAOTest {
	TrabalhoConclusao monografia;
	TrabalhoConclusaoDAO manager;
	Connection conn;

	@Before
	public void setUp() throws Exception {
		manager = new TrabalhoConclusaoDAO();
		conn = new Conexao().getConexao();

		monografia = new TrabalhoConclusao();
		monografia.setTitulo("TESTE para IOT");
		monografia.setTipo(TipoTrabalhoConclusao.MONOGRAFIA);
		monografia.setAnoDefesa("2012");
		monografia.setLocal("UEPB");
		monografia.setAutor("Joao");
		monografia.setOrientador("Lucas");

	}

	/**
	 * Caso de teste para inserir dados
	 * 
	 * @throws DAOException
	 */
	@Test
	public void inserir() throws DAOException {
		int id = manager.inserir(monografia);

		// O id indica o id do objeto que foi salvo no banco, se o id for zero quer
		// dizer os dados nao foram salvos
		if (id < 0) {
			Assert.fail();
		}
	}

	/**
	 * Caso de teste para pegar dados
	 * 
	 * @throws DAOException
	 */
	@Test
	public void get() throws DAOException {
		assertNotEquals(monografia, manager.get(27));

		monografia = manager.get(27);

		assertNotEquals("Joao", monografia.getAutor());
	}

	/**
	 * caso de teste para pegar todos os dados
	 * 
	 * @throws DAOException
	 */
	@Test
	public void getLista() throws DAOException {
		List<Item> listaTCC = manager.getLista();

		assertNotEquals(listaTCC.size(), 0);
		for (Item trabalhoConclusao : listaTCC) {
			System.out.println(trabalhoConclusao.toString());
		}

	}

	/**
	 * caso de teste para remove dados
	 * 
	 * @throws DAOException
	 */
	@Test
	public void remover() throws DAOException {
		manager.remover(manager.get(4));

		assertEquals(manager.get(4), null);

	}

	/**
	 * caso de teste para atualizar dados
	 * 
	 * @throws DAOException
	 */
	@Test
	public void atualizar() throws DAOException {
		TrabalhoConclusao dissertacao = new TrabalhoConclusao("Saude e Tecnologia", "Marcos",
				TipoTrabalhoConclusao.DISSERTACAO, "2001", "UFPB", "Luis");

		int id = manager.inserir(dissertacao);

		TrabalhoConclusao dissertacaoAtualizado = new TrabalhoConclusao("Saude e Tecnologia", "Marcos",
				TipoTrabalhoConclusao.DISSERTACAO, "2001", "UFRN", "Luis");

		dissertacaoAtualizado.setId(id);
		manager.atualizar(dissertacaoAtualizado);
		assertEquals(manager.get(id), dissertacaoAtualizado);
	}

	/**
	 * Caso de teste para verificar se o item ja existe no banco de dados
	 * 
	 * @throws DAOException
	 * @throws ItemExistException
	 */
	@Test
	public void isItemExiste() throws ItemExistException, DAOException {
		assertTrue(manager.isItemExiste(monografia));

		TrabalhoConclusao tese = new TrabalhoConclusao("Educacional", "Marcelo", TipoTrabalhoConclusao.TESE, "2017",
				"USP", "Edu");
		assertFalse(manager.isItemExiste(tese));
	}

}
