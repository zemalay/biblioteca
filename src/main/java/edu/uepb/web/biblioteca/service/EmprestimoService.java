package edu.uepb.web.biblioteca.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import edu.uepb.web.biblioteca.dao.AlunoDAOImpl;
import edu.uepb.web.biblioteca.dao.DividaDAOImpl;
import edu.uepb.web.biblioteca.dao.EmprestimoDAOImpl;
import edu.uepb.web.biblioteca.dao.FuncionarioDAOImpl;
import edu.uepb.web.biblioteca.dao.ItemDAOImpl;
import edu.uepb.web.biblioteca.dao.ReservaDAOImpl;
import edu.uepb.web.biblioteca.enums.TipoNivel;
import edu.uepb.web.biblioteca.exception.EmprestimoException;
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Emprestimo;
import edu.uepb.web.biblioteca.model.Item;
import edu.uepb.web.biblioteca.model.Reserva;
import edu.uepb.web.biblioteca.utils.BibliotecaDateTime;
import edu.uepb.web.biblioteca.utils.Email;

/**
 * A classe Service do Emprestimo
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Service
public class EmprestimoService {
	private static Logger logger = Logger.getLogger(EmprestimoService.class);
	private FuncionarioDAOImpl funcionarioDAO;
	private ItemDAOImpl itemDAO;
	private AlunoDAOImpl alunoDAO;
	private EmprestimoDAOImpl emprestimoDAO;
	private ReservaDAOImpl reservaDAO;
	private DividaDAOImpl dividaDAO;
	private DividaService dividaService;

	/**
	 * O funcionario cadastrar um emprestimo que foi pedido pelo aluno
	 * 
	 * @param idFuncionario
	 * @param idAluno
	 * @param idItem
	 * @return id do emprestimo cadastrado
	 * @throws EmprestimoException
	 */
	public int cadastrarEmprestimo(int idFuncionario, int idAluno, int idItem) throws EmprestimoException {
		logger.info("Executa metodo 'cadastrarEmprestimo' do emprestimoService: " + idFuncionario + " " + idAluno + " "
				+ idItem);
		itemDAO = new ItemDAOImpl();
		alunoDAO = new AlunoDAOImpl();
		dividaDAO = new DividaDAOImpl();

		Item item = itemDAO.getById(idItem);
		Aluno aluno = alunoDAO.getById(idAluno);

		// Verifica se falta mais de 20 dias para terminar o periodo
		String dataDevolucao = BibliotecaDateTime.getDataDevolucao(aluno.getCurso().getNivel());
		int dias = BibliotecaDateTime.diasParaFimPeriodo(dataDevolucao);
		if (dias < 20) {
			logger.error(
					"O emprestimo nao podera realizado, tem menos 20 dias para fim do periodo. dias para fim periodo: "
							+ dias);
			throw new EmprestimoException("O emprestimo nao podera realizado, tem menos 20 dias para fim do periodo");
		}

		// Verifica se o aluno tem divida ainda
		if (dividaDAO.isAlunoTemDivida(idAluno)) {
			logger.error("O aluno ainda tem divida ainda nao pago: " + idAluno + " e " + idItem);
			throw new EmprestimoException("O aluno ainda tem divida ainda nao pago");
		}

		// Verifica a quantidade do item no estoque
		if (item.getQuantidade() < 1) {
			logger.error("O item esta faltando no estoque" + idItem + " qtd: " + item.getQuantidade());
			throw new EmprestimoException("O item esta faltando no estoque");
		}

		// Verifica se o aluno reservou o item que ira emprestar, se for remove o
		// cadastro da reserva
		reservaDAO = new ReservaDAOImpl();
		Reserva reserva = reservaDAO.getByAlunoItemId(idAluno, idItem);
		if (reserva != null) {
			reservaDAO.remover(reserva);
			logger.info("A reserva foi removido: idAluno:" + idAluno + "idItem: " + idItem);
		}

		funcionarioDAO = new FuncionarioDAOImpl();
		emprestimoDAO = new EmprestimoDAOImpl();
		Emprestimo emprestimo = new Emprestimo();

		emprestimo.setFuncionario(funcionarioDAO.getById(idFuncionario));
		emprestimo.setAluno(aluno);
		emprestimo.setItem(item);
		if (emprestimoDAO.isExiste(emprestimo)) {
			throw new EmprestimoException("Este aluno ja fez emprestimo com este Item");
		}
		emprestimo.setDataCadastrado(BibliotecaDateTime.getDataCadastrado());
		emprestimo.setDataDevolucao(BibliotecaDateTime.getDataDevolucao(aluno.getCurso().getNivel()));
		emprestimo.setRenovacao(0);
		emprestimo.setEntregou(false);

		int idEmprestimo = emprestimoDAO.inserir(emprestimo);

		// Atualizar a quantidade do item
		item.setQuantidade(item.getQuantidade() - 1);
		itemDAO.atualizar(item);

		return idEmprestimo;
	}

	/**
	 * Realizar a devolucao do item emprestado boolean
	 * 
	 * @param idFuncionario
	 * @param idEmprestimo
	 * @return
	 */
	public boolean devolucaoEmprestimo(int idEmprestimo) {
		logger.info("Executa metodo 'devolucaoEmprestimo' do emprestimoService: idEmprestimo " + idEmprestimo);
		emprestimoDAO = new EmprestimoDAOImpl();
		itemDAO = new ItemDAOImpl();
		dividaService = new DividaService();
		reservaDAO = new ReservaDAOImpl();

		Emprestimo emprestimo = emprestimoDAO.getById(idEmprestimo);
		Aluno aluno = emprestimo.getAluno();

		// Verfica se tem dias atraso, se tiver calcular a divida
		dividaService.calcularDivida(aluno, emprestimo, emprestimo.getDataDevolucao());

		Item item = itemDAO.getById(emprestimo.getItem().getId());

		// Verifica se o item devolvido foi reservado, se for envia email para o aluno
		Reserva reserva = reservaDAO.getByItemId(item.getId());
		if (reserva != null) {
			logger.info("Enviar email no metodo 'devolucaoEmprestimo' " + reserva.getAluno().getEmail());
			Email email = new Email();
			email.setEmailDestino(reserva.getAluno().getEmail());
			email.sendNotificacaoDevolucao(item);
		}

		// aumentar a quantidade do item no estoque
		item.setQuantidade(item.getQuantidade() + 1);
		itemDAO.atualizar(item);

		// atualizar o status do emprestimo como entregado
		emprestimo.setEntregou(true);
		emprestimoDAO.atualizar(emprestimo);

		return true;
	}

	/**
	 * O Funcionario realizar a renovacao do seu emprestimo
	 * 
	 * @param idFuncionario
	 * @param idAluno
	 * @param idEmprestimo
	 * @return boolean
	 * @throws EmprestimoException
	 */
	public boolean renovarEmprestimo(int idAluno, int idEmprestimo) throws EmprestimoException {
		logger.info("Executa metodo 'renovarEmprestimo' do emprestimoService: idEmprestimo " + idEmprestimo);
		emprestimoDAO = new EmprestimoDAOImpl();
		reservaDAO = new ReservaDAOImpl();
		Emprestimo emprestimo = emprestimoDAO.getById(idEmprestimo);

		alunoDAO = new AlunoDAOImpl();
		Aluno aluno = alunoDAO.getById(idAluno);

		// aluno graduacao nao pode renovar mais de uma vez.
		if (aluno.getCurso().getNivel() == TipoNivel.GRADUACAO && emprestimo.getRenovacao() == 1) {
			logger.warn("O aluno de graduacao nao pode renovar mais e uma vez: idAluno" + aluno.getId());
			throw new EmprestimoException("Ja estourou o limite da renovacao!");
		}

		// Verificar se item ja foi reservado por alguem
		Reserva reserva = reservaDAO.getByItemId(emprestimo.getItem().getId());
		if (reserva != null) {
			logger.warn("O item ja foi reservado por alguem: idItem: " + emprestimo.getItem().getId());
			throw new EmprestimoException("Nao pode realizar a renovacao, o item ja foi reservado por alguem");
		}

		// Verifica se falta mais de 20 dias para terminar o periodo apartir da data de
		// devolucao
		int dias = BibliotecaDateTime.diasParaFimPeriodo(emprestimo.getDataDevolucao());
		if (dias < 20) {
			logger.error(
					"A renovacao nao podera realizada, tem menos 20 dias para fim do periodo. dias para fim periodo: "
							+ dias);
			throw new EmprestimoException("A renovacao nao podera realizada, tem menos 20 dias para fim do periodo");
		}

		// Renovar a data apartir da data devolucao
		emprestimo.setDataDevolucao(
				BibliotecaDateTime.getDataRenovacao(emprestimo.getDataDevolucao(), aluno.getCurso().getNivel()));
		emprestimo.setRenovacao(emprestimo.getRenovacao() + 1);

		emprestimoDAO.atualizar(emprestimo);

		return true;
	}

	/**
	 * Retornar a lista dos emprestimos que ainda nao entrega
	 * 
	 * @return List
	 */
	public List<Emprestimo> getListaEmprestimo() {
		logger.info("Executa o metodo 'getListaEmprestimo' do emprestimoService");
		emprestimoDAO = new EmprestimoDAOImpl();
		return emprestimoDAO.getLista();
	}

	/**
	 * Retornar todos Emprestimos
	 * 
	 * @return List
	 */
	public List<Emprestimo> getListaEmprestimoAll() {
		logger.info("Executa o metodo 'getListaEmprestimoAll' do emprestimoService");
		emprestimoDAO = new EmprestimoDAOImpl();
		return emprestimoDAO.getListaAll();
	}

	/**
	 * Pegar o Emprestimo pelo seu ID
	 * 
	 * @param id
	 * @return Emprestimo
	 */
	public Emprestimo getEmprestimo(int id) {
		logger.info("Executa o metodo 'getEmprestimo' emprestimoService: " + id);
		emprestimoDAO = new EmprestimoDAOImpl();
		return emprestimoDAO.getById(id);
	}

	/**
	 * Pegar o Emprestimor pelo ID do Aluno
	 * 
	 * @param idAluno
	 * @return Emprestimo
	 */
	public List<Emprestimo> getEmprestimoByAluno(int idAluno) {
		emprestimoDAO = new EmprestimoDAOImpl();
		return emprestimoDAO.getListByAlunoId(idAluno);
	}

}
