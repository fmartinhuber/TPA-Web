package serviceREST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import dto.SolicitudCompraDTO;
import serviceMessages.ProductorFabricaMessage;

@Path("/solicitud")
public class SolicitudCompraFabricaApp {

	@EJB
	ProductorFabricaMessage productor;
	
	@POST
	@Path("/solicitudCompra")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String solicitudCompra(InputStream incomingData) {
		
		StringBuilder crunchifyBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				crunchifyBuilder.append(line);
			}
			System.out.println("LLEGO: " + crunchifyBuilder.toString());
//			Gson gson = new Gson ();
//			String json = crunchifyBuilder.toString();
//			System.out.println("json: " + json.toString());
//			SolicitudCompraDTO solicitudCompraDTO = gson.fromJson(json, SolicitudCompraDTO.class);
//			productor.sendMessage(solicitudCompraDTO);
//			return solicitudCompraDTO.toString();
			Gson gson = new Gson ();
			String json = crunchifyBuilder.toString();
			System.out.println("json: " + json.toString());
			SolicitudCompraDTO solicitudCompraDTO = gson.fromJson(json, SolicitudCompraDTO.class);
			productor.sendMessage(solicitudCompraDTO);
			return solicitudCompraDTO.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		return Response.status(200).entity(crunchifyBuilder.toString()).build();
		return crunchifyBuilder.toString();
		
//		System.out.println(json.toString());
//		
//		try {
//			String decodedValue1 = URLDecoder.decode(json, StandardCharsets.UTF_8.name());
//			Gson gson = new Gson ();
//			System.out.println("json: " + json.toString());
//			System.out.println("decoded: " + decodedValue1.toString());
//			SolicitudCompraDTO solicitudCompraDTO = gson.fromJson(json, SolicitudCompraDTO.class);
//			productor.sendMessage(solicitudCompraDTO);
//			return solicitudCompraDTO.toString();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return "";
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
