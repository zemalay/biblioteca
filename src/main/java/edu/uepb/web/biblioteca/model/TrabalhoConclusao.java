package edu.uepb.web.biblioteca.model;

import edu.uepb.web.biblioteca.enums.TipoTrabalhoConclusao;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class TrabalhoConclusao implements Item {

	private int id;
	private String titulo;
	private String autor;
	private TipoTrabalhoConclusao tipo;
	private String anoDefesa;
	private String local;
	private String orientador;

	public TrabalhoConclusao(String titulo, String autor, TipoTrabalhoConclusao tipo, String anoDefesa, String local,
			String orientador) {
		this.titulo = titulo;
		this.autor = autor;
		this.tipo = tipo;
		this.anoDefesa = anoDefesa;
		this.local = local;
		this.orientador = orientador;
	}

	public TrabalhoConclusao() {

	}

	public String getOrientador() {
		return orientador;
	}

	public void setOrientador(String orientador) {
		this.orientador = orientador;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public TipoTrabalhoConclusao getTipo() {
		return tipo;
	}

	public void setTipo(TipoTrabalhoConclusao tipo) {
		this.tipo = tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = TipoTrabalhoConclusao.valueOf(tipo);
	}

	public String getAnoDefesa() {
		return anoDefesa;
	}

	public void setAnoDefesa(String anoDefesa) {
		this.anoDefesa = anoDefesa;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
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
		TrabalhoConclusao other = (TrabalhoConclusao) obj;
		if (id != other.id)
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrabalhoConclusao [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", tipo=" + tipo
				+ ", anoDefesa=" + anoDefesa + ", local=" + local + ", orientador=" + orientador + "]";
	}

}
