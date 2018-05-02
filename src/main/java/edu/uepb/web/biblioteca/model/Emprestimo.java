package edu.uepb.web.biblioteca.model;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class Emprestimo {
	private int id;
	private Funcionario funcionario;
	private Aluno aluno;
	private Item item;
	private String dataCadastrado;
	private String dataDevolucao;
	private int renovacao;

	public String getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(String dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getDataCadastrado() {
		return dataCadastrado;
	}

	public void setDataCadastrado(String dataCadastrado) {
		this.dataCadastrado = dataCadastrado;
	}

	public int getRenovacao() {
		return renovacao;
	}

	public void setRenovacao(int renovacao) {
		this.renovacao = renovacao;
	}

	@Override
	public String toString() {
		return "Emprestimo [id=" + id + ", funcionario=" + funcionario + ", aluno=" + aluno + ", item=" + item
				+ ", dataCadastrado=" + dataCadastrado + ", dataDevolucao=" + dataDevolucao + "]";
	}

}
