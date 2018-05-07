package edu.uepb.web.biblioteca.business;

import edu.uepb.web.biblioteca.dao.AlunoDAOImpl;
import edu.uepb.web.biblioteca.dao.EmprestimoDAOImpl;
import edu.uepb.web.biblioteca.dao.ItemDAOImpl;
import edu.uepb.web.biblioteca.dao.ReservaDAOImpl;
import edu.uepb.web.biblioteca.enums.TipoNivel;
import edu.uepb.web.biblioteca.exception.AutenticacaoException;
import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.exception.EmprestimoException;
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Emprestimo;
import edu.uepb.web.biblioteca.model.Item;
import edu.uepb.web.biblioteca.model.Reserva;
import edu.uepb.web.biblioteca.utils.BibliotecaDateTime;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class AlunoBusiness {
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
	 * Aluno realiza a reserva de um item
	 * 
	 * @param idALuno
	 * @param idItem
	 * @param dataReservada
	 * @return boolean
	 * @throws DAOException
	 */
	public boolean reservaItem(int idALuno, int idItem, String dataReservada) throws DAOException {
		reservaDAO = new ReservaDAOImpl();
		alunoDAO = new AlunoDAOImpl();

		Item item = itemDAO.get(idItem);
		Aluno aluno = alunoDAO.get(idALuno);

		Reserva reserva = new Reserva();
		reserva.setAluno(aluno);
		reserva.setItem(item);
		reserva.setDataReservado(dataReservada);

		reservaDAO.inserir(reserva);
		return true;
	}

	/**
	 * O aluno realizar a renovacao do seu emprestimo
	 * 
	 * @param idFuncionario
	 * @param idAluno
	 * @param idEmprestimo
	 * @return boolean
	 * @throws DAOException
	 * @throws EmprestimoException
	 */
	public boolean renovarEmprestimo(int idAluno, int idEmprestimo) throws DAOException, EmprestimoException {
		emprestimoDAO = new EmprestimoDAOImpl();
		Emprestimo emprestimo = emprestimoDAO.get(idEmprestimo);

		alunoDAO = new AlunoDAOImpl();
		Aluno aluno = alunoDAO.get(idEmprestimo);

		// aluno graduacao nao pode renovar mais de uma vez.
		if (aluno.getCurso().getNivel() == TipoNivel.GRADUACAO && emprestimo.getRenovacao() != 1) {
			throw new EmprestimoException("Ja estourou o limite da renovacao!");
		}

		emprestimo.setDataDevolucao(BibliotecaDateTime.getDataDevolucao(aluno.getCurso().getNivel()));
		emprestimo.setRenovacao(emprestimo.getRenovacao() + 1);

		emprestimoDAO.atualizar(emprestimo);

		return true;
	}
}
