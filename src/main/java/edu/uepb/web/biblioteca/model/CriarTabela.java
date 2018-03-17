package edu.uepb.web.biblioteca.model;

import javax.persistence.Persistence;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class CriarTabela {
	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("bibliotecaPers");
	}
}
