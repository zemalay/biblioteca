package edu.uepb.web.biblioteca.model;

/**
 * A classe POJO do item Jornal
 * 
 * @autor Larrissa Dantas
 * 
 */
public class Jornal implements Item {

	private int id;
	private String titulo;
	private String data;
	private String edicao;

	public Jornal(String tituloJornal, String dataJornal, String edicaoJornal) {
		this.titulo = tituloJornal;
		this.data = dataJornal;
		this.edicao = edicaoJornal;
	}

	public Jornal() {

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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
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
		Jornal other = (Jornal) obj;
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
		return "Jornal [id=" + id + ", tituloJornal=" + titulo + ", dataJornal=" + data + ", edicaoJornal=" + edicao
				+ "]";
	}

}
