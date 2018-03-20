package edu.uepb.web.biblioteca.model;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class Orientador {

	private int id;
	private String nome;
	private TrabalhoConclusao trabalhoConclusao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TrabalhoConclusao getTrabalhoConclusao() {
		return trabalhoConclusao;
	}

	public void setTrabalhoConclusao(TrabalhoConclusao trabalhoConclusao) {
		this.trabalhoConclusao = trabalhoConclusao;
	}

}
