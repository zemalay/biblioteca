package edu.uepb.web.biblioteca.model;

import java.util.Date;

/**
 * @autor Larrissa Dantas 
 *
 *
 */

public class Revista implements Item{

	private int id;
	private String tituloRevista;
	private String editoraRevista;
	private Date dataPublicacao;
	private String edicao;
	private int numeroPagina;
	
	

	public Revista(String tituloRevista, String editoraRevista, Date dataPublicacao, String edicao, int numeroPagina) {
		super();
		this.tituloRevista = tituloRevista;
		this.editoraRevista = editoraRevista;
		this.dataPublicacao = dataPublicacao;
		this.edicao = edicao;
		this.numeroPagina = numeroPagina;
	}
	
	public Revista() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTituloRevista() {
		return tituloRevista;
	}

	public void setTituloRevista(String tituloRevista) {
		this.tituloRevista = tituloRevista;
	}

	public String getEditoraRevista() {
		return editoraRevista;
	}

	public void setEditoraRevista(String editoraRevista) {
		this.editoraRevista = editoraRevista;
	}


	public Date getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	public String getEdicao() {
		return edicao;
	}

	public void setEdicao(String edicao) {
		this.edicao = edicao;
	}

	public int getNumeroPagina() {
		return numeroPagina;
	}

	public void setNumeroPagina(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edicao == null) ? 0 : edicao.hashCode());
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
		Revista other = (Revista) obj;
		if (edicao == null) {
			if (other.edicao != null)
				return false;
		} else if (!edicao.equals(other.edicao))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Revista [id=" + id + ", tituloRevista=" + tituloRevista + ", editoraRevista=" + editoraRevista
				+ ", dataPublicacao=" + dataPublicacao + ", edicao=" + edicao + ", numeroPagina=" + numeroPagina + "]";
	}

}
