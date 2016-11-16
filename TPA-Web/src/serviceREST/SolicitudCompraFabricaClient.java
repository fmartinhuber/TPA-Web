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
