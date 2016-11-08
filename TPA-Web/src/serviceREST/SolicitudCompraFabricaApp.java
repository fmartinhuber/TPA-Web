package serviceREST;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import dto.SolicitudArticuloDTO;
import serviceMessages.ProductorFabricaMessage;

@Path("/service")
public class SolicitudCompraFabricaApp {

	@GET @Path("/prueba") @Produces({ "text/plain" })
	public String hola() {
		
		SolicitudArticuloDTO solicitudArticulo = null; 
		ProductorFabricaMessage asd = new ProductorFabricaMessage();
		asd.sendMessage(solicitudArticulo);
		return "prueba!";
	}
	
}
