package net.abcdroid.kurmi.a.db.dao;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import net.abcdroid.kurmi.a.db.tables.LibrosTable;
import net.abcdroid.kurmi.a.db.tables.LibrosTable.LibrosColumns;
import net.abcdroid.kurmi.a.model.Libro;
import net.abcdroid.kurmi.a.ui.MainActivity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

public class LibroDAO {

	private SQLiteDatabase db;

	public LibroDAO(SQLiteDatabase db){
		this.db = db;
	}
	
	/**
	 * Retorna el ID del nuevo registro
	 * @param libro
	 * @return
	 */
	public long save(Libro libro){	

		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put(LibrosColumns.TITULO, libro.getTitulo());
		nuevoRegistro.put(LibrosColumns.AUTOR, libro.getAutor());
		nuevoRegistro.put(LibrosColumns.PAGINAS, libro.getPaginas());
		nuevoRegistro.put(LibrosColumns.CALIFICACION, libro.getCalificacion());
		nuevoRegistro.put(LibrosColumns.DNI_PARTICIPANTE, libro.getDniParticipante());
		nuevoRegistro.put(LibrosColumns.ID_DEVICE, MainActivity.ID_DEVICE);
		nuevoRegistro.put(LibrosColumns.VERSION, 1);
		
		Log.d(MainActivity.TAG, "LibroDAO - save - [ID_DEVICE = "+libro.getIdDevice()+"] - "+"IdMySQL : "+libro.getIdMySQL()+", version : 1");
		
		return db.insert(LibrosTable.TABLE_NAME, null, nuevoRegistro);	
	}
	

	/**
	 * Retorna el numero de filas afectadas, no se act id_device 
	 * @param libro
	 * @return
	 */
	public long update(Libro libro){	
		
		ContentValues nuevoRegistro = new ContentValues();
		nuevoRegistro.put(LibrosColumns.TITULO, libro.getTitulo());
		nuevoRegistro.put(LibrosColumns.AUTOR, libro.getAutor());
		nuevoRegistro.put(LibrosColumns.PAGINAS, libro.getPaginas());
		nuevoRegistro.put(LibrosColumns.CALIFICACION, libro.getCalificacion());
		nuevoRegistro.put(LibrosColumns.DNI_PARTICIPANTE, libro.getDniParticipante());
		
		nuevoRegistro.put(LibrosColumns.VERSION, libro.getVersion());
		nuevoRegistro.put(LibrosColumns.ID_MYSQL, libro.getIdMySQL());
		
		Log.d(MainActivity.TAG, "LibroDAO - update - [ID_DEVICE = "+libro.getIdDevice()+"] - "+"IdMySQL : "+libro.getIdMySQL()+", version : "+libro.getVersion());
		
		return db.update(LibrosTable.TABLE_NAME, nuevoRegistro, BaseColumns._ID+" = ? ", new String[]{String.valueOf(libro.getIdLibro())} );
	
	}
	
	/**
	 * Actualiza los ids provenientes de la syncronizacion
	 * @param libro
	 * @return
	 */
	public void updateIds(JSONArray array){	
		
    	for (int i = 0; i < array.length(); i++) {
    		
    		JSONObject book;
			try {
				
				book = array.getJSONObject(i);
				ContentValues nuevoRegistro = new ContentValues();
	    		nuevoRegistro.put(LibrosColumns.ID_MYSQL, book.getString("idMySQL"));
	    		db.update(LibrosTable.TABLE_NAME, nuevoRegistro, BaseColumns._ID+" = ? ", new String[]{book.getString("idSQLite")} );
		
			} catch (JSONException e) {
				e.printStackTrace();
			}
   		
		}
    	
	}
	
	public boolean delete(int idLibro){	
		 if(db.delete(LibrosTable.TABLE_NAME, BaseColumns._ID+" = "+idLibro, null) > 0){
			 return true;
		 }
		 return false;
	}
	
