package edu.uepb.web.biblioteca.business;

import edu.uepb.web.biblioteca.dao.AlunoDAOImpl;
import edu.uepb.web.biblioteca.dao.ItemDAOImpl;
import edu.uepb.web.biblioteca.dao.ReservaDAOImpl;
import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.exception.EmprestimoException;
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Item;
import edu.uepb.web.biblioteca.model.Reserva;
import edu.uepb.web.biblioteca.utils.BibliotecaDateTime;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class ReservaBusiness {
	private ReservaDAOImpl reservaDAO;
	private AlunoDAOImpl alunoDAO;
	private ItemDAOImpl itemDAO;

	/**
	 * Aluno realiza a reserva de um item
	 * 
	 * @param idALuno
	 * @param idItem
	 * @param dataReservada
	 * @return boolean
	 * @throws DAOException
	 * @throws EmprestimoException 
	 */
	public boolean reservaItem(int idALuno, int idItem) throws DAOException, EmprestimoException {
		reservaDAO = new ReservaDAOImpl();
		alunoDAO = new AlunoDAOImpl();

		Item item = itemDAO.get(idItem);
		Aluno aluno = alunoDAO.get(idALuno);

		Reserva reserva = new Reserva();
		reserva.setAluno(aluno);
		reserva.setItem(item);
		reserva.setDataReservado(BibliotecaDateTime.getDataCadastrado());
		
		if (reservaDAO.isExiste(reserva)) {
			throw new EmprestimoException("A reserva para este item ja existe");
		}
		reservaDAO.inserir(reserva);
		return true;
	}
	
	

}
