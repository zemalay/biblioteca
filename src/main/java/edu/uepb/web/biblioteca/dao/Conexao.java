package edu.uepb.web.biblioteca.dao;

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
		Connection connection = null;
		try {

			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(path, "root", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return connection;
	}
}
