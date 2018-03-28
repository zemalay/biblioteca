package edu.uepb.web.biblioteca.model;

import edu.uepb.web.biblioteca.enums.TipoFuncionario;

/**
 * A classe POJO do Funcionario
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 */
public class Funcionario {
	private int id;
	private String nome;
	private TipoFuncionario tipoFunc;

	public Funcionario(String nome, TipoFuncionario tipoFunc) {
		this.nome = nome;
		this.tipoFunc = tipoFunc;
	}

	public Funcionario() {

	}

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

	public TipoFuncionario getTipoFunc() {
		return tipoFunc;
	}

	public void setTipoFunc(String tipoFunc) {
		this.tipoFunc = TipoFuncionario.valueOf(tipoFunc);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", tipoFunc=" + tipoFunc + "]";
	}

}
