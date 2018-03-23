package edu.uepb.web.biblioteca.model;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Micro.com
 *
 *
 */

public class JornalDAO extends DAO<Jornal>{
/**
 * @see edu.uepb.web.biblioteca.model.DAO#get(int)
 */
	
	@Override
	
	public Jornal get(int id) {
		super.connection = new Conexao().getConexao();
		
		String sql = "SELECT jornal.idjornal, jornal.tituloJornal, jornal.dataJornal, jornal.edicaoJornal WHERE jornal.idjornal = ?";
		Jornal jornal = null;
		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();
			
			if (resultSet.next()) {
				jornal = new Jornal();
				
				jornal.setId(resultSet.getInt(1));
				jornal.setTituloJornal(resultSet.getString(2));
				jornal.setDataJornal(resultSet.getDate(3));
				jornal.setEdicaoJornal(resultSet.getString(4));
			}
			super.closeConnections();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		

		return jornal;
	}
/**
 * @see edu.uepb.web.biblioteca.model.DAO#get(int)
*/	

	@Override
	public List<Jornal> getLista() {
	super.connection = new Conexao().getConexao();
	List<Jornal> listajornais = new ArrayList<Jornal>();
	String sql = "SELECT jornal.idjornal, jornal.tituloJornal, jornal.dataJornal, jornal.edicaoJornal";
	
	try {
		super.statement = super.connection.prepareStatement(sql);
		super.resultSet = super.statement.executeQuery();
		
		while (resultSet.next()) {
			Jornal jornal = new Jornal();
			jornal.setId(resultSet.getInt(1));
			jornal.setTituloJornal(resultSet.getString(2));
			jornal.setDataJornal(resultSet.getDate(3));
			jornal.setEdicaoJornal(resultSet.getString(4));
			
			listajornais.add(jornal);
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
		return listajornais;
	}
	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#get(int)
	*/
	@Override
	public int inserir(Jornal obj) {
		int id = -1;
		super.connection = new Conexao().getConexao();
		String sql = "INSERT INTO jornal(tituloJornal, dataJornal, edicaoJornal) VALUES (?,?,?)";
		if (!obj.equals(null)){
			try {
				super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				super.statement.setString(1, obj.getTituloJornal());
				super.statement.setDate(2, (Date) obj.getDataJornal());
				super.statement.setString(2, obj.getEdicaoJornal());
				super.statement.execute();
				super.resultSet = super.statement.getGeneratedKeys();
				
				if(resultSet.next()) {
					id = super.resultSet.getInt(1);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
		return id;
	}

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#get(int)
	*/
	@Override
	public void remover(Jornal obj) {
	if(!obj.equals(null)) {
		super.connection = new Conexao().getConexao();
		String sql = "DELETE FROM jornal WHERE idjornal = ?";
		
		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, obj.getId());
			super.statement.execute();
			
			super.closeConnections();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	}
	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#get(int)
	*/
	@Override
	public void atualizar(Jornal obj) {
		if (!obj.equals(null)) {
			super.connection = new Conexao().getConexao();	
			String sql = "UPDATE jornal SET tituloJornal = ?, dataJornal = ?, edicaoJornal = ? WHERE idjornal";
			
			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getTituloJornal());
				super.statement.setDate(2, (Date) obj.getDataJornal());
				super.statement.setString(2, obj.getEdicaoJornal());
				
				super.statement.execute();
				super.closeConnections();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	

}
