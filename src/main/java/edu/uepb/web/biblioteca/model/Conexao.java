package edu.uepb.web.biblioteca.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A classe Conexao, criar a conexao com o banco de dados MYSQL
 * 
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 */
public class Conexao {

	/**
	 * 
	 * Connection
	 * 
	 * @return {@link Connection}
	 */
	public Connection getConexao() {
		String path = "jdbc:mysql://localhost:3306/biblioteca?autoReconnect=true&useSSL=false";
		try {
			return DriverManager.getConnection(path, "root", "");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
