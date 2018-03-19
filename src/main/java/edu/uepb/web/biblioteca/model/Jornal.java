package edu.uepb.web.biblioteca.model;

public class Jornal {

	private int id;
	private String tituloJornal;
	private String dataJornal;
	private String edicaoJornal;

	public String getTituloJornal() {
		return tituloJornal;
	}

	public void setTituloJornal(String tituloJornal) {
		this.tituloJornal = tituloJornal;
	}

	public String getDataJornal() {
		return dataJornal;
	}

	public void setDataJornal(String dataJornal) {
		this.dataJornal = dataJornal;
	}

	public String getEdicaoJornal() {
		return edicaoJornal;
	}

	public void setEdicaoJornal(String edicaoJornal) {
		this.edicaoJornal = edicaoJornal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
