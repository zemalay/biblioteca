package edu.uepb.web.biblioteca.business;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.dao.AlunoDAOImpl;
import edu.uepb.web.biblioteca.dao.CursoDAOImpl;
import edu.uepb.web.biblioteca.dao.EmprestimoDAOImpl;
import edu.uepb.web.biblioteca.dao.FuncionarioDAOImpl;
import edu.uepb.web.biblioteca.dao.ItemDAOImpl;
import edu.uepb.web.biblioteca.enums.TipoFuncionario;
import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.exception.EmprestimoException;
import edu.uepb.web.biblioteca.exception.ExistException;
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Curso;
import edu.uepb.web.biblioteca.model.Emprestimo;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.model.Item;
import edu.uepb.web.biblioteca.utils.BibliotecaDateTime;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class FuncionarioBusiness {
	private static Logger logger = Logger.getLogger(FuncionarioBusiness.class);
	private ItemDAOImpl itemDAO;
	private FuncionarioDAOImpl funcionarioDAO;
	private AlunoDAOImpl alunoDAO;
	private CursoDAOImpl cursoDAO;
	private EmprestimoDAOImpl emprestimoDAO;

	/**
	 * Cadastra os itens de acordo com os seus tipos. So o admin que pode realizar
	 * essa funcionalidade, retornar o id salvo do banco se nao retornar id_fake =
	 * -1
	 * 
	 * @param funcionario
	 * @param item
	 * @return int
	 * @throws AutenticacaoException
	 * @throws DAOException
	 * @throws ExistException
	 */
	public int cadastraItem(Funcionario funcionario, Item item)
			throws AutenticacaoException, DAOException, ExistException {
		logger.info("Executa o metodo 'cadastraItem' com param fucionario : " + funcionario + " e item : " + item);
		if (!funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR)) {
			throw new AutenticacaoException("Este funcionario nao esta autorizado");
		} else {
			itemDAO = new ItemDAOImpl();
			if (itemDAO.isExiste(item)) {
				throw new ExistException("Item ja existe");
			} else {
				logger.info("O item salvo com sucesso: " + item);
				return itemDAO.inserir(item);
			}
		}
	}

	/**
	 * Atualiza os itens de acordo com os seus tipos. So o admin que pode realizar
	 * essa funcionalidade, retornar true se for alvo no banco ao contrario false
	 * 
	 * @param funcionario
	 * @param item
	 * @return boolean
	 * @throws AutenticacaoException
	 * @throws DAOException
	 * @throws ExistException
	 */
	public boolean atualizarItem(Funcionario funcionario, Item item)
			throws AutenticacaoException, DAOException, ExistException {
		logger.info("Executa o metodo 'atualizarItem' com param fucionario : " + funcionario + " e item : " + item);
		if (!funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR)) {
			throw new AutenticacaoException("Este funcionario nao esta autorizado");
		} else {
			itemDAO = new ItemDAOImpl();
			itemDAO.atualizar(item);
			logger.info("O item atualizado com sucesso: " + item);
			return true;
		}
	}

	/**
	 * Remova os itens de acordo com os seus tipos. So o admin que pode realizar
	 * essa funcionalidade, retornar true se for alvo no banco ao contrario false
	 * 
	 * @param funcionario
	 * @param item
	 * @return boolean
	 * @throws AutenticacaoException
	 * @throws DAOException
	 */
	public boolean removerItem(Funcionario funcionario, Item item) throws AutenticacaoException, DAOException {
		logger.info("Executa o metodo 'removerItem' com param fucionario : " + funcionario + " e item : " + item);
		if (!funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR)) {
			throw new AutenticacaoException("Este funcionario nao esta autorizado");
		} else {
			itemDAO = new ItemDAOImpl();
			itemDAO.remover(item);
			logger.info("O item removido com sucesso: " + item);
			return true;
		}
	}

	// region curso

	/**
	 * Cadastra o curso. So o admin que pode realizar essa funcionalidade, retornar
	 * o id salvo do banco se nao retornar id_fake = -1
	 * 
	 * @param funcionariro
	 * @param curso
	 * @return
	 * @throws AutenticacaoException
	 * @throws DAOException
	 * @throws ExistException
	 */
	public int cadastraCurso(Funcionario funcionario, Curso curso)
			throws AutenticacaoException, DAOException, ExistException {
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
	 * id salvo do banco se nao retornar id_fake = -1
	 * 
	 * @param funcionario
	 * @param curso
	 * @return
	 * @throws AutenticacaoException
	 * @throws DAOException
	 */
	public boolean removerCurso(Funcionario funcionario, Curso curso) throws AutenticacaoException, DAOException {
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

	/**
	 * Gera matricula para o aluno cadastrado. A matricula e unico para cada aluno.
	 * 
	 * @param aluno
	 * @return matricula gerado
	 * @throws DAOException
	 */
	public String gerarMatricula(Aluno aluno) throws DAOException {
		logger.info("Execucao metodo  'gerarMatricula'");
		String nivelAbreviacao = "", cursoAbreviacao, anoAbreviacao, firstAbreviacao, secondAbreviacao, curso, ano,
				codigo;

		curso = aluno.getCurso().getNome();
		ano = aluno.getAno();

		// Criar Abreviacao do nivel (e.g. Graduacao -> G)
		switch (aluno.getCurso().getNivel()) {
		case GRADUACAO:
			nivelAbreviacao = "G";
			break;
		case ESPECIALIZACAO:
			nivelAbreviacao = "E";
			break;
		case MESTRADO:
			nivelAbreviacao = "M";
			break;
		case DOUTORADO:
			nivelAbreviacao = "D";
			break;
		}

		/**
		 * Criar abreviaca para nome do curso (e.g. Odontologia -> OD Ciencia da
		 * Computacao -> CC)
		 */
		int index = curso.indexOf(' ');
		int lastIndex = curso.lastIndexOf(' ');

		if (index == -1) {
			cursoAbreviacao = curso.substring(0, 2);
		} else {
			firstAbreviacao = Character.toString(curso.substring(0, index).charAt(0));
			secondAbreviacao = Character.toString(curso.substring(lastIndex + 1, curso.length()).charAt(0));

			cursoAbreviacao = firstAbreviacao + secondAbreviacao;
		}

		// Criar abreviacao do ano de ingresso (e.g. 2010 -> 10)
		anoAbreviacao = ano.substring(2, ano.length());

		// Criar codigo (e.g. 1 -> 001)
		alunoDAO = new AlunoDAOImpl();
		codigo = String.format("%03d", alunoDAO.getUltimoId() + 1);
		return nivelAbreviacao + cursoAbreviacao.toUpperCase() + "-" + anoAbreviacao + aluno.getPeriodoIngresso()
				+ codigo;
	}

	/**
	 * 
	 * Cadastrar aluno. Qualquer funcion�rio poder� cadastrar o aluno.
	 * 
	 * @param funcionario
	 * @param aluno
	 * @return id do aluno cadastrado
	 * @throws AutenticacaoException
	 * @throws DAOException
	 * @throws ExistException
	 */
	public int cadastrarAluno(Funcionario funcionario, Aluno aluno)
			throws AutenticacaoException, DAOException, ExistException {
		logger.info("Executa o metodo 'cadastrarAluno' com param Funcionario : " + funcionario + " e aluno: " + aluno);

		alunoDAO = new AlunoDAOImpl();
		aluno.setMatricula(this.gerarMatricula(aluno));
		if (alunoDAO.isExiste(aluno)) {
			throw new ExistException("O aluno ja cadastrado no sistema");
		}
		logger.info("O Aluno cadastrado com sucesso: " + aluno);
		return alunoDAO.inserir(aluno);
	}

	/**
	 * 
	 * Remover aluno. S� o funcion�rio do tipo administrador poder� remover.
	 * 
	 * @param funcionario
	 * @param aluno
	 * @return true se foi removido
	 * @throws DAOException
	 * @throws AutenticacaoException
	 */
	public boolean removerAluno(Funcionario funcionario, Aluno aluno) throws DAOException, AutenticacaoException {
		logger.info("Executa o metodo 'removerAluno' do funcionarioBusiness: " + funcionario + " e aluno: " + aluno);
		if (!funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR)) {
			throw new AutenticacaoException("Este funcionario nao esta autorizado");
		} else {
			alunoDAO = new AlunoDAOImpl();
			alunoDAO.remover(aluno);
			logger.info("O Aluno removido com sucesso: " + aluno);
			return true;
		}
	}

	/**
	 * 
	 * Atualizar dados do aluno. Qualquer funcion�rio poder� atualizar.
	 * 
	 * @param funcionario
	 * @param aluno
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean atualizarAluno(Funcionario funcionario, Aluno aluno) throws DAOException {
		logger.info("Executa o metodo 'atualizarAluno' funcionarioBusiness: " + funcionario + " e aluno: " + aluno);

		alunoDAO = new AlunoDAOImpl();
		alunoDAO.atualizar(aluno);
		logger.info("O Aluno atualizado com sucesso: " + aluno);
		return true;
	}

	/**
	 * O funcionario cadastrar um emprestimo que foi pedido pelo aluno
	 * 
	 * @param idFuncionario
	 * @param idAluno
	 * @param idItem
	 * @return id do emprestimo cadastrado
	 * @throws DAOException
	 * @throws EmprestimoException
	 */
	public int cadastrarEmprestimo(int idFuncionario, int idAluno, int idItem)
			throws DAOException, EmprestimoException {
		itemDAO = new ItemDAOImpl();
		Item item = itemDAO.get(idItem);

		if (item.getQuantidade() < 1) {
			throw new EmprestimoException("O item esta faltando no estoque");
		}
		funcionarioDAO = new FuncionarioDAOImpl();
		alunoDAO = new AlunoDAOImpl();
		emprestimoDAO = new EmprestimoDAOImpl();
		Emprestimo emprestimo = new Emprestimo();

		Aluno aluno = alunoDAO.get(idAluno);

		emprestimo.setFuncionario(funcionarioDAO.get(idFuncionario));
		emprestimo.setAluno(aluno);
		emprestimo.setItem(item);
		emprestimo.setDataCadastrado(BibliotecaDateTime.getDataCadastrado());
		emprestimo.setDataDevolucao(BibliotecaDateTime.getDataDevolucao(aluno.getCurso().getNivel()));

		int idEmprestimo = emprestimoDAO.inserir(emprestimo);

		// Atualizar a quantidade do item
		item.setQuantidade(item.getQuantidade() - 1);
		itemDAO.atualizar(item);

		return idEmprestimo;
	}

}
