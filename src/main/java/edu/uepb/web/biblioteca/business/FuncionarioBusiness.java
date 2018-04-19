package edu.uepb.web.biblioteca.business;

import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.dao.*;
import edu.uepb.web.biblioteca.enums.TipoFuncionario;
import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.exception.ItemExistException;
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.AnaisCongresso;
import edu.uepb.web.biblioteca.model.Autor;
import edu.uepb.web.biblioteca.model.Curso;
import edu.uepb.web.biblioteca.model.Funcionario;
import edu.uepb.web.biblioteca.model.Item;
import edu.uepb.web.biblioteca.model.Jornal;
import edu.uepb.web.biblioteca.model.Livro;
import edu.uepb.web.biblioteca.model.Midia;
import edu.uepb.web.biblioteca.model.Revista;
import edu.uepb.web.biblioteca.model.TrabalhoConclusao;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class FuncionarioBusiness {
	private static Logger logger = Logger.getLogger(FuncionarioBusiness.class);
	private static final int ID_FAKE = -1;
	private AlunoDAO alunoDAO;
	private AnaisCongressoDAO anaisDAO;
	private AutorDAO autorDAO;
	private CursoDAO cursoDAO;
	private JornalDAO jornalDAO;
	private LivroDAO livroDAO;
	private MidiaDAO midiaDAO;
	private RevistaDAO revistaDAO;
	private TrabalhoConclusaoDAO trabalhoDAO;

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
	 * @throws ItemExistException
	 */
	public int cadastraItem(Funcionario funcionario, Item item)
			throws AutenticacaoException, DAOException, ItemExistException {
		logger.info("Executa o metodo 'cadastraItem' com param fucionario : " + funcionario + " e item : " + item);
		if (!funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR)) {
			throw new AutenticacaoException("Este funcionario nao esta autorizado");
		} else {

			// Cadastra Item de tipo Anais Congresso
			if (item instanceof AnaisCongresso) {
				anaisDAO = new AnaisCongressoDAO();
				if (!anaisDAO.isItemExiste(item)) {
					autorDAO = new AutorDAO();
					int id = anaisDAO.inserir(item);
					List<Autor> listaAutor = ((AnaisCongresso) item).getListaAutores();
					for (Autor autor : listaAutor) {
						autor.getAnaisCongresso().setId(id);
						autorDAO.inserir(autor);
					}
					return id;
				}
				return FuncionarioBusiness.ID_FAKE;

				// Cadastra Item de tipo Jornal
			} else if (item instanceof Jornal) {
				jornalDAO = new JornalDAO();
				if (!jornalDAO.isItemExiste(item)) {
					return jornalDAO.inserir(item);
				}
				return FuncionarioBusiness.ID_FAKE;

				// Cadastra Item de tipo Livro
			} else if (item instanceof Livro) {
				livroDAO = new LivroDAO();
				if (!livroDAO.isItemExiste(item)) {
					return livroDAO.inserir(item);
				}
				return FuncionarioBusiness.ID_FAKE;

				// Cadastra Item de tipo Midia
			} else if (item instanceof Midia) {
				midiaDAO = new MidiaDAO();
				if (!midiaDAO.isItemExiste(item)) {
					return midiaDAO.inserir(item);
				}
				return FuncionarioBusiness.ID_FAKE;

				// Cadastra Item de tipo Revista
			} else if (item instanceof Revista) {
				revistaDAO = new RevistaDAO();
				if (!revistaDAO.isItemExiste(item)) {
					return revistaDAO.inserir(item);
				}
				return FuncionarioBusiness.ID_FAKE;

				// Cadastra Item de tipo Trabalho Conclusao
			} else if (item instanceof TrabalhoConclusao) {
				if (!trabalhoDAO.isItemExiste(item)) {
					return trabalhoDAO.inserir(item);
				}
				return FuncionarioBusiness.ID_FAKE;
			}
			return FuncionarioBusiness.ID_FAKE;
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
	 */
	public boolean atualizarItem(Funcionario funcionario, Item item) throws AutenticacaoException, DAOException {
		logger.info("Executa o metodo 'atualizarItem' com param fucionario : " + funcionario + " e item : " + item);
		if (!funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR)) {
			throw new AutenticacaoException("Este funcionario nao esta autorizado");
		} else {
			if (item instanceof AnaisCongresso) {
				anaisDAO = new AnaisCongressoDAO();
				autorDAO = new AutorDAO();
				List<Autor> listaAutor = ((AnaisCongresso) item).getListaAutores();
				for (Autor autor : listaAutor) {
					autorDAO.atualizar(autor);
				}
				anaisDAO.atualizar(item);
				return true;
			} else if (item instanceof Jornal) {
				jornalDAO = new JornalDAO();
				jornalDAO.atualizar(item);
				return true;
			} else if (item instanceof Livro) {
				livroDAO = new LivroDAO();
				livroDAO.atualizar(item);
				return true;
			} else if (item instanceof Midia) {
				midiaDAO = new MidiaDAO();
				midiaDAO.atualizar(item);
				return true;
			} else if (item instanceof Revista) {
				revistaDAO = new RevistaDAO();
				revistaDAO.atualizar(item);
				return true;
			} else if (item instanceof TrabalhoConclusao) {
				trabalhoDAO = new TrabalhoConclusaoDAO();
				trabalhoDAO.atualizar(item);
				return true;
			}
		}
		return false;
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
			if (item instanceof AnaisCongresso) {
				anaisDAO = new AnaisCongressoDAO();
				anaisDAO.remover(item);
				return true;

			} else if (item instanceof Jornal) {
				jornalDAO = new JornalDAO();
				jornalDAO.remover(item);
				return true;

			} else if (item instanceof Livro) {
				livroDAO = new LivroDAO();
				livroDAO.remover(item);
				return true;

			} else if (item instanceof Midia) {
				midiaDAO = new MidiaDAO();
				midiaDAO.remover(item);
				return true;

			} else if (item instanceof Revista) {
				revistaDAO = new RevistaDAO();
				revistaDAO.remover(item);
				return true;

			} else if (item instanceof TrabalhoConclusao) {
				trabalhoDAO = new TrabalhoConclusaoDAO();
				trabalhoDAO.remover(item);
				return true;
			}
		}
		return false;
	}
	// endregion

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
	 */
	public int cadastraCurso(Funcionario funcionario, Curso curso) throws AutenticacaoException, DAOException {
		logger.info("Executa o metodo 'cadastraCurso' com param fucionario : " + funcionario + " e curso : " + curso);
		if (!funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR)) {
			throw new AutenticacaoException("Este funcionario nao esta autorizado");
		} else {
			cursoDAO = new CursoDAO();
			return cursoDAO.inserir(curso);
		}
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
			cursoDAO = new CursoDAO();
			cursoDAO.remover(curso);
			return true;
		}
	}

	// endregion

	/**
	 * Gera matricula para o aluno cadastrado. A matricula e unico para cada aluno.
	 * 
	 * @param aluno
	 * @return matricula gerado
	 * @throws DAOException
	 */
	public String gerarMatricula(Aluno aluno) throws DAOException {
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
		alunoDAO = new AlunoDAO();
		codigo = String.format("%03d", alunoDAO.getUltimoId() + 1);

		return nivelAbreviacao + cursoAbreviacao.toUpperCase() + "-" + anoAbreviacao + aluno.getPeriodoIngresso()
				+ codigo;
	}

	/**
	 * 
	 * Cadastrar aluno. Qualquer funcionário poderá cadastrar o aluno.
	 * 
	 * @param funcionario
	 * @param aluno
	 * @return
	 * @throws AutenticacaoException
	 * @throws DAOException
	 */
	public boolean cadastrarAluno(Funcionario funcionario, Aluno aluno) throws AutenticacaoException, DAOException {
		logger.info("Executa o metodo 'cadastrarAluno' com param Funcionario : " + funcionario + " e item: " + aluno);

		alunoDAO = new AlunoDAO();
		alunoDAO.inserir(aluno);

		return true;
	}
	
	/**
	 * 
	 * Remover aluno. Só o funcionário do tipo administrador poderá remover.
	 * 
	 * @param funcionario
	 * @param aluno
	 * @return
	 * @throws DAOException
	 * @throws AutenticacaoException
	 */

	public boolean removerAluno(Funcionario funcionario, Aluno aluno) throws DAOException, AutenticacaoException {
		logger.info("Executa o metodo 'removerAluno' com param Funcionario: " + funcionario + " e item: " + aluno);
		if (!funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR)) {
			throw new AutenticacaoException("Este funcionario nao esta autorizado");
		} else {
			alunoDAO = new AlunoDAO();
			alunoDAO.inserir(aluno);
		}
		return true;
	}
	
	/**
	 * 
	 * Atualizar dados do aluno. Qualquer funcionário poderá atualizar.
	 * 
	 * @param funcionario
	 * @param aluno
	 * @return
	 * @throws DAOException
	 * @throws AutenticacaoException
	 */

	public boolean atualizarAluno(Funcionario funcionario, Aluno aluno) throws DAOException, AutenticacaoException {
		logger.info("Executa o metodo 'atualizarAluno' com param Funcionario: " + funcionario + " e item: " + aluno);

		alunoDAO = new AlunoDAO();
		alunoDAO.inserir(aluno);
		
		return true;
	}
}
