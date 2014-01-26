package net.abcdroid.kurmi.a.db.tables;

import java.util.ArrayList;
import java.util.List;

import net.abcdroid.kurmi.a.model.Libro;
import net.abcdroid.kurmi.a.ui.MainActivity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class LibrosTable {
	
	public static final String TABLE_NAME = "Libros";
	
	public static class LibrosColumns implements BaseColumns {
		public static final String DNI_PARTICIPANTE = "dniParticipante";
		public static final String TITULO = "titulo";
		public static final String AUTOR = "autor";
		public static final String PAGINAS = "paginas";
		public static final String CALIFICACION = "calificacion";
		public static final String ID_MYSQL = "idMySQL";
		public static final String VERSION = "version";
		public static final String ID_DEVICE = "idDevice";
		
	}
	
	public static void onCreate(SQLiteDatabase db) {
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE " + LibrosTable.TABLE_NAME + " (");
		sb.append(BaseColumns._ID + " INTEGER PRIMARY KEY, ");
		sb.append(LibrosColumns.TITULO + " TEXT, ");
		sb.append(LibrosColumns.AUTOR + " INTEGER, ");
		sb.append(LibrosColumns.PAGINAS+ " TEXT, ");
		sb.append(LibrosColumns.CALIFICACION + " TEXT, ");
		sb.append(LibrosColumns.DNI_PARTICIPANTE + " TEXT, ");
		sb.append(LibrosColumns.VERSION + " INTEGER, ");
		sb.append(LibrosColumns.ID_DEVICE+ " TEXT, ");
		sb.append(LibrosColumns.ID_MYSQL + " TEXT ");
		sb.append(");");		
		db.execSQL(sb.toString());
		
		llenarTabla(db);
	}

	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + LibrosTable.TABLE_NAME);
	}


	/**
	 * Este metodo es llamado por unica vez al momento de crear la tabla y sirve para llenar la tabla
	 * con los valores iniciales. 
	 * @param db: La conexion a SQLite
	 */
	private static void llenarTabla(SQLiteDatabase db){
		
		
        if(db != null)
        {   
        	Libro lb = new Libro("El Futbol","Julio Soliz",287, 2 , MainActivity.ID_DEVICE, 1);
        	lb.setDniParticipante("45454545");
        	lb.setVersion(1);
        	lb.setIdDevice(MainActivity.ID_DEVICE);
        	List<Libro> lstLibros = new ArrayList<Libro>();   
        	lstLibros.add(lb);
        	
        	for (Libro Libro : lstLibros) {     	
    			ContentValues nuevoRegistro = new ContentValues();
    			nuevoRegistro.put(LibrosColumns.TITULO, Libro.getTitulo());
    			nuevoRegistro.put(LibrosColumns.AUTOR, Libro.getAutor());	
    			nuevoRegistro.put(LibrosColumns.PAGINAS, Libro.getPaginas());	
    			nuevoRegistro.put(LibrosColumns.CALIFICACION, Libro.getCalificacion());	
    			nuevoRegistro.put(LibrosColumns.DNI_PARTICIPANTE, Libro.getDniParticipante());	
    			nuevoRegistro.put(LibrosColumns.ID_DEVICE, Libro.getIdDevice());	
    			nuevoRegistro.put(LibrosColumns.VERSION, Libro.getVersion());	
    			
    			//Insertamos el registro en la base de datos
    			db.insert(LibrosTable.TABLE_NAME, null, nuevoRegistro);
			}
        }
    }
}
