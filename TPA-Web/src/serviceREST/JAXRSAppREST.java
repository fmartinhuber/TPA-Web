package serviceREST;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Creacion SERVICIO REST
 * @author Martin
 *
 */
@Path("/service")
public class JAXRSAppREST {

	@GET @Path("/hola") @Produces({ "text/plain" })
	public String hola() {
		return "Hola!";
	}
	
	@POST @Path("/saludo") @Produces({ "text/plain" })
	public String saludo(String nombre) {
		return "Hola " + nombre;
	}
	
	
	
}
