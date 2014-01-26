package net.abcdroid.kurmi.w.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.abcdroid.kurmi.w.dao.LibroDao;
import net.abcdroid.kurmi.w.model.Libro;

public class LibrosController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LibroDao dao = new LibroDao();
		try {
			List<Libro> lstLibros =  dao.getAll();
			System.out.println("Sise : "+lstLibros.size());
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

}
