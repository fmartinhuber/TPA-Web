package serviceREST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dto.SolicitudCompraDTO;

public class SolicitudCompraFabricaClient {

	@GET
	@Path("ping")
	public String getServerTime() {
		System.out.println("RESTful Service 'MessageService' is running ==> ping");
		return "received ping on " + new Date().toString();
	}

	// public static void conexion(SolicitudCompraDTO articuloDTOs){
	//
	// try {
	// String URL =
	// "http://localhost:8080/TPA-Web-0.0.1-SNAPSHOT/rest/service/solicitudCompra";
	// Gson prettyGson = new GsonBuilder().serializeNulls().create();
	//// JsonObject JsonObject = new JsonObject();
	//// JsonObject.addProperty("codigo", articuloDTOs.get(0).getCodigo());
	//// JsonObject.addProperty("estado", articuloDTOs.get(0).getEstado());
	//// System.out.println("JsonObject: " + JsonObject.toString());
	// String JSON = prettyGson.toJson(articuloDTOs);
	// System.out.println("JSON: " + JSON.toString());
	// URL url;
	// String ecodedValue1 = URLEncoder.encode(JSON,
	// StandardCharsets.UTF_8.name());
	// System.out.println("URL: " + URL + ecodedValue1 );
	// url = new URL(URL + ecodedValue1 );
	//
	//
	// HttpURLConnection urlConnection = (HttpURLConnection)
	// url.openConnection();
	//// IOUtils.write("{\"id\" : \"1759\" , \"nombre\" : \"John Doe\" }",
	// url.getOutputStream());
	// if (urlConnection.getResponseCode() != 200) {
	// throw new RuntimeException("Error de conexión: " +
	// urlConnection.getResponseCode());
	// }
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	public static void conexion(SolicitudCompraDTO solicitudArticuloDTOs) {
		
		Gson prettyGson = new GsonBuilder().serializeNulls().create();
		URL url;
		try {
			url = new URL( "http://192.168.1.238:8080/TPA-Web-0.0.1-SNAPSHOT/rest/solicitud/solicitudCompra");
		
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
			
			while (in.readLine() != null) {
				System.out.println("\nCrunchify REST Service Invoked Successfully..");
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
