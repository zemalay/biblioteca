package edu.uepb.web.biblioteca.model;

import java.util.Date;

public class Jornal implements Item {

	private int id;
	private String tituloJornal;
	private Date dataJornal;
	private String edicaoJornal;

	public String getTituloJornal() {
		return tituloJornal;
	}

	public void setTituloJornal(String tituloJornal) {
		this.tituloJornal = tituloJornal;
	}

	public Date getDataJornal() {
		return dataJornal;
	}

	public void setDataJornal(Date dataJornal) {
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
