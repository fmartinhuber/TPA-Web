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
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import dto.ArticuloDTO;

public class CrearArticuloClient {
	
	public static void conexion(ArticuloDTO articuloDTO){
				
		
		try {
			URL url = new URL("http://192.168.1.238:8080/TPA-Web-0.0.1-SNAPSHOT/rest/articulo/crearArticulo");
//			Gson prettyGson = new GsonBuilder().serializeNulls().create();
			JsonObject jsonObject = new JsonObject();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			String date = sdf.format(new Date());
			LocalDateTime dateTime = LocalDateTime.parse("2016-03-23T18:21");
			jsonObject.addProperty("fecha", date);
			jsonObject.addProperty("tipo", "Deposito");
			jsonObject.addProperty("modulo", "G12");
			jsonObject.addProperty("descripcion", "Se dio de alta el Articulo " + articuloDTO.getCodArticulo());
			
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
			String JSON = jsonObject.toString();
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
	
	/*
	 * {
  "idDeposito": "Gxx",
  "codArticulo": 1234,
  "nombre": "Radio Electrico",
  "descripcion": "Radio Electrico 700w",
  "marca": "Phillips",
  "precio": 1500.0,
  "foto": "http://imgur.com/123456",
  "origen": "China",
  "tipo": "Electro",
  "datosExtra": {
    "fichaTecnica": "una ficha tecnica en texto libre"
  }  
}
	 * 
	 */
	
	public static void conexionDespacho(ArticuloDTO articuloDTO){			
		
		try {
			URL url = new URL("http://192.168.1.238:8080/TPA-Web-0.0.1-SNAPSHOT/rest/articulo/crearArticulo");
//			Gson prettyGson = new GsonBuilder().serializeNulls().create();
			JsonObject jsonObject = new JsonObject();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			String date = sdf.format(new Date());
			jsonObject.addProperty("modulo", "G12");
			jsonObject.addProperty("codigo", articuloDTO.getCodArticulo());
			jsonObject.addProperty("nombre", articuloDTO.getNombre());	
			jsonObject.addProperty("descripcion", articuloDTO.getDescripcion());
			jsonObject.addProperty("marca", articuloDTO.getMarca());
			jsonObject.addProperty("precio", articuloDTO.getPrecio());
			jsonObject.addProperty("foto", articuloDTO.getFoto());
			jsonObject.addProperty("origen", articuloDTO.getOrigen());
			jsonObject.addProperty("tipo", articuloDTO.getTipo());
			
			//jsonObject.addProperty("datosExtra", );
			
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
			String JSON = jsonObject.toString();
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
	
	
	
	
	
	
	
}