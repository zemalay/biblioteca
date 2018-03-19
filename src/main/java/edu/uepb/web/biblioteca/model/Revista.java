package edu.uepb.web.biblioteca.model;

public class Revista {

	private int id;
	private String tituloRevista;
	private String editoraRevista;
	private String dataPublicacao;
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

	public String getDataPublicacao() {
		return dataPublicacao;
	}

	public void setDataPublicacao(String dataPublicacao) {
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
