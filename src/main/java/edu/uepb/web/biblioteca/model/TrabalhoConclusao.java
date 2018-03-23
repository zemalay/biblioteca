package edu.uepb.web.biblioteca.model;

import edu.uepb.web.biblioteca.enums.TipoTrabalhoConclusao;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class TrabalhoConclusao implements Item {

	private int id;
	private String titulo;
	private String autor;
	private TipoTrabalhoConclusao tipo;
	private String anoDefesa;
	private String local;
	private String orientador;

	public String getOrientador() {
		return orientador;
	}

	public void setOrientador(String orientador) {
		this.orientador = orientador;
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

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public TipoTrabalhoConclusao getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = TipoTrabalhoConclusao.valueOf(tipo);
	}

	public String getAnoDefesa() {
		return anoDefesa;
	}

	public void setAnoDefesa(String anoDefesa) {
		this.anoDefesa = anoDefesa;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}
}
