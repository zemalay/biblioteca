package edu.uepb.web.biblioteca.model;

/**
 * A classe POJO para Divida
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class Divida {
	private int id;
	private Aluno aluno;
	private Emprestimo emprestimo;
	private float saldo;
	private boolean pago;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public boolean isPago() {
		return pago;
	}

	public void setPago(boolean pago) {
		this.pago = pago;
	}

	@Override
	public String toString() {
		return "Divida [id=" + id + ", aluno=" + aluno + ", emprestimo=" + emprestimo + ", saldo=" + saldo + ", pago="
				+ pago + "]";
	}

}
