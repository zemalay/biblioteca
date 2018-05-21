package edu.uepb.web.biblioteca.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import edu.uepb.web.biblioteca.dao.AlunoDAOImpl;
import edu.uepb.web.biblioteca.dao.DividaDAOImpl;
import edu.uepb.web.biblioteca.dao.EmprestimoDAOImpl;
import edu.uepb.web.biblioteca.dao.ReservaDAOImpl;
import edu.uepb.web.biblioteca.dao.UniversidadeDAOImpl;
import edu.uepb.web.biblioteca.enums.TipoFuncionario;
import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.exception.EmprestimoException;
import edu.uepb.web.biblioteca.exception.ExistException;
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.model.Universidade;

/**
 * Classe Service do Aluno
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Service
public class AlunoService {
	private static Logger logger = Logger.getLogger(AlunoService.class);
	private AlunoDAOImpl alunoDAO;
	private DividaDAOImpl dividaDAO;
	private EmprestimoDAOImpl emprestimoDAO;
	private ReservaDAOImpl reservaDAO;
	private UniversidadeDAOImpl universidadeDAO;

	/**
	 * Autenticar aluno
	 * 
	 * @param matricula
	 * @param senha
	 * @return Aluno
	 * @throws AutenticacaoException
	 */
	public Aluno autenticar(String matricula, String senha) throws AutenticacaoException {
		logger.info("Executa o metodo 'autenticar' do alunoService");
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
	 * @throws ExistException
	 */
	public int cadastrarAluno(Funcionario funcionario, Aluno aluno) throws AutenticacaoException, ExistException {
		logger.info("Executa o metodo 'cadastrarAluno' do alunoService com param: " + funcionario + " e : " + aluno);

		alunoDAO = new AlunoDAOImpl();
		universidadeDAO = new UniversidadeDAOImpl();
		Universidade universidade = universidadeDAO.get();
		aluno.setPeriodoIngresso(universidade.getPeriodo());
		aluno.setMatricula(this.gerarMatricula(aluno));
		if (alunoDAO.isExiste(aluno)) {
			logger.error("Ja existe aluno com esta matricula, matricula: " + aluno.getMatricula());
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
	 */
	public String gerarMatricula(Aluno aluno) {
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

		logger.info(nivelAbreviacao + cursoAbreviacao.toUpperCase() + "-" + anoAbreviacao + aluno.getPeriodoIngresso()
				+ codigo);

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
	 * @throws AutenticacaoException
	 * @throws EmprestimoException
	 */
	public void removerAluno(Funcionario funcionario, Aluno aluno) throws AutenticacaoException, EmprestimoException {
		logger.info("Executa o metodo 'removerAluno' do alunoBusiness: " + funcionario + " e aluno: " + aluno);
		if (!funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR)) {
			logger.error("Funcionario nao autorizado, idFuncionario: " + funcionario.getId());
			throw new AutenticacaoException("Este funcionario nao esta autorizado");
		} else {
			alunoDAO = new AlunoDAOImpl();
			dividaDAO = new DividaDAOImpl();
			reservaDAO = new ReservaDAOImpl();

			if (dividaDAO.isAlunoTemDivida(aluno.getId())) {
				logger.error("O aluno ainda tem divida " + aluno.getId());
				throw new EmprestimoException("Nao pode remover o aluno, ele ainda tem divida");
			}
			emprestimoDAO = new EmprestimoDAOImpl();

			if (emprestimoDAO.getEmprestimoAtivoDoAluno(aluno.getId()) != null) {
				logger.error("O aluno ainda tem item emprestado " + aluno.getId());
				throw new EmprestimoException("Nao pode remover o aluno, ele tem emprestimo registrado");
			}

			// remover as suas dividas registradas
			dividaDAO.removerDividasAluno(aluno.getId());

			// remover os seus remprestimos registrados
			emprestimoDAO.removerEmprestimoAluno(aluno.getId());

			// remover as suas reservas registradas
			reservaDAO.removerReservasAluno(aluno.getId());

			alunoDAO.remover(aluno);
			logger.info("O Aluno removido com sucesso: " + aluno);
		}
	}

	/**
	 * Atualizar dados do aluno. Qualquer funcion�rio poder� atualizar.
	 * 
	 * @param funcionario
	 * @param aluno
	 * @return boolean
	 */
	public boolean atualizarAluno(Aluno aluno) {
		logger.info("Executa o metodo 'atualizarAluno' alunoBusiness: " + aluno);

		alunoDAO = new AlunoDAOImpl();
		alunoDAO.atualizar(aluno);
		logger.info("O Aluno atualizado com sucesso: " + aluno);
		return true;
	}

	/**
	 * Pegar os alunos do sistema
	 * 
	 * @return List<Aluno>
	 */
	public List<Aluno> getListaAluno() {
		logger.info("Executa o metodo 'getListaAluno' do AlunoService");
		alunoDAO = new AlunoDAOImpl();
		return alunoDAO.getLista();
	}

	/**
	 * Pegar o Aluno pelo seu ID
	 * 
	 * @param idAluno
	 * @return Aluno
	 */
	public Aluno getAlunoById(int idAluno) {
		logger.info("Executa o metodo 'getAlunoById' do AlunoService :" + idAluno);
		alunoDAO = new AlunoDAOImpl();
		return alunoDAO.getById(idAluno);
	}

}
