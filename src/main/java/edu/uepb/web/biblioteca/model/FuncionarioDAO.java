package edu.uepb.web.biblioteca.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.enums.TipoFuncionario;

/**
 * A classe para acessar os dados no banco associando ao objeto
 * {@link Funcionario}
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class FuncionarioDAO extends DAO<Funcionario> {
	private static final int id_fake = -1;
	private static Logger logger = Logger.getLogger(FuncionarioDAO.class);
	private AnaisCongressoDAO anaisDAO;
	private AutorDAO autorDAO;
	private CursoDAO cursoDAO;
	private JornalDAO jornalDAO;
	private LivroDAO livroDAO;
	private MidiaDAO midiaDAO;
	private RevistaDAO revistaDAO;
	private TrabalhoConclusaoDAO trabalhoDAO;

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#get(int)
	 */
	@Override
	public Funcionario get(int id) {
		logger.info("Executa o metodo 'get' com param id : " + id);
		super.connection = new Conexao().getConexao();

		String sql = "SELECT * FROM funcionario WHERE funcionario.id";

		Funcionario funcionario = null;

		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();

			if (resultSet.next()) {
				funcionario = new Funcionario();
				funcionario.setId(resultSet.getInt(1));
				funcionario.setNome(resultSet.getString(2));
				funcionario.setTipoFunc(resultSet.getString(3));
			}
			super.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionario;
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#getLista()
	 */
	@Override
	public List<Funcionario> getLista() {
		logger.info("Executa o metodo 'getLista'");
		super.connection = new Conexao().getConexao();

		String sql = "SELECT * FROM funcionario";

		List<Funcionario> listaFuncionario = new ArrayList<Funcionario>();
		try {
			super.statement = super.connection.prepareStatement(sql);
			super.resultSet = super.statement.executeQuery();

			while (resultSet.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setId(resultSet.getInt(1));
				funcionario.setNome(resultSet.getString(2));
				funcionario.setTipoFunc(resultSet.getString(3));

				listaFuncionario.add(funcionario);
			}

			super.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaFuncionario;
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#inserir(Object)
	 */
	@Override
	public int inserir(Funcionario obj) {
		logger.info("Executa o metodo 'inserir' com param objeto : " + obj);
		int id = FuncionarioDAO.id_fake;
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "INSERT INTO funcionario (nome, tipoFuncionario) VALUES (?,?)";

			try {
				super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				super.statement.setString(1, obj.getNome());
				super.statement.setString(2, obj.getTipoFunc().name());
				super.statement.execute();
				super.resultSet = super.statement.getGeneratedKeys();
				if (resultSet.next()) {
					id = super.resultSet.getInt(1);
				}
				super.closeConnections();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#remover(Object)
	 */
	@Override
	public void remover(Funcionario obj) {
		logger.info("Executa o metodo 'remover' com param objeto : " + obj);
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "DELETE FROM funcionario WHERE funcionario.id = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setInt(1, obj.getId());
				super.statement.execute();

				super.closeConnections();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#atualizar(Object)
	 */
	@Override
	public void atualizar(Funcionario obj) {
		logger.info("Executa o metodo 'atualizar' com param objeto : " + obj);
		if (obj != null) {
			super.connection = new Conexao().getConexao();
			String sql = "UPDATE funcionario SET nome = ?, tipoFuncionario = ? WHERE fucnionario.id = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getNome());
				super.statement.setString(2, obj.getTipoFunc().name());
				super.statement.setInt(3, obj.getId());
				super.statement.execute();

				super.closeConnections();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// region item

	/**
	 * Cadastra os itens de acordo com os seus tipos. So o admin que pode realizar
	 * essa funcionalidade, retornar o id salvo do banco se nao retornar id_fake =
	 * -1
	 * 
	 * @param funcionario
	 * @param item
	 * @return int
	 */
	public int cadastraItem(Funcionario funcionario, Item item) {
		logger.info("Executa o metodo 'cadastraItem' com param fucionario : " + funcionario + " e item : " + item);
		if (!funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR)) {
			return FuncionarioDAO.id_fake;
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
				return FuncionarioDAO.id_fake;

				// Cadastra Item de tipo Jornal
			} else if (item instanceof Jornal) {
				jornalDAO = new JornalDAO();
				if (!jornalDAO.isItemExiste(item)) {
					return jornalDAO.inserir(item);
				}
				return FuncionarioDAO.id_fake;

				// Cadastra Item de tipo Livro
			} else if (item instanceof Livro) {
				livroDAO = new LivroDAO();
				if (!livroDAO.isItemExiste(item)) {
					return livroDAO.inserir(item);
				}
				return FuncionarioDAO.id_fake;

				// Cadastra Item de tipo Midia
			} else if (item instanceof Midia) {
				midiaDAO = new MidiaDAO();
				if (!midiaDAO.isItemExiste(item)) {
					return midiaDAO.inserir(item);
				}
				return FuncionarioDAO.id_fake;

				// Cadastra Item de tipo Revista
			} else if (item instanceof Revista) {
				revistaDAO = new RevistaDAO();
				if (!revistaDAO.isItemExiste(item)) {
					return revistaDAO.inserir(item);
				}
				return FuncionarioDAO.id_fake;

				// Cadastra Item de tipo Trabalho Conclusao
			} else if (item instanceof TrabalhoConclusao) {
				if (!trabalhoDAO.isItemExiste(item)) {
					return trabalhoDAO.inserir(item);
				}
				return FuncionarioDAO.id_fake;
			}
			return FuncionarioDAO.id_fake;
		}
	}

	/**
	 * Atualiza os itens de acordo com os seus tipos. So o admin que pode realizar
	 * essa funcionalidade, retornar true se for alvo no banco ao contrario false
	 * 
	 * @param funcionario
	 * @param item
	 * @return boolean
	 */
	public boolean atualizarItem(Funcionario funcionario, Item item) {
		logger.info("Executa o metodo 'atualizarItem' com param fucionario : " + funcionario + " e item : " + item);
		if (!funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR)) {
			return false;
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
	 */
	public boolean removerItem(Funcionario funcionario, Item item) {
		logger.info("Executa o metodo 'removerItem' com param fucionario : " + funcionario + " e item : " + item);
		if (!funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR)) {
			return false;
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
	 */
	public int cadastraCurso(Funcionario funcionario, Curso curso) {
		logger.info("Executa o metodo 'cadastraCurso' com param fucionario : " + funcionario + " e curso : " + curso);
		if (!funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR)) {
			return FuncionarioDAO.id_fake;
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
	 */
	public boolean removerCurso(Funcionario funcionario, Curso curso) {
		logger.info("Executa o metodo 'removerCurso' com param fucionario : " + funcionario + " e item : " + curso);
		if (!funcionario.getTipoFunc().equals(TipoFuncionario.ADMINISTRADOR)) {
			return false;
		} else {
			cursoDAO = new CursoDAO();
			cursoDAO.remover(curso);
			return true;
		}
	}

	// endregion
}
