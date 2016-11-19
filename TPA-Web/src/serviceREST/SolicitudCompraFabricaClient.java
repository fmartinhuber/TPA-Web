package serviceREST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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
		
		Gson prettyGson = new GsonBuilder().serializeNulls().create();
		URL url;
		try {
			url = new URL( "http://192.168.0.102:8080/DespachoJMS/api/json/solicitudArticulo");
		
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			JsonObject jsonObject = new JsonObject();
			for (Iterator iterator = solicitudCompraDTOs.getItemsSolicitudesCompra().iterator(); iterator.hasNext();) {
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				ItemSolicitudCompraDTO itemSolicitud = (ItemSolicitudCompraDTO) iterator;
				jsonObject.addProperty("idDeposito", "G12");
				jsonObject.addProperty("codArticulo", itemSolicitud.getArticulo().getCodArticulo());
				jsonObject.addProperty("cantidad", itemSolicitud.getArticulo().getCantidadDisponible());
				String JSON = prettyGson.toJson(solicitudCompraDTOs);
				out.write(JSON.toString());
				out.close();
				
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String response = IOUtils.toString(connection.getInputStream());
				while (in.readLine() != null) {
					System.out.println(response);
				}
				
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
