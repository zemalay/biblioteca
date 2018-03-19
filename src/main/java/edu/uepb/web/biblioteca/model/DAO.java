package edu.uepb.web.biblioteca.model;

import java.util.List;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public interface DAO<T> {
	/**
	 * Seleciona o obejeto de acordo com seu id. Caso não seja encontrado é
	 * retornado null
	 *
	 * @param id
	 *            O id do objeto no banco de dados
	 * @return T O objeto
	 */
	public T get(int id);

	/**
	 * Retorna uma lista com todos objetos cadastrados no banco de dados
	 *
	 * @return List<T> Lista de objetos
	 */
	public List<T> getLista();

	/**
	 * Insere um novo objeto no banco de dados. Se a operação for realizada com
	 * sucesso é retornado o id
	 *
	 * @param obj
	 *            O objeto a ser inserido
	 * @return int
	 */
	public int inserir(T obj);

	/**
	 * Remove o objeto do banco de dados. Se a operação for realizada com sucesso é
	 * retornado true, caso contrário false
	 *
	 * @param obj
	 *            O objeto a ser removido
	 */
	public void remover(T obj);

	/**
	 * Remove o objeto do banco de dados. Se a operação for realizada com sucesso é
	 * retornado true, caso contrário false
	 *
	 * @param id
	 *            O id do objeto a ser removido
	 */
	public void remover(int id);

	/**
	 * Atualiza os dados do objeto no banco de dados. Se a operação for realizada
	 * com sucesso é retornado true, caso contrário false
	 *
	 * @param obj
	 *            O objeto com os dados sa serem atualizados
	 */
	public void atualizar(T obj);

}
