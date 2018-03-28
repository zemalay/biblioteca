package edu.uepb.web.biblioteca.model;

import java.util.ArrayList;
import java.util.List;

import edu.uepb.web.biblioteca.enums.TipoAnais;

/**
 * 
 * A classe POJO do item Anais do Congresso
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 */
public class AnaisCongresso implements Item {
	private int id;
	private String titulo;
	private TipoAnais tipo;
	private String congresso;
	private String anoPublicacao;
	private String local;
	private List<Autor> listaAutores = new ArrayList<Autor>();

	public AnaisCongresso(String titulo, TipoAnais tipo, String congresso, String anoPublicacao, String local) {
		this.titulo = titulo;
		this.tipo = tipo;
		this.congresso = congresso;
		this.anoPublicacao = anoPublicacao;
		this.local = local;
	}

	public AnaisCongresso() {

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

	public TipoAnais getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = TipoAnais.valueOf(tipo);
	}

	public void setTipo(TipoAnais tipo) {
		this.tipo = tipo;
	}

	public String getCongresso() {
		return congresso;
	}

	public void setCongresso(String congresso) {
		this.congresso = congresso;
	}

	public String getAnoPublicacao() {
		return anoPublicacao;
	}

	public void setAnoPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public List<Autor> getListaAutores() {
		return listaAutores;
	}

	public void setListaAutores(List<Autor> listaAutores) {
		this.listaAutores = listaAutores;
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
		AnaisCongresso other = (AnaisCongresso) obj;
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
		return "AnaisCongresso [id=" + id + ", titulo=" + titulo + ", tipo=" + tipo + ", congresso=" + congresso
				+ ", anoPublicacao=" + anoPublicacao + ", local=" + local + ", listaAutores=" + listaAutores + "]";
	}

}
