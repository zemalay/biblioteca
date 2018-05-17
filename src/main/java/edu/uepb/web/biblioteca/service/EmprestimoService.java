package edu.uepb.web.biblioteca.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;

import edu.uepb.web.biblioteca.dao.AlunoDAOImpl;
import edu.uepb.web.biblioteca.dao.DividaDAOImpl;
import edu.uepb.web.biblioteca.dao.EmprestimoDAOImpl;
import edu.uepb.web.biblioteca.dao.FuncionarioDAOImpl;
import edu.uepb.web.biblioteca.dao.ItemDAOImpl;
import edu.uepb.web.biblioteca.dao.ReservaDAOImpl;
import edu.uepb.web.biblioteca.enums.TipoNivel;
import edu.uepb.web.biblioteca.exception.EmprestimoException;
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Divida;
import edu.uepb.web.biblioteca.model.Emprestimo;
import edu.uepb.web.biblioteca.model.Item;
import edu.uepb.web.biblioteca.model.Reserva;
import edu.uepb.web.biblioteca.utils.BibliotecaDateTime;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class EmprestimoService {
	private static Logger logger = Logger.getLogger(EmprestimoService.class);
	private FuncionarioDAOImpl funcionarioDAO;
	private ItemDAOImpl itemDAO;
	private AlunoDAOImpl alunoDAO;
	private EmprestimoDAOImpl emprestimoDAO;
	private ReservaDAOImpl reservaDAO;
	private DividaDAOImpl dividaDAO;

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

		// Verifica se o aluno tem divida ainda
		if (dividaDAO.isAlunoTemDivida(idAluno)) {
			logger.warn("O aluno ainda tem divida ainda nao pago: " + idAluno + " e " + idItem);
			throw new EmprestimoException("O aluno ainda tem divida ainda nao pago");
		}

		// Verifica a quantidade do item no estoque
		if (item.getQuantidade() < 1) {
			logger.warn("O item esta faltando no estoque" + idItem + " qtd: " + item.getQuantidade());
			throw new EmprestimoException("O item esta faltando no estoque");
		}

		reservaDAO = new ReservaDAOImpl();

		List<Reserva> listaReserva = reservaDAO.getLista();

		// Verifica se o aluno reservou o item que ira emprestar, se for remove o
		// cadastro da reserva
		for (Reserva reserva : listaReserva) {
			if (reserva.getAluno().getId() == idAluno && reserva.getItem().getId() == idItem) {
				reservaDAO.remover(reserva);
				logger.info("A reserva foi removido: idAluno:" + idAluno + "idItem: " + idItem);
			}
		}

		funcionarioDAO = new FuncionarioDAOImpl();
		emprestimoDAO = new EmprestimoDAOImpl();
		Emprestimo emprestimo = new Emprestimo();

		emprestimo.setFuncionario(funcionarioDAO.getById(idFuncionario));
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

	/**
	 * Realizar a devolucao do item emprestado boolean
	 * 
	 * @param idFuncionario
	 * @param idEmprestimo
	 * @return
	 */
	public boolean devolucaoEmprestimo(int idFuncionario, int idEmprestimo) {
		logger.info("Executa metodo 'devolucaoEmprestimo' do emprestimoService: idEmprestimo " + idEmprestimo);
		emprestimoDAO = new EmprestimoDAOImpl();
		itemDAO = new ItemDAOImpl();

		Emprestimo emprestimo = emprestimoDAO.getById(idEmprestimo);
		Aluno aluno = emprestimo.getAluno();

		// Verfica se tem dias atraso, se tiver calcular a divida
		calcularDivida(aluno, emprestimo, emprestimo.getDataDevolucao());

		Item item = itemDAO.getById(emprestimo.getId());

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
		if (aluno.getCurso().getNivel() == TipoNivel.GRADUACAO && emprestimo.getRenovacao() != 1) {
			logger.warn("O aluno de graduacao nao pode renovar mais e uma vez: idAluno" + aluno.getId());
			throw new EmprestimoException("Ja estourou o limite da renovacao!");
		}
		List<Reserva> listaReserva = reservaDAO.getLista();

		for (Reserva reserva : listaReserva) {
			if (emprestimo.getItem().getId() == reserva.getItem().getId()) {
				logger.warn("O item ja foi reservado por alguem: idItem: " + emprestimo.getItem().getId());
				throw new EmprestimoException("Nao pode realizar a renovacao, o item ja foi reservado por alguem");
			}
		}

		emprestimo.setDataDevolucao(BibliotecaDateTime.getDataDevolucao(aluno.getCurso().getNivel()));
		emprestimo.setRenovacao(emprestimo.getRenovacao() + 1);

		emprestimoDAO.atualizar(emprestimo);

		return true;
	}

	/**
	 * Verificar a data da devolucao do emprestimo, se passou a data calcula a
	 * divida e cadastra-lo no sistema
	 * 
	 * @param aluno
	 * @param emprestimo
	 * @param dataDevolucao
	 */
	public void calcularDivida(Aluno aluno, Emprestimo emprestimo, String dataDevolucao) {
		logger.info("Executar o metodo 'calcularDivida' do emprestimoService: aluno: " + aluno + " emprestimo: "
				+ emprestimo + " dataDevolucao: " + dataDevolucao);
		// Data de hoje
		DateTime dataInicio = new DateTime();

		// data devolucao do item
		DateTime dataFim = BibliotecaDateTime.stringToDateTime(dataDevolucao);

		// Pegar os dias atrasos usando biblioteca JodaDateTime
		int diasAtraso = Days.daysBetween(dataInicio.toLocalDate(), dataFim.toLocalDate()).getDays();

		if (diasAtraso > 0) {
			logger.info("O item esta com atraso: dias de atraso: " + diasAtraso);
			float saldo = (float) (diasAtraso * 0.5);

			dividaDAO = new DividaDAOImpl();
			Divida divida = new Divida();

			divida.setAluno(aluno);
			divida.setEmprestimo(emprestimo);
			divida.setSaldo(saldo);
			divida.setPago(false);

			// Cadastrar a divida do aluno no sistema
			dividaDAO.inserir(divida);
		}

	}

}
