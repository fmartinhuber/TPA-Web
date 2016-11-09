package serviceREST;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/service")
public class SolicitudCompraFabricaApp {

	@GET
	@Path("/solicitudCompra")
//	@Path("/{solicitudCompra}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public String solicitudCompra(@DefaultValue("Unknown address") @QueryParam("solicitud") String json) {
		
		System.out.println(json.toString());
//		SolicitudArticuloDTO solicitudArticulo = null; 
//		ProductorFabricaMessage asd = new ProductorFabricaMessage();
//		asd.sendMessage(solicitudArticulo);
		return json;
	}
	
}
