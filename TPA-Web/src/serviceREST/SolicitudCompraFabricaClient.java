package serviceREST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Iterator;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import dto.ItemSolicitudCompraDTO;
import dto.SolicitudCompraDTO;

public class SolicitudCompraFabricaClient {


	public static void conexion(SolicitudCompraDTO solicitudCompraDTOs) {
		
		Gson prettyGson = new GsonBuilder().create();
		URL url;
		try {
			url = new URL( "http://localhost:8080/TPA-Web-0.0.1-SNAPSHOT/rest/solicitud/solicitudCompra");
		
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.setRequestMethod("POST");

			
			System.out.println("json: " + prettyGson.toJson(solicitudCompraDTOs));
			OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
			out.write(prettyGson.toJson(solicitudCompraDTOs));
			out.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String response = IOUtils.toString(connection.getInputStream());
			while (in.readLine() != null) {
				System.out.println(response);
			
			in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
