package org.kurmi.w.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.kurmi.w.db.local.ConexionDB;
import org.kurmi.w.model.Libro;

public class LibroDao implements ILibroDao {
	
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement pstmt; 

	@Override
	public int save(Libro libro) throws SQLException {
		int idInserted = 0;
		try {
			conn = new ConexionDB().getConexion();
			pstmt = conn.prepareStatement("INSERT into Libros(titulo, autor, paginas, calificacion, dniParticipante, version, idDevice, idSQLite) values (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, libro.getTitulo());
			pstmt.setString(2, libro.getAutor());
			pstmt.setInt(3, libro.getPaginas());
			pstmt.setInt(4, libro.getCalificacion());
			pstmt.setString(5, libro.getDniParticipante());
			pstmt.setInt(6, libro.getVersion());
			pstmt.setString(7, libro.getIdDevice());
			pstmt.setString(8, libro.getIdSQLite());
			pstmt.executeUpdate();
			
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
		          idInserted = rs.getInt(1);
		    }
			
			//System.out.println("Se inserto : "+idInserted);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn.close();
			pstmt.close();
		}
		return idInserted;
	}

	@Override
	public int update(Libro libro) throws SQLException {
		int nup = 0;
		try {
			conn = new ConexionDB().getConexion();
			pstmt = conn.prepareStatement("UPDATE Libros set titulo = ?, autor = ?, paginas = ?, calificacion = ?, version = ? where idLibro = ?");
			pstmt.setString(1, libro.getTitulo());
			pstmt.setString(2, libro.getAutor());
			pstmt.setInt(3, libro.getPaginas());
			pstmt.setInt(4, libro.getCalificacion());
			pstmt.setInt(5, libro.getVersion());
			pstmt.setInt(6, libro.getIdLibro());
			
			nup = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn.close();
			pstmt.close();
		}
		return nup;
	}

	@Override
	public int delete(int id) throws SQLException {
		int ndel = 0;
		try {
			conn = new ConexionDB().getConexion();
			pstmt = conn.prepareStatement("DELETE from Libros where idLibro = ?");
			pstmt.setInt(1, id);
			ndel = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			conn.close();
			pstmt.close();
		}
		return ndel;
	}
	
	
	@Override
	public List<Libro> getAll() throws SQLException {
		List<Libro> paises = new ArrayList<Libro>();
		
		try {
			conn = new ConexionDB().getConexion();
			pstmt = conn.prepareStatement("SELECT * from Libros ");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Libro libro = new Libro();
				libro.setIdLibro(rs.getInt(1));
				libro.setTitulo(rs.getString(2));
				libro.setAutor(rs.getString(3));
				libro.setPaginas(rs.getInt(4));
				libro.setCalificacion(rs.getInt(5));
				libro.setDniParticipante(rs.getString(6));
				libro.setVersion(rs.getInt(7));
				libro.setIdDevice(rs.getString(8));
				libro.setIdSQLite(rs.getString(9));
				paises.add(libro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return paises;
	}

	@Override
	public Libro get(int id) throws SQLException {
		
        Libro libro = null;
		
		try {
			conn = new ConexionDB().getConexion();
			pstmt = conn.prepareStatement("SELECT * from Libros WHERE idLibro = ?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				libro = new Libro();
				libro.setIdLibro(rs.getInt(1));
				libro.setTitulo(rs.getString(2));
				libro.setAutor(rs.getString(3));
				libro.setPaginas(rs.getInt(4));
				libro.setCalificacion(rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return libro;
	}

	
}
