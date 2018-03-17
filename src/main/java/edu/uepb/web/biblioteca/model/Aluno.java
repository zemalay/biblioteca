package edu.uepb.web.biblioteca.model;

import javax.persistence.*;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
@Entity
@Table(name = "aluno")
public class Aluno {

	private Long id;
	private String nome;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(length = 100)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
