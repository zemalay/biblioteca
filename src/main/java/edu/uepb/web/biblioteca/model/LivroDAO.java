package edu.uepb.web.biblioteca.model;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor Larrissa Dantas 
 *
 *
 */

public class LivroDAO extends DAO<Livro> {
/**
 * @see edu.uepb.web.biblioteca.model.DAO#get(int)
 */
	@Override
	public Livro get(int id) {
		super.connection = new Conexao().getConexao();
		String sql  = "SELECT livro.isbn, livro.titulo, livro.autor, livro.editora, livro.anoPublicacao, livro.edicao, livro.numeroPagina, livro.area, livro.tema"
				+ "WHERE livro.idlivro = ?";
		
		Livro livro = null;
		
		try {
			super.statement = super.connection.prepareStatement(sql);
			super.statement.setInt(1, id);
			super.resultSet = super.statement.executeQuery();
			
			if(resultSet.next()) {
				
				livro = new Livro();
				
				livro.setId(resultSet.getInt(1));
				livro.setIsbn(resultSet.getString(2));
				livro.setTitulo(resultSet.getString(3));
				livro.setAutor(resultSet.getString(4));
				livro.setEditora(resultSet.getString(5));
				livro.setAnoPublicacao(resultSet.getString(6));
				livro.setEdicao(resultSet.getString(7));
				livro.setNumeroPagina(resultSet.getInt(8));
				livro.setArea(resultSet.getString(9));
				livro.setTema(resultSet.getString(10));
				
				
			}
			super.closeConnections();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	
		return livro;
	}
	
/**
* @see edu.uepb.web.biblioteca.model.DAO#get(int)
*/
	
	@Override
	public List<Livro> getLista() {
		super.connection = new Conexao().getConexao();
		List<Livro> listaLivros = new ArrayList<Livro>();
		
		String sql = "SELECT livro.idlivro, livro.isbn, livro.titulo, livro.autor, livro.editora, livro.anoPublicacao, livro.edição,"
				+ "livro.numeroPagina, livro.area, livro.tema";
		
		try {
			super.statement = super.connection.prepareStatement(sql);
			super.resultSet = super.statement.executeQuery();
			
			while (resultSet.next()) {
				
				Livro livro = new Livro();
				
				livro.setId(resultSet.getInt(1));
				livro.setIsbn(resultSet.getString(2));
				livro.setTitulo(resultSet.getString(3));
				livro.setAutor(resultSet.getString(4));
				livro.setEditora(resultSet.getString(5));
				livro.setAnoPublicacao(resultSet.getString(6));
				livro.setEdicao(resultSet.getString(7));
				livro.setNumeroPagina(resultSet.getInt(8));
				livro.setArea(resultSet.getString(9));
				livro.setTema(resultSet.getString(10));
				
				listaLivros.add(livro);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listaLivros;
	}

/**
* @see edu.uepb.web.biblioteca.model.DAO#get(int)
*/
	
	@Override
	public int inserir(Livro obj) {
		int id = -1;
		super.connection = new Conexao().getConexao();
		
		String sql = "INSERT INTO livro (isbn, titulo, autor, editora, anoPublicacao, edicao, numeroPagina, area, tema) VALUES (?,?,?,?,?,?,?,?,?)";
		if(!obj.equals(null)) {
			try {
				super.statement = super.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				super.statement.setString(1, obj.getIsbn());
				super.statement.setString(2, obj.getTitulo());
				super.statement.setString(3, obj.getAutor());
				super.statement.setString(4, obj.getEditora());
				super.statement.setString(5, obj.getAnoPublicacao());
				super.statement.setString(6, obj.getEdicao());
				super.statement.setInt(7, obj.getNumeroPagina());
				super.statement.setString(8, obj.getArea());
				super.statement.setString(9, obj.getTema());
				super.statement.execute();
				super.resultSet = super.statement.getGeneratedKeys();
				
				if (resultSet.next()) {
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
	public void remover(Livro obj) {
		if (!obj.equals(null)) {
			super.connection = new Conexao().getConexao();
			String sql = "DELETE FROM livro WHERE idlivro = ?";
			
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
	public void atualizar(Livro obj) {
		if (!obj.equals(null)) {
			super.connection = new Conexao().getConexao();
			String sql = "UPDATE livro SET isbn = ?, titulo= ?, autor = ?, editora = ?, anoPublicacao = ?, "
					+ "edicao = ?, numeroPagina = ?, area = ?, tema = ?"
					+"WHERE idlivro = ?";
			try {
				super.statement = super.connection.prepareStatement(sql);
				super.statement.setString(1, obj.getIsbn());
				super.statement.setString(2, obj.getTitulo());
				super.statement.setString(3, obj.getAutor());
				super.statement.setString(4, obj.getEditora());
				super.statement.setString(5, obj.getAnoPublicacao());
				super.statement.setString(6, obj.getEdicao());
				super.statement.setInt(7, obj.getNumeroPagina());
				super.statement.setString(8, obj.getArea());
				super.statement.setString(9, obj.getTema());
				
				super.statement.execute();
				super.closeConnections();
			}catch (SQLException e) {
				e.printStackTrace();
			}
	
		
	}

	}
}
