package net.abcdroid.kurmi.w.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;

import net.abcdroid.kurmi.w.dao.ILibroDao;
import net.abcdroid.kurmi.w.dao.LibroDao;
import net.abcdroid.kurmi.w.model.Libro;
import net.abcdroid.kurmi.w.model.Sync;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Path("/libros")
public class LibrosRemote {

	public static final String STATUS_SUCCESS = "success";
	public static final String STATUS_ERROR = "error";
	
	ILibroDao iLibroDao = new LibroDao();
		
	// Return the list of Libros for applications
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Libro> getLibros() throws SQLException {
		List<Libro> libros = iLibroDao.getAll();
		return libros;  
	}
		
	//Devuelve un libro por id
	@Path("{id}")
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Libro getlibro(@PathParam("id") String id) {
		Libro libro = null;
		try {
			libro = iLibroDao.get(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(libro==null)
			throw new RuntimeException("Get: Libro with " + id +  " not found");
		
		return libro;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String saveOrUpdate(
		  @FormParam("libros") String libros,
	      @FormParam("accion") String accion,
	      @Context HttpServletResponse servletResponse) throws IOException {

		  Gson myGson = new Gson();

		  List<Sync> lstSincronizados = new ArrayList<Sync>();
		  if(accion.equalsIgnoreCase("GUARDAR")){
			  
		      JsonParser parser = new JsonParser();
		      JsonArray booksArray = parser.parse(libros.toString()).getAsJsonArray();
		      
		      for(JsonElement book : booksArray ){
		    	  
		    	  Libro libro  = myGson.fromJson(book, Libro.class);
		    	  try {
		    		  String idMySQL = ""+iLibroDao.save(libro);
		    		  Sync s = new Sync(idMySQL, libro.getIdSQLite());
		    		  lstSincronizados.add(s);
			      }catch (SQLException e) {
					e.printStackTrace();
			      }
		      }
		      
			  return myGson.toJson(lstSincronizados);
		    
	      }else if(accion.equalsIgnoreCase("ACTUALIZAR")) {
	    	  /*Libro libro = new Libro(titulo, autor, paginas, calificacion);
		      libro.setVersion(version);
		      libro.setIdDevice(idDevice);
		      libro.setIdSQLite(idSQLite);
		      
		      try {
				  idInserted = iLibroDao.update(libro);
			  } catch (SQLException e) {
				  e.printStackTrace();
			  }
				
			  return idInserted;
	  		*/
		  }
		  
		  return "";
	}
	
	
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	public String savelibro(JAXBElement<Libro> libro) {
		String res = "";
		Libro l = libro.getValue();
		try {
			res = ""+iLibroDao.save(l);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	//Update
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public String updateLibro(JAXBElement<Libro> libro) {
		String res = "";
		Libro l = libro.getValue();
		
		try {
			res = ""+ iLibroDao.update(l);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	
	@DELETE @Path("{id}")
    @Produces({ MediaType.APPLICATION_JSON })
    public void remove(@PathParam("id") int id) {
		try {
			int del = iLibroDao.delete(id);
			if(del == 0)
				throw new RuntimeException("Delete: Libro with " + id +  " not found");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	
}
