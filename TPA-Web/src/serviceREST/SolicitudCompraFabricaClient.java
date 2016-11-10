package serviceREST;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import dto.SolicitudArticuloDTO;
import dto.SolicitudCompraDTO;

public class SolicitudCompraFabricaClient {

	
	@GET
    @Path("ping")
    public String getServerTime() {
        System.out.println("RESTful Service 'MessageService' is running ==> ping");
        return "received ping on "+new Date().toString();
    }
	
	public static void conexion(SolicitudCompraDTO articuloDTOs){
		
		try {
			String URL = "http://localhost:8080/TPA-Web-0.0.1-SNAPSHOT/rest/service/solicitudCompra?solicitud=";
			Gson prettyGson = new GsonBuilder().serializeNulls().create();
//			JsonObject JsonObject = new JsonObject();
//			JsonObject.addProperty("codigo", articuloDTOs.get(0).getCodigo());
//			JsonObject.addProperty("estado", articuloDTOs.get(0).getEstado());
//			System.out.println("JsonObject: " + JsonObject.toString());
			String JSON = prettyGson.toJson(articuloDTOs);
			System.out.println("JSON: " + JSON.toString());
			URL url;
			String ecodedValue1  = URLEncoder.encode(JSON, StandardCharsets.UTF_8.name());
			System.out.println("URL: " + URL + ecodedValue1 );
			url = new URL(URL + ecodedValue1 );
			
			
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//			IOUtils.write("{\"id\" : \"1759\" , \"nombre\" : \"John Doe\" }", url.getOutputStream());
			if (urlConnection.getResponseCode() != 200) {
				throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
