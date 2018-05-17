package edu.uepb.web.biblioteca.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import edu.uepb.web.biblioteca.dao.CursoDAOImpl;
import edu.uepb.web.biblioteca.dao.FuncionarioDAOImpl;
import edu.uepb.web.biblioteca.enums.TipoFuncionario;
import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.exception.ExistException;
import edu.uepb.web.biblioteca.model.Curso;
import edu.uepb.web.biblioteca.model.Funcionario;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Service
public class FuncionarioService {
	private static Logger logger = Logger.getLogger(FuncionarioService.class);
	private FuncionarioDAOImpl funcionarioDAO;
	private CursoDAOImpl cursoDAO;

	/**
	 * Autenticar o funcionario
	 * 
	 * @param usuario
	 * @param senha
	 * @return Funcionario
	 * @throws AutenticacaoException
	 * @
	 */
	public Funcionario autenticar(String usuario, String senha) throws AutenticacaoException {
		funcionarioDAO = new FuncionarioDAOImpl();
		return funcionarioDAO.login(usuario, senha);
	}

	/**
	 * Cadastra o funcionario
	 * 
	 * @param funcionario
	 * @return id do funcionario cadastrado
	 * @
	 */
	public int cadastrarFuncionario(Funcionario funcionario)  {
		logger.info("Executa o metodo 'cadastrarFuncionario' com param aluno: " + funcionario);
		funcionarioDAO = new FuncionarioDAOImpl();
		return funcionarioDAO.inserir(funcionario);
	}

	/**
	 * Atualizar o funcionario
	 * boolean
	 * @param funcionario
	 * @return
	 * @
	 */
	public boolean atualizarFuncionario(Funcionario funcionario)  {
		logger.info("Executa o metodo 'atualizarAluno' funcionarioBusiness: " + funcionario);

		funcionarioDAO = new FuncionarioDAOImpl();
		funcionarioDAO.atualizar(funcionario);
		return true;
	}

	/**
	 * Cadastra o curso. So o admin que pode realizar essa funcionalidade, retornar
	 * o id salvo do banco
	 * 
	 * @param funcionariro
	 * @param curso
	 * @return
	 * @throws AutenticacaoException
	 * @
	 * @throws ExistException
	 */
	public int cadastraCurso(Funcionario funcionario, Curso curso)
			throws AutenticacaoException,ExistException {
		logger.info("Executa o metodo 'cadastraCurso' com param fucionario : " + funcionario + " e curso : " + curso);
		cursoDAO = new CursoDAOImpl();
		if (cursoDAO.isExiste(curso)) {
			throw new ExistException("O Curso ja existe");
		}
		logger.info("O curso salvo com sucesso: " + curso);
		return cursoDAO.inserir(curso);
	}

	/**
	 * Remova o curso. So o admin que pode realizar essa funcionalidade, retornar o
	 * id salvo do banco
	 * 
	 * @param funcionario
	 * @param curso
	 * @return
	 * @throws AutenticacaoException
	 * @
	 */
	public boolean removerCurso(Funcionario funcionario, Curso curso) throws AutenticacaoException {
		logger.info("Executa o metodo 'removerCurso' com param fucionario : " + funcionario + " e item : " + curso);
		if (!funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR)) {
			throw new AutenticacaoException("Este funcionario nao esta autorizado");
		} else {
			cursoDAO = new CursoDAOImpl();
			cursoDAO.remover(curso);
			logger.info("O curso removido com sucesso: " + curso);
			return true;
		}
	}
}
