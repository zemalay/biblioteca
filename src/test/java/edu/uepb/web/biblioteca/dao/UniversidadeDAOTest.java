package edu.uepb.web.biblioteca.dao;

import org.junit.Before;
import org.junit.Test;

import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.model.Universidade;
import edu.uepb.web.biblioteca.utils.BibliotecaDateTime;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class UniversidadeDAOTest {
	Universidade uni;
	@Before
	public void setUp() throws Exception {
		Universidade uni = new Universidade();
		uni.setNome("UEPB");
		uni.setPeriodo("1");
		uni.setInicioPeriodo(BibliotecaDateTime.getDataCadastrado());
		uni.setFimPeriodo("22/10/2018");
	}

	@Test
	public void test() throws DAOException {
		UniversidadeDAOImpl uniDAO = new UniversidadeDAOImpl();
		uniDAO.inserir(uni);
	}

}
