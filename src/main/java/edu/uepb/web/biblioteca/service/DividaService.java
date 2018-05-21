package edu.uepb.web.biblioteca.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.stereotype.Service;

import edu.uepb.web.biblioteca.dao.DividaDAOImpl;
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Divida;
import edu.uepb.web.biblioteca.model.Emprestimo;
import edu.uepb.web.biblioteca.utils.BibliotecaDateTime;

/**
 * A classe Service da Divida
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
@Service
public class DividaService {
	private static Logger logger = Logger.getLogger(DividaService.class);
	private DividaDAOImpl dividaDAO;

	/**
	 * Retornar a lista das dividas que ainda nao pago no sistema
	 * 
	 * @return List<Divida>
	 */
	public List<Divida> getListaDivida() {
		logger.info("Executa o metodo 'getListaDivida' do dividaService");
		dividaDAO = new DividaDAOImpl();
		return dividaDAO.getLista();
	}

	/**
	 * O aluno Pagar a sua divida do emprestimo
	 * 
	 * @param idDivida
	 * @return boolean
	 */
	public boolean pagarDividaAluno(int idDivida) {
		logger.info("Executa o metodo 'pagarDividaAluno' do dividaService :" + idDivida);

		dividaDAO = new DividaDAOImpl();

		Divida divida = dividaDAO.getById(idDivida);
		divida.setPago(true);
		dividaDAO.atualizar(divida);

		return true;
	}

	/**
	 * Pegar as dividas pelo ID do Aluno
	 * 
	 * @param idAluno
	 * @return List<Divida>
	 */
	public List<Divida> getListaDividaByAluno(int idAluno) {
		logger.info("Executa o metodo 'getListaDividaByAluno' do dividaService :" + idAluno);
		dividaDAO = new DividaDAOImpl();
		return dividaDAO.getByAlunoId(idAluno);
	}

	/**
	 * Pegar divida pelo ID do Aluno
	 * 
	 * @param idAluno
	 * @return Divida
	 */
	public Divida getDividaAluno(int idAluno) {
		logger.info("Executa o metodo 'getDividaAluno' do dividaService :" + idAluno);
		dividaDAO = new DividaDAOImpl();
		return dividaDAO.getDividaByAlunoId(idAluno);
	}

	/**
	 * Pegar divida pelo ID da Divida
	 * 
	 * @param id
	 * @return Divida
	 */
	public Divida getDivida(int id) {
		logger.info("Executa o metodo 'getDivida' do dividaService :" + id);
		dividaDAO = new DividaDAOImpl();
		return dividaDAO.getById(id);
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

		if (diasAtraso < 0) {
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
