package edu.uepb.web.biblioteca.model;

import java.util.Date;

import edu.uepb.web.biblioteca.enums.TipoMidia;

/**
 * @autor Larrissa Dantas 
 *
 *
 */

public class Midia implements Item{

	private int id;
	private String tituloMidia;
	private TipoMidia tipoMidia;
	private Date dataGravacao;
	
	

	public Midia(String tituloMidia, TipoMidia tipoMidia, Date dataGravacao) {
		super();
		this.tituloMidia = tituloMidia;
		this.tipoMidia = tipoMidia;
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

	public String getTituloMidia() {
		return tituloMidia;
	}

	public void setTituloMidia(String tituloMidia) {
		this.tituloMidia = tituloMidia;
	}

	public TipoMidia getTipoMidia() {
		return tipoMidia;
	}

	public void setTipoMidia(String  tipoMidia) {
		this.tipoMidia = TipoMidia.valueOf(tipoMidia);
	}

	public Date getDataGravacao() {
		return dataGravacao;
	}

	public void setDataGravacao(Date dataGravacao) {
		this.dataGravacao = dataGravacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((tituloMidia == null) ? 0 : tituloMidia.hashCode());
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
		if (tituloMidia == null) {
			if (other.tituloMidia != null)
				return false;
		} else if (!tituloMidia.equals(other.tituloMidia))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Midia [id=" + id + ", tituloMidia=" + tituloMidia + ", tipoMidia=" + tipoMidia + ", dataGravacao="
				+ dataGravacao + "]";
	}


}
