package serviceREST;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dto.SolicitudArticuloDTO;

public class SolicitudCompraFabricaClient {

	public static void conexion(List <SolicitudArticuloDTO> articuloDTOs){
		
		try {
			String URL = "http://localhost:8080/TPA-WEB/rest/service/solicitudCompra";
			Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
			String JSON = prettyGson.toJson(articuloDTOs);
			URL url;
			System.out.println("URL: " + URL + JSON);
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
