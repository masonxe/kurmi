package org.kurmi.w.db.local;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

	private Connection c;

	public Connection getConexion()  {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://localhost/glibros", "root", "root");
			System.out.println("Om");
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return c;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		new ConexionDB().getConexion();
	}
}
