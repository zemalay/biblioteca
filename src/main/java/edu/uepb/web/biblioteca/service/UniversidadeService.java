package edu.uepb.web.biblioteca.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import edu.uepb.web.biblioteca.dao.UniversidadeDAOImpl;
import edu.uepb.web.biblioteca.model.Universidade;

/**
 * Classe Service da Universidade
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Service
public class UniversidadeService {
	private static Logger logger = Logger.getLogger(UniversidadeService.class);
	private UniversidadeDAOImpl universidadeDAO;

	public int cadastrarUniversidade(Universidade universidade) {
		logger.info("Executa o metodo 'cadastrarUniversidade' do universidadeService com param: " + universidade);
		universidadeDAO = new UniversidadeDAOImpl();
		return universidadeDAO.inserir(universidade);
	}

	public void atualizarUniversidade(Universidade universidade) {
		logger.info("Executa o metodo 'atualizarUniversidade' do universidadeService com param: " + universidade);
		universidadeDAO = new UniversidadeDAOImpl();
		universidadeDAO.atualizar(universidade);
	}

	public Universidade getUniversidade() {
		universidadeDAO = new UniversidadeDAOImpl();
		return universidadeDAO.get();
	}
	
	public Universidade getUnivById(int id) {
		universidadeDAO = new UniversidadeDAOImpl();
		return universidadeDAO.getById(id);
	}
}
