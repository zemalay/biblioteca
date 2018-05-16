package edu.uepb.web.biblioteca.business;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.dao.AlunoDAOImpl;
import edu.uepb.web.biblioteca.dao.EmprestimoDAOImpl;
import edu.uepb.web.biblioteca.dao.ItemDAOImpl;
import edu.uepb.web.biblioteca.dao.ReservaDAOImpl;
import edu.uepb.web.biblioteca.enums.TipoFuncionario;
import edu.uepb.web.biblioteca.enums.TipoNivel;
import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.exception.EmprestimoException;
import edu.uepb.web.biblioteca.exception.ExistException;
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Emprestimo;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.model.Item;
import edu.uepb.web.biblioteca.model.Reserva;
import edu.uepb.web.biblioteca.utils.BibliotecaDateTime;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class AlunoBusiness {
	private static Logger logger = Logger.getLogger(AlunoBusiness.class);
	private AlunoDAOImpl alunoDAO;
	private EmprestimoDAOImpl emprestimoDAO;
	private ReservaDAOImpl reservaDAO;
	private ItemDAOImpl itemDAO;

	/**
	 * Autenticar aluno
	 * 
	 * @param matricula
	 * @param senha
	 * @return Aluno
	 * @throws AutenticacaoException
	 * @throws DAOException
	 */
	public Aluno autenticar(String matricula, String senha) throws AutenticacaoException, DAOException {
		alunoDAO = new AlunoDAOImpl();
		return alunoDAO.login(matricula, senha);
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
	 * Remover aluno. S� o funcion�rio do tipo administrador poder� remover.
	 * 
	 * @param funcionario
	 * @param aluno
	 * @return true se foi removido
	 * @throws DAOException
	 * @throws AutenticacaoException
	 */
	public boolean removerAluno(Funcionario funcionario, Aluno aluno) throws DAOException, AutenticacaoException {
		logger.info("Executa o metodo 'removerAluno' do alunoBusiness: " + funcionario + " e aluno: " + aluno);
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
		logger.info("Executa o metodo 'atualizarAluno' alunoBusiness: " + funcionario + " e aluno: " + aluno);

		alunoDAO = new AlunoDAOImpl();
		alunoDAO.atualizar(aluno);
		logger.info("O Aluno atualizado com sucesso: " + aluno);
		return true;
	}

	
}
