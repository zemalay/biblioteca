package edu.uepb.web.biblioteca.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.dao.Conexao;
import edu.uepb.web.biblioteca.dao.CursoDAOImpl;
import edu.uepb.web.biblioteca.enums.TipoNivel;
import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.model.Curso;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class CursoDAOTest {
	Curso energia;
	Curso administracao;
	Curso letras;
	Curso direito;
	CursoDAOImpl manager;
	List<Curso> listaCurso;
	Connection conn;

	@Before
	public void setUp() throws Exception {
		manager = new CursoDAOImpl();
		conn = new Conexao().getConexao();
		energia = new Curso("Engenharia de Energia", TipoNivel.GRADUACAO, "Tecnologia");
		administracao = new Curso("administracao", TipoNivel.GRADUACAO, "Humanas");
		letras = new Curso("Letras", TipoNivel.GRADUACAO, "Linguistica");
		direito = new Curso("Direito", TipoNivel.GRADUACAO, "Juridica");
	}

	@Test
	public void inserir() throws DAOException {
		int id = manager.inserir(energia);
		if (id < 0) {
			Assert.fail();
		}
	}

	@Test
	public void get() throws DAOException {
		int id = manager.inserir(administracao);
		assertNotEquals(null, manager.get(id));
	}

	@Test
	public void getLista() throws DAOException {
		listaCurso = manager.getLista();

		if (listaCurso.size() < 0) {
			Assert.fail();
		}

	}

	@Test
	public void remover() throws DAOException {
		letras.setId(manager.inserir(letras));
		manager.remover(letras);

		assertEquals(null, manager.get(letras.getId()));
	}

	@Test
	public void atualizar() throws DAOException {
		int id = manager.inserir(direito);
		direito.setId(id);
		direito.setNivel(TipoNivel.ESPECIALIZACAO);
		manager.atualizar(direito);

		assertEquals(TipoNivel.ESPECIALIZACAO, manager.get(id).getNivel());
	}

	@Test
	public void isExiste() throws DAOException {

		Curso fisica = new Curso();
		fisica.setNome("Fisica");
		fisica.setNivel(TipoNivel.GRADUACAO);
		fisica.setArea("Exata");

		assertEquals(false, manager.isExiste(fisica));
	}

}
