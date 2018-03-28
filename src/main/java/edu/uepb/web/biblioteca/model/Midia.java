package edu.uepb.web.biblioteca.model;

import edu.uepb.web.biblioteca.enums.TipoMidia;

/**
 * A classe POJO do item Midia
 * 
 * @autor Larrissa Dantas
 *
 */
public class Midia implements Item {

	private int id;
	private String titulo;
	private TipoMidia tipo;
	private String dataGravacao;

	public Midia(String tituloMidia, TipoMidia tipoMidia, String dataGravacao) {
		super();
		this.titulo = tituloMidia;
		this.tipo = tipoMidia;
		this.dataGravacao = dataGravacao;
	}

	public Midia() {

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

	public TipoMidia getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = TipoMidia.valueOf(tipo);
	}

	public void setTipo(TipoMidia tipo) {
		this.tipo = tipo;
	}

	public String getDataGravacao() {
		return dataGravacao;
	}

	public void setDataGravacao(String dataGravacao) {
		this.dataGravacao = dataGravacao;
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
		Midia other = (Midia) obj;
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
		return "Midia [id=" + id + ", tituloMidia=" + titulo + ", tipoMidia=" + tipo + ", dataGravacao=" + dataGravacao
				+ "]";
	}

}
