package serviceREST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

import com.google.gson.JsonObject;

import dto.ItemSolicitudArticuloDTO;
import dto.SolicitudArticuloDTO;

public class SolicitudArticuloClient {

	public static void conexion(SolicitudArticuloDTO solicitudArticuloDTO) {

		try {
			
			for (Iterator iterator = solicitudArticuloDTO.getItemsSolicitudArticulo().iterator(); iterator.hasNext();) {
				ItemSolicitudArticuloDTO itemArticuloSol = (ItemSolicitudArticuloDTO) iterator.next();
				JsonObject jsonObject = new JsonObject();
				String urlString= "http://172.16.164.51:8080/DespachoJMS/api/json/solicitudArticulo";
//				URL url = new URL("http://172.16.164.51:8080/DespachoJMS/apis/json/solicitudArticulo");
				URL url = new URL(urlString);
//				URL url = new URL ("http://192.168.1.238:8080/TPA-Web-0.0.1-SNAPSHOT/rest/articulo/solicitudArticulo");;
//				if(solicitudArticuloDTO.getIdDespacho().equals("D03")){
//					url = new URL ("http://192.168.1.238:8080/TPA-Web-0.0.1-SNAPSHOT/rest/articulo/solicitudArticulo");
//				}else if(solicitudArticuloDTO.getIdDespacho().equals("D05")){
//					url = new URL ("http://192.168.1.238:8080/TPA-Web-0.0.1-SNAPSHOT/rest/articulo/solicitudArticulo");
//				}else if(solicitudArticuloDTO.getIdDespacho().equals("G03")){
//					url = new URL ("http://192.168.1.238:8080/TPA-Web-0.0.1-SNAPSHOT/rest/articulo/solicitudArticulo");
//				}else if(solicitudArticuloDTO.getIdDespacho().equals("G03")){
//					url = new URL ("http://192.168.1.238:8080/TPA-Web-0.0.1-SNAPSHOT/rest/articulo/solicitudArticulo");
//				}
				jsonObject.addProperty("idDeposito", "G12");
				jsonObject.addProperty("codigoArticulo", itemArticuloSol.getArticulo().getCodArticulo());
				jsonObject.addProperty("cantidad", itemArticuloSol.getCantidad());
//				String ecodedValue1 = URLEncoder.encode(jsonObject.toString(), StandardCharsets.UTF_8.name());
//				System.out.println("URL: " + URL + ecodedValue1);
//				url = new URL(URL + ecodedValue1);
//				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				
//				if (urlConnection.getResponseCode() != 200) {
//					throw new RuntimeException("Error de conexión: " + urlConnection.getResponseCode());
//				}
				
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				String JSON = jsonObject.toString();
				out.write(JSON.toString());
				out.close();
				System.out.println("URL: " + urlString+ "\nJSON: " + JSON);
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				while (in.readLine() != null) {
					System.out.println("\nSolicitudArticuloClient Invoked Successfully..");
				}
			}
				
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}