package serviceREST;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.google.gson.Gson;

import dto.SolicitudCompraDTO;

public class MockCrearArticuloJAXRSClient {

	@GET
	@Path("/solicitudCompra")
//	@Path("/{solicitudCompra}")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	public String solicitudCompra(@DefaultValue("prueeeeba") @QueryParam("solicitud") String json) {
		
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
}
