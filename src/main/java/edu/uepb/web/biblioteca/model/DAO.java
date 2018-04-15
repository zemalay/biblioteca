package edu.uepb.web.biblioteca.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.uepb.web.biblioteca.exception.DAOException;

/**
 * Classe abstrato DAO para todas as entidades no sistema biblioteca (excepto
 * para os itens) A classe tem a assinatura dos metodos basicos (CRUD).
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 * 
 */
public abstract class DAO<T> {
	public Connection connection;
	public PreparedStatement statement;
	public ResultSet resultSet;

	/**
	 * Fecha todas conexões que foram abertas na consulta
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
	 * Retorna uma lista com todos objetos cadastrados no banco de dados
	 *
	 * @return List<T> Lista de objetos
	 * @throws DAOException 
	 */
	public abstract List<T> getLista() throws DAOException;

	/**
	 * Insere um novo objeto no banco de dados. Se a operação for realizada com
	 * sucesso é retornado o id
	 *
	 * @param obj
	 *            O objeto a ser inserido
	 * @return int
	 * @throws DAOException 
	 */
	public abstract int inserir(T obj) throws DAOException;

	/**
	 * Remove o objeto do banco de dados. Se a operação for realizada com sucesso é
	 * retornado true, caso contrário false
	 *
	 * @param obj
	 *            O objeto a ser removido
	 * @throws DAOException 
	 */
	public abstract void remover(T obj) throws DAOException;

	/**
	 * Atualiza os dados do objeto no banco de dados. Se a operação for realizada
	 * com sucesso é retornado true, caso contrário false
	 *
	 * @param obj
	 *            O objeto com os dados sa serem atualizados
	 * @throws DAOException 
	 */
	public abstract void atualizar(T obj) throws DAOException;

}
