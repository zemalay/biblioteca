package edu.uepb.web.biblioteca.model;

import java.util.Date;

public class Revista implements Item{

	private int id;
	private String tituloRevista;
	private String editoraRevista;
	private Date dataPublicacao;
	private String edicao;
	private int numeroPagina;

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

}
