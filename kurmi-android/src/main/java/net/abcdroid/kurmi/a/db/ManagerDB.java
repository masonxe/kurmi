package net.abcdroid.kurmi.a.db;

import net.abcdroid.kurmi.a.db.tables.LibrosTable;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
 
public class ManagerDB extends SQLiteOpenHelper { 
 
    public ManagerDB(Context contexto, String nombre,CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }
    
    public static SQLiteDatabase getDatabaseConnection(Context c){
		return new ManagerDB(c, "glibros.sqlite", null, 1).getWritableDatabase();
	}

    @Override
    public void onCreate(SQLiteDatabase db) {
    	LibrosTable.onCreate(db);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //NOTA: De momento eliminaremos la tabla anterior y recrearemos de nuevo vacia.
        //      Sin embargo lo normal sera que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este metodo deberia ser mas elaborado.
 
        //Se elimina la version anterior de las tabla
    	
    	//ClientsTable.onUpgrade(db, oldVersion, newVersion); 
    }
    
}

