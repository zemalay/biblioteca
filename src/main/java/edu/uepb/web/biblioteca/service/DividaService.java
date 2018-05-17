package edu.uepb.web.biblioteca.service;

import java.util.List;

import org.apache.log4j.Logger;

import edu.uepb.web.biblioteca.dao.DividaDAOImpl;
import edu.uepb.web.biblioteca.model.Divida;

/**
 * A classe Servida da Divida
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
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

}
