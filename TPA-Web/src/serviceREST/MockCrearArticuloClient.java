package serviceREST;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import dto.SolicitudCompraDTO;

@Path("/articulo")
public class MockCrearArticuloClient {

	@GET
	@Path("/crearArticulo")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public String crearArticulo(@DefaultValue("prueeeeba") @QueryParam("solicitud") String json) {
		
		System.out.println(json.toString());
		
		try {
			String decodedValue1 = URLDecoder.decode(json, StandardCharsets.UTF_8.name());
			Gson gson = new Gson ();
			System.out.println("json: " + json.toString());
			System.out.println("decoded: " + decodedValue1.toString());
			SolicitudCompraDTO solicitudArticulo = gson.fromJson(json, SolicitudCompraDTO.class);
			return solicitudArticulo.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@GET
	@Path("/verify")
	@Produces(MediaType.TEXT_PLAIN)
	public Response verifyRESTService(InputStream incomingData) {
		String result = "CrunchifyRESTService Successfully started..";
 
		// return HTTP response 200 in case of success
		return Response.status(200).entity(result).build();
	}
}
