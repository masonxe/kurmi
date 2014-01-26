package net.abcdroid.kurmi.w.model;

public class Sync {
	
	private String idMySQL;
	private String idSQLite;
	
	public Sync(String idMySQL, String idSQLite) {
		super();
		this.idMySQL = idMySQL;
		this.idSQLite = idSQLite;
	}
	public String getIdMySQL() {
		return idMySQL;
	}
	public void setIdMySQL(String idMySQL) {
		this.idMySQL = idMySQL;
	}
	public String getIdSQLite() {
		return idSQLite;
	}
	public void setIdSQLite(String idSQLite) {
		this.idSQLite = idSQLite;
	}

}
