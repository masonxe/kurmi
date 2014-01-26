package net.abcdroid.kurmi.a.ui;

import java.util.List;

import net.abcdroid.kurmi.a.R;
import net.abcdroid.kurmi.a.db.ManagerDB;
import net.abcdroid.kurmi.a.db.dao.LibroDAO;
import net.abcdroid.kurmi.a.model.Libro;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListarLibros extends Activity { 
	
	private List<Libro> lstLibros; 
	private ListView lstViewLibros;
	private AdaptadorLibros adaptador;
	
	@Override
	public void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		setContentView(R.layout.listar_libros);
		
	    lstViewLibros = (ListView)findViewById(R.id.LstLibros);
        registerForContextMenu(lstViewLibros);
        
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		SQLiteDatabase db = ManagerDB.getDatabaseConnection(this);
		LibroDAO dao = new LibroDAO(db);
		lstLibros = dao.getLibros();
		db.close();
		
		adaptador = new AdaptadorLibros(this);
		lstViewLibros.setAdapter(adaptador);
		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)	{
	    super.onCreateContextMenu(menu, v, menuInfo);
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu_ctx_lista, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
	 
	    final AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
	    Intent intent ;
	    
	    switch (item.getItemId()) {
	    	
	        case R.id.Editar:
	        	Libro libro = lstLibros.get(info.position);
	        	
	        	Log.i(MainActivity.TAG, "libro dni : "+libro.getDniParticipante());
	        	
	        	intent = new Intent(this, FormularioLibro.class);
	        	intent.putExtra("libro",libro);
	        	startActivity(intent);
	        	
	            return true;
	        case R.id.Eliminar:
	        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle("Confirmación");
				builder.setMessage("¿Seguro que desea eliminar este libro?");
				builder.setCancelable(true);
				builder.setPositiveButton("Si", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which){
						SQLiteDatabase db = ManagerDB.getDatabaseConnection(ListarLibros.this);
						LibroDAO dao = new LibroDAO(db);
						dao.delete(lstLibros.get(info.position).getIdLibro());
						db.close();
						
						lstLibros.remove(info.position);
						adaptador.notifyDataSetChanged();
						
						Toast.makeText(getApplicationContext(), "Libro eliminado", Toast.LENGTH_SHORT).show();
					}
				});
				
				builder.setNegativeButton("No", new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which)
					{
						dialog.cancel();
					}
				});
				
				
				builder.create();
				builder.show();
	        	
	            return true;
	        default:
	            return super.onContextItemSelected(item);
	    }
	}
	
    @SuppressWarnings("rawtypes")
	class AdaptadorLibros extends ArrayAdapter {
    	
    	Activity context;
    	
    	@SuppressWarnings("unchecked")
		AdaptadorLibros(Activity context) {
    		super(context, R.layout.list_item_libro, lstLibros);
    		this.context = context;
    	}
    	
    	@Override
    	public View getView(int position, View convertView, ViewGroup parent) {
    		
    		View item = convertView;
    	    ViewHolder holder;
    	 
    	    if(item == null){
    	    	LayoutInflater inflater = context.getLayoutInflater();
    	        item = inflater.inflate(R.layout.list_item_libro, null);
    	 
    	        holder = new ViewHolder();
    	        holder.titulo = (TextView)item.findViewById(R.id.LblTitulo);
    	        holder.autor = (TextView)item.findViewById(R.id.LblAutor);
    	 
    	        item.setTag(holder);
    	    }else{
    	    	holder = (ViewHolder)item.getTag();
    	    }
    	    
    	    holder.id = lstLibros.get(position).getIdLibro();
    	   
    	    
    	    if(lstLibros.get(position).getIdMySQL() != null){
    	    	holder.titulo.setText(lstLibros.get(position).getTitulo());
    	    }else{
    	    	holder.titulo.setText(lstLibros.get(position).getTitulo()+" - [No Sync]");
    	    }
    	    
    	    holder.autor.setText("Autor: "+lstLibros.get(position).getAutor()+", "+lstLibros.get(position).getPaginas()+"p.");
			return(item);
		}
    }
    
    static class ViewHolder {
    	int id;
        TextView titulo;
        TextView autor;
    }
    
}
