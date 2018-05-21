package edu.uepb.web.biblioteca.dao;

import java.util.List;

/**
 * Interface DAO . Alem dos metodos (CRUD) adicionamos
 * tambem metodo para verificar se a entidade ja existe
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 * 
 */
public interface DAO<T> {
	/**
	 * Seleciona o objeto de acordo com seu id. Caso não seja encontrado é retornado
	 * null
	 *
	 * @param id
	 *            O id do objeto no banco de dados
	 * @return T O objeto
	 * @
	 */
	T getById(int id) ;

	/**
	 * Retorna uma lista com todos objeto cadastrados no banco de dados
	 *
	 * @return List<T> Lista de objetos
	 * @
	 */
	List<T> getLista() ;

	/**
	 * Insere um novo objeto no banco de dados. Se a operação for realizada com
	 * sucesso é retornado o id
	 *
	 * @param obj
	 *            O objeto a ser inserido
	 * @return int retorna o id do objeto salvo
	 * @
	 */
	int inserir(T obj) ;

	/**
	 * Remove o objeto do banco de dados. Se a operação for realizada com sucesso é
	 * retornado true, caso contrário false
	 *
	 * @param obj
	 *            O objeto a ser removido
	 * @
	 */
	void remover(T obj) ;

	/**
	 * Atualiza os dados do objeto no banco de dados. Se a operação for realizada
	 * com sucesso é retornado true, caso contrário false
	 *
	 * @param obj
	 *            O objeto com os dados sa serem atualizados
	 * @
	 */
	void atualizar(T obj) ;

	/**
	 * Verifica se o objeto ja existe no banco de dados. Se a operação for
	 * realizada com sucesso é retornado true, caso contrário false
	 * 
	 * @param item
	 * 
	 * @return boolean
	 * @
	 */
	boolean isExiste(T obj) ;

}
