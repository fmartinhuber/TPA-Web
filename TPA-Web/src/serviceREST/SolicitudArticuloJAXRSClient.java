package serviceREST;

import java.net.HttpURLConnection;
import java.net.URL;

public class SolicitudArticuloJAXRSClient {
	public static void main(String[] args) throws Exception {
		
		URL url = new URL("http://localhost:8080/TPA-WEB/rest/service/PEPE");
		
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		
		if (urlConnection.getResponseCode() != 200) {
			throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
		}
		
		String response = "ASDASDAS";
		System.out.println("Respuesta: " + response);
	}
}