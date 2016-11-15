package serviceREST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dto.SolicitudArticuloDTO;
import dto.SolicitudCompraDTO;

public class EntragaArticuloDespachoClient {

	@GET
    @Path("ping")
    public String getServerTime() {
        System.out.println("RESTful Service 'MessageService' is running ==> ping");
        return "received ping on "+new Date().toString();
    }
	
	public static void conexion(SolicitudArticuloDTO solicitudArticuloDTOs){
		
		try {
			URL url = new URL("http://172.16.164.51:8080/DespachoJMS/apis/json/solicitudArticulo");
			Gson prettyGson = new GsonBuilder().serializeNulls().create();
//			JsonObject JsonObject = new JsonObject();
//			JsonObject.addProperty("codigo", articuloDTOs.get(0).getCodigo());
//			JsonObject.addProperty("estado", articuloDTOs.get(0).getEstado());
//			System.out.println("JsonObject: " + JsonObject.toString());
//			String JSON = prettyGson.toJson(solicitudArticuloDTOs);
//			System.out.println("JSON: " + JSON.toString());
//			URL url;
//			String ecodedValue1  = URLEncoder.encode(JSON, StandardCharsets.UTF_8.name());
//			System.out.println("URL: " + URL + ecodedValue1 );
//			url = new URL(URL + ecodedValue1 );
			
//			JsonObject jsonObject = new JsonObject();
//			jsonObject.addProperty("codigo", articuloDTO);
//			System.out.println("JsonObject: " + JsonObject.toString());
//			String JSON = prettyGson.toJson(articuloDTO);
//			System.out.println("JSON: " + JSON.toString());
//			URL url;
//			String ecodedValue1  = URLEncoder.encode(JSON.toString(), StandardCharsets.UTF_8.name());
//			System.out.println("URL: " + URL + ecodedValue1 );
			

			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			String JSON = prettyGson.toJson(solicitudArticuloDTOs);
			out.write(JSON.toString());
			out.close();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			
//			IOUtils.write("{\"id\" : \"1759\" , \"nombre\" : \"John Doe\" }", url.getOutputStream());
//			if (urlConnection.getResponseCode() != 200) {
//				throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
//			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
