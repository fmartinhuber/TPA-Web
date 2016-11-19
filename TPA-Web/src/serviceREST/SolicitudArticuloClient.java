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
			
			String DespachoXX = "http://172.16.164.51:8080/DespachoJMS/apis/json/solicitudArticulo";
			for (Iterator iterator = solicitudArticuloDTO.getItemsSolicitudArticulo().iterator(); iterator.hasNext();) {
				//Daro 17.11: Definir las URL de Despacho
				String DespachoYY = "acaURL";
				String DespachoZZ = "acaURL";
				
				//Obtener el Despacho que se comunica con nosotros
				String obtenerDespacho = solicitudArticuloDTO.getIdDespacho();
				
				//Dependiendo el despacho, llamo a esa URL
				URL url = null;
				if (obtenerDespacho.equals("D11")){
					url = new URL (DespachoXX);
				}
				if (obtenerDespacho.equals("DYY")){
					url = new URL (DespachoYY);
				}
				if (obtenerDespacho.equals("DZZ")){
					url = new URL (DespachoZZ);
				}
				
				if(url != null){
					
					ItemSolicitudArticuloDTO itemArticuloSol = (ItemSolicitudArticuloDTO) iterator.next();
					JsonObject jsonObject = new JsonObject();
					
					//Daro 17.11: Aca probamos mocks
					//URL Anterior: http://172.16.164.51:8080/DespachoJMS/api/json/solicitudArticulo
					//Despacho Nube: http://files.big-net.com.ar:8080/Mock/api/json/guardarLog
//				String urlString= "http://files.big-net.com.ar:8080/Mock/api/json/guardarLog";
//				URL url = new URL(urlString);
					
					
					//Daro 17.11: Logica anterior de decidir, feisima
//				URL url = new URL ("http://192.168.1.238:8080/TPA-Web-0.0.1-SNAPSHOT/rest/articulo/solicitudArticulo");
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
					
					
					//Daro 17.11: Que es esto? Aclara un poco la magia Mar
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
//				System.out.println("URL: " + urlString + "\nJSON: " + JSON);
					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					
					while (in.readLine() != null) {
						System.out.println("\nSolicitudArticuloClient Invoked Successfully..");
					}
				}
			}
				
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}