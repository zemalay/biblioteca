package edu.uepb.web.biblioteca.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class Conexao {
	public Connection getConexao() {
		String path = "jdbc:mysql://localhost:3306/biblioteca";
		try {
			return DriverManager.getConnection(path, "root", "");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
