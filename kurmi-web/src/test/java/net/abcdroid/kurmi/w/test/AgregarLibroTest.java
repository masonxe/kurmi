package net.abcdroid.kurmi.w.test;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import net.abcdroid.kurmi.w.util.PSON;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

public class AgregarLibroTest {
	
	public static void main(String[] args) {
		
		ClientConfig config = new DefaultClientConfig();
		Client client = Client.create(config);

		WebResource service = client.resource(getBaseURI());
		
		PSON pson = new PSON();
		pson.put("titulo", "Osama bin Laden");
		pson.put("autor", "Marina");
		pson.put("paginas", "234");
		pson.put("calificacion", "4");
		pson.put("idDevice", "cea6c21c");
		pson.put("dniParticipante", "234343434");
		pson.put("idMySQL", null);
		pson.put("idSQLite", "21");
		pson.put("version", "1");
		
		String jsss = "["+pson.toJSON()+"]";
		
		Form form = new Form();
	    form.add("libros", jsss);
	    form.add("accion", "GUARDAR");

	    ClientResponse  response = service.path("rest").path("libros").type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, form);
	    System.out.println("Form response " + response.getEntity(String.class));
	
	}

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/kurmi-web").build();
	}

}
