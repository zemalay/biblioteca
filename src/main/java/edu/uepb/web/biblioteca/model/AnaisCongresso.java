package edu.uepb.web.biblioteca.model;

import edu.uepb.web.biblioteca.enums.TipoAnais;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class AnaisCongresso implements Item{
	private int id;
	private String titulo;
	private TipoAnais tipo;
	private String congresso;
	private String anoPublicacao;
	private String local;

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
}

