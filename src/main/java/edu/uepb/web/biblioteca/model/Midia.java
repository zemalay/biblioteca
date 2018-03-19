package edu.uepb.web.biblioteca.model;

import edu.uepb.web.biblioteca.enums.TipoMidia;

public class Midia {

	private int id;
	private String tituloMidia;
	private TipoMidia tipoMidia;
	private String dataGravacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTituloMidia() {
		return tituloMidia;
	}

	public void setTituloMidia(String tituloMidia) {
		this.tituloMidia = tituloMidia;
	}

	public TipoMidia getTipoMidia() {
		return tipoMidia;
	}

	public void setTipoMidia(TipoMidia tipoMidia) {
		this.tipoMidia = tipoMidia;
	}

	public String getDataGravacao() {
		return dataGravacao;
	}

	public void setDataGravacao(String dataGravacao) {
		this.dataGravacao = dataGravacao;
	}

}
