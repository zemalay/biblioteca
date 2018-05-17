package edu.uepb.web.biblioteca.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.enums.TipoFuncionario;
import edu.uepb.web.biblioteca.model.Curso;
import edu.uepb.web.biblioteca.model.Funcionario;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class FuncionarioDAOTest {
	Funcionario admin;
	Funcionario operador1;
	Funcionario operador2;
	Funcionario operador3;
	Curso curso;
	FuncionarioDAOImpl manager;
	Connection conn;
	List<Funcionario> listaFuncionario;

	@Before
	public void setUp() throws Exception {
		conn = new Conexao().getConexao();
		manager = new FuncionarioDAOImpl();
		admin = new Funcionario();
		admin.setNome("Naruto");
		admin.setTipoFunc(TipoFuncionario.ADMINISTRADOR);
		admin.setCpf("2345");
		admin.setRg("h232");
		admin.setEmail("@email.com");
		admin.setEndereco("deded");
		admin.setNaturalidade("Hekrne");
		admin.setTelefone("12345677");
		admin.setUsuario("nar");
		admin.setSenha("qwerty");

		operador1 = new Funcionario();
		operador1.setNome("Kajduse");
		operador1.setTipoFunc(TipoFuncionario.OPERADOR);
		operador1.setCpf("565");
		operador1.setRg("jsd3");
		operador1.setEmail("@email.com");
		operador1.setEndereco("sdg");
		operador1.setNaturalidade("sdsb");
		operador1.setTelefone("9073");
		operador1.setUsuario("ksjs");
		operador1.setSenha("mkfdhe");

		operador2 = new Funcionario();
		operador2.setNome("Juans");
		operador2.setTipoFunc(TipoFuncionario.OPERADOR);
		operador2.setCpf("434");
		operador2.setRg("skdni8");
		operador2.setEmail("@email.com");
		operador2.setEndereco("sdg");
		operador2.setNaturalidade("sdsb");
		operador2.setTelefone("9073");
		operador2.setUsuario("ksjs");
		operador2.setSenha("mkfdhe");

		operador3 = new Funcionario();
		operador3.setNome("Ghua");
		operador3.setTipoFunc(TipoFuncionario.OPERADOR);
		operador3.setCpf("323214");
		operador3.setRg("sdj9");
		operador3.setEmail("@email.com");
		operador3.setEndereco("sdg");
		operador3.setNaturalidade("sdsb");
		operador3.setTelefone("9073");
		operador3.setUsuario("ksjs");
		operador3.setSenha("mkfdhe");
	}

	@Test
	public void inserirFuncionario() {
		int id_fun = manager.inserir(admin);
		if (id_fun < 0) {
			Assert.fail();
		}
	}

	@Test
	public void get() {
		int id = manager.inserir(operador1);
		assertNotEquals(null, manager.getById(id));
	}

	@Test
	public void getLista() {
		listaFuncionario = manager.getLista();
		if (listaFuncionario.size() <= 0) {
			Assert.fail();
		}
	}

	@Test
	public void remover() {
		int id = manager.inserir(operador2);
		operador2.setId(id);
		manager.remover(operador2);
		assertEquals(null, manager.getById(id));
	}

	@Test
	public void atualizar() {
		int id = manager.inserir(operador3);
		operador3.setId(id);
		operador3.setEmail("jj@email.com");

		manager.atualizar(operador3);
		assertEquals("jj@email.com", manager.getById(operador3.getId()).getEmail());
	}

	@Test
	public void isExiste() {
		assertEquals(true, manager.isExiste(admin));
		Funcionario admin1 = new Funcionario();
		admin1.setNome("Hjas");
		admin1.setTipoFunc(TipoFuncionario.ADMINISTRADOR);
		admin1.setCpf("324");
		admin1.setRg("8374");
		admin1.setEmail("@email.com");
		admin1.setEndereco("deded");
		admin1.setNaturalidade("Hekrne");
		admin1.setTelefone("12345677");
		admin1.setUsuario("nar");
		admin1.setSenha("qwerty");

		assertEquals(false, manager.isExiste(admin1));

	}
}
