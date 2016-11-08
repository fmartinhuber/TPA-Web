package serviceREST;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import dto.ArticuloDTO;

public class CrearArticuloJAXRSClient {
	
	public static void conexion(ArticuloDTO articuloDTO){
		
		try {
			String URL = "http://localhost:8080/TPA-WEB/rest/service/";
			String JSON = "";
			
			URL url;
			url = new URL(URL + JSON);
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