package org.kurmi.w.db;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConexionDB {

	private Connection c;

	public Connection getConexion() {
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/glibreria");
			c = ds.getConnection();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	public static void main(String[] args) {
		new ConexionDB().getConexion();
	}
}
