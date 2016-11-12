package serviceREST;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import dto.ArticuloDTO;

public class CrearArticuloClient {
	
	public static void conexion(ArticuloDTO articuloDTO){
		
		
		try {
			String URL = "http://192.168.1.45:8080/WebStock/rest/despacho/recibirArticulos";
			Gson prettyGson = new GsonBuilder().serializeNulls().create();
//			JsonObject jsonObject = new JsonObject();
//			jsonObject.addProperty("codigo", articuloDTO);
//			System.out.println("JsonObject: " + JsonObject.toString());
			String JSON = prettyGson.toJson(articuloDTO);
			System.out.println("JSON: " + JSON.toString());
			URL url;
			String ecodedValue1  = URLEncoder.encode(JSON.toString(), StandardCharsets.UTF_8.name());
			System.out.println("URL: " + URL + ecodedValue1 );
			
			url = new URL(URL + ecodedValue1);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			
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