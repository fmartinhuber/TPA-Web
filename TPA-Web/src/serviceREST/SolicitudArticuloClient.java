package serviceREST;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import dto.ArticuloDTO;
import dto.ItemSolicitudArticuloDTO;
import dto.SolicitudArticuloDTO;

public class SolicitudArticuloClient {

	public static void conexion(SolicitudArticuloDTO solicitudArticuloDTO) {

		try {
			String URL = "http://localhost:8080/TPA-Web-0.0.1-SNAPSHOT/rest/articulo/solicitudArticulos?articulo=";
			
//			Gson prettyGson = new GsonBuilder().serializeNulls().create();
			for (Iterator iterator = solicitudArticuloDTO.getItemsSolicitudArticulo().iterator(); iterator.hasNext();) {
				ItemSolicitudArticuloDTO itemArticuloSol = (ItemSolicitudArticuloDTO) iterator.next();
				JsonObject jsonObject = new JsonObject();
				
				jsonObject.addProperty("idDeposito", "G12");
				jsonObject.addProperty("codigo", itemArticuloSol.getArticulo().getCodArticulo());
				jsonObject.addProperty("cantidad", itemArticuloSol.getArticulo().getCantidadDisponible());
				URL url;
				String ecodedValue1 = URLEncoder.encode(jsonObject.toString(), StandardCharsets.UTF_8.name());
				System.out.println("URL: " + URL + ecodedValue1);
				url = new URL(URL + ecodedValue1);
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				
				if (urlConnection.getResponseCode() != 200) {
					throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
				}
			}
				
//			System.out.println("JsonObject: " + JsonObject.toString());
//			String JSON = jsonObject.toString();
//			System.out.println("JSON: " + JSON.toString());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}