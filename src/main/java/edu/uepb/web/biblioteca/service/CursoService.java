package edu.uepb.web.biblioteca.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import edu.uepb.web.biblioteca.dao.CursoDAOImpl;
import edu.uepb.web.biblioteca.enums.TipoFuncionario;
import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.exception.ExistException;
import edu.uepb.web.biblioteca.model.Curso;
import edu.uepb.web.biblioteca.model.Funcionario;

/**
 * Classe Service do Curso
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Service
public class CursoService {
	private static Logger logger = Logger.getLogger(CursoService.class);
	private CursoDAOImpl cursoDAO;

	/**
	 * Cadastra o curso. So o admin que pode realizar essa funcionalidade, retornar
	 * o id salvo do banco
	 * 
	 * @param funcionariro
	 * @param curso
	 * @return
	 * @throws AutenticacaoException
	 * @throws ExistException
	 */
	public int cadastraCurso(Funcionario funcionario, Curso curso) throws AutenticacaoException, ExistException {
		logger.info("Executa o metodo 'cadastraCurso' do cursoService com param: " + funcionario + " e : " + curso);
		cursoDAO = new CursoDAOImpl();
		if (cursoDAO.isExiste(curso)) {
			logger.warn("O curso ja existe, curso: " + curso);
			throw new ExistException("O Curso ja existe");
		}
		logger.info("O curso salvo com sucesso: " + curso);
		return cursoDAO.inserir(curso);
	}

	/**
	 * Pegar todos os curso cadastrados no sistema
	 * 
	 * @return List
	 */
	public List<Curso> getListaCurso() {
		logger.info("Executa o metodo 'getListaCurso' do cursoService");
		cursoDAO = new CursoDAOImpl();
		return cursoDAO.getLista();
	}

	/**
	 * Remova o curso. So o admin que pode realizar essa funcionalidade, retornar o
	 * id salvo do banco
	 * 
	 * @param funcionario
	 * @param curso
	 * @return
	 * @throws AutenticacaoException
	 */
	public boolean removerCurso(Funcionario funcionario, Curso curso) throws AutenticacaoException {
		logger.info("Executa o metodo 'removerCurso' do cursoService com param: " + funcionario + " e : " + curso);
		if (!funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR)) {
			logger.error("Funcionario nao autorizado, idFuncionario: " + funcionario.getId());
			throw new AutenticacaoException("Este funcionario nao esta autorizado");
		} else {
			cursoDAO = new CursoDAOImpl();
			cursoDAO.remover(curso);
			logger.info("O curso removido com sucesso: " + curso);
			return true;
		}
	}

	/**
	 * Pegar o curso pelo seu ID
	 * 
	 * @param idCurso
	 * @return Curso
	 */
	public Curso getCursoById(int idCurso) {
		logger.info("Executa o metodo 'getCursoById' do itemService, idItem: " + idCurso);
		cursoDAO = new CursoDAOImpl();
		return cursoDAO.getById(idCurso);
	}

}
