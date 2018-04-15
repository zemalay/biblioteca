package edu.uepb.web.biblioteca.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.uepb.web.biblioteca.exception.DAOException;
import edu.uepb.web.biblioteca.exception.ItemExistException;

/**
 * Classe abstrato DAO especifico para os itens. Alem dos metodos (CRUD)
 * adicionamos tambem metodo para verificar se o item ja existe
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 * 
 */
public abstract class ItemDAO<T> {
	public Connection connection;
	public PreparedStatement statement;
	public ResultSet resultSet;

	/**
	 * Fecha todas conexões que foram aberats na consulta
	 *
	 * @throws SQLException
	 */
	public void closeConnections() throws SQLException {
		if (statement != null) {
			statement.close();
		}
		if (resultSet != null) {
			resultSet.close();
		}
	}

	/**
	 * Seleciona o objeto de acordo com seu id. Caso não seja encontrado é retornado
	 * null
	 *
	 * @param id
	 *            O id do objeto no banco de dados
	 * @return T O objeto
	 * @throws DAOException
	 */
	public abstract T get(int id) throws DAOException;

	/**
	 * Retorna uma lista com todos objeto cadastrados no banco de dados
	 *
	 * @return List<Item> Lista de objetos
	 * @throws DAOException
	 */
	public abstract List<Item> getLista() throws DAOException;

	/**
	 * Insere um novo objeto no banco de dados. Se a operação for realizada com
	 * sucesso é retornado o id
	 *
	 * @param item
	 *            O objeto a ser inserido
	 * @return int
	 * @throws DAOException
	 */
	public abstract int inserir(Item item) throws DAOException;

	/**
	 * Remove o objeto do banco de dados. Se a operação for realizada com sucesso é
	 * retornado true, caso contrário false
	 *
	 * @param item
	 *            O objeto a ser removido
	 * @throws DAOException
	 */
	public abstract void remover(Item item) throws DAOException;

	/**
	 * Atualiza os dados do objeto no banco de dados. Se a operação for realizada
	 * com sucesso é retornado true, caso contrário false
	 *
	 * @param item
	 *            O objeto com os dados sa serem atualizados
	 * @throws DAOException
	 */
	public abstract void atualizar(Item item) throws DAOException;

	/**
	 * Verifica se o objeto (item) ja existe no banco de dados. Se a operação for
	 * realizada com sucesso é retornado true, caso contrário false
	 * 
	 * @param item
	 * 
	 * @return boolean
	 * @throws DAOException
	 * @throws ItemExistException
	 */
	public abstract boolean isItemExiste(Item item) throws DAOException, ItemExistException;

}
