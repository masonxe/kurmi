package org.kurmi.w.dao;

import java.sql.SQLException;
import java.util.List;

import org.kurmi.w.model.Libro;

public interface ILibroDao {

	public int save(Libro libro) throws SQLException;
	public int update(Libro libro) throws SQLException;
	public int delete(int id) throws SQLException;
	public List<Libro> getAll() throws SQLException;
	public Libro get(int id) throws SQLException;
}
