package serviceREST;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonObject;

import dto.ArticuloDTO;

public class CrearArticuloClient {
	
	public static void conexionLogistica(ArticuloDTO articuloDTO){
				
		
		try {
			
			String URL = "http://192.168.1.45:8080/WebStock/rest/despacho/recibirArticulos";
			JsonObject jsonObject = new JsonObject();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			String date = sdf.format(new Date());
			jsonObject.addProperty("fecha", date);
			jsonObject.addProperty("tipo", "Deposito");
			jsonObject.addProperty("modulo", "G12");
			jsonObject.addProperty("descripcion", "Se dio de alta el Articulo " + articuloDTO.getCodArticulo());

			
			System.out.println("JSON: " + jsonObject.toString());
			URL url;
			
			url = new URL(URL);
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