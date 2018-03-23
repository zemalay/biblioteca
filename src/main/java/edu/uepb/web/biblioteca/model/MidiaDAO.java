package edu.uepb.web.biblioteca.model;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Larrissa Dantas 
 *
 *
 */

public class MidiaDAO extends DAO<Midia> {

	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#get(int);
	 */
	@Override
	public Midia get(int id) {
		super.connection = new Conexao().getConexao();
		String sql = "SELECT midia.idmidia, midia.tituloMidia, midia.dataGravacao, midia.tipoMidia"
				+ "WHERE midia.idmidia = ?";
		Midia midia = null;
		
		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();
			
			if (resultSet.next()) {
				midia = new Midia();

				midia.setId(resultSet.getInt(1));
				midia.setTituloMidia(resultSet.getString(2));
				midia.setDataGravacao(resultSet.getDate(3));
				midia.setTipoMidia(resultSet.getString(4));

			}
			super.closeConnections();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return midia;
	}
	
	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#get(int);
	 */
	
	@Override
	public List<Midia> getLista() {
		super.connection = new Conexao().getConexao();
		List<Midia> listaMidias = new ArrayList<Midia>();
		String sql = "SELECT midia.idmidia, midia.tituloMidia, midia.dataGravacao, midia.tipoMidia";
		
		
			try {
				super.statement = super.connection.prepareStatement(sql);
				super.resultSet = super.statement.executeQuery();

				while (resultSet.next()) {
					Midia midia = new Midia();
					midia.setId(resultSet.getInt(1));
					midia.setTituloMidia(resultSet.getString(2));
					midia.setDataGravacao(resultSet.getDate(3));
					midia.setTipoMidia(resultSet.getString(4));
				
					
				}
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return listaMidias;

	}
	
	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#get(int);
	 */
	@Override
	public int inserir(Midia obj) {
	
		int id = -1;
		super.connection = new Conexao().getConexao();
		String sql = "INSERT INTO midia( tituloMidia, dataGravacao, tipoMidia) VALUES (?,?,?)";
		if (!obj.equals(null)) {
			try {
				super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				super.statement.setString(1, obj.getTituloMidia());
				super.statement.setDate(2, (Date) obj.getDataGravacao());
				super.statement.setString(3,obj.getTipoMidia().name());
				
				super.statement.execute();
				super.resultSet = super.statement.getGeneratedKeys();
		
				if (resultSet.next()) {
					id = super.resultSet.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}
	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#get(int);
	 */
	@Override
	public void remover(Midia obj) {
		if (!obj.equals(null)) {
			super.connection = new Conexao().getConexao();
			String sql = "DELETE FROM midia WHERE idmidia = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setInt(1, obj.getId());
				super.statement.execute();

				super.closeConnections();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#get(int);
	 */
	@Override
	public void atualizar(Midia obj) {
		if (!obj.equals(null)) {
			super.connection = new Conexao().getConexao();
			String sql = "UPDATE midia SET tituloMidia = ?, dataGravacao = ?, tipoMidia = ?"
					+ " WHERE idtrabalho = ?";

			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getTituloMidia());
				super.statement.setDate(2, (Date) obj.getDataGravacao());
				super.statement.setString(3, obj.getTipoMidia().name());
		
			
				super.statement.execute();
				super.closeConnections();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}

	}
		
	}
	