	public Libro get(int id){
		
		Libro libro = null;
    	Cursor c = db.query(LibrosTable.TABLE_NAME,
    			new String[]{
    			BaseColumns._ID,
    			LibrosColumns.TITULO,
    			LibrosColumns.AUTOR,
    			LibrosColumns.PAGINAS,
    			LibrosColumns.CALIFICACION, 
    			LibrosColumns.DNI_PARTICIPANTE}, 
    			BaseColumns._ID+" = ?", new String[]{String.valueOf(id)}, null, null, null,"1");
    	    	
    	if(c.moveToFirst() && c!=null){

    		libro = new Libro(c.getInt(0),
    				c.getString(1),
    				c.getString(2),
    				c.getInt(3),
    				c.getInt(4));

    	}
    	
    	if(!c.isClosed()){
    		c.close();
    	}
    	
    	return libro;
    }
	

	public List<Libro> getLibros() {
		List<Libro> lstLibros = new ArrayList<Libro>();
    	
    	String sql = "select * from " + LibrosTable.TABLE_NAME;
    	Cursor c = db.rawQuery(sql, new String[]{});
    	if(c.moveToFirst()){
    		do{
    			Libro libro = null;
    			if(c!=null){
    				libro = new Libro(c.getInt(0),
    	    				c.getString(1),
    	    				c.getString(2),
    	    				c.getInt(3),
    	    				c.getInt(4));
    				
    				libro.setDniParticipante(c.getString(5));
    				libro.setIdMySQL(c.getString(6));
    				libro.setVersion(c.getInt(7));
    				libro.setIdDevice(c.getString(8));
    				
    				
    				Log.d(MainActivity.TAG, "dni : "+c.getString(5));
    				Log.d(MainActivity.TAG, "idMysql : "+c.getString(6));
    				Log.d(MainActivity.TAG, "version : "+c.getString(7));
    			}
    			if(libro != null){
    				lstLibros.add(libro);
    			}
    			
    		}while(c.moveToNext());
    		
    		if(!c.isClosed()){
    			c.close();
    		}
    		
    	}
    	
    	return lstLibros;
	}
	
	public List<Libro> getLibrosNoSincronizados() {
		List<Libro> lstLibros = new ArrayList<Libro>();
    	
    	String sql = "select * from " + LibrosTable.TABLE_NAME + " where "+LibrosColumns.ID_MYSQL+" is null";
    	Cursor c = db.rawQuery(sql, new String[]{});
    	if(c.moveToFirst()){
    		do{
    			Libro libro = null;
    			if(c!=null){
    				libro = new Libro(c.getInt(0),
    	    				c.getString(1),
    	    				c.getString(2),
    	    				c.getInt(3),
    	    				c.getInt(4));
    				
    				libro.setDniParticipante(c.getString(5));
    				libro.setIdMySQL(c.getString(6));
    				libro.setVersion(c.getInt(7));
    				libro.setIdDevice(c.getString(8));
    			}
    			if(libro != null){
    				lstLibros.add(libro);
    			}
    			
    		}while(c.moveToNext());
    		
    		if(!c.isClosed()){
    			c.close();
    		}
    		
    	}
    	
    	return lstLibros;
	}
	
	public List<String> getCadenaLibros() {
		List<String> lstLibros = new ArrayList<String>();
    	
    	String sql = "select * from " + LibrosTable.TABLE_NAME;
    	Cursor c = db.rawQuery(sql, new String[]{});
    	if(c.moveToFirst()){
    		do{
    			String libro = null;
    			if(c!=null){
    				libro = "["+c.getInt(0)+"] Titulo: "+c.getString(1) + ", Autor: "+c.getString(2) + ", Páginas: "+c.getInt(3)+", Calif: "+c.getInt(4);
    			}
    			if(libro != null){
    				lstLibros.add(libro);
    			}
    			
    		}while(c.moveToNext());
    		
    		if(!c.isClosed()){
    			c.close();
    		}
    		
    	}
    	
    	return lstLibros;
	}
	
	
}
