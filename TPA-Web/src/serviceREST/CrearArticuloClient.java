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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import dto.ArticuloDTO;

public class CrearArticuloClient {
	
	public static void conexion(ArticuloDTO articuloDTO){
				
		
		try {
			URL url = new URL("http://192.168.1.45:8080/WebStock/rest/despacho/recibirArticulos");
			Gson prettyGson = new GsonBuilder().serializeNulls().create();
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
			String JSON = prettyGson.toJson(articuloDTO);
			out.write(JSON.toString());
			out.close();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
//			if (urlConnection.getResponseCode() != 200) {
//				throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
//			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void logisticaConexion(ArticuloDTO articuloDTO){
				
		
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