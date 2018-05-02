package edu.uepb.web.biblioteca.business;

import edu.uepb.web.biblioteca.dao.AlunoDAOImpl;
import edu.uepb.web.biblioteca.dao.EmprestimoDAOImpl;
import edu.uepb.web.biblioteca.enums.TipoNivel;
import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.exception.EmprestimoException;
import edu.uepb.web.biblioteca.model.Aluno;
import edu.uepb.web.biblioteca.model.Emprestimo;
import edu.uepb.web.biblioteca.utils.BibliotecaDateTime;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class AlunoBusiness {
	private AlunoDAOImpl alunoDAO;
	private EmprestimoDAOImpl emprestimoDAO;

	/**
	 * O aluno realizar a renovacao do seu emprestimo
	 * 
	 * boolean
	 * 
	 * @param idFuncionario
	 * @param idAluno
	 * @param idEmprestimo
	 * @return
	 * @throws DAOException
	 * @throws EmprestimoException
	 */
	public boolean renovarEmprestimo(int idAluno, int idEmprestimo)
			throws DAOException, EmprestimoException {
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
