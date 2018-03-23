package edu.uepb.web.biblioteca.model;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class Autor {
	private int id;
	private String nome;
	private AnaisCongresso anaisCongresso;

	
	public Autor(String nome, AnaisCongresso anaisCongresso) {
		super();
		this.nome = nome;
		this.anaisCongresso = anaisCongresso;
	}
	
	public Autor() {
		
	}

	public AnaisCongresso getAnaisCongresso() {
		return anaisCongresso;
	}

	public void setAnaisCongresso(AnaisCongresso anaisCongresso) {
		this.anaisCongresso = anaisCongresso;
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
		Autor other = (Autor) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nome=" + nome + "]";
	}
	
}
