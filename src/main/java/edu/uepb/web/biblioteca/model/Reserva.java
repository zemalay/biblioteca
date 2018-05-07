package edu.uepb.web.biblioteca.model;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class Reserva {
	private int id;
	private Aluno aluno;
	private Item item;
	private String dataReservado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getDataReservado() {
		return dataReservado;
	}

	public void setDataReservado(String dataReservado) {
		this.dataReservado = dataReservado;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", aluno=" + aluno + ", item=" + item + ", dataReservado=" + dataReservado + "]";
	}

}
