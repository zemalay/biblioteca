package edu.uepb.web.biblioteca.model;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import edu.uepb.web.biblioteca.dao.Conexao;


/**
 * @autor geovanniovinhas <vinhasgeovannio@gmail.com
 *
 *
 */
public class ConexaoTest {
	Connection conn;

	@Test
	public void getConexao() {
		try {
			conn = new Conexao().getConexao();
			Assert.assertFalse(conn.isValid(10));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
