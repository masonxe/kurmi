package net.abcdroid.kurmi.a.ui;

import net.abcdroid.kurmi.a.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.abcdroid.kurmi.a.Util;
import net.abcdroid.kurmi.a.db.ManagerDB;
import net.abcdroid.kurmi.a.db.dao.LibroDAO;
import net.abcdroid.kurmi.a.db.tables.LibrosTable.LibrosColumns;
import net.abcdroid.kurmi.a.model.Libro;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	public static String TAG = "GLibros";
	private static final String SERVICE_URL = "http://glibreria.ameison.cloudbees.net/rest/libros";
	public static String ID_DEVICE = "";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ID_DEVICE = Util.id(this);
	}

	public void guardar(View view) {
		Intent i = new Intent(this, FormularioLibro.class );
		startActivity(i);
	}

	public void listar(View view) {
		Intent i = new Intent(this, ListarLibros.class );
		startActivity(i);
	}
	
	public void sincronizar(View view) {
		enviarLibrosAMySQL();
	}
	
	private void enviarLibrosAMySQL() {
		try
        {
			WebServiceTask wst = new WebServiceTask(WebServiceTask.POST_TASK, this, "Sincronizando...");
			 
			
			SQLiteDatabase db = ManagerDB.getDatabaseConnection(MainActivity.this);
			LibroDAO dao = new LibroDAO(db);
			List<Libro> lstLibros = dao.getLibrosNoSincronizados();
			db.close();
			
			
			JSONArray libros = new JSONArray();
			
			for (Libro libro : lstLibros) {
				
				JSONObject lib = new JSONObject();
				 
				try {
				    lib.put("idSQLite", libro.getIdLibro());
				    lib.put(LibrosColumns.TITULO, libro.getTitulo());
				    lib.put(LibrosColumns.AUTOR, libro.getAutor());
				    lib.put(LibrosColumns.PAGINAS, libro.getPaginas());
				    lib.put(LibrosColumns.CALIFICACION, libro.getCalificacion());
				    lib.put(LibrosColumns.DNI_PARTICIPANTE, libro.getDniParticipante());
				    lib.put(LibrosColumns.VERSION, libro.getVersion());
				    lib.put(LibrosColumns.ID_DEVICE, libro.getIdDevice());
				    lib.put(LibrosColumns.ID_MYSQL, (libro.getIdMySQL()==null)?"NULL":libro.getIdMySQL());
				    libros.put(lib);
				} catch (JSONException e) {
				    e.printStackTrace();
				}
			}
			
			
			Log.i(MainActivity.TAG, "libros : "+libros.toString());
	        wst.addNameValuePair("libros", libros.toString());
	        wst.addNameValuePair("accion", "GUARDAR");
	 
	        wst.execute(new String[] { SERVICE_URL });
	        
        }
        catch(Exception ex)
        {
        	Log.e("ServicioRest","Error!", ex);
        }
	}

	
	
	public void handleResponse(String idsInserted) {
         
		SQLiteDatabase db = ManagerDB.getDatabaseConnection(MainActivity.this);
		LibroDAO dao = new LibroDAO(db);
		
        try {
        	
        	Log.i(MainActivity.TAG, "Esti viene : "+idsInserted);
        	
        	JSONArray ids = new JSONArray(idsInserted);
        	dao.updateIds(ids);

          
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage(), e);
        }finally{
        	db.close();
        }
        
         
    }
	
	/*
	private void hideKeyboard() {
		 
        InputMethodManager inputManager = (InputMethodManager) MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }*/
	
	private class WebServiceTask extends AsyncTask<String, Integer, String> {
		 
        public static final int POST_TASK = 1;
        public static final int GET_TASK = 2;
         
        private static final String TAG = "WebServiceTask";
 
        // connection timeout, in milliseconds (waiting to connect)
        private static final int CONN_TIMEOUT = 5000;
         
        // socket timeout, in milliseconds (waiting for data)
        private static final int SOCKET_TIMEOUT = 8000;
         
        private int taskType = GET_TASK;
        private Context mContext = null;
        private String processMessage = "Procesando...";
 
        private ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
 
        private ProgressDialog pDlg = null;
 
        public WebServiceTask(int taskType, Context mContext, String processMessage) {
 
            this.taskType = taskType;
            this.mContext = mContext;
            this.processMessage = processMessage;
        }
 
        public void addNameValuePair(String name, String value) {
 
        	
            params.add(new BasicNameValuePair(name, value));
        }
 
        private void showProgressDialog() {
             
            pDlg = new ProgressDialog(mContext);
            pDlg.setMessage(processMessage);
            pDlg.setProgressDrawable(mContext.getWallpaper());
            pDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDlg.setCancelable(false);
            pDlg.show();
 
        }
 
        @Override
        protected void onPreExecute() {
 
            //hideKeyboard();
            showProgressDialog();
 
        }
 
        protected String doInBackground(String... urls) {
 
            String url = urls[0];
            String result = "";
 
            HttpResponse response = doResponse(url);
 
            if (response == null) {
                return result;
            } else {
                try {
                    result = inputStreamToString(response.getEntity().getContent());
                } catch (IllegalStateException e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                } catch (IOException e) {
                    Log.e(TAG, e.getLocalizedMessage(), e);
                }
 
            }
 
            return result;
        }
 
        @Override
        protected void onPostExecute(String response) {
             
            handleResponse(response);
            pDlg.dismiss();
             
        }
         
        // Establish connection and socket (data retrieval) timeouts
        private HttpParams getHttpParams() {
             
            HttpParams htpp = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(htpp, CONN_TIMEOUT);
            HttpConnectionParams.setSoTimeout(htpp, SOCKET_TIMEOUT);
             
            return htpp;
        }
         
        private HttpResponse doResponse(String url) {
             
            // Use our connection and data timeouts as parameters for our
            // DefaultHttpClient
            HttpClient httpclient = new DefaultHttpClient(getHttpParams());
 
            HttpResponse response = null;
 
            try {
                switch (taskType) {
 
                case POST_TASK:
                    HttpPost httppost = new HttpPost(url);
                    // Add parameters
                    httppost.setEntity(new UrlEncodedFormEntity(params));
                    response = httpclient.execute(httppost);
                    break;
                case GET_TASK:
                    HttpGet httpget = new HttpGet(url);
                    response = httpclient.execute(httpget);
                    break;
                }
            } catch (Exception e) {
 
                Log.e(TAG, e.getLocalizedMessage(), e);
 
            }
 
            return response;
        }
         
        private String inputStreamToString(InputStream is) {
 
            String line = "";
            StringBuilder total = new StringBuilder();
 
            // Wrap a BufferedReader around the InputStream
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
 
            try {
                // Read response until the end
                while ((line = rd.readLine()) != null) {
                    total.append(line);
                }
            } catch (IOException e) {
                Log.e(TAG, e.getLocalizedMessage(), e);
            }
 
            // Return full string
            return total.toString();
        }
 
    }
	
	

}
