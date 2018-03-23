package edu.uepb.web.biblioteca.model;

import java.util.Date;

/**
 * @autor Larrissa Dantas 
 *
 *
 */

public class Jornal implements Item {

	private int id;
	private String tituloJornal;
	private Date dataJornal;
	private String edicaoJornal;
	
	

	public Jornal(String tituloJornal, Date dataJornal, String edicaoJornal) {
		super();
		this.tituloJornal = tituloJornal;
		this.dataJornal = dataJornal;
		this.edicaoJornal = edicaoJornal;
	}
	
	public Jornal() {
		
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((edicaoJornal == null) ? 0 : edicaoJornal.hashCode());
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
		Jornal other = (Jornal) obj;
		if (edicaoJornal == null) {
			if (other.edicaoJornal != null)
				return false;
		} else if (!edicaoJornal.equals(other.edicaoJornal))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Jornal [id=" + id + ", tituloJornal=" + tituloJornal + ", dataJornal=" + dataJornal + ", edicaoJornal="
				+ edicaoJornal + "]";
	}
	

}
