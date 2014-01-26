package net.abcdroid.kurmi.a.ui;

import net.abcdroid.kurmi.a.R;
import net.abcdroid.kurmi.a.db.ManagerDB;
import net.abcdroid.kurmi.a.db.dao.LibroDAO;
import net.abcdroid.kurmi.a.model.Libro;
import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.Toast;

public class FormularioLibro extends Activity implements OnClickListener{
	
	private EditText txtTitulo, txtAutor, txtPaginas, txtDni;
	private RatingBar ratingBar;
	private Button btnGuardar;
	private Libro libro;
	private int intRatingValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_libros);
        
        Bundle data = getIntent().getExtras();
        if(data!=null){
        	libro = data.getParcelable("libro");
        }
        
        txtDni = (EditText) findViewById(R.id.txtDni);
        txtTitulo = (EditText) findViewById(R.id.txtTitulo);
        txtAutor = (EditText) findViewById(R.id.txtAutor);
        txtPaginas = (EditText) findViewById(R.id.txtPaginas);
        ratingBar = (RatingBar) findViewById(R.id.txtCalificacion);
     
    	//if rating value is changed,
    	//display the current rating value in the result (textview) automatically
    	ratingBar.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
    		public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
     
    			intRatingValue = (int) rating + 1;
     
    		}
    	});
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(this);
    }
    
    @Override
    protected void onResume() {
       	super.onResume();
       	if(libro!=null){
       		setTitle("Editar Libro");
       		txtDni.setText(libro.getDniParticipante());
       		txtTitulo.setText(libro.getTitulo());
            txtAutor.setText(libro.getAutor());
            txtPaginas.setText(""+libro.getPaginas());
            ratingBar.setRating(libro.getCalificacion());
       	}else{
       		Log.i("TAG", "Libro Null");
       	}
    }

    private long actualizarLibro(int idLibro){
    	
    	SQLiteDatabase db = ManagerDB.getDatabaseConnection(this);
    	LibroDAO dao = new LibroDAO(db);
    	
    	libro.setDniParticipante(txtDni.getText().toString());  
    	libro.setTitulo(txtTitulo.getText().toString());  
    	libro.setAutor(txtAutor.getText().toString());
    	libro.setPaginas(Integer.parseInt(txtPaginas.getText().toString()));
    	libro.setCalificacion(intRatingValue);  
    	

    	long ind = dao.update(libro);
		db.close();
		
    	return ind;
		
    }
 
    private long guardarLibro(){
    	
    	if(!txtDni.getText().toString().equals("") && 
    			!txtTitulo.getText().toString().equals("") && 
    			!txtAutor.getText().toString().equals("") && 
    			!txtPaginas.getText().toString().equals("")){
    		SQLiteDatabase db = ManagerDB.getDatabaseConnection(this);
        	LibroDAO dao = new LibroDAO(db);
        	
        	Libro libro = new Libro();
        	libro.setDniParticipante(txtDni.getText().toString());
        	libro.setTitulo(txtTitulo.getText().toString());
        	libro.setAutor(txtAutor.getText().toString());
        	libro.setPaginas(Integer.parseInt(txtPaginas.getText().toString()));
        	libro.setCalificacion(intRatingValue);
        	long id = dao.save(libro);
        	
    		db.close();
    		return id;
    	
    	}else{
    		
    		Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
    		return 0;
    	}
    	
    	
    	
    }


	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		
		case R.id.btnGuardar:
			if(libro == null){
				if(guardarLibro() > 0 ){
					Toast.makeText(this, "Libro guardado", Toast.LENGTH_SHORT).show();
				}
			}else{
				if(actualizarLibro(libro.getIdLibro()) > 0 ){
					Toast.makeText(this, "Libro actualizado", Toast.LENGTH_SHORT).show();
				}
			}
			break;

		default:
			break; 
		}
		
	}
    
}
