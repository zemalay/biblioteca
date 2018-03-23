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

public class RevistaDAO extends DAO<Revista> {

/**
 * @see edu.uepb.web.biblioteca.model.DAO#get(int)
 */
	@Override
	public Revista get(int id) {
		
		super.connection = new Conexao().getConexao();
		
		String sql = "SELECT revista.idrevista, revista.tituloRevista, revista.editoraRevista, revista.dataPublicacao, revista.edicao, revista.numeroPagina, "
				+ "WHERE revista.idrevista = ?";
		Revista revista =  null;
		
		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();
			
			if (resultSet.next()) {
				
				revista = new Revista();
				
				revista.setId(resultSet.getInt(1));
				revista.setTituloRevista(resultSet.getString(2));
				revista.setEditoraRevista(resultSet.getString(3));
				revista.setDataPublicacao(resultSet.getDate(4));
				revista.setEdicao(resultSet.getString(5));
				revista.setNumeroPagina(resultSet.getInt(6));
				
			}
			super.closeConnections();
			}catch(SQLException e) {
				e.printStackTrace();
		}
		
		return revista;
	}
	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#get(int)
	 */
	
	@Override
	public List<Revista> getLista() {
		super.connection = new Conexao().getConexao();
		List<Revista> listaRevistas = new ArrayList<Revista>();
		
		String sql = "SELECT revista.idrevista, revista.tituloRevista, revista.editoraRevista, revista.dataPublicacao, revista.edicao, revista.numeroPagina";
		
		try {
			super.statement = super.connection.prepareStatement(sql);
			super.resultSet = super.statement.executeQuery();
			
			while (resultSet.next()) {
				
				Revista revista = new Revista();
				revista.setId(resultSet.getInt(1));
				revista.setTituloRevista(resultSet.getString(2));
				revista.setEditoraRevista(resultSet.getString(3));
				revista.setDataPublicacao(resultSet.getDate(4));
				revista.setEdicao(resultSet.getString(5));
				revista.setTituloRevista(resultSet.getString(6));
				
				listaRevistas.add(revista);
			
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listaRevistas;
	}
	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#get(int)
	 */
	@Override
	public int inserir(Revista obj) {
		int id = -1;
		super.connection = new Conexao().getConexao();
		
		String sql = "INSERT INTO revista(tituloRevista, editoraRevista, dataPublicacao, edicao, numeroPagina) VALUES (?,?,?,?,?)";
		
		if (!obj.equals(null)) {
			try {
				super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				super.statement.setString(1, obj.getTituloRevista());
				super.statement.setString(2, obj.getEditoraRevista());
				super.statement.setDate(3, (Date) obj.getDataPublicacao());
				super.statement.setString(4, obj.getEdicao());
				super.statement.setInt(5, obj.getNumeroPagina());
				
				super.statement.execute();
				super.resultSet = super.statement.getGeneratedKeys();
				
				if (resultSet.next()) {
					id = super.resultSet.getInt(1);
				}
			
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
	}
	
		return id ;
	}
	
	/**
	 * @see edu.uepb.web.biblioteca.model.DAO#get(int)
	 */
	@Override
	public void remover(Revista obj) {
		if (!obj.equals(null)) {
			super.connection = new Conexao().getConexao();
			String sql = "DELETE FROM revista WHERE idrevista = ?";
			
			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setInt(1,obj.getId());
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
	public void atualizar(Revista obj) {
		if(!obj.equals(null)) {
			super.connection = new Conexao().getConexao();
			String sql = "UPDATE revista SET tituloRevista = ?, editoraRevista = ?, dataPublicacao = ?, edicao = ?, numeroPagina = ?"
					+ "WHERE idrevista = ?";
			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getTituloRevista());
				super.statement.setString(2, obj.getEditoraRevista());
				super.statement.setDate(3, (Date) obj.getDataPublicacao());
				super.statement.setString(4, obj.getEdicao());
				super.statement.setInt(5, obj.getNumeroPagina());
				
				super.statement.execute();
				super.closeConnections();
						
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	

}
