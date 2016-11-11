package serviceREST;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.gson.Gson;

import dto.ArticuloDTO;
import dto.SolicitudArticuloDTO;
import dto.SolicitudCompraDTO;
import serviceMessages.ProductorFabricaMessage;

@Path("/service")
public class SolicitudCompraFabricaApp {

	@GET
	@Path("/articulo")
//	@Path("/{solicitudCompra}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public String articulo(@DefaultValue("prueeeeba") @QueryParam("articulo") String json) {
		
		System.out.println(json.toString());
		
		try {
			String decodedValue1 = URLDecoder.decode(json, StandardCharsets.UTF_8.name());
			Gson gson = new Gson ();
			System.out.println("json: " + json.toString());
			System.out.println("decoded: " + decodedValue1.toString());
			ArticuloDTO articuloDTO = gson.fromJson(json, ArticuloDTO.class);
			return articuloDTO.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
}
