package edu.uepb.web.biblioteca.model;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class Universidade {

	private int id;
	private String nome;
	private String endereco;
	private String periodo;
	private String inicioPeriodo;
	private String fimPeriodo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getInicioPeriodo() {
		return inicioPeriodo;
	}

	public void setInicioPeriodo(String inicioPeriodo) {
		this.inicioPeriodo = inicioPeriodo;
	}

	public String getFimPeriodo() {
		return fimPeriodo;
	}

	public void setFimPeriodo(String fimPeriodo) {
		this.fimPeriodo = fimPeriodo;
	}

	@Override
	public String toString() {
		return "Universidade [id=" + id + ", nome=" + nome + ", endereco=" + endereco + ", periodo=" + periodo
				+ ", inicioPeriodo=" + inicioPeriodo + ", fimPeriodo=" + fimPeriodo + "]";
	}

}
