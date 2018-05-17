package edu.uepb.web.biblioteca.dao;

import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.model.Universidade;
import edu.uepb.web.biblioteca.utils.BibliotecaDateTime;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class UniversidadeDAOTest {
	Universidade uni;
	UniversidadeDAOImpl uniDAO;

	@Before
	public void setUp() throws Exception {
		uniDAO = new UniversidadeDAOImpl();
		uni = new Universidade();
		uni.setNome("UEPB");
		uni.setEndereco("campina");
		uni.setPeriodo("1");
		uni.setInicioPeriodo(BibliotecaDateTime.getDataCadastrado());
		uni.setFimPeriodo("22/10/2018");
	}

	@Test
	public void inserir() {

		uniDAO.inserir(uni);
	}

	@Test
	public void atualizar() {
		Universidade u = uniDAO.getById(1);
		u.setFimPeriodo("22/12/2018");
		uniDAO.atualizar(u);

	}

}
