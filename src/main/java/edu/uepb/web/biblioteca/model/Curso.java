package edu.uepb.web.biblioteca.model;

import edu.uepb.web.biblioteca.enums.TipoNivel;

/**
 * A classe POJO do Curso
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 */
public class Curso {
	private int id;
	private String nome;
	private String area;
	private TipoNivel nivel;

	public Curso(String nome, TipoNivel nivel, String area) {
		this.nome = nome;
		this.nivel = nivel;
		this.area = area;
	}

	public Curso() {

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

	public TipoNivel getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = TipoNivel.valueOf(nivel);
	}

	public void setNivel(TipoNivel nivel) {
		this.nivel = nivel;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
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
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Curso [id=" + id + ", nome=" + nome + ", area=" + area + ", nivel=" + nivel + "]";
	}

}
